package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import java.lang.reflect.Field;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;



public class KhachHang_DAO {
    private EntityManagerFactory emf;

    public KhachHang_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }
    

    // Phương thức lấy tổng tiền và số lần mua hàng cho từng khách hàng
    public List<Object[]> danhSachTongTienVaSoLanMuaHangCuaTatCaKhachHang(int thang, int nam) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = "SELECT kh.maKhachHang, kh.tenKhachHang, " +
                          "COUNT(DISTINCT hd.maHoaDonXuat) AS soLanMuaHang, " +
                          "SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT)) AS tongTien " +
                          "FROM ChiTietHoaDon cthd " +
                          "JOIN cthd.hoaDonXuat hd " +
                          "JOIN hd.khachHang kh " +
                          "JOIN cthd.sanPham sp " +
                          "WHERE MONTH(hd.ngayTao) = :thang AND YEAR(hd.ngayTao) = :nam " +
                          "GROUP BY kh.maKhachHang, kh.tenKhachHang";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }
    //thêm
    public boolean create(KhachHang e) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        boolean isSuccess = false;

        try {
            transaction.begin(); // Bắt đầu giao dịch
            em.persist(e); // Thêm đối tượng KhachHang vào cơ sở dữ liệu
            transaction.commit(); // Cam kết giao dịch
            isSuccess = true; // Đánh dấu thành công
        } catch (Exception e1) {
            if (transaction.isActive()) {
                transaction.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            System.err.println("Lỗi khi thêm khách hàng trong create: " + e1.getMessage());
            e1.printStackTrace();
        } finally {
            em.close(); // Đóng EntityManager
        }

        return isSuccess;
    }
    //
   

    public String maTuSinhKhachHang() {
        EntityManager em = emf.createEntityManager();
        String maKhachHang = null;
        Random random = new Random();

        try {
            boolean unique = false;

            // Lặp cho đến khi tìm được mã khách hàng ngẫu nhiên không trùng
            while (!unique) {
                // Tạo mã ngẫu nhiên "KH" + 10 chữ số
                long randomId = Math.abs(random.nextLong() % 10000000000L);
                maKhachHang = String.format("KH%010d", randomId);

                // Kiểm tra mã khách hàng ngẫu nhiên có trùng không
                String jpql = "SELECT COUNT(kh) FROM KhachHang kh WHERE kh.maKhachHang = :maKhachHang";
                TypedQuery<Long> query = em.createQuery(jpql, Long.class);
                query.setParameter("maKhachHang", maKhachHang);
                long count = query.getSingleResult();

                // Nếu không trùng, thì chấp nhận mã này
                if (count == 0) {
                    unique = true;
                }
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi tự sinh mã khách hàng: " + e.getMessage());
            e.printStackTrace();
            maKhachHang = "KH0123456789"; // Trong trường hợp lỗi, dùng mã mặc định
        } finally {
            em.close();
        }

        return maKhachHang;
    }


//
    public boolean delete(String maKhachHang) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isDeleted = false;

        try {
            entityManager.getTransaction().begin();
            KhachHang khachHang = entityManager.find(KhachHang.class, maKhachHang);
            if (khachHang != null) {
                entityManager.remove(khachHang);
                isDeleted = true; // Đánh dấu là xóa thành công
            } else {
                System.out.println("Không tìm thấy khách hàng với mã: " + maKhachHang);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Khôi phục nếu có lỗi
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Đảm bảo đóng entity manager
        }

        return isDeleted; // Trả về true nếu đã xóa thành công
    }
//
    public boolean updateKhachHang(String maKhachHang, String tenKhachHang, String sDT) {
        EntityManager em = emf.createEntityManager();
        boolean isUpdated = false;

        try {
            em.getTransaction().begin();
            
            // Truy vấn JPQL để cập nhật thông tin khách hàng
            int updatedCount = em.createQuery(
                    "UPDATE KhachHang kh SET kh.tenKhachHang = :tenKhachHang, kh.sDT = :sDT WHERE kh.maKhachHang = :maKhachHang")
                .setParameter("tenKhachHang", tenKhachHang)
                .setParameter("sDT", sDT)
                .setParameter("maKhachHang", maKhachHang)
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
//
    public boolean saveKhachHang(String maNV, String tenNV, String sdt, int diemTichLuy) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isSaved = false;

        try {
            entityManager.getTransaction().begin(); // Bắt đầu giao dịch

            // Tạo đối tượng KhachHang mà không cần constructor
            KhachHang kh = new KhachHang(); // Tạo đối tượng KhachHang rỗng

            // Sử dụng Reflection để gán giá trị cho các trường của đối tượng KhachHang
            Field maKhachHangField = KhachHang.class.getDeclaredField("maKhachHang");
            maKhachHangField.setAccessible(true);  // Đảm bảo có thể truy cập trường private
            maKhachHangField.set(kh, maNV);

            Field tenKhachHangField = KhachHang.class.getDeclaredField("tenKhachHang");
            tenKhachHangField.setAccessible(true);
            tenKhachHangField.set(kh, tenNV);

            Field sdtField = KhachHang.class.getDeclaredField("sDT");
            sdtField.setAccessible(true);
            sdtField.set(kh, sdt);

            Field diemTichLuyField = KhachHang.class.getDeclaredField("diemTichLuy");
            diemTichLuyField.setAccessible(true);
            diemTichLuyField.set(kh, diemTichLuy);

            // Lưu đối tượng KhachHang vào cơ sở dữ liệu
            entityManager.persist(kh);
            entityManager.getTransaction().commit(); // Cam kết giao dịch
            isSaved = true; // Đánh dấu là đã lưu thành công
        } catch (Exception e) {
            // Hoàn tác giao dịch nếu có lỗi
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // In ra lỗi
        } finally {
            // Đảm bảo EntityManager được đóng
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return isSaved; // Trả về true nếu lưu thành công
    }


//


    // Phương thức tìm kiếm khách hàng theo mã khách hàng
    public KhachHang findKhachHangById(String maKhachHang) {
        EntityManager em = emf.createEntityManager();
        KhachHang khachHang = null;

        try {
            khachHang = em.find(KhachHang.class, maKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return khachHang;
    }

    // Phương thức tìm kiếm khách hàng theo tên (có thể có nhiều khách hàng trùng tên)
    public List<KhachHang> findKhachHangByName(String tenKhachHang) {
        EntityManager em = emf.createEntityManager();
        List<KhachHang> result = null;

        try {
            String jpql = "SELECT kh FROM KhachHang kh WHERE kh.tenKhachHang LIKE :ten";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("ten", "%" + tenKhachHang + "%");
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Phương thức tìm kiếm khách hàng theo số điện thoại
    public List<KhachHang> findKhachHangByPhoneNumber(String soDienThoai) {
        EntityManager em = emf.createEntityManager();
        List<KhachHang> result = null;

        try {
            String jpql = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :phone";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("phone", soDienThoai);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Phương thức tìm kiếm khách hàng theo giới tính
    public List<KhachHang> findKhachHangByGender(String gioiTinh) {
        EntityManager em = emf.createEntityManager();
        List<KhachHang> result = null;

        try {
            String jpql = "SELECT kh FROM KhachHang kh WHERE kh.gioiTinh = :gender";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("gender", gioiTinh);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Phương thức lấy tất cả khách hàng
    public List<KhachHang> getAllKhachHangs() {
        EntityManager em = emf.createEntityManager();
        List<KhachHang> khachHangs = null;

        try {
            TypedQuery<KhachHang> query = em.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class);
            khachHangs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return khachHangs;
    }



 // Thống kê khách hàng có số lần mua = 1 (Khách hàng mới)
    public List<Object[]> thongKeSoLanMuaBang1() {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = """
                SELECT kh.maKhachHang, kh.tenKhachHang, 
                       COUNT(DISTINCT hd.maHoaDonXuat) AS soLanMuaHang,
                       SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT)) AS tongTien
                FROM ChiTietHoaDon cthd 
                JOIN cthd.hoaDonXuat hd 
                JOIN hd.khachHang kh 
                JOIN cthd.sanPham sp 
                GROUP BY kh.maKhachHang, kh.tenKhachHang
                HAVING COUNT(DISTINCT hd.maHoaDonXuat) = 1
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

    // Thống kê khách hàng có số lần mua > 2 và < 5 (Khách hàng quen) 
    public List<Object[]> thongKeSoLanMuaLonHon2NhoHon5() {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = """
                SELECT kh.maKhachHang, kh.tenKhachHang, 
                       COUNT(DISTINCT hd.maHoaDonXuat) AS soLanMuaHang,
                       SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT)) AS tongTien
                FROM ChiTietHoaDon cthd 
                JOIN cthd.hoaDonXuat hd 
                JOIN hd.khachHang kh 
                JOIN cthd.sanPham sp 
                GROUP BY kh.maKhachHang, kh.tenKhachHang
                HAVING COUNT(DISTINCT hd.maHoaDonXuat) >= 2 AND COUNT(DISTINCT hd.maHoaDonXuat) < 5
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

    // Thống kê khách hàng có số lần mua > 5 (Khách hàng thân thiết)
    public List<Object[]> thongKeSoLanMuaLonHon5() {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = """
                SELECT kh.maKhachHang, kh.tenKhachHang, 
                       COUNT(DISTINCT hd.maHoaDonXuat) AS soLanMuaHang,
                       SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT)) AS tongTien
                FROM ChiTietHoaDon cthd 
                JOIN cthd.hoaDonXuat hd 
                JOIN hd.khachHang kh 
                JOIN cthd.sanPham sp 
                GROUP BY kh.maKhachHang, kh.tenKhachHang
                HAVING COUNT(DISTINCT hd.maHoaDonXuat) > 5
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
    
    // Lưu khách hàng vào cơ sở dữ liệu
    public boolean save(KhachHang khachHang) {
        // Tạo EntityManager để thao tác với cơ sở dữ liệu
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();  // Bắt đầu giao dịch

            // Lưu khách hàng vào cơ sở dữ liệu
            em.persist(khachHang);

            transaction.commit();  // Cam kết giao dịch

            return true;  // Nếu thành công, trả về true
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Nếu có lỗi, hoàn tác giao dịch
            }
            e.printStackTrace();  // In ra lỗi
            return false;  // Nếu có lỗi, trả về false
        } finally {
            em.close();  // Đảm bảo EntityManager được đóng sau khi sử dụng
        }
    }
    // Đóng EntityManagerFactory
    public void close() {
        if (emf != null) emf.close();
    }
    
    
    public static void main(String[] args) {
        KhachHang_DAO thongKeDao = new KhachHang_DAO();

        System.out.println("===== Khách hàng có số lần mua = 1 =====");
        List<Object[]> ketQua1 = thongKeDao.thongKeSoLanMuaBang1();
        inKetQua(ketQua1);

        System.out.println("\n===== Khách hàng có số lần mua >= 2 và < 5 =====");
        List<Object[]> ketQua2 = thongKeDao.thongKeSoLanMuaLonHon2NhoHon5();
        inKetQua(ketQua2);

        System.out.println("\n===== Khách hàng có số lần mua > 5 =====");
        List<Object[]> ketQua3 = thongKeDao.thongKeSoLanMuaLonHon5();
        inKetQua(ketQua3);
    }

    private static void inKetQua(List<Object[]> ketQua) {
        if (ketQua != null && !ketQua.isEmpty()) {
            for (Object[] row : ketQua) {
                String maKhachHang = (String) row[0];
                String tenKhachHang = (String) row[1];
                Long soLanMuaHang = (Long) row[2];
                Double tongTien = (Double) row[3];

                System.out.printf("Mã KH: %s, Tên KH: %s, Số lần mua: %d, Tổng tiền: %.2f%n",
                        maKhachHang, tenKhachHang, soLanMuaHang, tongTien);
            }
        } else {
            System.out.println("Không có kết quả nào.");
        }
    }
        
    //xóa toàn bộ dữ iêu csdl
    public boolean clearAllKhachHang() {
        EntityManager entityManager = emf.createEntityManager();
        boolean isCleared = false;

        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

            // Xóa toàn bộ dữ liệu trong bảng KhachHang
            String jpql = "DELETE FROM KhachHang";
            Query query = entityManager.createQuery(jpql);
            query.executeUpdate();

            entityManager.getTransaction().commit();
            isCleared = true; // Đánh dấu là xóa thành công
           // JOptionPane.showMessageDialog(null, "Đã xóa toàn bộ khách hàng thành công!");
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
    
    


}
