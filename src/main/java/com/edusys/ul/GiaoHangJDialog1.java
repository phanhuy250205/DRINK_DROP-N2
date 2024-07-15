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
        DefaultTableModel model = (DefaultTableModel) tblbanggiaohang.getModel();
        model.setRowCount(0);
        try {
            List<Giaohang> list = dao.selectAll();
            for (Giaohang gh : list) {
                Object[] row = {
                    gh.getMaGiaoHang(),
                    gh.getLoaiNuoc(),
                    gh.getMaSP(),
                    gh.getDiaChiGiao(),
                    gh.getTenNguoiGiao(),
                    gh.getSDTNguoiGiao(),
                    gh.getSDTNguoiNhan(),
                    gh.getTenNguoiNhan(),
//                    gh.isTinhTrangGiaoHang()? "Thành Công" : "Thất bại"
		    gh.getTinhTrang()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu ");
        }
    }
      Giaohang getfrom() {
        Giaohang gh = new Giaohang();
        gh.setMaGiaoHang(txtma.getText());
        gh.setLoaiNuoc(txtloainuoc1.getText());
        gh.setMaSP(idloainuoc.getText());
        gh.setDiaChiGiao(txtdiachi.getText());
        gh.setTenNguoiGiao(txtnguoidao.getText());
        gh.setSDTNguoiGiao(txtsdtgiao.getText());
        gh.setTenNguoiNhan(txtnguoinhan.getText());
        gh.setSDTNguoiNhan(txtsdtnguoinhan.getText());
        gh.setTinhTrang(txttinhtrang.getText());
        return gh;
    }
      void clearform() {
        Giaohang nv = new Giaohang();
        this.setfrom(nv);
        this.row = -1;
//        this.updateStatus();
    }
      void setfrom(Giaohang model) {
    txtma.setText(model.getMaGiaoHang());
    txtloainuoc1.setText(model.getLoaiNuoc());
    idloainuoc.setText(String.valueOf(model.getMaSP())); 
    txtdiachi.setText(model.getDiaChiGiao());
    txtnguoidao.setText(model.getTenNguoiGiao());
    txtsdtgiao.setText(model.getSDTNguoiGiao());
    txtnguoinhan.setText(model.getTenNguoiNhan());
    txtsdtnguoinhan.setText(model.getSDTNguoiNhan());
    txttinhtrang.setText(model.getTinhTrang());
}

      void insert() {
        Giaohang model = getfrom();
//        String confirm = new String(txtmatkhau2.getPassword());
        
            try {
                dao.insert(model);
                this.filltable();
                this.clearform();
                MsgBox.alert(this, "Thêm Thành Công !");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm Thất Bại");

            }
        
    }
      void update() {
        Giaohang model = getfrom();
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
            String maNv = (String) tblbanggiaohang.getValueAt(this.row, 0);
            Giaohang model = dao.selectById(maNv);
            if (model != null) {
                setfrom(model);
                updateStatus();
//                tabs.setSelectedIndex(0);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Du lieu");
        }
    }
      void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean fist = (this.row == 0);
        boolean last = (this.row == tblbanggiaohang.getRowCount() - 1);
//        Trạng thái form
        txtma.setEditable(!edit);
        btnthemm.setEnabled(!edit);
        btnsua.setEnabled(edit);
        btnxoa.setEnabled(edit);
//      Trạng thái điều hướng
//        btnfirst.setEnabled(edit && !fist);
//        btnpev.setEnabled(edit && !fist);
//        btnnext.setEnabled(edit && !last);
//        btnlast.setEnabled(edit && !last);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbanggiaohang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        idloainuoc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnguoidao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtsdtgiao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtsdtnguoinhan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnthemm = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnlammoi1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtnguoinhan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtloainuoc1 = new javax.swing.JTextField();
        txttinhtrang = new javax.swing.JTextField();

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

        tblbanggiaohang.setAutoCreateRowSorter(true);
        tblbanggiaohang.setBackground(new java.awt.Color(255, 255, 255));
        tblbanggiaohang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        tblbanggiaohang.setForeground(new java.awt.Color(0, 51, 255));
        tblbanggiaohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã GH", "Loại Nước", "ID Nước", "ĐC Giao", "TÊN NG", "SĐT NG", "TÊN NN", "SĐT NG", "TÌNH TRẠNG"
            }
        ));
        tblbanggiaohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbanggiaohangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbanggiaohang);
        if (tblbanggiaohang.getColumnModel().getColumnCount() > 0) {
            tblbanggiaohang.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblbanggiaohang.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblbanggiaohang.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblbanggiaohang.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setForeground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("                 THÔNG TIN GIAO HÀNG");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã Giao Hàng :");

        txtma.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Loại Nước :");

        idloainuoc.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Địa Chỉ Giao :");

        txtdiachi.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên Người Giao :");

        txtnguoidao.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("SĐT Người Giao :");

        txtsdtgiao.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tên Người Nhận :");

        txtsdtnguoinhan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Ghi Chú Tình Trang : ");

        btnthemm.setBackground(new java.awt.Color(255, 255, 255));
        btnthemm.setForeground(new java.awt.Color(0, 0, 0));
        btnthemm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/add-user.png"))); // NOI18N
        btnthemm.setText("Thêm");
        btnthemm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemmActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(255, 255, 255));
        btnsua.setForeground(new java.awt.Color(0, 0, 0));
        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/edit.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setBackground(new java.awt.Color(255, 255, 255));
        btnxoa.setForeground(new java.awt.Color(0, 0, 0));
        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/delete.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnlammoi1.setBackground(new java.awt.Color(255, 255, 255));
        btnlammoi1.setForeground(new java.awt.Color(0, 0, 0));
        btnlammoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/diskette.png"))); // NOI18N
        btnlammoi1.setText("Làm Mới");
        btnlammoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoi1ActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("SĐT Người Nhận :");

        txtnguoinhan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ID Nước:");

        txtloainuoc1.setBackground(new java.awt.Color(255, 255, 255));

        txttinhtrang.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtma, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(idloainuoc)
            .addComponent(txtdiachi)
            .addComponent(txtnguoidao)
            .addComponent(txtsdtgiao)
            .addComponent(txtsdtnguoinhan)
            .addComponent(txtnguoinhan)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(0, 361, Short.MAX_VALUE))
            .addComponent(txtloainuoc1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnthemm)
                        .addGap(18, 18, 18)
                        .addComponent(btnsua)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnlammoi1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttinhtrang))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtloainuoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnguoidao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsdtgiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnguoinhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsdtnguoinhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txttinhtrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemm)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(btnlammoi1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnthemmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemmActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnthemmActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void tblbanggiaohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbanggiaohangMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 1) {
            this.row = tblbanggiaohang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblbanggiaohangMouseClicked

    private void btnlammoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoi1ActionPerformed
        // TODO add your handling code here:
        clearform();
    }//GEN-LAST:event_btnlammoi1ActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnxoaActionPerformed

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
    private javax.swing.JButton btnlammoi1;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthemm;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField idloainuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblbanggiaohang;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtloainuoc1;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtnguoidao;
    private javax.swing.JTextField txtnguoinhan;
    private javax.swing.JTextField txtsdtgiao;
    private javax.swing.JTextField txtsdtnguoinhan;
    private javax.swing.JTextField txttinhtrang;
    // End of variables declaration//GEN-END:variables
}
