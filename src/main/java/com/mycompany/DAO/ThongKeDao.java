package com.mycompany.DAO;

import com.mycompany.Helper.ConnectUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ThongKeDao {

    // SQL Queries
    private final String sql_doanhthutong_ngay_label = "SELECT SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND h.TTthanhtoan = 1 AND d.TTThanhtoan = 1 AND DAY(Ngaytao) = DAY(GETDATE())";
    private final String sql_tongdonban_ngay_label = "SELECT COUNT(*) AS soluong FROM HoaDon WHERE Trangthai = 1 AND TTThanhtoan = 1 AND DAY(Ngaytao) = DAY(GETDATE())";
    private final String sql_tongdonban_thang_label = "SELECT COUNT(*) AS soluong FROM HoaDon WHERE Trangthai = 1 AND TTThanhtoan = 1 AND MONTH(Ngaytao) = MONTH(GETDATE())";
    private final String sql_tongdonban_nam_label = "SELECT COUNT(*) AS soluong FROM HoaDon WHERE Trangthai = 1 AND TTThanhtoan = 1 AND YEAR(Ngaytao) = YEAR(GETDATE())";
    private final String sql_tongspban_nam_label = "SELECT SUM(h.Soluong) AS soluong FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 AND YEAR(Ngaytao) = YEAR(GETDATE())";
    private final String sql_tongspban_thang_label = "SELECT SUM(h.Soluong) AS soluong FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 AND MONTH(Ngaytao) = MONTH(GETDATE())";
    private final String sql_tongspban_ngay_label = "SELECT SUM(h.Soluong) AS soluong FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 AND DAY(Ngaytao) = DAY(GETDATE())";
    private final String sql_doanhthutong_thang_label = "SELECT SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND h.TTthanhtoan = 1 AND d.TTThanhtoan = 1 AND MONTH(Ngaytao) = MONTH(GETDATE())";
    private final String sql_doanhthutong_nam_label = "SELECT SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND h.TTthanhtoan = 1 AND d.TTThanhtoan = 1 AND YEAR(Ngaytao) = YEAR(GETDATE())";
    private final String sql_sosanh_thang_label = "SELECT SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND h.TTthanhtoan = 1 AND d.TTThanhtoan = 1 AND MONTH(Ngaytao) = MONTH(GETDATE()) - 1";
    private final String sql_sosanh_ngay_label = "SELECT SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND h.TTthanhtoan = 1 AND d.TTThanhtoan = 1 AND DAY(Ngaytao) = DAY(GETDATE()) - 1";
    private final String sql_sosanh_nam_label = "SELECT SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND h.TTthanhtoan = 1 AND d.TTThanhtoan = 1 AND YEAR(Ngaytao) = YEAR(GETDATE()) - 1";
    private final String sql_doanhthutong_ngay_table = "SELECT TenSP, SUM(h.Soluong) AS 'Số Lượng', SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 AND DAY(Ngaytao) = DAY(GETDATE()) GROUP BY TenSP";
    private final String sql_doanhthutong_Thang_table = "SELECT TenSP, SUM(h.Soluong) AS 'Số Lượng', SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 AND MONTH(Ngaytao) = MONTH(GETDATE()) GROUP BY TenSP";
    private final String sql_doanhthutong_nam_table = "SELECT TenSP, SUM(h.Soluong) AS 'Số Lượng', SUM(h.TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 AND YEAR(Ngaytao) = YEAR(GETDATE()) GROUP BY TenSP";
    private final String sql_doanhthunam = "SELECT TenSP, COUNT(h.ID_SanPham) AS 'Số Lượng', SUM(TongGia) AS 'Tổng Tiền1' FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE YEAR(d.Ngaytao) = ? AND h.TTthanhtoan = 1 GROUP BY TenSP";
    private final String sql_donhuy_ngay = "SELECT ID_Nhanvien, COUNT(ID_Nhanvien) AS soluong FROM HoaDon WHERE Trangthai = 0 AND DAY(Ngaytao) = DAY(GETDATE()) GROUP BY ID_Nhanvien";
    private final String sql_donhuy_thang = "SELECT ID_Nhanvien, COUNT(ID_Nhanvien) AS soluong FROM HoaDon WHERE Trangthai = 0 AND MONTH(Ngaytao) = MONTH(GETDATE()) GROUP BY ID_Nhanvien";
    private final String sql_donhuy_nam = "SELECT ID_Nhanvien, COUNT(ID_Nhanvien) AS soluong FROM HoaDon WHERE Trangthai = 0 AND YEAR(Ngaytao) = YEAR(GETDATE()) GROUP BY ID_Nhanvien";
    private final String sql_sphuyNgay = "SELECT SUM(Soluongsanphamhuy) AS soluong FROM HoaDon WHERE ID_Nhanvien = ? AND DAY(Ngaytao) = DAY(GETDATE()) GROUP BY ID_Nhanvien";
    private final String sql_sphuyThang = "SELECT SUM(Soluongsanphamhuy) AS soluong FROM HoaDon WHERE ID_Nhanvien = ? AND MONTH(Ngaytao) = MONTH(GETDATE()) GROUP BY ID_Nhanvien";
    private final String sql_sphuyNam = "SELECT SUM(Soluongsanphamhuy) AS soluong FROM HoaDon WHERE ID_Nhanvien = ? AND YEAR(Ngaytao) = YEAR(GETDATE()) GROUP BY ID_Nhanvien";
    private final String sql_donhuy_sanpham = "SELECT TenSP, SUM(h.Soluong) AS soluong FROM HoaDon d JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham WHERE d.Trangthai = 0 AND DAY(Ngaytao) = DAY(GETDATE()) GROUP BY TenSP";

    // Helper method to execute a query and get a single result
    private double getSingleResult(String sql) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Helper method to execute a query and get a result set
    private ResultSet getResultSet(String sql) {
        ResultSet rs = null;
        try {
            Connection conn = ConnectUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public double getDoanhThuNgay() {
        return getSingleResult(sql_doanhthutong_ngay_label);
    }

    public double getTongDonBanNgay() {
        return getSingleResult(sql_tongdonban_ngay_label);
    }

    public double getTongDonBanThang() {
        return getSingleResult(sql_tongdonban_thang_label);
    }

    public double getTongDonBanNam() {
        return getSingleResult(sql_tongdonban_nam_label);
    }

    public double getTongSPBanNam() {
        return getSingleResult(sql_tongspban_nam_label);
    }

    public double getTongSPBanThang() {
        return getSingleResult(sql_tongspban_thang_label);
    }

    public double getTongSPBanNgay() {
        return getSingleResult(sql_tongspban_ngay_label);
    }

    public double getDoanhThuThang() {
        return getSingleResult(sql_doanhthutong_thang_label);
    }

    public double getDoanhThuNam() {
        return getSingleResult(sql_doanhthutong_nam_label);
    }

    public double getSoSanhThang() {
        return getSingleResult(sql_sosanh_thang_label);
    }

    public double getSoSanhNgay() {
        return getSingleResult(sql_sosanh_ngay_label);
    }

    public double getSoSanhNam() {
        return getSingleResult(sql_sosanh_nam_label);
    }

    public ResultSet getDoanhThuNgayTable() {
        return getResultSet(sql_doanhthutong_ngay_table);
    }

    public ResultSet getDoanhThuThangTable() {
        return getResultSet(sql_doanhthutong_Thang_table);
    }

    public ResultSet getDoanhThuNamTable() {
        return getResultSet(sql_doanhthutong_nam_table);
    }

    public ResultSet getDoanhThuNamTable(int nam) {
        ResultSet rs = null;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_doanhthunam)) {
            pstmt.setInt(1, nam);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public double getSoLuongDonHuyNgay(String idNhanVien) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_donhuy_ngay)) {
            pstmt.setString(1, idNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getDouble("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getSoLuongDonHuyThang(String idNhanVien) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_donhuy_thang)) {
            pstmt.setString(1, idNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getDouble("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getSoLuongDonHuyNam(String idNhanVien) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_donhuy_nam)) {
            pstmt.setString(1, idNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getDouble("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getSoLuongSanPhamHuyNgay(String idNhanVien) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_sphuyNgay)) {
            pstmt.setString(1, idNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getDouble("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getSoLuongSanPhamHuyThang(String idNhanVien) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_sphuyThang)) {
            pstmt.setString(1, idNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getDouble("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getSoLuongSanPhamHuyNam(String idNhanVien) {
        double result = 0;
        try (Connection conn = ConnectUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql_sphuyNam)) {
            pstmt.setString(1, idNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getDouble("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet getDonHuySanPhamNgay() {
        return getResultSet(sql_donhuy_sanpham);
    }

    public ResultSet getDoanhThuTheoKhoangThoiGian(java.sql.Date ngayBD, java.sql.Date ngayKT) {
        String sql = "SELECT TenSP, SUM(h.Soluong) AS 'Số Lượng', SUM(h.TongGia) AS 'Tổng Tiền1' "
                + "FROM HoaDon d "
                + "JOIN HoaDonChiTiet h ON h.ID_HoaDon = d.ID_Hoadon "
                + "JOIN SanPham s ON h.ID_SanPham = s.ID_Sanpham "
                + "WHERE d.Trangthai = 1 AND d.TTThanhtoan = 1 AND h.TTthanhtoan = 1 "
                + "AND d.Ngaytao BETWEEN ? AND ? "
                + "GROUP BY TenSP";
        try {
            Connection conn = ConnectUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, ngayBD);
            pstmt.setDate(2, ngayKT);
            return pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
