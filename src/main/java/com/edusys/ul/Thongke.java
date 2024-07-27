/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.edusys.ul;

import com.edusys.dao.NhanVienDao;
import com.edusys.dao.SanPhamDao;
import com.edusys.dao.ThongKedao;
import com.edusys.entity.NhanVien;
import com.edusys.entity.SanPham;
import com.edusys.utils.XImage;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hoang
 */
public class Thongke extends javax.swing.JDialog {

    SanPhamDao spdao = new SanPhamDao();
    NhanVienDao nvdao = new NhanVienDao();

    /**
     * Creates new form Thongke
     */
    public Thongke(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("DRINK-DROP Giao Hàng");
//        filltable();
        fillComboBoxSanPham();
        fillComboBoxnhanvien();

    }

    public void fillComboBoxnhanvien() {
        DefaultComboBoxModel<String> model2 = (DefaultComboBoxModel<String>) cbnhanvien.getModel();
        model2.removeAllElements();

        List<NhanVien> list = nvdao.selectAll();
        for (NhanVien nv : list) {
            model2.addElement(nv.getMaNV()); // Thêm mã nhân viên vào ComboBox
        }
    }

    public void fillComboBoxSanPham() {
        DefaultTableModel model23 = (DefaultTableModel) tblsanpham.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cbsp.getModel();
        model2.removeAllElements();
        List<SanPham> list = spdao.selectAll();
        for (SanPham nv : list) {
            model2.addElement(nv);
        }
    }

    private void filltablenhanvien() {
        DefaultTableModel model = (DefaultTableModel) tblnhanvien.getModel();
        model.setRowCount(0);

        // Lấy mã nhân viên từ ComboBox
        String maNhanVien = (String) cbnhanvien.getSelectedItem();

        // Gọi DAO để lấy thông tin thống kê
        NhanVienDao nvdao = new NhanVienDao();
        List<Object[]> thongKeList = nvdao.getThongKeByNhanVien(maNhanVien);
        //Định Dạng Số với Dấu Ngăn Cách Hàng Nghìn
        DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
        // Điền dữ liệu vào bảng
        for (Object[] row : thongKeList) {// Lấy thông tin từ row của thongKeList và tính toán số tiền lợi nhuận
            float thanhTien = ((Number) row[3]).floatValue(); // Tổng tiền bán ra (kiểu Float)
            float tongTienThuLai = ((Number) row[4]).floatValue(); // Tổng tiền thu lại (kiểu Float)
            float loiNhuan = thanhTien - tongTienThuLai;

            // Đảm bảo chuyển đổi kiểu dữ liệu phù hợp trước khi đổ vào model
            model.addRow(new Object[]{
                row[0], // Thời gian
                row[1], // Số hóa đơn
                row[2], // Tổng số hóa đơn
                decimalFormat.format(thanhTien),
                decimalFormat.format(tongTienThuLai),
                row[5], // Mã nhân viên
                row[6], // Nhân viên lập hóa đơn
                row[7], // Sản phẩm
                decimalFormat.format(((Number) row[8]).floatValue()), // Giá nhập với định dạng
                decimalFormat.format(((Number) row[9]).floatValue()), // Giá bán với định dạng
                decimalFormat.format(((Number) row[10]).intValue()),
                decimalFormat.format(loiNhuan)
            });
        }

        // Nếu danh sách thống kê không rỗng, đổ dữ liệu vào lblnhanvien
        if (!thongKeList.isEmpty()) {
            // Tính tổng lợi nhuận
            float totalLoiNhuan = 0;
            for (Object[] row : thongKeList) {
                float thanhTien = ((Number) row[3]).floatValue(); // Tổng tiền bán ra (kiểu Float)
                float tongTienThuLai = ((Number) row[4]).floatValue(); // Tổng tiền thu lại (kiểu Float)
                totalLoiNhuan += (thanhTien - tongTienThuLai);
            }

            // Đổ dữ liệu vào lblnhanvien
            lblnhanvien.setText(("Tổng " + decimalFormat.format(totalLoiNhuan)));
        } else {
            // Nếu không có dữ liệu, đặt giá trị mặc định cho lblnhanvien
            lblnhanvien.setText("Tổng : 0.00");
        }
    }

