package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class KhachHang_DAO {
    private EntityManagerFactory emf;

    public KhachHang_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
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
