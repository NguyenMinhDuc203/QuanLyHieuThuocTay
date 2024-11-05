package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

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
    public String maTuSinh() {
        EntityManager em = emf.createEntityManager();
        String maKhachHang = null;

        try {
            // Truy vấn để lấy mã khách hàng lớn nhất
            String jpql = "SELECT MAX(kh.maKhachHang) FROM KhachHang kh";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            String maxMaKhachHang = query.getSingleResult();

            if (maxMaKhachHang != null && maxMaKhachHang.startsWith("KH")) {
                try {
                    int nextId = Integer.parseInt(maxMaKhachHang.substring(2)) + 1; // Bỏ qua "KH"
                    maKhachHang = String.format("KH%03d", nextId); // Định dạng lại thành "KH001", "KH002", ...
                } catch (NumberFormatException ex) {
                    System.err.println("Định dạng mã khách hàng không hợp lệ: " + maxMaKhachHang);
                    maKhachHang = "KH001"; // Trường hợp lỗi, bắt đầu lại từ "KH001"
                }
            } else {
                maKhachHang = "KH001"; // Nếu chưa có khách hàng nào, bắt đầu từ "KH001"
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tự sinh mã khách hàng: " + e.getMessage());
            e.printStackTrace();
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
    public boolean update(KhachHang kh) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isUpdated = false; // Đặt cờ cho việc cập nhật thành công

        try {
            entityManager.getTransaction().begin(); // Bắt đầu giao dịch
            KhachHang existingKhachHang = entityManager.find(KhachHang.class, kh.getMaKhachHang());
            
            if (existingKhachHang != null) {
                // Cập nhật thông tin cho khách hàng
                existingKhachHang.setTenKhachHang((String) kh.getTenKhachHang());
                existingKhachHang.getSDT();
                
                entityManager.merge(existingKhachHang); // Kết hợp thay đổi
                isUpdated = true; // Đánh dấu là đã cập nhật thành công
            }

            entityManager.getTransaction().commit(); // Cam kết giao dịch
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            e.printStackTrace(); // In ra lỗi
        } finally {
            entityManager.close(); // Đảm bảo EntityManager được đóng
        }

        return isUpdated; // Trả về true nếu cập nhật thành công
    }
//

    public boolean saveKhachHang(KhachHang kh) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isSaved = false; // Đặt cờ cho việc lưu thành công

        try {
            entityManager.getTransaction().begin(); // Bắt đầu giao dịch
            entityManager.persist(kh); // Lưu đối tượng KhachHang
            entityManager.getTransaction().commit(); // Cam kết giao dịch
            isSaved = true; // Đánh dấu là đã lưu thành công
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            e.printStackTrace(); // In ra lỗi
        } finally {
            entityManager.close(); // Đảm bảo EntityManager được đóng
        }

        return isSaved; // Trả về true nếu lưu thành công
    }
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
}
