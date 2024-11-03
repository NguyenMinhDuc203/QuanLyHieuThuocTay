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

    // Đóng EntityManagerFactory
    public void close() {
        if (emf != null) emf.close();
    }
}
