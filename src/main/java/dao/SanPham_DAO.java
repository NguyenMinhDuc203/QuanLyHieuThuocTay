package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

import entity.SanPham;
import jakarta.persistence.Query;
import java.lang.reflect.Field;



public class SanPham_DAO {
    private static final Logger LOGGER = Logger.getLogger(SanPham_DAO.class.getName());
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
    public void updateSanPham(SanPham sp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(sp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

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


    // save sp
    public boolean saveSanPham(List<SanPham> danhSachSanPham) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isSaved = false;
        
        // Xóa tất cả các sản phẩm cũ (nếu cần)
        clearAllSanPham();

        try {
            entityManager.getTransaction().begin();

            // Duyệt qua từng sản phẩm và lưu vào cơ sở dữ liệu
            for (SanPham sp : danhSachSanPham) {
                try {
                    // Sử dụng Reflection để kiểm tra maSanPham
                    Field maSanPhamField = SanPham.class.getDeclaredField("maSanPham"); // Truy cập trường maSanPham
                    maSanPhamField.setAccessible(true); // Cho phép truy cập trường private

                    // Kiểm tra giá trị của maSanPham
                    Object maSanPham = maSanPhamField.get(sp);
                    
                    // Nếu maSanPham là null, sử dụng persist, nếu không dùng merge
                    if (maSanPham == null) {
                        entityManager.persist(sp); // Lưu sản phẩm mới
                    } else {
                        entityManager.merge(sp); // Cập nhật sản phẩm đã tồn tại
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    System.err.println("Lỗi khi truy xuất trường maSanPham: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // Commit giao dịch
            entityManager.getTransaction().commit();
            System.out.println("Giao dịch thành công!");

            isSaved = true;
        } catch (Exception e) {
            // Rollback nếu có lỗi xảy ra
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


}
    