    void filltablesanpham() {
        DefaultTableModel model = (DefaultTableModel) tblsanpham.getModel();
        model.setRowCount(0);

        // Lấy đối tượng SanPham từ combobox
        SanPham selectedSanPham = (SanPham) cbsp.getSelectedItem();

        if (selectedSanPham != null) {
            // Lấy dữ liệu thống kê sản phẩm từ DAO dựa trên tên sản phẩm
            List<Object[]> list = spdao.getThongKeByThoiGian(selectedSanPham.getTenSanPham());

            if (list != null && !list.isEmpty()) {
                // Thêm dữ liệu vào bảng
                for (Object[] row : list) {
                    model.addRow(row);
                }

                Object[] firstRow = list.get(0);
                int tongSanPham = (int) firstRow[3];
                int soLuongBan = (int) firstRow[4];
                int soLuongCon = (int) firstRow[5];

                // Hiển thị các giá trị lên các JTextField tương ứng
                lbltongsoluong.setText(String.valueOf(tongSanPham));
                lblsoluongban.setText(String.valueOf(soLuongBan));
                lblsoluongcon.setText(String.valueOf(soLuongCon));
            } else {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu thống kê cho sản phẩm được chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm nào được chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void doanhthu(Date dateFrom, Date dateTo) {
        DefaultTableModel model = (DefaultTableModel) tbldoanhthut13.getModel();
        model.setRowCount(0); // Clear the current table rows

        // Gọi phương thức từ DAO để lấy danh sách dữ liệu doanh thu từ cơ sở dữ liệu
        ThongKedao thongKeDAO = new ThongKedao(); // Khởi tạo đối tượng DAO
        List<Object[]> doanhThuList = thongKeDAO.getDoanhthu1(dateFrom, dateTo);
        //Định Dạng Số với dấu Ngăn cách Hàng Ngìn
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

        // Thêm các dòng dữ liệu vào bảng
        for (Object[] row : doanhThuList) {
            model.addRow(row);
        }

        // Tính tổng lợi nhuận
        float tongLoi = 0;
        for (Object[] row : doanhThuList) {
            tongLoi += (float) row[2];
        }

        lbldoanhthutong13.setText(decimalFormat.format(tongLoi) + "VND");
        if (doanhThuList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu thống kê cho ngày đã chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        thongke_dateform13 = new com.toedter.calendar.JDateChooser();
        jLabel43 = new javax.swing.JLabel();
        btnthongkedoanhthu13 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbldoanhthut13 = new javax.swing.JTable();
        lbldoanhthutong13 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        thongke_dateto13 = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        cbnhanvien = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();
        lbldoanhthu = new javax.swing.JLabel();
        lblnhanvien = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbltongsoluong = new javax.swing.JLabel();
        lblsoluongcon = new javax.swing.JLabel();
        lblsoluongban = new javax.swing.JLabel();
        cbsp = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        thongke_dateform13.setBackground(new java.awt.Color(255, 255, 255));
        thongke_dateform13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thongke_dateform13.setForeground(new java.awt.Color(0, 204, 204));
        thongke_dateform13.setDateFormatString("dd/MM/yyyy ");

        jLabel43.setText("CHỌN NGÀY ĐỂ THỐNG KÊ : ");

        btnthongkedoanhthu13.setText("THỐNG KÊ");
        btnthongkedoanhthu13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkedoanhthu13ActionPerformed(evt);
            }
        });

        tbldoanhthut13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Thời Gian Lập", "Tổng Số SP", "Tổng Tiền", "Mã Nhân Viên", "Nhân Viên Lập Hóa Đơn"
            }
        ));
        tbldoanhthut13.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbldoanhthut13AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane16.setViewportView(tbldoanhthut13);

        lbldoanhthutong13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setText("TỔNG DOANH THU :");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setText("Đến :");

        thongke_dateto13.setBackground(new java.awt.Color(255, 255, 255));
        thongke_dateto13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thongke_dateto13.setForeground(new java.awt.Color(0, 255, 255));
        thongke_dateto13.setDateFormatString("dd/MM/yyyy ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thongke_dateform13, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(thongke_dateto13, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnthongkedoanhthu13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbldoanhthutong13, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(482, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(thongke_dateform13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(thongke_dateto13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnthongkedoanhthu13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbldoanhthutong13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh thu", jPanel14);

        cbnhanvien.setBackground(new java.awt.Color(255, 255, 255));
        cbnhanvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbnhanvien.setForeground(new java.awt.Color(0, 0, 0));
        cbnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnhanvienActionPerformed(evt);
            }
        });

        tblnhanvien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ThoiGian", "SoHoaDon", "TongHD", "TongTienBanRa", "TongTienThuLai", "MaNhanVien", "NhanVienLapHoaDon", "SanPham", "GiaNhap", "GiaBan", "SoLuong"
            }
        ));
        jScrollPane2.setViewportView(tblnhanvien);

        lbldoanhthu.setBackground(new java.awt.Color(255, 255, 255));
        lbldoanhthu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbldoanhthu.setForeground(new java.awt.Color(51, 102, 255));
        lbldoanhthu.setText("TỔNG TIỀN LỜI : ");

        lblnhanvien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblnhanvien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblnhanvien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbnhanvien, 0, 1030, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbldoanhthu)
                .addGap(11, 11, 11)
                .addComponent(lblnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cbnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbldoanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel15);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("SỐ LƯỢNG  CÒN :");

        lbltongsoluong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblsoluongcon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblsoluongban.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        cbsp.setBackground(new java.awt.Color(255, 255, 255));
        cbsp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbsp.setForeground(new java.awt.Color(0, 0, 0));
        cbsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbspActionPerformed(evt);
            }
        });

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MaSanPham", "TenSanPham", "Loai San Pham", "So Luong Tong", "So Luong Ban", "So Luong Con"
            }
        ));
        jScrollPane1.setViewportView(tblsanpham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("TỔNG SỐ LƯỢNG :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText(" SỐ LƯỢNG BÁN :");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbsp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltongsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblsoluongcon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsoluongban, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cbsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsoluongcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblsoluongban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltongsoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthongkedoanhthu13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkedoanhthu13ActionPerformed
        java.util.Date dateFrom = thongke_dateform13.getDate();
        java.util.Date dateTo = thongke_dateto13.getDate(); // Assuming thongke_dateto is your end date chooser

        if (dateFrom != null && dateTo != null) {
            java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
            java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
            doanhthu(sqlDateFrom, sqlDateTo); // Pass both dates to the doanhthu method
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn cả hai ngày bắt đầu và kết thúc.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnthongkedoanhthu13ActionPerformed

    private void cbnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnhanvienActionPerformed
        // TODO add your handling code here:
        filltablenhanvien();
    }//GEN-LAST:event_cbnhanvienActionPerformed

    private void cbspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbspActionPerformed
        // TODO add your handling code here:
        filltablesanpham();
    }//GEN-LAST:event_cbspActionPerformed

    private void tbldoanhthut13AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbldoanhthut13AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbldoanhthut13AncestorAdded

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Thongke dialog = new Thongke(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnthongkedoanhthu13;
    private javax.swing.JComboBox<String> cbnhanvien;
    private javax.swing.JComboBox<String> cbsp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbldoanhthu;
    private javax.swing.JLabel lbldoanhthutong13;
    private javax.swing.JLabel lblnhanvien;
    private javax.swing.JLabel lblsoluongban;
    private javax.swing.JLabel lblsoluongcon;
    private javax.swing.JLabel lbltongsoluong;
    private javax.swing.JTable tbldoanhthut13;
    private javax.swing.JTable tblnhanvien;
    private javax.swing.JTable tblsanpham;
    private com.toedter.calendar.JDateChooser thongke_dateform13;
    private com.toedter.calendar.JDateChooser thongke_dateto13;
    // End of variables declaration//GEN-END:variables

//    private void doanhthu(Date sqlDateFrom, Date sqlDateTo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
