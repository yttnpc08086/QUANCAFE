package com.mycompany.views;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import com.mycompany.Helper.ConnectUtil;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

public class QuanLyBanHangJPanel extends javax.swing.JPanel {

    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelSize;
    private DefaultTableModel modelJTable1;

    public QuanLyBanHangJPanel(String url, String username, String password) {

        initComponents();
        loadData();

        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        tblSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblSanPham.getSelectedRow();
                    if (selectedRow != -1) {
                        Vector<Object> rowData = new Vector<>();
                        for (int i = 0; i < tblSanPham.getColumnCount(); i++) {
                            rowData.add(tblSanPham.getValueAt(selectedRow, i));
                        }
                        modelJTable1.addRow(rowData);
                    }
                }
            }
        });

        tblSize.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblSize.getSelectedRow();
                    if (selectedRow != -1) {
                        Vector<Object> rowData = new Vector<>();
                        for (int i = 0; i < tblSize.getColumnCount(); i++) {
                            rowData.add(tblSize.getValueAt(selectedRow, i));
                        }
                        modelJTable1.addRow(rowData);
                    }
                }
            }
        });

        tblSanPham.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblSanPham.getSelectedRow() != -1) {
            }
        });

        tblSize.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblSize.getSelectedRow() != -1) {
            }
        });
        btnThanhToan = new JButton("Thanh Toán");

        this.add(btnThanhToan);
    }

    private void loadData() {
        loadSanPhamData();
        loadSizeData();
    }

    private void loadSanPhamData() {
        String sql = "SELECT * FROM SanPham";
        loadTableData(sql, tblSanPham, new String[]{"ID_SanPham", "TenSP", "ID_LoaiSP", "Gia"});
    }

    private void loadSizeData() {
        String sql = "SELECT * FROM DonViSanPham";
        loadTableData(sql, tblSize, new String[]{"TenDonvi", "ThemTien"});
    }

    private void loadTableData(String sql, javax.swing.JTable table, String[] columnNames) {
        ResultSet rs = null;
        try {
            rs = ConnectUtil.query(sql);

            DefaultTableModel model = new DefaultTableModel();
            for (String columnName : columnNames) {
                model.addColumn(columnName);
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (String columnName : columnNames) {
                    row.add(rs.getObject(columnName));
                }
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnHoadonchitiet = new javax.swing.JPopupMenu();
        btnxoaHDchitiet = new javax.swing.JMenuItem();
        mnSuaSl = new javax.swing.JMenuItem();
        pmnHoadonctt = new javax.swing.JPopupMenu();
        mnChuyenban = new javax.swing.JMenuItem();
        mnHuyDon = new javax.swing.JMenuItem();
        mnTachDon = new javax.swing.JMenuItem();
        mnGopDon = new javax.swing.JMenuItem();
        mnSuaThongtinkhach = new javax.swing.JMenuItem();
        pmnBan = new javax.swing.JPopupMenu();
        mnXoaDan = new javax.swing.JMenuItem();
        mnSua = new javax.swing.JMenuItem();
        mnNhomBan = new javax.swing.JMenuItem();
        mnGopBan = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSize = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnIN = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnHuydon = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        btnxoaHDchitiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnxoaHDchitiet.setText("Xóa sản phẩm");
        btnxoaHDchitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaHDchitietActionPerformed(evt);
            }
        });
        pmnHoadonchitiet.add(btnxoaHDchitiet);

        mnSuaSl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mnSuaSl.setText("Sửa số lượng");
        mnSuaSl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSuaSlActionPerformed(evt);
            }
        });
        pmnHoadonchitiet.add(mnSuaSl);

        mnChuyenban.setText("Chuyển bàn");
        mnChuyenban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnChuyenbanActionPerformed(evt);
            }
        });
        pmnHoadonctt.add(mnChuyenban);

        mnHuyDon.setText("Hủy Đơn");
        mnHuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHuyDonActionPerformed(evt);
            }
        });
        pmnHoadonctt.add(mnHuyDon);

        mnTachDon.setText("Tách đơn");
        mnTachDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTachDonActionPerformed(evt);
            }
        });
        pmnHoadonctt.add(mnTachDon);

        mnGopDon.setText("Gộp tới");
        mnGopDon.setToolTipText("");
        mnGopDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGopDonActionPerformed(evt);
            }
        });
        pmnHoadonctt.add(mnGopDon);

        mnSuaThongtinkhach.setText("Sửa thông tin khách hàng");
        mnSuaThongtinkhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSuaThongtinkhachActionPerformed(evt);
            }
        });
        pmnHoadonctt.add(mnSuaThongtinkhach);

        mnXoaDan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mnXoaDan.setText("Xoá bàn");
        mnXoaDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnXoaDanActionPerformed(evt);
            }
        });
        pmnBan.add(mnXoaDan);

        mnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mnSua.setText("Sửa");
        mnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSuaActionPerformed(evt);
            }
        });
        pmnBan.add(mnSua);

        mnNhomBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mnNhomBan.setText("Nhóm Bàn");
        mnNhomBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNhomBanActionPerformed(evt);
            }
        });
        pmnBan.add(mnNhomBan);

        mnGopBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mnGopBan.setText("Gộp bàn tới");
        mnGopBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGopBanActionPerformed(evt);
            }
        });
        pmnBan.add(mnGopBan);

        setBackground(new java.awt.Color(241, 241, 241));

        jPanel1.setBackground(new java.awt.Color(241, 241, 241));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Tìm kiếm(theo tên)");

        tblSize.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblSize.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Size", "Giá upsize"
            }
        ));
        jScrollPane5.setViewportView(tblSize);

        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản phẩm", "Tên sản phẩm", "Loại", "Giá giảm(VND)", "Giá gốc (VND)"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnIN.setBackground(new java.awt.Color(255, 204, 204));
        btnIN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIN.setText("In");
        btnIN.setToolTipText("");
        btnIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnINActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 255, 0));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuydon.setBackground(new java.awt.Color(255, 51, 0));
        btnHuydon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHuydon.setForeground(new java.awt.Color(255, 255, 255));
        btnHuydon.setText("Hủy đơn");
        btnHuydon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuydonActionPerformed(evt);
            }
        });

        btnthem.setBackground(new java.awt.Color(204, 102, 0));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnthem.setText("Tạo Đơn");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Giá", "Size", "Giá Upsize", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIN, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnHuydon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)))
                .addComponent(btnIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void filterTableByProductCode(String productCode) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tblSanPham.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter("(?i)" + productCode, 1));
    }

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        // TODO add your handling code here:
        String searchText = txtTimkiem.getText().trim();
        filterTableByProductCode(searchText);
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void deleteHoaDon(String idHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE ID_Hoadon = ?";
        try {
            ConnectUtil.update(sql, idHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể hủy hóa đơn.");
        }
    }

    private void deleteHoaDonChiTiet(String idHoaDon) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE ID_HoaDon = ?";
        try {
            ConnectUtil.update(sql, idHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể hủy chi tiết hóa đơn.");
        }
    }

    private void btnHuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuydonActionPerformed

    }//GEN-LAST:event_btnHuydonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void mnHuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHuyDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnHuyDonActionPerformed

    private void mnChuyenbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnChuyenbanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnChuyenbanActionPerformed

    private void btnxoaHDchitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaHDchitietActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnxoaHDchitietActionPerformed

    private void mnXoaDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnXoaDanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnXoaDanActionPerformed

    private void mnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnSuaActionPerformed

    private void mnNhomBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNhomBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnNhomBanActionPerformed

    private void mnTachDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTachDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnTachDonActionPerformed

    private void mnGopDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGopDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnGopDonActionPerformed

    private void mnGopBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGopBanActionPerformed

    }//GEN-LAST:event_mnGopBanActionPerformed

    private void btnINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnINActionPerformed

    private void mnSuaSlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaSlActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_mnSuaSlActionPerformed

    private void mnSuaThongtinkhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaThongtinkhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnSuaThongtinkhachActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthemActionPerformed

    private boolean isBanExists(String banSo) {
        String sql = "SELECT COUNT(*) FROM Ban WHERE ID_Ban = ?";
        try (ResultSet rs = ConnectUtil.query(sql, banSo)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateBan(String banSo, String trangThai, String hoatDong, int soLuongCho) {
        // Check if ID_Ban exists
        if (!isBanExists(banSo)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Mã bàn không tồn tại.");
            return false;
        }

        String sql = "UPDATE Ban SET Trangthai = ?, Hoatdong = ?, Soluongcho = ? WHERE ID_Ban = ?";
        try {
            ConnectUtil.update(sql, trangThai, hoatDong, soLuongCho, banSo);
            javax.swing.JOptionPane.showMessageDialog(this, "Cập nhật bàn thành công.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể cập nhật bàn.");
            return false;
        }
    }

    private boolean deleteBan(String banSo) {
        String sql = "DELETE FROM Ban WHERE ID_Ban = ?";
        try {
            ConnectUtil.update(sql, banSo);
            javax.swing.JOptionPane.showMessageDialog(this, "Xóa bàn thành công.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể xóa bàn.");
            return false;
        }
    }

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuydon;
    private javax.swing.JButton btnIN;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnthem;
    private javax.swing.JMenuItem btnxoaHDchitiet;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem mnChuyenban;
    private javax.swing.JMenuItem mnGopBan;
    private javax.swing.JMenuItem mnGopDon;
    private javax.swing.JMenuItem mnHuyDon;
    private javax.swing.JMenuItem mnNhomBan;
    private javax.swing.JMenuItem mnSua;
    private javax.swing.JMenuItem mnSuaSl;
    private javax.swing.JMenuItem mnSuaThongtinkhach;
    private javax.swing.JMenuItem mnTachDon;
    private javax.swing.JMenuItem mnXoaDan;
    private javax.swing.JPopupMenu pmnBan;
    private javax.swing.JPopupMenu pmnHoadonchitiet;
    private javax.swing.JPopupMenu pmnHoadonctt;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSize;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables

}
