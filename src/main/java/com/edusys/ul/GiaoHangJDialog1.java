/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.edusys.ul;

//import Edusys.dao.Giaohangdao;
//import Edusys.ennity.Giaohang;
//import Edusys.untils.MsgBox;
//import Edusys.untils.Ximage;
import com.edusys.dao.Giaohangdao;
import com.edusys.entity.Giaohang;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class GiaoHangJDialog1 extends javax.swing.JFrame {
private Map<String, Integer> productQuantities = new HashMap<>();
    DefaultTableModel tableModel = new DefaultTableModel();
    Giaohangdao dao = new Giaohangdao();
    int row = 0;

    /**
     * Creates new form GiaoHangJDialog1
     */
    public GiaoHangJDialog1() {
        initComponents();
        init();
        setLocationRelativeTo(null);
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("DRINK-DROP Giao Hàng");
        filltable();
    }

    void filltable() {
        DefaultTableModel model = (DefaultTableModel) tblbanhang.getModel();
        model.setRowCount(0);
        try {
            List<Giaohang> list = dao.selectAll();
            for (Giaohang gh : list) {
                Object[] row = {
                    gh.getTenKhachHang(),
                    gh.getTenSanPham(),
                    gh.getMaNhanVien(),
                    gh.getMaSanPham(),
                    gh.getSoluong(),
                    gh.getDonGia(),
                    gh.getTongTien(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu ");
        }
    }
    // Phương thức để lấy dữ liệu từ các trường nhập liệu và tạo đối tượng Giaohang

      Giaohang getFrom() {
        Giaohang gh = new Giaohang();
        gh.setTenKhachHang(txttenkhach.getText());
        gh.setTenSanPham(txttensanpham.getText());
        gh.setMaNhanVien(txtmanhanvien.getText());
        gh.setMaSanPham(txtmasanpham.getText());

        try {
            gh.setSoluong(Integer.parseInt(lblsoluong.getText().trim()));
        } catch (NumberFormatException e) {
            gh.setSoluong(0); // Hoặc xử lý theo cách khác
        }

        try {
            gh.setDonGia(Float.parseFloat(txtdongia.getText().trim()));
        } catch (NumberFormatException e) {
            gh.setDonGia(0); // Hoặc xử lý theo cách khác
        }

        try {
            gh.setTongTien(Float.parseFloat(txttongtien.getText().trim()));
        } catch (NumberFormatException e) {
            gh.setTongTien(0); // Hoặc xử lý theo cách khác
        }

        return gh;
    }
// Phương thức để làm sạch các trường nhập liệu

    void clearForm() {
        // Xóa sạch dữ liệu trong các trường nhập liệuư
        txttenkhach.setText(" ");
        txttensanpham.setText(" ");
        txtmanhanvien.setText("");
        txtmasanpham.setText("");
        lblsoluong.setText(" ");// Thêm các trường khác vào đây
        txtdongia.setText("");
        txttongtien.setText("");
        this.row = -1; // Đặt lại chỉ số hàng được chọn
        this.updateStatus(); // Cập nhật lại trạng thái
    }

// Phương thức để thiết lập dữ liệu cho các trường nhập liệu từ đối tượng Giaohang
    void setFrom(Giaohang model) {
        // Cập nhật các trường nhập liệu với dữ liệu từ đối tượng Giaohang
        txttenkhach.setText(String.valueOf(model.getTenKhachHang()));
        txttensanpham.setText(String.valueOf(model.getTenSanPham()));
        txtmanhanvien.setText(model.getMaSanPham());
        txtmasanpham.setText(model.getMaSanPham());
        lblsoluong.setText(String.valueOf(model.getSoluong()));
        txtdongia.setText(String.format("%.2f", model.getDonGia()));
        txttongtien.setText(String.format("%.2f", model.getTongTien()));
    }

    void insert() {
        Giaohang model = getFrom();
        try {
            dao.insert(model);
            this.filltable();
            this.clearForm();
            MsgBox.alert(this, "Thêm Thành Công !");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm Thất Bại");
        }
    }

    void thanhtoan() {

        try {
            int selectedRow = tblbanhang.getSelectedRow();
            System.out.println("Selected row: " + selectedRow);

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để thanh toán.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel tableModel = (DefaultTableModel) tblbanhang.getModel();
            System.out.println("Row count before: " + tableModel.getRowCount());
            tableModel.removeRow(selectedRow);
            System.out.println("Row count after: " + tableModel.getRowCount());

            JOptionPane.showMessageDialog(null, "Bạn đã thanh toán thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thanh toán thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    void update() {
        Giaohang model = getFrom();
        try {
            dao.update(model);
            this.filltable();
            MsgBox.alert(this, "Cập Nhật Thành Công !");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập Nhật Thất Bại");
        }
    }

void edit() {
    this.row = tblbanhang.getSelectedRow(); // Lấy chỉ số của hàng được chọn
    if (this.row >= 0) {
        // Lấy giá trị từ cột tên khách hàng
        String tenKhachHang = tblbanhang.getValueAt(this.row, 0).toString();

        // Truy vấn dữ liệu giao hàng từ cơ sở dữ liệu
        Giaohang model = dao.selectByCustomerName(tenKhachHang);
        if (model != null) {
            setFrom(model); // Đặt dữ liệu giao hàng vào form
            updateStatus(); // Cập nhật trạng thái các nút và bảng
        } else {
            MsgBox.alert(this, "Không tìm thấy thông tin giao hàng cho khách hàng: " + tenKhachHang);
        }
    } else {
        MsgBox.alert(this, "Vui lòng chọn một hàng để chỉnh sửa.");
    }
}


    void updateStatus() {
        boolean edit = (this.row >= 0); // Kiểm tra xem có hàng nào đang được chọn hay không

        // Trạng thái form
        txttenkhach.setEditable(!edit);
        btnthem.setEnabled(!edit);
        btnsua.setEnabled(edit);         // Nếu có hàng được chọn, hiển thị nút Sửa
        btnxoa.setEnabled(!edit);
//    btnlammoi.setEnabled(!edit);// Nếu có hàng được chọn, hiển thị nút Xóa
        tblbanhang.setEnabled(!edit);    // Nếu có hàng được chọn, vô hiệu hóa bảng
        btnthanhtoan.setEnabled(edit);   // Nếu có hàng được chọn, hiển thị nút Thanh toán
    }

    void lamMoi() {
        this.row = -1; // Đặt lại chỉ số hàng được chọn
        clearForm();   // Xóa các trường nhập liệu
        updateStatus(); // Cập nhật lại trạng thái
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtmasanpham = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtmanhanvien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttensanpham = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txttenkhach = new javax.swing.JTextField();
        btnthanhtoan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblsoluong = new javax.swing.JTextField();
        panebang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbanhang = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jRadioButton1.setBackground(new java.awt.Color(153, 255, 255));
        jRadioButton1.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setText("Thành Công");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 255));

        jLabel3.setText("Tên Sản Phẩm");

        jLabel4.setText("Mã Nhân Viên");

        jLabel5.setText("Đơn Giá");

        jLabel6.setText("Tổng Tiền");

        jLabel1.setText("Tên Khách");

        btnthanhtoan.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnthanhtoan.setText("Thanh Toán");
        btnthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoanActionPerformed(evt);
            }
        });

        jLabel7.setText("Mã Sản Phẩm ");

        jLabel8.setText("Số Lượng : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttongtien)
                    .addComponent(txtmanhanvien)
                    .addComponent(txttensanpham)
                    .addComponent(btnthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(txtmasanpham)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenkhach)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdongia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addContainerGap())
                    .addComponent(lblsoluong)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttenkhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtmanhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtmasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        tblbanhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Khách", "Tên Sản Phẩm", "Mã Nhân Viên", "Mã Sản Phẩm ", "Số Lượng", "Đơn Giá", "Tổng Tiền"
            }
        ));
        tblbanhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbanhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbanhang);

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnlammoi.setText("Làm Mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panebangLayout = new javax.swing.GroupLayout(panebang);
        panebang.setLayout(panebangLayout);
        panebangLayout.setHorizontalGroup(
            panebangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
            .addGroup(panebangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panebangLayout.setVerticalGroup(
            panebangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panebangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panebangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panebang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panebang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void tblbanhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbanhangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int row = tblbanhang.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                // Lấy mã sản phẩm từ hàng đã chọn
                String masanpham = tblbanhang.getValueAt(row, 3).toString();

                // Tăng số lượng sản phẩm
                int currentQuantity = productQuantities.getOrDefault(masanpham, 0);
                productQuantities.put(masanpham, currentQuantity + 1);

                // Lấy thông tin sản phẩm từ cơ sở dữ liệu hoặc đối tượng Giaohang
                Giaohang model = dao.selectByMaSanPham(masanpham);
                if (model != null) {
                    // Cập nhật số lượng mới
                    model.setSoluong(currentQuantity + 1);

                    // Cập nhật đơn giá và tổng tiền
                    double donGia = model.getDonGia();
                    double tongTien = donGia * (currentQuantity + 1);

                    // Cập nhật giao diện người dùng
                    setFrom(model); // Cập nhật các trường nhập liệu
                    txtdongia.setText(String.format("%.2f", donGia)); // Cập nhật đơn giá
                    txttongtien.setText(String.format("%.2f", tongTien)); // Cập nhật tổng tiền
                }
            }
    }
    }//GEN-LAST:event_tblbanhangMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoanActionPerformed
        // TODO add your handling code here:
        thanhtoan();

    }//GEN-LAST:event_btnthanhtoanActionPerformed

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
            java.util.logging.Logger.getLogger(GiaoHangJDialog1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoHangJDialog1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoHangJDialog1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoHangJDialog1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoHangJDialog1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthanhtoan;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblsoluong;
    private javax.swing.JPanel panebang;
    private javax.swing.JTable tblbanhang;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtmanhanvien;
    private javax.swing.JTextField txtmasanpham;
    private javax.swing.JTextField txttenkhach;
    private javax.swing.JTextField txttensanpham;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
