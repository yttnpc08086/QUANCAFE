/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ThanhToan extends javax.swing.JFrame {

    /**
     * Creates new form ThanhToan
     */
    public ThanhToan() {
        initComponents();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
    }

    public javax.swing.JLabel getLblGhichu() {
        return lblGhichu;
    }

    // Các phương thức getter và setter cho các label
    public javax.swing.JLabel getLblSanPham() {
        return lblSanPham;
    }

    public void setLblSanPham(javax.swing.JLabel lblSanPham) {
        this.lblSanPham = lblSanPham;
    }

    public javax.swing.JLabel getLblTienHang() {
        return lblTienHang;
    }

    public void setLblTienHang(javax.swing.JLabel lblTienHang) {
        this.lblTienHang = lblTienHang;
    }

    public javax.swing.JLabel getLblThanhtoan() {
        return lblThanhtoan;
    }

    public void setLblThanhtoan(javax.swing.JLabel lblThanhtoan) {
        this.lblThanhtoan = lblThanhtoan;
    }

    public javax.swing.JLabel getLblhhoadon() {
        return lblhhoadon;
    }

    public void setLblhhoadon(javax.swing.JLabel lblhhoadon) {
        this.lblhhoadon = lblhhoadon;
    }

    public javax.swing.JLabel getLblThoiGian() {
        return lblThoiGian;
    }

    public void setLblThoiGian(javax.swing.JLabel lblThoiGian) {
        this.lblThoiGian = lblThoiGian;
    }

    public javax.swing.JLabel getjLabel10() {
        return jLabel10;
    }

    public void setjLabel10(javax.swing.JLabel jLabel10) {
        this.jLabel10 = jLabel10;
    }

    // Getter for lblTienHang1
    public javax.swing.JLabel getLblTienHang1() {
        return lblTienHang1;
    }

    public void setLblsoluong(String text) {
        lblsoluong.setText(text);
    }

    public JLabel getLblsoluong() {
        return lblsoluong;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblhhoadon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTienHang1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblThanhtoan = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblTienHang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTenkhachhanh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblGhichu = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblsoluong = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtsanpham = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("HÓA ĐƠN THANH TOÁN");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Mặt hàng:");

        jLabel8.setText("Vào lúc:");

        lblThoiGian.setText("...............");

        lblSanPham.setText("...............");

        jLabel4.setText("ID Hóa đơn:");

        lblhhoadon.setText("................");

        jLabel7.setText("ID nhân viên:");

        jLabel10.setText("...................");

        lblTienHang1.setText("..............");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblThoiGian)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblhhoadon)
                                .addGap(107, 107, 107)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTienHang1)
                        .addGap(87, 87, 87))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblThoiGian)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblhhoadon)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblSanPham)
                    .addComponent(lblTienHang1))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Thanh Toán");

        lblThanhtoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblThanhtoan.setText(".......");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Tiền hàng:");

        lblTienHang.setText("..............");

        jLabel11.setText("Tên khách hàng:");

        jLabel12.setText("Ghi chú: ");

        lblGhichu.setText("...........");

        jLabel6.setText("Số lượng:");

        lblsoluong.setText("...............");

        jLabel13.setText("IDSanPham:");

        txtsanpham.setText("SP1");
        txtsanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsanphamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenkhachhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTienHang)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblGhichu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblsoluong)
                            .addComponent(txtsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblTienHang)
                    .addComponent(jLabel6)
                    .addComponent(lblsoluong))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblGhichu)
                    .addComponent(jLabel13)
                    .addComponent(txtsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenkhachhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jLabel2.setText("Cửa hàng chúc quý khách ngon miệng, hẹn gặp lại ạ!!!");

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Lưu Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lblThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String url = "jdbc:sqlserver://localhost;databaseName=COFFEE;trustServerCertificate=true;";
        String user = "sa";
        String password = "20190400";

        Connection conn = null;
        PreparedStatement pstmtHoaDon = null;
        PreparedStatement pstmtHoaDonChiTiet = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            // Bật IDENTITY_INSERT cho bảng HoaDon
            String enableIdentityInsert = "SET IDENTITY_INSERT HoaDon ON";
            conn.createStatement().execute(enableIdentityInsert);

            // Thêm dữ liệu vào bảng HoaDon
            String sqlHoaDon = "INSERT INTO HoaDon (ID_HoaDon, ID_Nhanvien, Ngaytao, Ten, Thanhtien, ghichu) VALUES (?, ?, ?, ?, ?, ?)";
            pstmtHoaDon = conn.prepareStatement(sqlHoaDon);
            pstmtHoaDon.setString(1, getLblhhoadon().getText());
            pstmtHoaDon.setString(2, getjLabel10().getText());
            pstmtHoaDon.setString(3, getLblThoiGian().getText());
            pstmtHoaDon.setString(4, txtTenkhachhanh.getText());
            pstmtHoaDon.setString(5, getLblThanhtoan().getText());
            pstmtHoaDon.setString(6, getLblGhichu().getText());

            int affectedRowsHoaDon = pstmtHoaDon.executeUpdate();

            // Tắt IDENTITY_INSERT cho bảng HoaDon
            String disableIdentityInsert = "SET IDENTITY_INSERT HoaDon OFF";
            conn.createStatement().execute(disableIdentityInsert);

            // Thêm dữ liệu vào bảng HoaDonChiTiet
            // Thêm dữ liệu vào bảng HoaDonChiTiet
            String sqlHoaDonChiTiet = "INSERT INTO HoaDonChiTiet (ID_HoaDon, ID_SanPham, Soluong, Gia, Tong) VALUES (?, ?, ?, ?)";
            pstmtHoaDonChiTiet = conn.prepareStatement(sqlHoaDonChiTiet);
            pstmtHoaDonChiTiet.setString(1, getLblhhoadon().getText());
            pstmtHoaDonChiTiet.setString(2, txtsanpham.getText());
            pstmtHoaDonChiTiet.setString(3, getLblsoluong().getText());
            pstmtHoaDonChiTiet.setString(4, getLblThanhtoan().getText()); // Giá trị cho cột 'Gia'

            int affectedRowsHoaDonChiTiet = pstmtHoaDonChiTiet.executeUpdate();

            // Kiểm tra nếu dữ liệu được thêm thành công
            if (affectedRowsHoaDon > 0 && affectedRowsHoaDonChiTiet > 0) {
                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Không thể lưu dữ liệu.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // Đóng kết nối và các tài nguyên
            try {
                if (pstmtHoaDon != null) {
                    pstmtHoaDon.close();
                }
                if (pstmtHoaDonChiTiet != null) {
                    pstmtHoaDonChiTiet.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi đóng tài nguyên: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsanphamActionPerformed

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
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThanhToan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGhichu;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThanhtoan;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTienHang;
    private javax.swing.JLabel lblTienHang1;
    private javax.swing.JLabel lblhhoadon;
    private javax.swing.JLabel lblsoluong;
    private javax.swing.JTextField txtTenkhachhanh;
    private javax.swing.JTextField txtsanpham;
    // End of variables declaration//GEN-END:variables
}
