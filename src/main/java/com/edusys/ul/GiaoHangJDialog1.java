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
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class GiaoHangJDialog1 extends javax.swing.JFrame {
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
     void init(){
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
                    gh.getMagiaohang(),
                    gh.getMasp(),
                    gh.getTensp(),
                    gh.getSoluong(),
                    gh.getDongia(),
                    gh.getTongtien(),
                   
//                    gh.isTinhTrangGiaoHang()? "Thành Công" : "Thất bại"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu ");
        }
    }
      // Phương thức để lấy dữ liệu từ các trường nhập liệu và tạo đối tượng Giaohang
Giaohang getFrom() {
    Giaohang gh = new Giaohang();
    // Chuyển đổi kiểu dữ liệu và lấy dữ liệu từ các trường nhập liệu
    gh.setMagiaohang(Integer.parseInt(txtmabanhang.getText()));
    gh.setMasp(txtmasanpham.getText());
    gh.setTensp(txttensanpham.getText());
    gh.setSoluong(Integer.parseInt(txtsoluong.getText()));
     gh.setDongia(Float.parseFloat(txtdongia.getText()));
    gh.setTongtien(Float.parseFloat(txttongtien.getText()));
    return gh;
}
// Phương thức để làm sạch các trường nhập liệu
void clearForm() {
    Giaohang gh = new Giaohang();
    this.setFrom(gh);
    this.row = -1;
    // Có thể cập nhật trạng thái tại đây nếu cần
    // this.updateStatus();
}

// Phương thức để thiết lập dữ liệu cho các trường nhập liệu từ đối tượng Giaohang
void setFrom(Giaohang model) {
    // Cập nhật các trường nhập liệu với dữ liệu từ đối tượng Giaohang
    txtmabanhang.setText(String.valueOf(model.getMagiaohang()));
    txtmasanpham.setText(String.valueOf(model.getMasp()));
    txttensanpham.setText(model.getTensp());
    txtsoluong.setText(String.valueOf(model.getSoluong()));
     txtdongia.setText(String.format("%.2f", model.getDongia()));
    txttongtien.setText(String.format("%.2f", model.getTongtien()));
    // Cập nhật các trường khác nếu có
    // txtdiachi.setText(model.getDiaChiGiao());
    // txtnguoidao.setText(model.getTenNguoiGiao());
    // txtsdtgiao.setText(model.getSDTNguoiGiao());
    // txtnguoinhan.setText(model.getTenNguoiNhan());
    // txtsdtnguoinhan.setText(model.getSDTNguoiNhan());
    // txttinhtrang.setText(model.getTinhTrang());
}


      void insert() {
        Giaohang model = getFrom();
//        String confirm = new String(txtmatkhau2.getPassword());
        
            try {
                dao.insert(model);
                this.filltable();
                this.clearForm();
                MsgBox.alert(this, "Thêm Thành Công !");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm Thất Bại");

            }
        
    }
      void update() {
        Giaohang model = getFrom();
//        String Confirm = new String(txtmatkhau2.getPassword());
         
            try {
                dao.update(model);
                this.filltable();
                MsgBox.alert(this, "Cập Nhật Thành Công !");
            } catch (Exception e) {
                MsgBox.alert(this, " Cập Nhật Thất Bại");

            }
        
    }
      
      void edit() {
    try {
        // Lấy mã giao hàng từ bảng
        int maGiaoHang = (int) tblbanhang.getValueAt(this.row, 0);
        // Lấy dữ liệu từ DAO dựa trên mã giao hàng
        Giaohang model = dao.selectById(maGiaoHang);
        if (model != null) {
            // Cập nhật các trường nhập liệu với dữ liệu từ đối tượng Giaohang
            setFrom(model);
            // Cập nhật trạng thái
            updateStatus();
            // Chuyển đến tab hoặc thực hiện các thao tác khác nếu cần
            // tabs.setSelectedIndex(0);
        }
    } catch (Exception e) {
        // Hiển thị thông báo lỗi nếu có lỗi xảy ra
        MsgBox.alert(this, "Lỗi truy vấn dữ liệu: " + e.getMessage());
    }
}
      void updateStatus() {
    boolean edit = (this.row >= 0);
    boolean first = (this.row == 0);
    boolean last = (this.row == tblbanhang.getRowCount() - 1);

    // Trạng thái form
    txtmabanhang.setEditable(!edit);
    btnthem.setEnabled(!edit);
    btnsua.setEnabled(edit);
    btnxoa.setEnabled(edit);

    // Trạng thái điều hướng
//    btnfirst.setEnabled(edit && !first);
//    btnprev.setEnabled(edit && !first);
//    btnnext.setEnabled(edit && !last);
//    btnlast.setEnabled(edit && !last);
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
        txtdongia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttensanpham = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        txtmasanpham = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtmabanhang = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbanhang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

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

        jLabel3.setText("Mã Sản Phẩm :");

        jLabel4.setText("Tên Sản Phẩm");

        jLabel5.setText("Số Lượng");

        jLabel6.setText("Đơn Giá ");

        jLabel7.setText("Tổng Tiền");

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

        btnlammoi.setText("Làm Mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã Bán Hàng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdongia)
                    .addComponent(txttongtien)
                    .addComponent(txtsoluong)
                    .addComponent(txttensanpham)
                    .addComponent(txtmasanpham)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnthem)
                                .addGap(35, 35, 35)
                                .addComponent(btnsua)
                                .addGap(30, 30, 30)
                                .addComponent(btnxoa)
                                .addGap(27, 27, 27)
                                .addComponent(btnlammoi))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmabanhang)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmabanhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtmasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txttensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(btnlammoi))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        tblbanhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Bán hàng ", "Mã sản phẩm", "Tên Sản phẩm", "Số Lượng", "Đơn giá", "Tổng Tiền "
            }
        ));
        tblbanhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbanhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbanhang);

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton1.setText("Thanh Toán");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        clearForm();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void tblbanhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbanhangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblbanhang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblbanhangMouseClicked

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
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblbanhang;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtmabanhang;
    private javax.swing.JTextField txtmasanpham;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttensanpham;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
