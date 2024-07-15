/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.edusys.ul;

import com.edusys.dao.NhanVienDao;
import com.edusys.dao.SanPhamDao;
import com.edusys.dao.ThongKedao;
import com.edusys.entity.NhanVien;
import com.edusys.entity.SanPham;
import com.edusys.utils.XImage;
import java.awt.HeadlessException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ThongKeJDialog extends javax.swing.JFrame {

//    public ThongKeJDialog(JComboBox<String> cbnhanvien, JComboBox<String> cbsp, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JScrollPane jScrollPane1, JTabbedPane ko, JTable tblsanpham) throws HeadlessException {
//        this.cbnhanvien = cbnhanvien;
//        this.cbsp = cbsp;
//        this.jPanel1 = jPanel1;
//        this.jPanel2 = jPanel2;
//        this.jPanel3 = jPanel3;
//        this.jScrollPane1 = jScrollPane1;
//        this.ko = ko;
//        this.tblsanpham = tblsanpham;
//    }
    SanPhamDao spdao = new SanPhamDao();
    NhanVienDao nvdao = new NhanVienDao();

    /**
     * Creates new form ThongKeJDialog
     */
    public ThongKeJDialog() {
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
        lblnhanvien.setText(("Tổng "+decimalFormat.format(totalLoiNhuan)));
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
    DefaultTableModel model = (DefaultTableModel) tbldoanhthut.getModel();
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
    
    lbldoanhthutong.setText(decimalFormat.format(tongLoi)+"VND");
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

        ko = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        thongke_dateform = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        btnthongkedoanhthu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbldoanhthut = new javax.swing.JTable();
        lbldoanhthutong = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        thongke_dateto = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        cbnhanvien = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();
        lbldoanhthu = new javax.swing.JLabel();
        lblnhanvien = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbsp = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbltongsoluong = new javax.swing.JLabel();
        lblsoluongcon = new javax.swing.JLabel();
        lblsoluongban = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        thongke_dateform.setBackground(new java.awt.Color(255, 255, 255));
        thongke_dateform.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thongke_dateform.setForeground(new java.awt.Color(0, 204, 204));
        thongke_dateform.setDateFormatString("dd/MM/yyyy ");

        jLabel4.setText("CHỌN NGÀY ĐỂ THỐNG KÊ : ");

        btnthongkedoanhthu.setText("THỐNG KÊ");
        btnthongkedoanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkedoanhthuActionPerformed(evt);
            }
        });

        tbldoanhthut.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbldoanhthut);

        lbldoanhthutong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("TỔNG DOANH THU :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Đến :");

        thongke_dateto.setBackground(new java.awt.Color(255, 255, 255));
        thongke_dateto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thongke_dateto.setForeground(new java.awt.Color(0, 255, 255));
        thongke_dateto.setDateFormatString("dd/MM/yyyy ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thongke_dateform, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(thongke_dateto, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnthongkedoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbldoanhthutong, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(304, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(thongke_dateform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(thongke_dateto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnthongkedoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbldoanhthutong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap())
        );

        ko.addTab("DOANH THU ", jPanel1);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbnhanvien, 0, 852, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbldoanhthu)
                .addGap(11, 11, 11)
                .addComponent(lblnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cbnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbldoanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addContainerGap())
        );

        ko.addTab("NHÂN VIÊN ", jPanel2);

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("SỐ LƯỢNG  CÒN :");

        lbltongsoluong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblsoluongcon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblsoluongban.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbsp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
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
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cbsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsoluongcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblsoluongban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltongsoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        ko.addTab("SẢN PHẨM", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ko))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ko)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbspActionPerformed
        // TODO add your handling code here:
        filltablesanpham();

    }//GEN-LAST:event_cbspActionPerformed

    private void cbnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnhanvienActionPerformed
        // TODO add your handling code here:
        filltablenhanvien();
    }//GEN-LAST:event_cbnhanvienActionPerformed

    private void btnthongkedoanhthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkedoanhthuActionPerformed
        java.util.Date dateFrom = thongke_dateform.getDate();
        java.util.Date dateTo = thongke_dateto.getDate(); // Assuming thongke_dateto is your end date chooser

        if (dateFrom != null && dateTo != null) {
            java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
            java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
            doanhthu(sqlDateFrom, sqlDateTo); // Pass both dates to the doanhthu method
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn cả hai ngày bắt đầu và kết thúc.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnthongkedoanhthuActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeJDialog().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnthongkedoanhthu;
    private javax.swing.JComboBox<String> cbnhanvien;
    private javax.swing.JComboBox<String> cbsp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane ko;
    private javax.swing.JLabel lbldoanhthu;
    private javax.swing.JLabel lbldoanhthutong;
    private javax.swing.JLabel lblnhanvien;
    private javax.swing.JLabel lblsoluongban;
    private javax.swing.JLabel lblsoluongcon;
    private javax.swing.JLabel lbltongsoluong;
    private javax.swing.JTable tbldoanhthut;
    private javax.swing.JTable tblnhanvien;
    private javax.swing.JTable tblsanpham;
    private com.toedter.calendar.JDateChooser thongke_dateform;
    private com.toedter.calendar.JDateChooser thongke_dateto;
    // End of variables declaration//GEN-END:variables
}
