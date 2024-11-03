package dao;

import entity.HoaDonNhap;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDonNhap_DAO {
    private EntityManagerFactory emf;

    public HoaDonNhap_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }

    public ArrayList<Object[]> layDanhSachHoaDon(String searchTerm, String searchType) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Object[]> result = new ArrayList<>();

        try {
            // Bắt đầu xây dựng câu truy vấn SQL
            String sql = "SELECT " +
                         "hdn.maHoaDonNhap, " +
                         "hdn.ngayNhap, " + 
                         "hdn.maNhaPhanPhoi, " +
                         "ROUND(SUM(hdn.soLuong * sp.giaBan), 2) AS tongTien "+
                         "FROM HoaDonNhap hdn " +
                         "JOIN SanPham sp ON hdn.maHoaDonNhap = sp.maHoaDonNhap " +
                         "WHERE ";

            // Xây dựng điều kiện tìm kiếm dựa trên loại tìm kiếm
            switch (searchType) {
                case "Mã nhà phân phối":
                    sql += "hdn.maNhaPhanPhoi LIKE :searchTerm";
                    break;
                case "Mã hóa đơn":
                    sql += "hdn.maHoaDonNhap LIKE :searchTerm";
                    break;
                default:
                    throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ");
            }

            sql += " GROUP BY " +
            		"hdn.maHoaDonNhap, " +
                    "hdn.maNhaPhanPhoi, " +
                    "hdn.ngayNhap ";  // Sửa lại tên cột ở đây

            // Tạo truy vấn
            Query query = em.createNativeQuery(sql); // Sử dụng createNativeQuery cho SQL
            query.setParameter("searchTerm", "%" + searchTerm + "%"); // Thêm ký tự đại diện cho tìm kiếm chứa

            // Thực hiện truy vấn và lấy kết quả
            result = new ArrayList<>(query.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return result; // Trả về danh sách các hóa đơn tìm thấy
    }


    public ArrayList<Object[]> layDanhSachChiTietSanPhamTheoMaHoaDonNhap(String maHoaDonNhap) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Object[]> result = new ArrayList<>(); // Khởi tạo ArrayList để lưu kết quả

        try {
            // Câu truy vấn để lấy chi tiết sản phẩm dựa trên mã hóa đơn xuất
        	String sql = "SELECT " +
                    "sp.maSanPham, " +
                    "sp.tenSanPham, " +
                    "lsp.tenLoai, " +
                    "hdn.soLuong, " +
                    "sp.giaBan, " +
                    "ROUND(SUM(hdn.soLuong * sp.giaBan), 2) AS thanhTien " +
                    "FROM HoaDonNhap hdn " +
                    "JOIN hdn.sanPham sp " +
                    "JOIN sp.loaiSanPham lsp " + // Sử dụng thuộc tính loaiSanPham
                    "WHERE hdn.maHoaDonNhap LIKE :maHoaDonNhap " +
                    "GROUP BY sp.maSanPham, sp.tenSanPham, lsp.tenLoai, hdn.soLuong, sp.giaBan";




            // Tạo câu truy vấn
            Query query = em.createQuery(sql);
            query.setParameter("maHoaDonNhap", "%" + maHoaDonNhap + "%"); // Sử dụng ký tự đại diện cho tìm kiếm

            // Thực thi câu truy vấn và lấy kết quả
            result = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả thành ArrayList
            System.out.println("Câu truy vấn: " + sql);

            // In kết quả ra console
            if (result.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm cho mã hóa đơn: " + maHoaDonNhap);
            } else {
                for (Object[] row : result) {
                    System.out.println("Mã Sản Phẩm: " + row[0] + 
                                       ", Tên Sản Phẩm: " + row[1] + 
                                       ", Loại Sản Phẩm: " + row[2] + 
                                       ", Số Lượng: " + row[3] + 
                                       ", Giá Nhập: " + row[4] + 
                                       ", Thành Tiền: " + row[5]);
                }
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách chi tiết sản phẩm: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close(); // Đảm bảo EntityManager được đóng
            }
        }

        return result; // Trả về ArrayList chứa thông tin chi tiết sản phẩm
    }

}
