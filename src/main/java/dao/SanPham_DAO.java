package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.System.Logger;
import java.sql.Connection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import entity.SanPham;
import jakarta.persistence.Query;
import java.lang.reflect.Field;

    
public class SanPham_DAO {
    private EntityManagerFactory emf;
    private Connection connection;

    public SanPham_DAO(Connection connection) {
        this.connection = connection;
    }
    public SanPham_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }
    public List<String[]> laySanPhamDaMua(String maKhachHang) {
        List<String[]> danhSachSanPham = new ArrayList<>();
        try {
            String sql = "SELECT sp.maSanPham, sp.tenSanPham, cthd.soLuong, hd.ngayTao " +
                         "FROM chitiethoadon cthd " +
                         "JOIN hoadonxuat hd ON cthd.maHoaDonXuat = hd.maHoaDonXuat " +
                         "JOIN sanpham sp ON cthd.maSanPham = sp.maSanPham " +
                         "WHERE hd.maKhachHang = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maKhachHang);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String[] sanPham = {
                    rs.getString("maSanPham"),
                    rs.getString("tenSanPham"),
                    rs.getString("soLuong"),
                    rs.getString("ngayTao")
                };
                danhSachSanPham.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachSanPham;
    }

   
     
    // Bổ sung phương thức tìm kiếm sản phẩm
    public List<String[]> timKiemSanPham(String maSanPham, String tenSanPham, String loaiSanPham) {
        List<String[]> danhSachSanPham = new ArrayList<>();
        String query = "SELECT maSanPham, tenSanPham, giaNhap, giaBan, soLuongTonkho FROM sanpham WHERE 1=1";

        if (maSanPham != null && !maSanPham.isEmpty()) {
            query += " AND maSanPham LIKE ?";
        }
        if (tenSanPham != null && !tenSanPham.isEmpty()) {
            query += " AND tenSanPham LIKE ?";
        }
        if (loaiSanPham != null && !loaiSanPham.equals("Tất Cả")) {
            query += " AND maLoaiSanPham = ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int index = 1;
            if (maSanPham != null && !maSanPham.isEmpty()) {
                stmt.setString(index++, "%" + maSanPham + "%");
            }
            if (tenSanPham != null && !tenSanPham.isEmpty()) {
                stmt.setString(index++, "%" + tenSanPham + "%");
            }
            if (loaiSanPham != null && !loaiSanPham.equals("Tất Cả")) {
                stmt.setString(index, loaiSanPham);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                danhSachSanPham.add(new String[]{
                    rs.getString("maSanPham"),
                    rs.getString("tenSanPham"),
                    rs.getString("giaNhap"),
                    rs.getString("giaBan"),
                    rs.getString("soLuongTonkho")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachSanPham;
    }

    // Bổ sung phương thức lấy chi tiết sản phẩm
    public String[] layChiTietSanPham(String maSanPham) {
        String query = "SELECT * FROM sanpham WHERE maSanPham = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maSanPham);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[]{
                    rs.getString("maSanPham"), rs.getString("tenSanPham"), rs.getString("giaNhap"),
                    rs.getString("giaBan"), rs.getString("soLuongTonkho"), rs.getString("congDung"),
                    rs.getString("thanhPhan"), rs.getString("baoQuan"), rs.getString("chongChiDinh"),
                    rs.getString("ngaySanXuat"), rs.getString("hanSuDung"), rs.getString("nhaSanXuat"),
                    rs.getString("thueGTGT"), rs.getString("ghiChu")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức lấy tất cả sản phẩm
    public List<SanPham> getAllSanPhams() {
        EntityManager em = emf.createEntityManager();
        List<SanPham> sanPhams = null;

        try {
            TypedQuery<SanPham> query = em.createQuery("SELECT sp FROM SanPham sp", SanPham.class);
            sanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return sanPhams;
    }

    // Phương thức tìm kiếm sản phẩm theo mã
    public SanPham findSanPhamById(String maSanPham) {
        EntityManager em = emf.createEntityManager();
        SanPham sanPham = null;

        try {
            String jpql = "SELECT sp FROM SanPham sp WHERE sp.maSanPham = :maSanPham";
            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
            query.setParameter("maSanPham", maSanPham);
            sanPham = query.getResultStream().findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return sanPham;
    }

    // Phương thức tìm kiếm sản phẩm theo tên
    public List<SanPham> findSanPhamByName(String tenSanPham) {
        EntityManager em = emf.createEntityManager();
        List<SanPham> result = null;

        try {
            String jpql = "SELECT sp FROM SanPham sp WHERE sp.tenSanPham LIKE :ten";
            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
            query.setParameter("ten", "%" + tenSanPham + "%");
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Phương thức thêm sản phẩm
    public boolean addSanPham(SanPham sp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sp);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return false;
    }

    // Phương thức cập nhật sản phẩm
//    public void updateSanPham(SanPham sp) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.merge(sp);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }

    // Phương thức xóa sản phẩm theo ID
    public void deleteSanPham(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            SanPham sp = em.find(SanPham.class, id);
            if (sp != null) {
                em.remove(sp);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Thống kê sản phẩm theo mã loại
    public List<SanPham> thongKeSanPhamTheoLoaiMa(String maLoai) {
        EntityManager em = emf.createEntityManager();
        List<SanPham> result = new ArrayList<>();

        try {
            String jpql = "SELECT sp FROM SanPham sp JOIN FETCH sp.loaiSanPham WHERE sp.loaiSanPham.maLoai = :maLoai";
            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
            query.setParameter("maLoai", maLoai);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }
    // Hàm lấy danh sách sản phẩm theo mã giảm giá từ hóa đơn
    public ArrayList<Object[]> layDanhSachSanPhamTheoMaGiamGia(String maGiamGia) {
    	ArrayList<Object[]> sanPhamList = new ArrayList<>();
        
        // Câu truy vấn SQL lấy mã sản phẩm và tên sản phẩm dựa trên mã giảm giá
        String query = "SELECT sp.maSanPham, sp.tenSanPham " +
                       "FROM SanPham sp " +
                       "WHERE hdx.maGiamGia = ?";  // Điều kiện tìm sản phẩm theo mã giảm giá

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set mã giảm giá vào câu lệnh SQL
            stmt.setString(1, maGiamGia);
            
            // Thực thi truy vấn và nhận kết quả
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua kết quả trả về và thêm từng sản phẩm vào danh sách
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                sanPhamList.add(new String[]{maSanPham, tenSanPham});  // Thêm sản phẩm vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }

        // Trả về danh sách các sản phẩm tìm được
        return sanPhamList;
    }
    // Tính số lượng đã bán cho sản phẩm
    public int tinhSoLuongDaBan(String maSanPham) {
        EntityManager em = emf.createEntityManager();
        int totalSold = 0;

        try {
            String jpql = "SELECT SUM(cth.soLuong) FROM ChiTietHoaDon cth WHERE cth.sanPham.maSanPham = :maSanPham";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("maSanPham", maSanPham);

            Long result = query.getSingleResult();
            totalSold = (result != null) ? result.intValue() : 0;
        } catch (NoResultException e) {
            totalSold = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return totalSold;
    }

    // Thống kê sản phẩm theo số lượng bán ra
    public List<Object[]> thongKeSanPhamTheoSoLuongBan() {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = new ArrayList<>();

        try {
            String jpql = """
                SELECT sp.maSanPham, sp.tenSanPham, sp.loaiSanPham.tenLoai, 
                       SUM(cth.soLuong) AS soLuongBanRa, sp.soLuongTonkho 
                FROM ChiTietHoaDon cth 
                JOIN cth.sanPham sp 
                GROUP BY sp.maSanPham, sp.tenSanPham, sp.loaiSanPham.tenLoai, sp.soLuongTonkho 
                ORDER BY soLuongBanRa DESC
            """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Phương thức thống kê sản phẩm đã quá hạn sử dụng
    public List<SanPham> thongKeSanPhamDaQuaHan() {
        EntityManager em = emf.createEntityManager();
        List<SanPham> result = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        try {
            String jpql = "SELECT sp FROM SanPham sp WHERE sp.hanSuDung < :currentDate";
            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
            query.setParameter("currentDate", currentDate);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Đóng EntityManagerFactory
    public void close() {
        if (emf != null) emf.close();
        
    }
    


    
// // Phương thức thống kê sản phẩm đã quá hạn sử dụng
//    public List<SanPham> thongKeSanPhamDaQuaHan() {
//        EntityManager em = emf.createEntityManager();
//        List<SanPham> result = new ArrayList<>();
//        LocalDate currentDate = LocalDate.now(); // Sử dụng LocalDate để lấy ngày hiện tại
//
//        try {
//            String jpql = "SELECT sp FROM SanPham sp WHERE sp.hanSuDung < :currentDate";
//            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
//            query.setParameter("currentDate", currentDate);
//            result = query.getResultList();
//
//            if (result.isEmpty()) {
//                System.out.println("Không có sản phẩm nào đã quá hạn sử dụng.");
//            } else {
//                System.out.println("Truy vấn thành công, số lượng sản phẩm đã quá hạn: " + result.size());
//                System.out.printf("%-10s %-20s %-15s %-15s %-15s%n", "Mã", "Tên", "Loại", "Hạn sử dụng", "Số lượng tồn kho");
//                System.out.println("-----------------------------------------------------------------------");
//                for (SanPham sp : result) {
//                    System.out.printf("%-10s %-20s %-15s %-15s %-15d%n", 
//                        sp.getMaSanPham(), 
//                        sp.getTenSanPham(), 
//                        sp.getLoaiSanPham().getTenLoai(),
//                        sp.getHanSuDung(),
//                        sp.getSoLuongTonkho());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // In ra lỗi nếu có
//        } finally {
//            em.close();
//        }
//
//        return result;
//    }

    public SanPham getSanPhamByMaSanPham(String maSanPham) {
    	EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT s FROM SanPham s WHERE s.maSanPham = :maSanPham";
            return em.createQuery(query, SanPham.class)
                     .setParameter("maSanPham", maSanPham)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;  // Trả về null nếu không tìm thấy sản phẩm
        }
    }

    public boolean saveSanPham(SanPham sanPham) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isSaved = true;

        try {
            // Bắt đầu giao dịch
            entityManager.getTransaction().begin();

            // Gọi phương thức addSanPham1 để thêm sản phẩm vào cơ sở dữ liệu
            boolean result = addSanPham1(sanPham);
            if (!result) {
                // Nếu có lỗi khi lưu sản phẩm, set isSaved = false
                isSaved = false;
            }

            // Commit giao dịch nếu sản phẩm đã được lưu thành công
            if (isSaved) {
                entityManager.getTransaction().commit();
                System.out.println("Giao dịch thành công!");
            }

        } catch (Exception e) {
            // Nếu có lỗi, rollback giao dịch
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return isSaved;
    }

// xóa toàn bộ trong csdl
    public boolean clearAllSanPham() {
        EntityManager entityManager = emf.createEntityManager();
        boolean isCleared = false;

        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

            String jpql = "DELETE FROM SanPham";
            Query query = entityManager.createQuery(jpql);
            query.executeUpdate();

            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
            entityManager.getTransaction().commit();
            isCleared = true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return isCleared;
    }


    //
    public SanPham findSanPhamWithLoaiSanPham(String maSP) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT sp FROM SanPham sp JOIN FETCH sp.loaiSanPham WHERE sp.maSanPham = :maSP", SanPham.class)
                     .setParameter("maSP", maSP)
                     .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
   
    public boolean addSanPham1(SanPham sp) {
        EntityManager entityManager = emf.createEntityManager();
        boolean result = false;

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sp);
            entityManager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }
    //Thêm
	  public boolean createSanPham(SanPham nv) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();
	        boolean isSuccess = false;
	        try {
	            transaction.begin(); // Bắt đầu giao dịch
	            em.persist(nv); // Thêm đối tượng NhanVien vào cơ sở dữ liệu
	            transaction.commit(); // Cam kết giao dịch
	            isSuccess = true; // Đánh dấu thành công
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback(); // Hoàn tác giao dịch nếu có lỗi
	            }
	            System.err.println("Lỗi khi thêm sản phẩm trong createNhanVien: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            em.close(); // Đóng EntityManager
	        }
	        return isSuccess;
	    }
	 // xóa 
	  public boolean deleteSanPham(String maSanPham) {
		    EntityManager em = emf.createEntityManager(); // emf là đối tượng EntityManagerFactory
		    boolean isDeleted = false;

		    try {
		        em.getTransaction().begin();

		        // Xóa sản phẩm khỏi bảng SanPham
		        int deletedCount = em.createQuery("DELETE FROM SanPham sp WHERE sp.maSanPham = :maSanPham")
		                             .setParameter("maSanPham", maSanPham)
		                             .executeUpdate();

		        if (deletedCount > 0) {
		            isDeleted = true; // Xóa thành công
		        } else {
		            System.out.println("Không tìm thấy sản phẩm với mã: " + maSanPham);
		        }

		        em.getTransaction().commit();
		    } catch (Exception e) {
		        if (em.getTransaction().isActive()) {
		            em.getTransaction().rollback(); // Khôi phục giao dịch nếu có lỗi
		        }
		        e.printStackTrace();
		    } finally {
		        em.close(); // Đóng EntityManager
		    }

		    return isDeleted; // Trả về kết quả
		}
	  
//	  public void updateSanPham(SanPham sp) {
//	        EntityManager em = emf.createEntityManager();
//	        try {
//	            em.getTransaction().begin();
//	            em.merge(sp);
//	            em.getTransaction().commit();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            em.getTransaction().rollback();
//	        } finally {
//	            em.close();
//	        }
//	    }
//sửa
	  public boolean updateSanPham(SanPham sanPham) {
		    EntityManager em = emf.createEntityManager();
		    boolean isUpdated = false;

		    try {
		        em.getTransaction().begin();

		        // Truy vấn JPQL để cập nhật thông tin sản phẩm dựa vào maSanPham
		        int updatedCount = em.createQuery(
		                "UPDATE SanPham sp SET sp.tenSanPham = :tenSanPham, sp.giaBan = :giaBan, sp.congDung = :congDung, " +
		                "sp.hanSuDung = :hanSuDung, sp.baoQuan = :baoQuan, sp.chongChiDinh = :chongChiDinh, " +
		                "sp.ngaySanXuat = :ngaySanXuat, sp.thanhPhan = :thanhPhan, sp.soLuongTonkho = :soLuongTonkho, " +
		                "sp.ghiChu = :ghiChu, sp.nhaSanXuat = :nhaSanXuat, sp.donViTinh = :donViTinh, " +
		                "sp.thueGTGT = :thueGTGT, sp.giaNhap = :giaNhap, sp.loaiSanPham = :loaiSanPham, " +
		                "sp.hoaDonNhap = :hoaDonNhap WHERE sp.maSanPham = :maSanPham")
		            .setParameter("tenSanPham", sanPham.getTenSanPham())
		            .setParameter("giaBan", sanPham.getGiaBan())
		            .setParameter("congDung", sanPham.getCongDung())
		            .setParameter("hanSuDung", sanPham.getHanSuDung())
		            .setParameter("baoQuan", sanPham.getBaoQuan())
		            .setParameter("chongChiDinh", sanPham.getChongChiDinh())
		            .setParameter("ngaySanXuat", sanPham.getNgaySanXuat())
		            .setParameter("thanhPhan", sanPham.getThanhPhan())
		            .setParameter("soLuongTonkho", sanPham.getSoLuongTonkho())
		            .setParameter("ghiChu", sanPham.getGhiChu())
		            .setParameter("nhaSanXuat", sanPham.getNhaSanXuat())
		            .setParameter("donViTinh", sanPham.getDonViTinh()) // Enum hoặc String
		            .setParameter("thueGTGT", sanPham.getThueGTGT())
		            .setParameter("giaNhap", sanPham.getGiaNhap())
		            .setParameter("loaiSanPham", sanPham.getLoaiSanPham()) // Entity quan hệ ManyToOne
		            .setParameter("hoaDonNhap", sanPham.getHoaDonNhap())   // Entity quan hệ ManyToOne
		            .setParameter("maSanPham", sanPham.getMaSanPham())
		            .executeUpdate();

		        em.getTransaction().commit();

		        isUpdated = updatedCount > 0; // Kiểm tra xem có bản ghi nào được cập nhật không
		    } catch (Exception e) {
		        if (em.getTransaction().isActive()) {
		            em.getTransaction().rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        em.close();
		    }

		    return isUpdated;
		}
	  public List<SanPham> findSanPhamByPartialId(String partialId) {
		    EntityManager em = emf.createEntityManager();
		    try {
		        String queryStr = "SELECT sp FROM SanPham sp WHERE sp.maSanPham LIKE :partialId";
		        TypedQuery<SanPham> query = em.createQuery(queryStr, SanPham.class);
		        query.setParameter("partialId", partialId + "%"); // Tìm kiếm với từ khóa bắt đầu bằng partialId
		        List<SanPham> resultList = query.getResultList();
		        return resultList != null ? resultList : new ArrayList<>(); // Trả về danh sách trống nếu không có kết quả
		    } finally {
		        em.close();
		    }
		}
	  public String maTuSinhSanPham(String loaiSanPham) {
		    EntityManager em = emf.createEntityManager();
		    String maSanPham = null;

		    try {
		        // Đếm số lượng sản phẩm hiện tại trong loại sản phẩm
		        String jpql = "SELECT COUNT(sp) FROM SanPham sp WHERE sp.loaiSanPham = :loaiSanPham";
		        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		        query.setParameter("loaiSanPham", loaiSanPham);
		        Long count = query.getSingleResult();

		        // Tạo 18 số ngẫu nhiên
		        Random random = new Random();
		        StringBuilder soNgauNhien = new StringBuilder();
		        for (int i = 0; i < 18; i++) {
		            soNgauNhien.append(random.nextInt(10));
		        }

		        // Lấy mã loại sản phẩm là chữ cái đầu tiên của loaiSanPham viết hoa
		        String maLoaiSanPham = loaiSanPham.substring(0, 1).toUpperCase();

		        // Tạo mã sản phẩm
		        maSanPham = String.format("SP%04d%s%s%04d", 
		            (count.intValue() + 1) * 10, // 4 số đầu
		            maLoaiSanPham, // Mã loại sản phẩm
		            soNgauNhien.toString(), // 18 số ngẫu nhiên
		            (count.intValue() + 1)); // 4 số cuối

		    } catch (Exception e) {
		        System.err.println("Lỗi khi tự sinh mã sản phẩm: " + e.getMessage());
		        e.printStackTrace();
		        maSanPham = "SP0001"; // Bắt đầu lại từ "SP0001" nếu có lỗi
		    } finally {
		        em.close();
		    }

		    return maSanPham;
		}
<<<<<<< HEAD
=======

>>>>>>> 1bd206c4b2918ffccdcbc2388bd48674f8f06b1d

    
}
 
