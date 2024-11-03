package dao;

import entity.HoaDonXuat;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class HoaDonXuat_DAO {
    private EntityManagerFactory emf;

    public HoaDonXuat_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }

    // Phương thức thêm mới hóa đơn
    public void addHoaDon(HoaDonXuat hoaDon) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hoaDon);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Phương thức cập nhật hóa đơn
    public void updateHoaDon(HoaDonXuat hoaDon) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(hoaDon);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Phương thức xóa hóa đơn theo mã
    public void deleteHoaDon(String maHoaDon) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            HoaDonXuat hoaDon = em.find(HoaDonXuat.class, maHoaDon);
            if (hoaDon != null) {
                em.remove(hoaDon);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Phương thức tìm kiếm hóa đơn theo mã
    public HoaDonXuat findHoaDonById(String maHoaDon) {
        EntityManager em = emf.createEntityManager();
        HoaDonXuat hoaDon = null;

        try {
            hoaDon = em.find(HoaDonXuat.class, maHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return hoaDon;
    }

    // Phương thức lấy danh sách doanh số
    public List<Object[]> thongKeDoanhSo() {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = """
                SELECT hdx.maHoaDonXuat, 
                       nv.maNhanVien, 
                       kh.maKhachHang, 
                       hdx.ngayTao, 
                       SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT))
                FROM HoaDonXuat hdx
                JOIN hdx.khachHang kh
                JOIN hdx.nhanVien nv
                JOIN ChiTietHoaDon cthd ON cthd.hoaDonXuat = hdx
                JOIN cthd.sanPham sp
                GROUP BY hdx.maHoaDonXuat, nv.maNhanVien, kh.maKhachHang, hdx.ngayTao
            """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            result = query.getResultList();

            if (result.isEmpty()) {
                System.out.println("Không có kết quả nào trả về từ truy vấn.");
            } else {
                System.out.println("Truy vấn thành công, số lượng kết quả: " + result.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }
    
 // Thống kê doanh số theo ngày
    public List<Object[]> thongKeDoanhSoTheoNgay(LocalDate ngay) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = """
                SELECT hdx.maHoaDonXuat, 
                       nv.maNhanVien, 
                       kh.maKhachHang, 
                       hdx.ngayTao, 
                       SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT))
                FROM HoaDonXuat hdx
                JOIN hdx.khachHang kh
                JOIN hdx.nhanVien nv
                JOIN ChiTietHoaDon cthd ON cthd.hoaDonXuat = hdx
                JOIN cthd.sanPham sp
                WHERE hdx.ngayTao = :ngay
                GROUP BY hdx.maHoaDonXuat, nv.maNhanVien, kh.maKhachHang, hdx.ngayTao
            """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("ngay", ngay);

            result = query.getResultList();

            if (result.isEmpty()) {
                System.out.println("Không có kết quả nào trả về từ truy vấn theo ngày.");
            } else {
                System.out.println("Truy vấn thành công, số lượng kết quả theo ngày: " + result.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    
 // Thống kê doanh số theo tháng
    public List<Object[]> thongKeDoanhSoTheoThang(LocalDate ngay) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> result = null;

        try {
            String jpql = """
                SELECT hdx.maHoaDonXuat, 
                       nv.maNhanVien, 
                       kh.maKhachHang, 
                       hdx.ngayTao, 
                       SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT))
                FROM HoaDonXuat hdx
                JOIN hdx.khachHang kh
                JOIN hdx.nhanVien nv
                JOIN ChiTietHoaDon cthd ON cthd.hoaDonXuat = hdx
                JOIN cthd.sanPham sp
                WHERE FUNCTION('MONTH', hdx.ngayTao) = :thang
                  AND FUNCTION('YEAR', hdx.ngayTao) = :nam
                GROUP BY hdx.maHoaDonXuat, nv.maNhanVien, kh.maKhachHang, hdx.ngayTao
            """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("thang", ngay.getMonthValue());
            query.setParameter("nam", ngay.getYear());

            result = query.getResultList();

            if (result.isEmpty()) {
                System.out.println("Không có kết quả nào trả về từ truy vấn theo tháng.");
            } else {
                System.out.println("Truy vấn thành công, số lượng kết quả theo tháng: " + result.size());
            }
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

    // Phương thức main để thử nghiệm
    public static void main(String[] args) {
        HoaDonXuat_DAO dao = new HoaDonXuat_DAO();
        
        // Ví dụ: Tạo mới hóa đơn
        HoaDonXuat hoaDonMoi = new HoaDonXuat();
//        hoaDonMoi.setMaHoaDonXuat("HDX12345");
//        hoaDonMoi.setNgayTao(LocalDate.now());
        // Set thêm các thuộc tính cần thiết cho hóa đơn và các thực thể liên quan
        dao.addHoaDon(hoaDonMoi);

        // Thống kê doanh số theo ngày 3/11/2024
        List<Object[]> resultsNgay = dao.thongKeDoanhSoTheoNgay(LocalDate.of(2024, 10, 3));
        System.out.println("Thống kê doanh số theo ngày 3/10/2024:");
        for (Object[] row : resultsNgay) {
            System.out.println(row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4]);
        }

        // Thống kê doanh số theo tháng 11/2024
        List<Object[]> resultsThang = dao.thongKeDoanhSoTheoThang(LocalDate.of(2024, 11, 1));
        System.out.println("Thống kê doanh số theo tháng 11/2024:");
        for (Object[] row : resultsThang) {
            System.out.println(row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4]);
        }
    }
}
