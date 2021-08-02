/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.ui;

import edu.poly.dao.NhanVienDao;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.NhanVien;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class LoginFrm extends javax.swing.JFrame {
 int showHidePass = 1;
    /**
     * Creates new form LoginFrm
     */
    public LoginFrm() {
      
        initComponents();
        setTitle("Đăng nhập");
        setLocationRelativeTo(null);
       
    }
    private void showHidePassword() {
        showHidePass++;
        if (showHidePass % 2 == 0) {
            Image img = getToolkit().createImage("D:\\DU AN NETBEANS\\DuAnMau\\src\\main\\resources\\edu\\poly\\ui\\images\\eyeBlack.png");
            btnEye.setIcon(new ImageIcon(img));
            txtPassword.setEchoChar((char) 0);
        } else {
            Image img = getToolkit().createImage("D:\\DU AN NETBEANS\\DuAnMau\\src\\main\\resources\\edu\\poly\\ui\\images\\eyeHideBlack.png");
            btnEye.setIcon(new ImageIcon(img));
            txtPassword.setEchoChar('\u25cf');
        }
    }
    NhanVienDao dao = new NhanVienDao();
    void login() {
        String manv = txtusername.getText();
        String matKhau = new String(txtPassword.getPassword());
        try {
            if (manv.equals("")) {
                DialogHelper.alert(this, "Vui lòng nhập tài khoản ");
                return;
            }
            NhanVien nhanVien = dao.findById(manv);
            if (nhanVien != null) {
                String matKhau2 = nhanVien.getMatKhau();
                if (matKhau.equalsIgnoreCase(matKhau2)) {
                    ShareHelper.USER = nhanVien;
                    
                    DialogHelper.alert(this, "Đăng nhập thành công ");                   
                    this.dispose();
                    new FrmMain().setVisible(true);
                    
                    
                } else {
                    DialogHelper.alert(this, "Sai mật khẩu!");
                }
            } else {
                DialogHelper.alert(this, "Vui lòng nhập mật khẩu!");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
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

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        btnEye = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnExit = new javax.swing.JButton();
        lblIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setBackground(new java.awt.Color(1, 45, 138));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/icons8-login-30.png"))); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.setBorder(null);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 170, 40));

        lblUsername.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Tên Đăng Nhập");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        lblPassword.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Mật Khẩu");
        jPanel1.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        txtusername.setText("admin");
        txtusername.setBorder(null);
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 210, 30));

        btnEye.setBackground(new java.awt.Color(255, 255, 255));
        btnEye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/eyeHideBlack.png"))); // NOI18N
        btnEye.setBorder(null);
        btnEye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEyeActionPerformed(evt);
            }
        });
        jPanel1.add(btnEye, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 40, 30));

        txtPassword.setText("admin");
        txtPassword.setBorder(null);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 210, 30));

        btnExit.setBackground(new java.awt.Color(1, 45, 138));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/closeMain.png"))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.setBorder(null);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 170, 40));

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/logun.png"))); // NOI18N
        jPanel1.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnEyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEyeActionPerformed
        // TODO add your handling code here:
        showHidePassword();
        
    }//GEN-LAST:event_btnEyeActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        login();
       
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnEye;
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
