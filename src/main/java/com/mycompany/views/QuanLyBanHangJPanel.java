package com.mycompany.views;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import com.mycompany.Helper.ConnectUtil;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class QuanLyBanHangJPanel extends javax.swing.JPanel {

    private ChucNangThanhToanJPanel chucNangThanhToanPanel;

    public QuanLyBanHangJPanel(String url, String username, String password) {

        initComponents();
        loadData();

        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

//        tblhoadon.getSelectionModel().addListSelectionListener(event -> {
//            if (!event.getValueIsAdjusting() && tblhoadon.getSelectedRow() != -1) {
//                displayHoaDonDetails();
//            }
//        });
        tblSanPham.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblSanPham.getSelectedRow() != -1) {
                displaySanPhamDetails();
            }
        });

        tblSize.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblSize.getSelectedRow() != -1) {
                displaySizeDetails();
            }
        });
        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.addActionListener(e -> openChucNangThanhToanJPanel());

        this.add(btnThanhToan);
    }

//    private void displayHoaDonDetails() {
//        int selectedRow = tblhoadon.getSelectedRow();
//        if (selectedRow >= 0) {
//            DefaultTableModel model = (DefaultTableModel) tblhoadon.getModel();
//            String masp = model.getValueAt(selectedRow, 0).toString();  // Adjust index according to your columns
//            String tensp = model.getValueAt(selectedRow, 1).toString();
//            String loai = model.getValueAt(selectedRow, 2).toString();
//            String giagiam = model.getValueAt(selectedRow, 3).toString();
//            String giagoc = model.getValueAt(selectedRow, 4).toString();  // If there's more columns, adjust accordingly
//            String size = model.getValueAt(selectedRow, 5).toString();
//            String giasize = model.getValueAt(selectedRow, 6).toString();
//
//            txtmasp.setText(masp);
//            txttensp.setText(tensp);
//            txtloai.setText(loai);
//            txtgiagiam.setText(giagiam);
//            txtgiagoc.setText(giagoc);
//            txtsize.setText(size);
//            txtgiasize.setText(giasize);
//        }
//    }

    private void displaySanPhamDetails() {
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
            String idSanPham = model.getValueAt(selectedRow, 0).toString();
            String tenSP = model.getValueAt(selectedRow, 1).toString();
            String idLoaiSP = model.getValueAt(selectedRow, 2).toString();
            String gia = model.getValueAt(selectedRow, 3).toString();

            txtmasp.setText(idSanPham);
            txttensp.setText(tenSP);
            txtloai.setText(idLoaiSP);
            txtgiagoc.setText(gia);
        }
    }

    private void displaySizeDetails() {
        int selectedRow = tblSize.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblSize.getModel();
            String tenDonvi = model.getValueAt(selectedRow, 0).toString();
            String themTien = model.getValueAt(selectedRow, 1).toString();
            txtsize.setText(tenDonvi);
            txtgiasize.setText(themTien);
        }
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtmasp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txttensp = new javax.swing.JTextField();
        txtloai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtgiagiam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtgiagoc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsize = new javax.swing.JTextField();
        txtgiasize = new javax.swing.JTextField();
        btnlammoi = new javax.swing.JButton();
        btnHuydon = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();

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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.setForeground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Mã sp:");

        txtmasp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Giá gốc:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Loại:");

        txttensp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtloai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("Tên SP:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Giảm giá:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Size:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Giá upsize:");

        btnlammoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnlammoi.setText("làm mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel7)))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtloai)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttensp)
                                    .addComponent(txtmasp))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtgiagoc, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                            .addComponent(txtsize)
                            .addComponent(txtgiasize))
                        .addGap(300, 300, 300))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtgiagiam)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 641, Short.MAX_VALUE)
                .addComponent(btnlammoi)
                .addGap(369, 369, 369))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtgiagoc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsize, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(4, 4, 4)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtgiasize, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtloai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtgiagiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnlammoi)
                .addGap(14, 14, 14))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(btnIN, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuydon, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
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
        // TODO add your handling code here: 
//        int selectedRow = tblhoadon.getSelectedRow();
//        if (selectedRow >= 0) {
//            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hóa đơn này?", "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION);
//            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
//                DefaultTableModel model = (DefaultTableModel) tblhoadon.getModel();
//                model.removeRow(selectedRow);
//                txtmasp.setText("");
//                txttensp.setText("");
//                txtloai.setText("");
//                txtgiagiam.setText("");
//                txtgiagoc.setText("");
//                txtsize.setText("");
//                txtgiasize.setText("");
//            }
//        } else {
//            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
//        }
    }//GEN-LAST:event_btnHuydonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        openChucNangThanhToanJPanel();
    }//GEN-LAST:event_btnThanhToanActionPerformed
    private void openChucNangThanhToanJPanel() {
        // Create a new JDialog
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Chức Năng Thanh Toán", true);

        // Create and set up the ChucNangThanhToanJPanel
        ChucNangThanhToanJPanel panel = new ChucNangThanhToanJPanel();
        panel.setPanelData(
                txtmasp.getText().trim(),
                txttensp.getText().trim(),
                txtloai.getText().trim(),
                txtgiagiam.getText().trim(),
                txtgiagoc.getText().trim(),
                txtsize.getText().trim(),
                txtgiasize.getText().trim()
        );

        // Add the panel to the dialog
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center the dialog on the screen
        dialog.setVisible(true); // Show the dialog
    }


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
//        exportToExcel();
    }//GEN-LAST:event_btnINActionPerformed

