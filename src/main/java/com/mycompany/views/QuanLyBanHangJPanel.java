package com.mycompany.views;

import com.mycompany.Helper.ConnectUtil;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;

public class QuanLyBanHangJPanel extends javax.swing.JPanel {

    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelSize;
    private DefaultTableModel modelJTable1;

    public QuanLyBanHangJPanel(String url, String username, String password) {

        initComponents();
        loadData();

        // Khởi tạo Timer để cập nhật giờ hiện tại mỗi giây
        Timer timer = new Timer(1000, e -> updateCurrentTime());
        timer.start();

        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        modelSize = (DefaultTableModel) tblSize.getModel();
        modelJTable1 = (DefaultTableModel) jTable1.getModel();

        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        tblSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblSanPham.getSelectedRow();
                    if (selectedRow != -1) {
                        int rowCount = modelJTable1.getRowCount();
                        if (rowCount > 0) {
                            try {
                                Object lastSizeObj = modelJTable1.getValueAt(rowCount - 1, jTable1.getColumn("Size").getModelIndex());
                                String lastSize = (lastSizeObj != null) ? lastSizeObj.toString() : "";
                                if (lastSize.isEmpty()) {
                                    javax.swing.JOptionPane.showMessageDialog(QuanLyBanHangJPanel.this, "Bạn chưa thêm Size cho sản phẩm trước đó!");
                                    return;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                javax.swing.JOptionPane.showMessageDialog(QuanLyBanHangJPanel.this, "Lỗi khi kiểm tra kích thước sản phẩm.");
                                return;
                            }
                        }

                        Vector<Object> rowData = new Vector<>();
                        for (int i = 0; i < tblSanPham.getColumnCount(); i++) {
                            rowData.add(tblSanPham.getValueAt(selectedRow, i));
                        }
                        rowData.add("01");
                        addOrUpdateRowInJTable1(rowData);
                    }
                }
            }
        });

        tblSize.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblSize.getSelectedRow();
                    if (selectedRow != -1) {
                        String size = tblSize.getValueAt(selectedRow, 0).toString();
                        String giaUpsize = tblSize.getValueAt(selectedRow, 1).toString();
                        int rowCount = modelJTable1.getRowCount();
                        if (rowCount > 0) {
                            modelJTable1.setValueAt(size, rowCount - 1, jTable1.getColumn("Size").getModelIndex());
                            modelJTable1.setValueAt(giaUpsize, rowCount - 1, jTable1.getColumn("Giá Upsize").getModelIndex());
                            updateTotalAmount(); // Gọi phương thức tính tổng ngay sau khi cập nhật giá trị
                            updateTotalQuantity(); // Cập nhật tổng số lượng sau khi thay đổi kích thước
                        }
                    }
                }
            }
        });

    }

    public void clearTableData() {
        modelJTable1.setRowCount(0); // Xóa tất cả các hàng trong bảng
    }

    private String generateInvoiceNumber() {
        int nextInvoiceNumber = 1; // Giá trị mặc định nếu không có số hóa đơn nào

        // Truy vấn cơ sở dữ liệu để lấy số hóa đơn tiếp theo
        String sql = "SELECT MAX(CAST(ID_Hoadon AS INT)) AS MaxInvoiceNumber FROM HoaDon";
        try (ResultSet rs = ConnectUtil.query(sql)) {
            if (rs.next()) {
                nextInvoiceNumber = rs.getInt("MaxInvoiceNumber") + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Định dạng số hóa đơn với hai chữ số, ví dụ: "01", "02", ...
        return String.format("%02d", nextInvoiceNumber);
    }

    private int getNextInvoiceNumber() {
        int nextInvoiceNumber = 1; // Giá trị mặc định nếu không có số hóa đơn nào

        // Truy vấn cơ sở dữ liệu để lấy số hóa đơn tiếp theo
        String sql = "SELECT MAX(CAST(SUBSTRING(ID_Hoadon, 3, LEN(ID_Hoadon) - 2) AS INT)) AS MaxInvoiceNumber FROM HoaDon";
        try (ResultSet rs = ConnectUtil.query(sql)) {
            if (rs.next()) {
                nextInvoiceNumber = rs.getInt("MaxInvoiceNumber") + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextInvoiceNumber;
    }

    private void updateTotalQuantity() {
        int totalQuantity = 0;
        int quantityColumnIndex = jTable1.getColumn("Số lượng").getModelIndex();

        for (int i = 0; i < modelJTable1.getRowCount(); i++) {
            Object quantityValue = modelJTable1.getValueAt(i, quantityColumnIndex);

            if (quantityValue != null) {
                try {
                    totalQuantity += Integer.parseInt(quantityValue.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi tính tổng số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        txtSoluong.setText(String.valueOf(totalQuantity));
    }

    // Phương thức để cập nhật giờ hiện tại vào lblNgay 2024-06-27 15:00:00.000
    private void updateCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        lblNgay.setText(currentTime);
    }

//    private String formatCurrency(double amount) {
//        java.text.NumberFormat currencyFormat = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("vi", "VN"));
//        String formattedAmount = currencyFormat.format(amount);
//        // Loại bỏ ký tự tiền tệ "₫" và khoảng trắng ở cuối
//        return formattedAmount.replace("₫", "đ").trim();
//    }
    private void addOrUpdateRowInJTable1(Vector<Object> rowData) {
        try {
            int productIdIndex = jTable1.getColumn("Mã sản phẩm").getModelIndex();
            int sizeIndex = jTable1.getColumn("Size").getModelIndex();
            int quantityIndex = jTable1.getColumn("Số lượng").getModelIndex();

            String productId = rowData.get(tblSanPham.getColumnModel().getColumnIndex("ID_SanPham")).toString();
            String size = rowData.get(tblSize.getColumnModel().getColumnIndex("TenDonvi")).toString();

            boolean rowExists = false;
            int rowIndexToUpdate = -1;

            // Kiểm tra nếu đã có sản phẩm và kích thước
            for (int i = 0; i < modelJTable1.getRowCount(); i++) {
                String existingProductId = modelJTable1.getValueAt(i, productIdIndex).toString();
                String existingSize = modelJTable1.getValueAt(i, sizeIndex).toString();

                if (existingProductId.equals(productId) && existingSize.equals(size)) {
                    rowExists = true;
                    rowIndexToUpdate = i;
                    break;
                }
            }

            if (rowExists) {
                // Nếu đã tồn tại, cập nhật số lượng
                int currentQuantity = Integer.parseInt(modelJTable1.getValueAt(rowIndexToUpdate, quantityIndex).toString());
                int newQuantity = currentQuantity + 1; // Tăng số lượng lên 1
                modelJTable1.setValueAt(newQuantity, rowIndexToUpdate, quantityIndex);
            } else {
                // Nếu chưa tồn tại, thêm hàng mới
                modelJTable1.addRow(rowData);
            }

            updateTotalAmount(); // Gọi phương thức tính tổng ngay sau khi cập nhật giá trị
            updateTotalQuantity(); // Cập nhật tổng số lượng sau khi thêm hoặc cập nhật hàng

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức tính tổng giá trị của cột "Giá" và cột "Giá Upsize"
    private void updateTotalAmount() {
        double total = 0.0;
        int giaColumnIndex = jTable1.getColumn("Giá").getModelIndex();
        int giaUpsizeColumnIndex = jTable1.getColumn("Giá Upsize").getModelIndex();

        for (int i = 0; i < modelJTable1.getRowCount(); i++) {
            Object giaValue = modelJTable1.getValueAt(i, giaColumnIndex);
            Object giaUpsizeValue = modelJTable1.getValueAt(i, giaUpsizeColumnIndex);

            if (giaValue != null) {
                total += Double.parseDouble(giaValue.toString());
            }

            if (giaUpsizeValue != null) {
                total += Double.parseDouble(giaUpsizeValue.toString());
            }
        }
        lblTongTien.setText(String.format("%.0f", total));
    }

    private void loadData() {
        loadSanPhamData();
        loadSizeData();
    }

    private void loadSanPhamData() {
        String sql = "SELECT * FROM SanPham ";
        loadTableData(sql, tblSanPham, new String[]{"ID_SanPham", "TenSP", "Gia"});
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNhanVien = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txttienKhachTra = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtghichu = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnHuydon = new javax.swing.JButton();
        txttienThoi = new javax.swing.JLabel();

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
        tblSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSizeMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSize);

        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        add(jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(1100, 354));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Size", "Giá Upsize"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setText("ID nhân viên:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setText("Tiền thừa:");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setText("..................");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Tổng Tiền:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("Tiền khách trả:");

        txttienKhachTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttienKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienKhachTraKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setText("Tổng SLSP:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setText("Ghi chú:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("Ngày:");

        lblNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNgay.setText("......");

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

        txttienThoi.setText(".........");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(26, 26, 26)
                .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(468, 468, 468)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(txttienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTongTien))))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtghichu))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txttienKhachTra))
                                .addContainerGap(44, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(txttienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttienThoi))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTongTien)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        add(jPanel2);
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

    private void btnHuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuydonActionPerformed
        // TODO add your handling code here: 
        // Lấy chỉ số của hàng được chọn trong jTable1
        int selectedRow = jTable1.getSelectedRow();

        // Kiểm tra nếu có hàng nào được chọn
        if (selectedRow != -1) {
            // Xóa hàng được chọn khỏi mô hình bảng
            modelJTable1.removeRow(selectedRow);

            // Cập nhật tổng số lượng và tổng tiền sau khi xóa hàng
            updateTotalQuantity();
            updateTotalAmount();

            // Thông báo cho người dùng biết hàng đã được xóa
            JOptionPane.showMessageDialog(this, "Hàng đã được xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Thông báo nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnHuydonActionPerformed

    private ThanhToan thanhToanFrame;

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        // Kiểm tra xem ID nhân viên đã được nhập chưa
        String ghiChu = txtghichu.getText().trim();
        String idNhanVien = txtNhanVien.getText().trim();

        // Kiểm tra nếu ID nhân viên trống
        if (idNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập ID nhân viên trước khi thanh toán hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng thực hiện nếu chưa nhập ID nhân viên
        }
        if (isAllFieldsFilled()) {
            try {
                thanhToanFrame = new ThanhToan();

                StringBuilder sanPhamBuilder = new StringBuilder();
                StringBuilder tienHangBuilder = new StringBuilder();
                double tongTien = 0.0;
                int tongSoLuong = 0;

                int rowCount = modelJTable1.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String tenSanPham = modelJTable1.getValueAt(i, jTable1.getColumn("Tên sản phẩm").getModelIndex()).toString();
                    String size = modelJTable1.getValueAt(i, jTable1.getColumn("Size").getModelIndex()).toString();
                    String gia = modelJTable1.getValueAt(i, jTable1.getColumn("Giá").getModelIndex()).toString();
                    String soLuong = modelJTable1.getValueAt(i, jTable1.getColumn("Số lượng").getModelIndex()).toString();

                    sanPhamBuilder.append(tenSanPham).append(" ").append(size).append("<br>");
                    tienHangBuilder.append(gia).append("<br>");

                    tongTien += Double.parseDouble(gia);
                    tongSoLuong += Integer.parseInt(soLuong);
                }

                String soLuongFromTxt = txtSoluong.getText().trim();
                int soLuong = 0;
                try {
                    soLuong = Integer.parseInt(soLuongFromTxt);
                } catch (NumberFormatException e) {
                    soLuong = 0;
                }

                thanhToanFrame.getLblSanPham().setText("<html>" + sanPhamBuilder.toString() + "</html>");
                thanhToanFrame.getLblTienHang().setText("<html>" + tienHangBuilder.toString() + "</html>");
                thanhToanFrame.getLblTienHang1().setText("<html>" + tienHangBuilder.toString() + "</html>");
                thanhToanFrame.getLblThanhtoan().setText(lblTongTien.getText());
                thanhToanFrame.getLblhhoadon().setText(generateInvoiceNumber()); // Gán số hóa đơn mới
                thanhToanFrame.getLblThoiGian().setText(lblNgay.getText());
                thanhToanFrame.getjLabel10().setText(txtNhanVien.getText());
                thanhToanFrame.getLblsoluong().setText(String.valueOf(soLuong));
                thanhToanFrame.getLblGhichu().setText(txtghichu.getText());

                thanhToanFrame.setVisible(true);

                // Sau khi thanh toán thành công, đóng cửa sổ thanh toán và xóa dữ liệu
                thanhToanFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        clearTableData(); // Xóa dữ liệu trong bảng jTable1
                        clearInputFields(); // Xóa dữ liệu trên các trường nhập liệu
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi hiển thị giao diện thanh toán: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void clearInputFields() {
        txtSoluong.setText("");
        txttienKhachTra.setText("");
        txttienThoi.setText("");
        txtghichu.setText("");
        lblTongTien.setText("");
        txtNhanVien.setText("");

        // Xóa dữ liệu các trường nhập liệu khác nếu cần
    }

    private boolean isAllFieldsFilled() {
        // Add checks for each required field. Here is an example:
        return !txttienKhachTra.getText().trim().isEmpty()
                && !lblTongTien.getText().trim().isEmpty()
                && !txttienKhachTra.getText().trim().isEmpty();
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

    private void mnSuaSlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaSlActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_mnSuaSlActionPerformed

    private void mnSuaThongtinkhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSuaThongtinkhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnSuaThongtinkhachActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSizeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSizeMouseClicked

    private void txttienKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienKhachTraKeyReleased
        // Lấy tổng tiền từ lblTongTien
        double tongTien = 0.0;
        try {
            tongTien = Double.parseDouble(lblTongTien.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi, đặt tổng tiền về 0
            tongTien = 0.0;
        }

        // Lấy tiền khách trả từ txttienKhachTra
        double tienKhachTra = 0.0;
        try {
            tienKhachTra = Double.parseDouble(txttienKhachTra.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi, đặt tiền khách trả về 0
            tienKhachTra = 0.0;
        }

        // Tính tiền thừa
        double tienThoi = tienKhachTra - tongTien;

        // Hiển thị tiền thừa vào txttienThoi
        txttienThoi.setText(String.format("%.2f", tienThoi));
    }//GEN-LAST:event_txttienKhachTraKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuydon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JMenuItem btnxoaHDchitiet;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblTongTien;
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
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtghichu;
    private javax.swing.JTextField txttienKhachTra;
    private javax.swing.JLabel txttienThoi;
    // End of variables declaration//GEN-END:variables

}
