package dao;

import java.util.List;


import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ThongKeKhachHang_DAO {
    private EntityManagerFactory emf;

    public ThongKeKhachHang_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay"); 
    }

   
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
                          "WHERE MONTH(hd.ngayTao) = :thang AND YEAR(hd.ngayTao) = :nam " + // Thêm điều kiện theo tháng và năm
                          "GROUP BY kh.maKhachHang, kh.tenKhachHang";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("thang", thang); // Thiết lập tham số tháng
            query.setParameter("nam", nam); // Thiết lập tham số năm
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
        ThongKeKhachHang_DAO dao = new ThongKeKhachHang_DAO();
        
        int thang = 10; // Ví dụ tháng 11
        int nam = 2024; // Ví dụ năm 2024
        List<Object[]> danhSachKhachHang = dao.danhSachTongTienVaSoLanMuaHangCuaTatCaKhachHang(thang, nam);

        if (danhSachKhachHang == null || danhSachKhachHang.isEmpty()) {
            System.out.println("Không có dữ liệu khách hàng nào.");
            return;
        }

        System.out.println("Mã khách hàng\tTên khách hàng\tSố lần mua\tTổng tiền");
        System.out.println("---------------------------------------------------------------");

        for (Object[] khachHang : danhSachKhachHang) {
            String maKhachHang = (String) khachHang[0];
            String tenKhachHang = (String) khachHang[1];
            Long soLanMua = (Long) khachHang[2];
            Double tongTien = (Double) khachHang[3];

            System.out.printf("%s\t%s\t\t%d\t\t%.2f\n", maKhachHang, tenKhachHang, soLanMua, tongTien);
        }
    }


}
