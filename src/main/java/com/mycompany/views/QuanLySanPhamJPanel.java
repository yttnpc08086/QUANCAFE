/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.views;

import com.mycompany.Helper.ConnectUtil;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class QuanLySanPhamJPanel extends javax.swing.JPanel {

    private DefaultTableModel modelSanPhansd;
    private DefaultTableModel modelSanPHamksd;

    /**
     * Creates new form QuanLySanPhamJPanel
     */
    public QuanLySanPhamJPanel() {
        initComponents();
        initializeTableModels();
        loadSanPhamData();
    }

    private void initializeTableModels() {
        modelSanPhansd = (DefaultTableModel) tblSanPhansd.getModel();
        modelSanPHamksd = (DefaultTableModel) tblSanPHamksd.getModel();
        modelSanPhansd.setColumnIdentifiers(new Object[]{"ID_Sanpham", "Tên Sản Phẩm", "Giá", "Loại", "Trạng thái"});
        modelSanPHamksd.setColumnIdentifiers(new Object[]{"ID_Sanpham", "Tên Sản Phẩm", "Giá", "Loại", "Trạng thái"});
    }

    private void loadSanPhamData() {
        try {
            // Load data for active products (tblSanPhansd)
            String querySanPhansd = "SELECT * FROM SanPham WHERE Trangthai = 1"; // Active products
            ResultSet rsSanPhansd = ConnectUtil.query(querySanPhansd);
            populateTable((DefaultTableModel) tblSanPhansd.getModel(), rsSanPhansd);

            // Load data for inactive products (tblSanPHamksd)
            String querySanPHamksd = "SELECT * FROM SanPham WHERE Trangthai = 0"; // Inactive products
            ResultSet rsSanPHamksd = ConnectUtil.query(querySanPHamksd);
            populateTable((DefaultTableModel) tblSanPHamksd.getModel(), rsSanPHamksd);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTable(DefaultTableModel model, ResultSet rs) throws SQLException {
        model.setRowCount(0); // Clear existing rows
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("ID_Sanpham"),
                rs.getString("TenSP"),
                rs.getInt("Gia"),
                rs.getString("ID_LoaiSP"),
                rs.getString("Trangthai"), // Assuming you also want to display the status
            });
        }
    }

    private void filterTable(String query) {
        DefaultTableModel model = (DefaultTableModel) tblSanPhansd.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tblSanPhansd.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }

    private void filterTableByDate(String startDate, String endDate) {
        DefaultTableModel model = (DefaultTableModel) tblSanPhansd.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tblSanPhansd.setRowSorter(tr);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);

            RowFilter<DefaultTableModel, Object> dateFilter = new RowFilter<DefaultTableModel, Object>() {
                @Override
                public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                    try {
                        String dateString = (String) entry.getValue(2); // Giả sử cột 3 chứa ngày tháng
                        Date date = dateFormat.parse(dateString);
                        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            };

            tr.setRowFilter(dateFilter);
        } catch (ParseException e) {
            e.printStackTrace();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhansd = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPHamksd = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoksd = new javax.swing.JRadioButton();
        rdosd = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnloaidouong = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtDoUong = new javax.swing.JTextField();

        setBackground(new java.awt.Color(241, 241, 241));

        jPanel1.setBackground(new java.awt.Color(241, 241, 241));
        jPanel1.setLayout(null);

        tabs.setPreferredSize(new java.awt.Dimension(1000, 200));
        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(100, 200));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 200));

        tblSanPhansd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm ", "Loại đồ uống ", "Giá", "Trạng thái"
            }
        ));
        tblSanPhansd.setPreferredSize(new java.awt.Dimension(1000, 200));
        tblSanPhansd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhansdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhansd);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );

        tabs.addTab("Sản Phẩm sử dụng", jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(100, 200));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1000, 200));

        tblSanPHamksd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Loại đồ uống ", "Giá", "Trạng thái"
            }
        ));
        tblSanPHamksd.setPreferredSize(new java.awt.Dimension(1000, 200));
        tblSanPHamksd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPHamksdMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPHamksd);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Sản Phẩm không sử dụng", jPanel3);

        jPanel1.add(tabs);
        tabs.setBounds(10, 460, 980, 240);

        jPanel4.setBackground(new java.awt.Color(241, 241, 241));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Tìm kiếm(Tên hoặc Mã)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(20, 380, 0, 0);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(100, 90, 100, 17);

        txtMaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaSP);
        txtMaSP.setBounds(100, 120, 290, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên sản phẩm");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(100, 180, 120, 17);

        txtTenSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });
        txtTenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenSPKeyReleased(evt);
            }
        });
        jPanel1.add(txtTenSP);
        txtTenSP.setBounds(100, 210, 290, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Loại đồ uống");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(100, 270, 110, 17);

        txtGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtGia);
        txtGia.setBounds(510, 130, 230, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Giá");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(510, 100, 60, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Trạng thái");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(520, 200, 100, 17);

        rdoksd.setBackground(new java.awt.Color(241, 241, 241));
        buttonGroup1.add(rdoksd);
        rdoksd.setText("Không sử dụng");
        jPanel1.add(rdoksd);
        rdoksd.setBounds(510, 230, 110, 21);

        rdosd.setBackground(new java.awt.Color(241, 241, 241));
        buttonGroup1.add(rdosd);
        rdosd.setSelected(true);
        rdosd.setText("Sử dụng");
        jPanel1.add(rdosd);
        rdosd.setBounds(630, 230, 90, 21);

        btnThem.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);
        btnThem.setBounds(790, 140, 101, 34);

        btnXoa.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);
        btnXoa.setBounds(790, 240, 101, 34);

        btnSua.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);
        btnSua.setBounds(790, 190, 101, 34);

        btnLamMoi.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi);
        btnLamMoi.setBounds(790, 300, 99, 34);

        btnloaidouong.setBackground(new java.awt.Color(225, 193, 144));
        btnloaidouong.setText("+");
        btnloaidouong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloaidouongActionPerformed(evt);
            }
        });
        jPanel1.add(btnloaidouong);
        btnloaidouong.setBounds(100, 300, 30, 30);

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("QUẢN LÝ ĐỒ UỐNG");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(330, 20, 380, 44);
        jPanel1.add(txtDoUong);
        txtDoUong.setBounds(130, 300, 260, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhansdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhansdMouseClicked
        if (tabs.getSelectedIndex() == 0) {
            int row = tblSanPhansd.getSelectedRow();
            if (row >= 0) {
                // Retrieve data from the selected row
                String maSP = tblSanPhansd.getValueAt(row, 0).toString();
                String tenSP = tblSanPhansd.getValueAt(row, 1).toString();
                String gia = tblSanPhansd.getValueAt(row, 2).toString();
                String loai = tblSanPhansd.getValueAt(row, 3).toString();
                String status = tblSanPhansd.getValueAt(row, 4).toString().trim();

                // Set values in input fields
                txtMaSP.setText(maSP);
                txtTenSP.setText(tenSP);
                txtGia.setText(gia);
                txtDoUong.setText(loai);

                if (status.equals("Sử dụng")) {
                    rdosd.setSelected(true);
                    rdoksd.setSelected(false);
                } else {
                    rdosd.setSelected(true);
                    rdoksd.setSelected(false);
                }
            }
        }
    }//GEN-LAST:event_tblSanPhansdMouseClicked

    private void tblSanPHamksdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPHamksdMouseClicked

        if (tabs.getSelectedIndex() == 1) { // Ensure you are in the "Sản Phẩm không sử dụng" tab
            int row = tblSanPHamksd.getSelectedRow();
            if (row >= 0) {
                txtMaSP.setText(tblSanPHamksd.getValueAt(row, 0).toString());
                txtTenSP.setText(tblSanPHamksd.getValueAt(row, 1).toString());
                txtGia.setText(tblSanPHamksd.getValueAt(row, 2).toString());
                txtDoUong.setText(tblSanPHamksd.getValueAt(row, 3).toString());
                String status = tblSanPHamksd.getValueAt(row, 4).toString().trim();
                if (status.equals("Sử dụng")) {
                    rdosd.setSelected(false);
                } else {
                    rdoksd.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_tblSanPHamksdMouseClicked

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabsMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String searchText = txtTimKiem.getText().trim();
        filterTableByProductCode(searchText);
    }

    private void filterTableByProductCode(String productCode) {
        DefaultTableModel model = (DefaultTableModel) tblSanPhansd.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tblSanPhansd.setRowSorter(tr);

        // Apply RowFilter to filter by product ID (assuming column 0 is the product ID)
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + productCode, 0)); // Adjust column index if necessary
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTenSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSPKeyReleased
        // TODO add your handling code here:
        String maSP = txtMaSP.getText();
        String tenSP = txtTenSP.getText();
        int gia = Integer.parseInt(txtGia.getText());
        String loai = txtDoUong.getText();
        int trangThai = rdosd.isSelected() ? 1 : 0;

        // Update the product name in the database (assuming ConnectUtil.update() is your method for DB operations)
        String query = "UPDATE SanPham SET TenSP = ?, Gia = ?, ID_LoaiSP = ?, Trangthai = ? WHERE ID_Sanpham = ?";
        try {
            ConnectUtil.update(query, tenSP, gia, loai, trangThai, maSP);
//            JOptionPane.showMessageDialog(this, "Sửa tên sản phẩm thành công!");
            loadSanPhamData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa tên sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtTenSPKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String maSP = txtMaSP.getText().trim();
        String tenSP = txtTenSP.getText().trim();
        String giaStr = txtGia.getText().trim();
        String loaiSP = txtDoUong.getText().trim();
        int trangThai = rdosd.isSelected() ? 1 : 0; // 1 for "Sử dụng", 0 for "Không sử dụng"

        // Input validation
        if (maSP.isEmpty() || tenSP.isEmpty() || giaStr.isEmpty() || loaiSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int gia;
        try {
            gia = Integer.parseInt(giaStr);
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phải là một số dương.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prepare SQL query for insertion
        String query = "INSERT INTO SanPham (ID_Sanpham, TenSP, Gia, ID_LoaiSP, Trangthai) VALUES (?, ?, ?, ?, ?)";
        try {
            // Execute database update
            int rowsAffected = ConnectUtil.update(query, maSP, tenSP, gia, loaiSP, trangThai);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");

                // Clear input fields after successful insertion
                txtMaSP.setText("");
                txtTenSP.setText("");
                txtGia.setText("");
                txtDoUong.setText("");
                rdosd.setSelected(true); // Reset to default (Sử dụng)
                rdoksd.setSelected(false);

                // Refresh the table data
                loadSanPhamData();
            } else {
                JOptionPane.showMessageDialog(this, "Không có dòng nào được thêm. Kiểm tra lại dữ liệu đầu vào.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi không xác định khi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Handle SQL specific exceptions

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        String maSP = txtMaSP.getText();

        // Xác nhận xóa
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Xóa sản phẩm khỏi cơ sở dữ liệu
            String query = "DELETE FROM SanPham WHERE ID_Sanpham = ?";
            try {
                ConnectUtil.update(query, maSP);
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
                loadSanPhamData(); // Tải lại dữ liệu bảng
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            txtMaSP.setText("");
            txtTenSP.setText("");
            txtDoUong.setText(""); // Assuming txtLoai is a JTextField for product type
            txtGia.setText("");
            rdosd.setSelected(true);
            loadSanPhamData();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String maSP = txtMaSP.getText().trim();  // Product code
        String tenSP = txtTenSP.getText().trim();  // New product name
        int trangThai = rdosd.isSelected() ? 1 : 0;  // 1 for Active, 0 for Inactive

        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String query = "UPDATE SanPham SET TenSP = ?, Trangthai = ? WHERE ID_Sanpham = ?";
        try {
            // Execute the update
            int rowsAffected = ConnectUtil.update(query, tenSP, trangThai, maSP);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sửa tên sản phẩm thành công!");

//                // Clear input fields after successful update
//                txtMaSP.setText("");  // Clear the product code field
//                txtTenSP.setText("");  // Clear the product name field
                // Refresh the table data
                loadSanPhamData();
            } else {
                JOptionPane.showMessageDialog(this, "Không có dòng nào được cập nhật. Kiểm tra lại mã sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi không xác định khi sửa tên sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtDoUong.setText(""); // Assuming txtLoai is a JTextField for product type
        txtGia.setText("");
        rdosd.setSelected(true);
        loadSanPhamData();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed

    }//GEN-LAST:event_txtTenSPActionPerformed

    private void btnloaidouongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloaidouongActionPerformed
        // TODO add your handling code here:
        String newLoai = JOptionPane.showInputDialog(this, "Nhập loại đồ uống mới:");
        // Kiểm tra đầu vào của người dùng
        if (newLoai != null && !newLoai.trim().isEmpty()) {
            newLoai = newLoai.trim(); // Xóa bỏ khoảng trắng đầu cuối

            // Assuming txtLoai is a JTextField for drink type
            txtDoUong.setText(newLoai);
            JOptionPane.showMessageDialog(this, "Thêm loại đồ uống thành công!");
        } else if (newLoai != null) {
            JOptionPane.showMessageDialog(this, "Tên loại đồ uống không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnloaidouongActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
        String maSP = txtMaSP.getText().trim();
        // Check if the product ID is not empty
        if (maSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Query the database to retrieve product details
        String query = "SELECT TenSP, Gia, ID_LoaiSP, Trangthai FROM SanPham WHERE ID_Sanpham = ?";
        try {
            // Assuming ConnectUtil.query() returns a ResultSet or a similar object
            ResultSet rs = ConnectUtil.query(query, maSP);

            if (rs.next()) {
                // Populate the form fields with the retrieved data
                String tenSP = rs.getString("TenSP");
                int gia = rs.getInt("Gia");
                String loai = rs.getString("ID_LoaiSP");
                int trangThai = rs.getInt("Trangthai");

                txtTenSP.setText(tenSP);
                txtGia.setText(String.valueOf(gia));
                txtDoUong.setText(loai);
                if (trangThai == 1) {
                    rdosd.setSelected(true);
                } else {
                    rdoksd.setSelected(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất thông tin sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtDoUong.setText(""); // Assuming txtLoai is a JTextField for product type
        txtGia.setText("");
        rdosd.setSelected(true);
        loadSanPhamData(); // Tải lại dữ liệu bảng
    }//GEN-LAST:event_btnLamMoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnloaidouong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoksd;
    private javax.swing.JRadioButton rdosd;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblSanPHamksd;
    private javax.swing.JTable tblSanPhansd;
    private javax.swing.JTextField txtDoUong;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
