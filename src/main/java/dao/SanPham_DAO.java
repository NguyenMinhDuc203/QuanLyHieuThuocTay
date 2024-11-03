package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO {
    private EntityManagerFactory emf;

    public SanPham_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
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
    public SanPham findSanPhamById(int maSanPham) {
        EntityManager em = emf.createEntityManager();
        SanPham sanPham = null;

        try {
            sanPham = em.find(SanPham.class, maSanPham);
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
    public void addSanPham(SanPham sp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
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

    // Đóng EntityManagerFactory
    public void close() {
        if (emf != null) emf.close();
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

            // Xử lý kết quả nếu cần
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
            String jpql = """
                SELECT SUM(cth.soLuong) 
                FROM ChiTietHoaDon cth 
                WHERE cth.sanPham.maSanPham = :maSanPham
            """;

            // Sử dụng TypedQuery<Long> để nhận kết quả đúng kiểu
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("maSanPham", maSanPham);

            // Lấy kết quả và chuyển đổi
            Long result = query.getSingleResult();
            totalSold = (result != null) ? result.intValue() : 0; // Chuyển Long thành int
        } catch (NoResultException e) {
            // Nếu không có kết quả, số lượng bán ra là 0
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

            if (result.isEmpty()) {
                System.out.println("Không có sản phẩm nào đã được bán.");
            } else {
                System.out.println("Truy vấn thành công, số lượng sản phẩm đã bán ra: " + result.size());
                System.out.printf("%-15s %-30s %-20s %-15s %-15s%n", "Mã", "Tên", "Loại", "SL Bán Ra", "SL Tồn Kho");
                System.out.println("-----------------------------------------------------------------------");
                for (Object[] row : result) {
                    System.out.printf("%-15s %-30s %-20s %-15d %-15d%n", 
                        row[0], row[1], row[2], (Long) row[3], (Integer) row[4]);
                }
            }
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
        LocalDate currentDate = LocalDate.now(); // Sử dụng LocalDate để lấy ngày hiện tại

        try {
            String jpql = "SELECT sp FROM SanPham sp WHERE sp.hanSuDung < :currentDate";
            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
            query.setParameter("currentDate", currentDate);
            result = query.getResultList();

            if (result.isEmpty()) {
                System.out.println("Không có sản phẩm nào đã quá hạn sử dụng.");
            } else {
                System.out.println("Truy vấn thành công, số lượng sản phẩm đã quá hạn: " + result.size());
                System.out.printf("%-10s %-20s %-15s %-15s %-15s%n", "Mã", "Tên", "Loại", "Hạn sử dụng", "Số lượng tồn kho");
                System.out.println("-----------------------------------------------------------------------");
                for (SanPham sp : result) {
                    System.out.printf("%-10s %-20s %-15s %-15s %-15d%n", 
                        sp.getMaSanPham(), 
                        sp.getTenSanPham(), 
                        sp.getLoaiSanPham().getTenLoai(),
                        sp.getHanSuDung(),
                        sp.getSoLuongTonkho());
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        } finally {
            em.close();
        }

        return result;
    }

    
}