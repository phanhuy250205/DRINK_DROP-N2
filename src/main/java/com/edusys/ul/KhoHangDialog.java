package com.edusys.ul;

import com.edusys.dao.KhoHangDao;
import com.edusys.dao.NhapKhoDao;
import com.edusys.dao.XuatKhoDao;
import com.edusys.entity.KhoHang;
import com.edusys.entity.NhapKho;
import com.edusys.entity.XuatKho;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;
import com.edusys.utils.XDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class KhoHangDialog extends javax.swing.JDialog {

    KhoHangDao khDao = new KhoHangDao();
    NhapKhoDao nkDao = new NhapKhoDao();
    XuatKhoDao xkDao = new XuatKhoDao();
    int row = -1;
    SimpleDateFormat sdf = new SimpleDateFormat();

    public KhoHangDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("QUẢN LÝ KHO HÀNG");
        fillTable();
        fillTableNhapKho();
        fillTableXuatKho();
        updateStatus();
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhoHang.getModel();
        model.setRowCount(0);
        try {
            List<KhoHang> list = khDao.selectAll();
            for (KhoHang kh : list) {
                Object[] row = {
                    kh.getMaSanPham(),
                    kh.getTenSanPham(),
                    kh.getNCC(),
                    kh.getSoLuong(),
                    kh.getGiaNhap(),
                    kh.getGiaBan(),
                    kh.getSLTonKho(),
                    XDate.toString(kh.getNgayNhap(), "dd/MM/yyyy"),
                    XDate.toString(kh.getNgayXuat(), "dd/MM/yyyy"),
                    kh.getLoaiSanPham()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillTableNhapKho() {
        DefaultTableModel model = (DefaultTableModel) tblNhapKho.getModel();
        model.setRowCount(0);
        try {
            List<NhapKho> list = nkDao.selectAll();
            for (NhapKho nk : list) {
                Object[] row = {
                    nk.getMaPhieuNhap(),
                    nk.getNCC(),
                    nk.getMaNhanVien(),
                    nk.getSoLuong(),
                    nk.getNgayNhap(),
                    nk.getTienNhap(),
                    nk.getLoaiSanPham(),
                    nk.getMaSanPham()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu tbl NhapKho" + e);
        }
    }

    void setForm(NhapKho model) {
        txtMaPhieuNhap.setText(String.valueOf(model.getMaPhieuNhap()));
        txtNCC.setText(model.getNCC());
        txtMaNV.setText(model.getMaNhanVien());
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        txtNgayNhap.setText(XDate.toString(model.getNgayNhap(), "dd/MM/yyyy"));
        txtTienNhap.setText(String.valueOf(model.getTienNhap()));
        txtLoaiSanPham.setText(model.getLoaiSanPham());
        txtMaSanPham.setText(model.getMaSanPham());
    }

    NhapKho getForm() {
        NhapKho nk = new NhapKho();
        nk.setMaPhieuNhap(Integer.parseInt(txtMaPhieuNhap.getText()));
        nk.setNCC(txtNCC.getText());
        nk.setMaNhanVien(txtMaNV.getText());
        nk.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        nk.setNgayNhap(XDate.toDate(txtNgayNhap.getText(), "dd/MM/yyyy"));
        nk.setTienNhap(Float.parseFloat(txtTienNhap.getText()));
        nk.setLoaiSanPham(txtLoaiSanPham.getText());
        nk.setMaSanPham(txtMaSanPham.getText());
        return nk;
    }

    void edit() {
        try {
            // Lấy giá trị tại hàng và cột xác định và đảm bảo nó là String
            String maPN = tblNhapKho.getValueAt(this.row, 0).toString();
            int maPN1 = Integer.parseInt(maPN); // Chuyển đổi từ String sang Integer
            NhapKho nk = nkDao.selectById(maPN); // Đảm bảo phương thức selectById chấp nhận kiểu Integer
            if (nk != null) {
                setForm(nk);
                updateStatus();
                tabs.setSelectedIndex(2);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu: " + e.getMessage());
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        txtMaPhieuNhap.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
    }

    void clearForm() {
        NhapKho nk = new NhapKho();
        nk.setMaPhieuNhap(0);
        nk.setNCC("");
        nk.setMaNhanVien("");
        nk.setSoLuong(0);
        nk.setNgayNhap(new Date());
        nk.getTienNhap();
        nk.setLoaiSanPham("");
        nk.setMaSanPham("");
        this.setForm(nk);
        row = -1;
        updateStatus();
    }

    boolean validateForm(NhapKho model) {
        if (model.getMaPhieuNhap() <= 0) {
            MsgBox.alert(this, "Mã phiếu nhập không được trống");
            return false;
        }
        if (model.getMaNhanVien() == null || model.getMaNhanVien().trim().isEmpty()) {
            MsgBox.alert(this, "Mã nhân viên không được trống");
            return false;
        }
        if (model.getNCC() == null || model.getNCC().trim().isEmpty()) {
            MsgBox.alert(this, "Mã NCC không được trống");
            return false;
        }
        if (model.getSoLuong() <= 0) {
            MsgBox.alert(this, "Số lượng phải lớn hơn 0");
            return false;
        }
        if (model.getTienNhap() <= 0) {
            MsgBox.alert(this, "Tiền nhập phải lớn hơn 0");
            return false;
        }
        if (model.getLoaiSanPham() == null || model.getLoaiSanPham().trim().isEmpty()) {
            MsgBox.alert(this, "Loại sản phẩm không được trống");
            return false;
        }
        if (model.getMaSanPham() == null || model.getMaSanPham().trim().isEmpty()) {
            MsgBox.alert(this, "Mã sản phẩm không được trống");
            return false;
        }
        return true;
    }

    void insert() {
        NhapKho model = getForm();
        if (!validateForm(model)) {
            return;
        }
        try {
            nkDao.insert(model);
            this.fillTableNhapKho();
            this.clearForm();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại");
        }
    }

    void update() {
        NhapKho model = getForm();
        try {
            nkDao.update(model);
            this.fillTableNhapKho();
            updateStatus();
            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại: " + e.getMessage());
        }
    }

    void fillTableXuatKho() {
        DefaultTableModel model = (DefaultTableModel) tblXuatKho.getModel();
        model.setRowCount(0);
        try {
            List<XuatKho> list = xkDao.selectAll();
            for (XuatKho xk : list) {
                Object[] row = {
                    xk.getMaPhieuXuat(),
                    xk.getMaSanPham(),
                    xk.getTenSanPham(),
                    xk.getSoLuong(),
                    xk.getNgayXuat(),
                    xk.getMaNguoiXuat(),
                    xk.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu tbl XuatKho" + e);
        }
    }

    void setForm1(XuatKho model) {
        txtMaPhieuXuat.setText(model.getMaPhieuXuat());
        txtMaSP.setText(model.getMaSanPham());
        txtTenSP.setText(model.getTenSanPham());
        txtSoLuongXuat.setText(String.valueOf(model.getSoLuong()));
        txtNgayXuat.setText(XDate.toString(model.getNgayXuat(), "dd/MM/yyyy"));
        txtMaNguoiXuat.setText(model.getMaNguoiXuat());
        txtGhiChu.setText(model.getGhiChu());
    }

    XuatKho getForm1() {
        XuatKho xk = new XuatKho();
        xk.setMaPhieuXuat(txtMaPhieuXuat.getText());
        xk.setMaSanPham(txtMaSP.getText());
        xk.setTenSanPham(txtTenSP.getText());
        xk.setSoLuong(Integer.parseInt(txtSoLuongXuat.getText()));
        xk.setNgayXuat(XDate.toDate(txtNgayXuat.getText(), "dd/MM/yyyy"));
        xk.setMaNguoiXuat(txtMaNguoiXuat.getText());
        xk.setGhiChu(txtGhiChu.getText());
        return xk;
    }

    void edit1() {
        try {
            String maPX = (String) tblXuatKho.getValueAt(this.row, 0);
            XuatKho xk = xkDao.selectById(maPX);
            if (xk != null) {
                setForm1(xk);
                updateStatus1();
                tabs.setSelectedIndex(1);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Loi truy van du lieu 5");
        }
    }

    void updateStatus1() {
        boolean edit = this.row >= 0;
        txtMaPhieuXuat.setEditable(!edit);
        btnThemXuat.setEnabled(!edit);
        btnSuaXuat.setEnabled(edit);
    }

    void clearForm1() {
        XuatKho xk = new XuatKho();
        xk.setMaPhieuXuat("");
        xk.setMaSanPham("");
        xk.setTenSanPham("");
        xk.setSoLuong(0);
        xk.setNgayXuat(new Date());
        xk.setMaNguoiXuat("");
        xk.setGhiChu("");
        this.setForm1(xk);
        row = -1;
        updateStatus1();
    }
    boolean validateForm1(XuatKho model) {
        if (model.getMaPhieuXuat()== null || model.getMaPhieuXuat().trim().isEmpty()) {
            MsgBox.alert(this, "Mã phiếu xuất không được trống");
            return false;
        }
        if (model.getMaSanPham() == null || model.getMaSanPham().trim().isEmpty()) {
            MsgBox.alert(this, "Mã nhân viên không được trống");
            return false;
        }
        if (model.getTenSanPham() == null || model.getTenSanPham().trim().isEmpty()) {
            MsgBox.alert(this, "Mã NCC không được trống");
            return false;
        }
        if (model.getSoLuong() <= 0) {
            MsgBox.alert(this, "Số lượng phải lớn hơn 0");
            return false;
        }
        if (model.getMaNguoiXuat()== null || model.getMaNguoiXuat().trim().isEmpty()) {
            MsgBox.alert(this, "Loại sản phẩm không được trống");
            return false;
        }
        if (model.getGhiChu() == null || model.getGhiChu().trim().isEmpty()) {
            MsgBox.alert(this, "Mã sản phẩm không được trống");
            return false;
        }
        return true;
    }

    void insert1() {
        XuatKho model = getForm1();
        if (!validateForm1(model)) {
            return;
        }
        try {
            xkDao.insert(model);
            this.fillTableXuatKho();
            this.clearForm1();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại");
        }
    }

    void update1() {
        XuatKho model = getForm1();
        try {
            xkDao.update(model);
            this.fillTableXuatKho();
            updateStatus1();
            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhoHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtMaPhieuXuat = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSoLuongXuat = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNgayXuat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtMaNguoiXuat = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThemXuat = new javax.swing.JButton();
        btnSuaXuat = new javax.swing.JButton();
        btnLamMoiXuat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblXuatKho = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNCC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        txtTienNhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLoaiSanPham = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhapKho = new javax.swing.JTable();

        jLabel12.setText("jLabel12");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabs.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        tblKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "NCC", "Số Lượng", "Giá Nhập", "Giá Bán", "SL Tồn Kho", "Ngày Nhập", "Ngày Xuất", "Loại Sản Phẩm"
            }
        ));
        jScrollPane1.setViewportView(tblKhoHang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÍ KHO HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("QUẢN LÍ KHO HÀNG", jPanel1);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("QUẢN LÍ XUẤT KHO");

        jLabel13.setText("Mã Phiếu Xuất:");

        jLabel14.setText("Mã Sản Phẩm:");

        jLabel15.setText("Tên Sản Phẩm:");

        jLabel16.setText("Số Lượng Xuất:");

        jLabel17.setText("Ngày Xuất:");

        jLabel18.setText("Mã Người Xuất:");

        jLabel19.setText("Ghi Chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        btnThemXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/box.png"))); // NOI18N
        btnThemXuat.setText("Thêm");
        btnThemXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuatActionPerformed(evt);
            }
        });

        btnSuaXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/edit.png"))); // NOI18N
        btnSuaXuat.setText("Sửa");
        btnSuaXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaXuatActionPerformed(evt);
            }
        });

        btnLamMoiXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/circular.png"))); // NOI18N
        btnLamMoiXuat.setText("Làm Mới");
        btnLamMoiXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(txtMaPhieuXuat)
                    .addComponent(jLabel14)
                    .addComponent(txtMaSP)
                    .addComponent(jLabel15)
                    .addComponent(txtTenSP)
                    .addComponent(txtSoLuongXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17)
                    .addComponent(txtNgayXuat)
                    .addComponent(jLabel18)
                    .addComponent(txtMaNguoiXuat)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnThemXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnSuaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnLamMoiXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaPhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtNgayXuat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(txtMaNguoiXuat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuongXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemXuat)
                    .addComponent(btnSuaXuat)
                    .addComponent(btnLamMoiXuat))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblXuatKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Xuất", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Ngày Xuất", "Mã Người Xuất", "Ghi Chú"
            }
        ));
        tblXuatKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblXuatKhoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblXuatKho);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );

        tabs.addTab("XUẤT KHO", jPanel3);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QUẢN LÍ NHẬP KHO");

        txtMaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuNhapActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã Phiếu Nhập:");

        txtNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCCActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã NCC:");

        jLabel5.setText("Số Lượng:");

        jLabel6.setText("Mã Nhân Viên");

        jLabel7.setText("Ngày Nhập");

        jLabel8.setText("Tiền Nhập");

        jLabel9.setText("Loại Sản Phẩm");

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/circular.png"))); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel10.setText("Mã Sản Phẩm");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/box.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtLoaiSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(txtMaNV)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(txtTienNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(txtMaSanPham))
                .addGap(32, 32, 32))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtMaPhieuNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtNgayNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtMaSanPham))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThem))
                .addGap(149, 149, 149))
        );

        tblNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập", "Mã NCC", "Mã Nhân Viên", "Số Lượng", "Ngày Nhập", "Tổng Tiền Nhập", "Loại Sản Phẩm", "Mã Sản Phẩm"
            }
        ));
        tblNhapKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhapKhoMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblNhapKho);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
        );

        tabs.addTab("NHẬP KHO", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuNhapActionPerformed

    private void txtNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCCActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblNhapKhoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapKhoMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblNhapKho.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblNhapKhoMousePressed

    private void btnThemXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuatActionPerformed
        // TODO add your handling code here:
        insert1();
    }//GEN-LAST:event_btnThemXuatActionPerformed

    private void btnSuaXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaXuatActionPerformed
        // TODO add your handling code here:
        update1();
    }//GEN-LAST:event_btnSuaXuatActionPerformed

    private void btnLamMoiXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiXuatActionPerformed
        // TODO add your handling code here:
        clearForm1();
    }//GEN-LAST:event_btnLamMoiXuatActionPerformed

    private void tblXuatKhoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXuatKhoMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblXuatKho.rowAtPoint(evt.getPoint());
            edit1();
        }
    }//GEN-LAST:event_tblXuatKhoMousePressed

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhoHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhoHangDialog dialog = new KhoHangDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiXuat;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaXuat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKhoHang;
    private javax.swing.JTable tblNhapKho;
    private javax.swing.JTable tblXuatKho;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtLoaiSanPham;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNguoiXuat;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtMaPhieuXuat;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNgayXuat;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuongXuat;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTienNhap;
    // End of variables declaration//GEN-END:variables
}