//    private void exportToExcel() {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheetHoadon = workbook.createSheet("Hoadon");
//        Sheet sheetHoadonChitiet = workbook.createSheet("HoadonChitiet");
//
////        exportTableToSheet(tblhoadon, sheetHoadon);
//
////        exportTableToSheet(tblHoadonchitiet, sheetHoadonChitiet);
//        try (FileOutputStream fileOut = new FileOutputStream("Hoadon.xlsx")) {
//            workbook.write(fileOut);
//            workbook.close();
//            javax.swing.JOptionPane.showMessageDialog(this, "Xuất dữ liệu ra Excel thành công.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            javax.swing.JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất dữ liệu ra Excel.");
//        }
//    }

    private void exportTableToSheet(javax.swing.JTable table, Sheet sheet) {
        TableModel model = table.getModel();

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
        }

        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                Cell cell = row.createCell(colIndex);
                Object value = model.getValueAt(rowIndex, colIndex);
                if (value != null) {
                    cell.setCellValue(value.toString());
                } else {
                    cell.setCellValue("");
                }
            }
        }
    }

    private void mnSuaSlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaSlActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_mnSuaSlActionPerformed

    private void mnSuaThongtinkhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaThongtinkhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnSuaThongtinkhachActionPerformed

    private boolean insertBan(String banSo, String trangThai, String hoatDong, int soLuongCho) {
        String sql = "INSERT INTO Ban (ID_Ban, Trangthai, Hoatdong, Soluongcho) VALUES (?, ?, ?, ?)";
        try {
            ConnectUtil.update(sql, banSo, trangThai, hoatDong, soLuongCho);
            javax.swing.JOptionPane.showMessageDialog(this, "Thêm bàn mới thành công.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể thêm bàn mới.");
            return false;
        }
    }

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        String masp = txtmasp.getText().trim();
        String tensp = txttensp.getText().trim();
        String loai = txtloai.getText().trim();
        String giagiam = txtgiagiam.getText().trim();
        String giagoc = txtgiagoc.getText().trim();
        String size = txtsize.getText().trim();
        String giasize = txtgiasize.getText().trim();

        // Validate input data
        if (masp.isEmpty() || tensp.isEmpty() || loai.isEmpty() || giagiam.isEmpty() || giagoc.isEmpty() || size.isEmpty() || giasize.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
            return;
        }

        // Check for duplicate product code
        String checkSql = "SELECT COUNT(*) FROM SanPham WHERE ID_SanPham = ?";
        try (ResultSet rs = ConnectUtil.query(checkSql, masp)) {
//            if (rs.next() && rs.getInt(1) > 0) {
//                javax.swing.JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại.");
//                return;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra mã sản phẩm.");
            return;
        }

        // Add new data to the table
//        DefaultTableModel model = (DefaultTableModel) tblhoadon.getModel();
//        Vector<Object> row = new Vector<>();
//        row.add(masp);
//        row.add(tensp);
//        row.add(loai);
//        row.add(giagiam);
//        row.add(giagoc);
//        row.add(size);
//        row.add(giasize);
//        model.addRow(row);

        // Optionally: Clear the text fields
        txtmasp.setText("");
        txttensp.setText("");
        txtloai.setText("");
        txtgiagiam.setText("");
        txtgiagoc.setText("");
        txtsize.setText("");
        txtgiasize.setText("");
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

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
        txtmasp.setText("");
        txttensp.setText("");
        txtloai.setText("");
        txtgiagiam.setText("");
        txtgiagoc.setText("");
        txtsize.setText("");
        txtgiasize.setText("");
    }//GEN-LAST:event_btnlammoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuydon;
    private javax.swing.JButton btnIN;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnthem;
    private javax.swing.JMenuItem btnxoaHDchitiet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JTextField txtgiagiam;
    private javax.swing.JTextField txtgiagoc;
    private javax.swing.JTextField txtgiasize;
    private javax.swing.JTextField txtloai;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JTextField txtsize;
    private javax.swing.JTextField txttensp;
    // End of variables declaration//GEN-END:variables

}
