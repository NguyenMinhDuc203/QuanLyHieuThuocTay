package dao;

import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import entity.MaGiamGia;
public class MaGiamGia_DAO {
	private EntityManagerFactory emf;
	public MaGiamGia_DAO() {
         emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }
	// Lấy danh sách mã giảm giá còn hiệu lực
    public ArrayList<MaGiamGia> getDanhSachMaGiamGiaConHieuLuc() {
        EntityManager em = emf.createEntityManager();
        try {
            // Câu lệnh JPQL để lấy mã giảm giá còn hiệu lực
            String query = "SELECT m FROM MaGiamGia m WHERE m.ngayHetHan > :today";
            Query q = em.createQuery(query, MaGiamGia.class);
            q.setParameter("today", java.time.LocalDate.now()); // Ngày hiện tại
            return (ArrayList<MaGiamGia>) q.getResultList(); // Trả về danh sách mã giảm giá hợp lệ
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy kết quả
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
        }
    }
 // Lấy danh sách mã giảm giá còn hiệu lực, tìm kiếm chứa từ khóa
    public ArrayList<MaGiamGia> getDanhSachMaGiamGiaTheoMa(String maGG) {
        EntityManager em = emf.createEntityManager();
        try {
            // Sử dụng LIKE để tìm kiếm mã giảm giá chứa từ khóa
            String query = "SELECT m FROM MaGiamGia m WHERE m.maGiamGia LIKE :maGG";
            Query q = em.createQuery(query, MaGiamGia.class);
            q.setParameter("maGG", "%" + maGG + "%"); // Thêm ký tự đại diện % ở đầu và cuối từ khóa tìm kiếm
            return (ArrayList<MaGiamGia>) q.getResultList(); // Trả về danh sách mã giảm giá chứa từ khóa tìm kiếm
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy kết quả
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
        }
    }
    public MaGiamGia getMaGiamGiaTheoMa(String maGiamGia) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT m FROM MaGiamGia m WHERE m.maGiamGia = :maGiamGia";
            Query q = em.createQuery(query, MaGiamGia.class);
            q.setParameter("maGiamGia", maGiamGia);

            // Lấy kết quả đầu tiên từ danh sách, nếu có
            return (MaGiamGia) q.getSingleResult();  // Dùng getSingleResult nếu chỉ muốn lấy một kết quả
        } catch (NoResultException e) {
            return null; // Trả về null nếu không có kết quả
        } finally {
            em.close();
        }
    }

    public ArrayList<MaGiamGia> getDanhSachMaGiamGiaTheoLoai(String loaiGG) {
        EntityManager em = emf.createEntityManager();
        try {
            // Sử dụng LIKE để tìm kiếm mã giảm giá theo loại, nếu loại không phải "Loại"
            String query = "SELECT m FROM MaGiamGia m WHERE m.loaiGiamGia LIKE :loaiGG";
            Query q = em.createQuery(query, MaGiamGia.class);
            q.setParameter("loaiGG", "%" + loaiGG + "%"); // Thêm ký tự đại diện % ở đầu và cuối từ khóa tìm kiếm
            return (ArrayList<MaGiamGia>) q.getResultList(); // Trả về danh sách mã giảm giá theo loại tìm kiếm
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy kết quả
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
        }
    }

    public static String getLoaiGiamGiaByMa(String maGiamGia) {
        if (maGiamGia == null || maGiamGia.isEmpty()) {
            return "Mã giảm giá không hợp lệ";
        }

        // Kiểm tra tiền tố của mã giảm giá
        if (maGiamGia.startsWith("GGCH")) {
            return "Tiền";  // Nếu mã bắt đầu với "GGCH", loại giảm giá là Tiền
        } else if (maGiamGia.startsWith("GGTL")) {
            return "Phần trăm";  // Nếu mã bắt đầu với "GGTL", loại giảm giá là Phần trăm
        } else {
            return "Loại giảm giá không xác định";  // Nếu mã không phải là "GGCH" hoặc "GGTL", trả về thông báo lỗi
        }
    }
    public static String getLoaiGiamGiaByMa2(String maGiamGia) {
        if (maGiamGia == null || maGiamGia.isEmpty()) {
            return "Mã giảm giá không hợp lệ";
        }

        // Kiểm tra xem mã giảm giá có chứa "SP" không
        if (maGiamGia.contains("SP")) {
            return "Sản phẩm";  // Nếu mã giảm giá chứa "SP", trả về "Sản phẩm"
        }
        
        // Kiểm tra xem mã giảm giá có chứa "HD" không
        else if (maGiamGia.contains("HD")) {
            return "HD";  // Nếu mã giảm giá chứa "HD", trả về "HD"
        }

        // Kiểm tra tiền tố của mã giảm giá
        else if (maGiamGia.startsWith("GGCH")) {
            return "Tiền";  // Nếu mã bắt đầu với "GGCH", loại giảm giá là Tiền
        } else if (maGiamGia.startsWith("GGTL")) {
            return "Phần trăm";  // Nếu mã bắt đầu với "GGTL", loại giảm giá là Phần trăm
        } else {
            return "Loại giảm giá không xác định";  // Nếu mã không phải là "GGCH" hoặc "GGTL", trả về thông báo lỗi
        }
    }

    
	 }
