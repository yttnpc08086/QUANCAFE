/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.views;

import com.mycompany.DAO.NhanVienDAO;
import com.mycompany.Helper.MsgBox;
import com.mycompany.Helper.VerificationCode;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * @author HP
 */
public class QuenMatKhauJDialog extends javax.swing.JDialog {

    private VerificationCode verificationCode;

    /**
     * Creates new form QuenMatKhauJDialog
     */
    public QuenMatKhauJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        txtMaxacnhan.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnguima = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaxacnhan = new javax.swing.JTextField();
        btnxacnhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Quên Mật Khẩu");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnguima.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnguima.setForeground(new java.awt.Color(204, 102, 0));
        btnguima.setText("Lấy Mã");
        btnguima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguimaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 102, 0));
        jLabel3.setText("Mã OTP:");

        txtMaxacnhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnxacnhan.setBackground(new java.awt.Color(51, 255, 0));
        btnxacnhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnxacnhan.setText("Đổi mật khẩu");
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 0, 51));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnxacnhan))
                            .addComponent(txtEmail)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnguima)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel1)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguima, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnguimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguimaActionPerformed
        try {
            String email = txtEmail.getText().trim();
            if (!email.isEmpty()) {
                boolean emailExists = NhanVienDAO.checkEmailExists(email);

                if (emailExists) {
                    if (verificationCode == null || verificationCode.isExpired()) {
                        verificationCode = new VerificationCode(generateVerificationCode());
                        sendVerificationCode(email, verificationCode.getCode());
                        txtMaxacnhan.setEnabled(true);
                        MsgBox.alert(this, "Mã xác nhận đã được gửi tới email của bạn.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Mã xác nhận đã được gửi, vui lòng chờ 1 phút để gửi lại.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Email không tồn tại trong hệ thống.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập email.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnguimaActionPerformed

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed

        if (txtMaxacnhan.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập mã otp");
            txtMaxacnhan.setText("");
            txtMaxacnhan.requestFocus();
        } else {
            // Xử lý xác nhận OTP và cập nhật mật khẩu
            String code = txtMaxacnhan.getText().trim();
            String newPassword = JOptionPane.showInputDialog(this, "Nhập mật khẩu mới:");
            String email = txtEmail.getText();

            if (verificationCode != null && !verificationCode.isExpired()) {
                if (code.equals(verificationCode.getCode())) {
                    if (newPassword != null && !newPassword.isEmpty()) {
                        String updateResult = NhanVienDAO.updatePass(email, newPassword);
                        if (updateResult.equals("Password updated successfully!")) {
                            MsgBox.alert(this, "Đặt lại mật khẩu thành công.");
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, updateResult);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mã xác nhận không đúng.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã xác nhận đã hết hạn. Vui lòng lấy lại mã mới.");
                txtMaxacnhan.setText("");
            }
        }


    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void sendVerificationCode(String email, String code) {
        String from = "yttnpc08086@fpt.edu.vn";
        String host = "smtp.gmail.com"; // Sử dụng SMTP server của Gmail
        final String username = "yttnpc08086@fpt.edu.vn"; // Thay bằng email của bạn
        final String password = "rgnv bird rdku iped"; // Thay bằng mật khẩu email của bạn

        // Cài đặt thuộc tính SMTP server
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587"); // Cổng SMTP thường dùng
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Tạo session để gửi email
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage để định dạng email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Mã xác nhận đặt lại mật khẩu");
            message.setText("Mã xác nhận của bạn là: " + code);

            // Gửi email
            Transport.send(message);
            System.out.println("Gửi mã xác nhận thành công...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi gửi email.");
        }
    }

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
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuenMatKhauJDialog dialog = new QuenMatKhauJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnguima;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaxacnhan;
    // End of variables declaration//GEN-END:variables

    private String generateVerificationCode() {
        Random rand = new Random();
        int code = 100000 + rand.nextInt(900000);
        return String.valueOf(code);
    }
}
