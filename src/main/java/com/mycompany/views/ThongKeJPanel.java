/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.views;

import com.mycompany.Helper.ConnectUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ASUS
 */
public class ThongKeJPanel extends javax.swing.JPanel {

    private JButton btnOpenEmailForm;

    /**
     * Creates new form ThongKeJPanel
     */
    public ThongKeJPanel() {
        initComponents();
        loadDataToTable();
        calculateTotalRevenue();
        calculateTotalQuantities();
        setupEmailButton();
    }

    private void setupEmailButton() {
        btnOpenEmailForm = new JButton("Open Email Form");
        btnOpenEmailForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmailForm();
            }
        });
        this.add(btnOpenEmailForm);
        btnbieudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBieuDoForm();
            }
        });
    }

    private void showBieuDoForm() {
        // Lấy dữ liệu từ bảng tblbang để tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultTableModel modelDaBan = (DefaultTableModel) tblbang.getModel();

        for (int i = 0; i < modelDaBan.getRowCount(); i++) {
            String tenSanPham = modelDaBan.getValueAt(i, 0).toString();
            int soLuongDaBan = (int) modelDaBan.getValueAt(i, 1);
            dataset.addValue(soLuongDaBan, "Số lượng đã bán", tenSanPham);
        }
        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống Kê Sản Phẩm Đã Bán",
                "Tên Sản Phẩm",
                "Số Lượng",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        // Hiển thị biểu đồ trong JFame BieuDo
        BieuDo bieuDoForm = new BieuDo();
        bieuDoForm.setContentPane(chartPanel);
        bieuDoForm.pack();
        // Đảm bảo biểu đồ hiển thị phía trước
        bieuDoForm.setAlwaysOnTop(true);
        bieuDoForm.setVisible(true);
        bieuDoForm.toFront(); // Đưa biểu đồ lên phía trước
    }

    private void showEmailForm() {
        EmailForm emailForm = new EmailForm();
        emailForm.setVisible(true); // Hiển thị JFrame
    }

    private void loadDataToTable() {
        try {
            DefaultTableModel modelDaBan = (DefaultTableModel) tblbang.getModel();
            modelDaBan.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

            // Câu lệnh SQL để lấy dữ liệu từ các bảng SanPham, HoaDonChiTiet, và HoaDon
            String sql = "SELECT sp.TenSP AS TenSanPham, "
                    + "SUM(hdct.Soluong) AS SoLuongDaBan, "
                    + "SUM(hdct.TongGia) AS TongTien, "
                    + "hd.Ngaytao AS NgayThanhToan "
                    + "FROM HoaDonChiTiet hdct "
                    + "JOIN HoaDon hd ON hdct.ID_HoaDon = hd.ID_Hoadon "
                    + "JOIN SanPham sp ON hdct.ID_SanPham = sp.ID_SanPham "
                    + "GROUP BY sp.TenSP, hd.Ngaytao";

            PreparedStatement pstmt = ConnectUtil.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Thêm dữ liệu vào bảng
                Object[] rowDaBan = {
                    rs.getString("TenSanPham"),
                    rs.getInt("SoLuongDaBan"),
                    rs.getInt("TongTien"),
                    rs.getDate("NgayThanhToan")
                };
                modelDaBan.addRow(rowDaBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateTotalRevenue() {
        int totalRevenue = 0;
        DefaultTableModel modelDaBan = (DefaultTableModel) tblbang.getModel();
        for (int i = 0; i < modelDaBan.getRowCount(); i++) {
            totalRevenue += (int) modelDaBan.getValueAt(i, 2); // Cột thứ 3 là TongTienDaBan
        }
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedTotalRevenue = currencyFormat.format(totalRevenue).replace("₫", "VND");
        lblDoanhthu.setText(formattedTotalRevenue);
    }

    private void calculateTotalQuantities() {
        int totalSoldQuantity = 0;
        int totalCancelledQuantity = 0;

        DefaultTableModel modelDaBan = (DefaultTableModel) tblbang.getModel();
        for (int i = 0; i < modelDaBan.getRowCount(); i++) {
            totalSoldQuantity += (int) modelDaBan.getValueAt(i, 1); // Cột thứ 2 là SoLuongDaBan
        }

        lblDoanhthu1.setText(String.valueOf(totalSoldQuantity));
        lblsltheongay.setText(String.valueOf(totalCancelledQuantity));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        lbldoanhthutext = new javax.swing.JLabel();
        lblDoanhthu = new javax.swing.JLabel();
        lblsosanh = new javax.swing.JLabel();
        lbltheongay = new javax.swing.JLabel();
        lblDoanhthu1 = new javax.swing.JLabel();
        lbldoanhthutext1 = new javax.swing.JLabel();
        lblsltheongay = new javax.swing.JLabel();
        lbldoanhthutext2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnIn = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnbieudo = new javax.swing.JButton();
        DateTuNgay = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DateDenNgay = new com.toedter.calendar.JDateChooser();
        btntinhtong = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbang = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(255, 193, 75));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Doanh thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        lbldoanhthutext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldoanhthutext.setForeground(new java.awt.Color(255, 255, 255));
        lbldoanhthutext.setText("Doanh thu");

        lblDoanhthu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDoanhthu.setText("00000000000000");

        lblsosanh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblsosanh.setForeground(new java.awt.Color(255, 255, 255));
        lblsosanh.setText("Doanh thu theo ngày");

        lbltheongay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltheongay.setText("00000000000000");

        lblDoanhthu1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDoanhthu1.setText("00000000000000");

        lbldoanhthutext1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldoanhthutext1.setForeground(new java.awt.Color(255, 255, 255));
        lbldoanhthutext1.setText("Tổng số lượng đã bán");

        lblsltheongay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblsltheongay.setText("00000000000000");

        lbldoanhthutext2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldoanhthutext2.setForeground(new java.awt.Color(255, 255, 255));
        lbldoanhthutext2.setText("Tổng số lượng theo ngày");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbldoanhthutext, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldoanhthutext1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhthu1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsosanh, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltheongay)
                    .addComponent(lblsltheongay, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldoanhthutext2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldoanhthutext)
                    .addComponent(lblsosanh))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoanhthu)
                    .addComponent(lbltheongay))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbldoanhthutext1)
                        .addGap(15, 15, 15)
                        .addComponent(lblDoanhthu1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbldoanhthutext2)
                        .addGap(15, 15, 15)
                        .addComponent(lblsltheongay)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 193, 75));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        btnIn.setText("Xuất Excel");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("tìm kiếm(theo tên)");

        btnbieudo.setText("Biểu đồ");

        jLabel2.setText("từ ngày:");

        jLabel3.setText("đến ngày:");

        btntinhtong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntinhtong.setText("Tính");
        btntinhtong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntinhtongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnbieudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(62, 62, 62))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(DateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(btntinhtong)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btntinhtong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbieudo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel1.setBackground(new java.awt.Color(241, 241, 241));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu Sản Phẩm:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng Đã Bán", "Tổng Tiền", "Ngày Thanh Toán"
            }
        ));
        jScrollPane2.setViewportView(tblbang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportToExcel(File file) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheetBang = workbook.createSheet("Doanh thu sản phẩm");
        Sheet sheetPanel = workbook.createSheet("Thông tin khác");

        // Xuất dữ liệu từ tblbang
        exportTableToSheet(tblbang, sheetBang);

        // Xuất dữ liệu từ jPanel4
        exportPanelToSheet(jPanel4, sheetPanel);

        // Lưu file Excel vào máy
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            workbook.close();
            javax.swing.JOptionPane.showMessageDialog(this, "Xuất dữ liệu ra Excel thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất dữ liệu ra Excel.");
        }
    }

    private void exportTableToSheet(javax.swing.JTable table, Sheet sheet) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Workbook workbook = sheet.getWorkbook();

        // Tạo tiêu đề cột
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            headerRow.createCell(i).setCellValue(model.getColumnName(i));
        }

        // Tạo kiểu định dạng số
        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        numberCellStyle.setDataFormat(format.getFormat("#,##0")); // Định dạng số

        // Tạo dữ liệu bảng
        for (int i = 0; i < model.getRowCount(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                Object value = model.getValueAt(i, j);
                if (value instanceof Number) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(((Number) value).doubleValue());
                    cell.setCellStyle(numberCellStyle);
                } else {
                    row.createCell(j).setCellValue(value.toString());
                }
            }
        }
        for (int i = 0; i < model.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void exportPanelToSheet(JPanel panel, Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();
        int rowIndex = 0;

        for (java.awt.Component component : panel.getComponents()) {
            if (component instanceof javax.swing.JLabel) {
                javax.swing.JLabel label = (javax.swing.JLabel) component;
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(label.getText());
            } else if (component instanceof javax.swing.JTextField) {
                javax.swing.JTextField textField = (javax.swing.JTextField) component;
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(textField.getText());
            }
            // Thêm điều kiện khác nếu cần
        }
    }


    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel files", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave + ".xlsx");
            }
            exportToExcel(fileToSave);
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void filterTableByProductCode(String productCode) {
        DefaultTableModel model = (DefaultTableModel) tblbang.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tblbang.setRowSorter(tr);

        // Sử dụng RowFilter để lọc theo mã sản phẩm (ID_Sanpham)
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + productCode, 0)); // Cột 0 là cột chứa mã sản phẩm
    }

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        // TODO add your handling code here:
        String searchText = txttimkiem.getText().trim();
        filterTableByProductCode(searchText);
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void btntinhtongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntinhtongActionPerformed
        // TODO add your handling code here:
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tuNgay = sdf.format(DateTuNgay.getDate());
            String denNgay = sdf.format(DateDenNgay.getDate());

            String sql = "SELECT SUM(hdct.TongGia) AS TongTien, "
                    + "SUM(hdct.Soluong) AS TongSoLuong "
                    + "FROM HoaDonChiTiet hdct "
                    + "JOIN HoaDon hd ON hdct.ID_HoaDon = hd.ID_Hoadon "
                    + "WHERE hd.Ngaytao BETWEEN ? AND ?";

            PreparedStatement pstmt = ConnectUtil.getConnection().prepareStatement(sql);
            pstmt.setString(1, tuNgay);
            pstmt.setString(2, denNgay);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int tongTien = rs.getInt("TongTien");
                int tongSoLuong = rs.getInt("TongSoLuong");

                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String formattedTotalRevenue = currencyFormat.format(tongTien).replace("₫", "VND");

                lbltheongay.setText(formattedTotalRevenue);
                lblsltheongay.setText(String.valueOf(tongSoLuong));
            } else {
                lbltheongay.setText("0 VND");
                lblsltheongay.setText("0");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btntinhtongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.toedter.calendar.JDateChooser DateDenNgay;
    public static com.toedter.calendar.JDateChooser DateTuNgay;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnbieudo;
    private javax.swing.JButton btntinhtong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDoanhthu;
    private javax.swing.JLabel lblDoanhthu1;
    private javax.swing.JLabel lbldoanhthutext;
    private javax.swing.JLabel lbldoanhthutext1;
    private javax.swing.JLabel lbldoanhthutext2;
    private javax.swing.JLabel lblsltheongay;
    private javax.swing.JLabel lblsosanh;
    private javax.swing.JLabel lbltheongay;
    private javax.swing.JTable tblbang;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
