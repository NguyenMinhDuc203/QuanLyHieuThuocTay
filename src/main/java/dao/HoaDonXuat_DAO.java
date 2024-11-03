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

    public static void main(String[] args) {
        HoaDonXuat_DAO dao = new HoaDonXuat_DAO();
        List<Object[]> danhSachDoanhSo = dao.thongKeDoanhSo();

        if (danhSachDoanhSo == null || danhSachDoanhSo.isEmpty()) {
            System.out.println("Không có dữ liệu doanh số nào.");
            return;
        }

        System.out.println("Mã hóa đơn\tMã nhân viên\tMã khách hàng\tNgày mua\t\tTổng tiền");
        System.out.println("---------------------------------------------------------------");

        for (Object[] doanhSo : danhSachDoanhSo) {
            String maHoaDon = (String) doanhSo[0];
            String maNhanVien = (String) doanhSo[1];
            String maKhachHang = (String) doanhSo[2];
            LocalDate ngayMua = (LocalDate) doanhSo[3];
            Double tongTien = (Double) doanhSo[4];

            System.out.printf("%s\t%s\t%s\t%s\t%.2f%n", maHoaDon, maNhanVien, maKhachHang, ngayMua, tongTien);
        }
    }
}
