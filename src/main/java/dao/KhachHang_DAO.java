package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class KhachHang_DAO {
    private EntityManagerFactory emf;
    public KhachHang_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }
    private Connection connection;

    public KhachHang_DAO(Connection connection) {
        this.connection = connection;
    }

    public List<String[]> timKiemKhachHang(String maKhachHang, String tenKhachHang, String sdt) {
        List<String[]> danhSachKhachHang = new ArrayList<>();
        try {
            String sql = "SELECT maKhachHang, tenKhachHang, sDT FROM khachhang WHERE 1=1";
            if (!maKhachHang.isEmpty()) {
                sql += " AND maKhachHang LIKE ?";
            }
            if (!tenKhachHang.isEmpty()) {
                sql += " AND tenKhachHang LIKE ?";
            }
            if (!sdt.isEmpty()) {
                sql += " AND sDT LIKE ?";
            }
            PreparedStatement statement = connection.prepareStatement(sql);

            int index = 1;
            if (!maKhachHang.isEmpty()) {
                statement.setString(index++, "%" + maKhachHang + "%");
            }
            if (!tenKhachHang.isEmpty()) {
                statement.setString(index++, "%" + tenKhachHang + "%");
            }
            if (!sdt.isEmpty()) {
                statement.setString(index++, "%" + sdt + "%");
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String[] khachHang = {
                    rs.getString("maKhachHang"),
                    rs.getString("tenKhachHang"),
                    rs.getString("sDT")
                };
                danhSachKhachHang.add(khachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
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
   

    public String maTuSinhKhachHang(String sdtKH) {
        EntityManager em = emf.createEntityManager();
        String maKhachHang = "KH" + sdtKH;

        try {
            // Kiểm tra xem mã khách hàng với số điện thoại đã tồn tại chưa
            String jpql = "SELECT COUNT(kh) FROM KhachHang kh WHERE kh.maKhachHang = :maKhachHang";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("maKhachHang", maKhachHang);
            long count = query.getSingleResult();

            // Nếu mã khách hàng đã tồn tại, thêm hậu tố số ngẫu nhiên để tránh trùng lặp
            if (count > 0) {
                Random random = new Random();
                maKhachHang = maKhachHang + random.nextInt(100); // Thêm số ngẫu nhiên từ 0 đến 99
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi tự sinh mã khách hàng: " + e.getMessage());
            e.printStackTrace();
            maKhachHang = "KH" + sdtKH + "01"; // Trong trường hợp lỗi, thêm hậu tố mặc định
        } finally {
            em.close();
        }

        return maKhachHang;
    }



//
    public boolean delete(String maKhachHang) {
        EntityManager em = emf.createEntityManager();
        boolean isDeleted = false;

        try {
            em.getTransaction().begin();

            // Xóa các bản ghi trong bảng chitiethoadon liên quan đến maHoaDonXuat của khách hàng
            em.createQuery("DELETE FROM ChiTietHoaDon ct WHERE ct.hoaDonXuat.khachHang.maKhachHang = :maKhachHang")
              .setParameter("maKhachHang", maKhachHang)
              .executeUpdate();

            // Xóa các hóa đơn xuất liên quan đến khách hàng
            em.createQuery("DELETE FROM HoaDonXuat hd WHERE hd.khachHang.maKhachHang = :maKhachHang")
              .setParameter("maKhachHang", maKhachHang)
              .executeUpdate();

            // Xóa khách hàng khỏi bảng KhachHang
            int deletedCount = em.createQuery("DELETE FROM KhachHang kh WHERE kh.maKhachHang = :maKhachHang")
                                 .setParameter("maKhachHang", maKhachHang)
                                 .executeUpdate();

            if (deletedCount > 0) {
                isDeleted = true; // Xóa thành công
            } else {
                System.out.println("Không tìm thấy khách hàng với mã: " + maKhachHang);
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



//
    public boolean updateKhachHang(String maKhachHang, String tenKhachHang, String sDT, int diemTichLuy) {
        EntityManager em = emf.createEntityManager();
        boolean isUpdated = false;

        try {
            em.getTransaction().begin();
            
            // Truy vấn JPQL để cập nhật thông tin khách hàng dựa vào maKhachHang
            int updatedCount = em.createQuery(
                    "UPDATE KhachHang kh SET kh.tenKhachHang = :tenKhachHang, kh.sDT = :sDT, kh.diemTichLuy = :diemTichLuy WHERE kh.maKhachHang = :maKhachHang")
                .setParameter("tenKhachHang", tenKhachHang)
                .setParameter("sDT", sDT)
                .setParameter("diemTichLuy", diemTichLuy)
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
 // Phương thức tìm kiếm khách hàng theo số điện thoại
    public boolean kiemTraKHTonTai(String soDienThoai) {
        EntityManager em = emf.createEntityManager();
        boolean result = false;

        try {
            String jpql = "SELECT kh FROM KhachHang kh WHERE kh.sDT = :phone";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("phone", soDienThoai);

            // Kiểm tra xem kết quả trả về có rỗng không
            if (!query.getResultList().isEmpty()) {
                result = true;  // Nếu danh sách không rỗng, có khách hàng tồn tại
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;  // Trả về true nếu khách hàng tồn tại, false nếu không
    }
    public int layDiemTichLuyTheoSDT(String soDienThoai) {
        EntityManager em = emf.createEntityManager();
        int diemTichLuy = 0;  // Giá trị mặc định khi không tìm thấy khách hàng

        try {
            // Truy vấn JPQL để lấy điểm tích lũy (kiểu int)
            String jpql = "SELECT kh.diemTichLuy FROM KhachHang kh WHERE kh.sDT = :phone";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            query.setParameter("phone", soDienThoai);

            // Lấy kết quả điểm tích lũy
            diemTichLuy = query.getSingleResult();  // Kết quả sẽ là một giá trị kiểu int

        } catch (NoResultException e) {
            // Nếu không tìm thấy khách hàng, trả về 0
            diemTichLuy = 0;
        } catch (Exception e) {
            e.printStackTrace();
            diemTichLuy = 0;  // Mặc định trả về 0 nếu có lỗi
        } finally {
            em.close();
        }

        return diemTichLuy;  // Trả về điểm tích lũy của khách hàng
    }
    public KhachHang layKhachHangTheoSDT(String soDienThoai) {
        EntityManager em = emf.createEntityManager();
        KhachHang khachHang = null;  // Biến mặc định khi không tìm thấy khách hàng

        try {
            // Truy vấn JPQL để lấy khách hàng theo số điện thoại
            String jpql = "SELECT kh FROM KhachHang kh WHERE kh.sDT = :phone";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("phone", soDienThoai);

            // Lấy kết quả khách hàng
            khachHang = query.getSingleResult();  // Kết quả là đối tượng KhachHang

        } catch (NoResultException e) {
            // Nếu không tìm thấy khách hàng, trả về null
            khachHang = null;
        } catch (Exception e) {
            e.printStackTrace();
            khachHang = null;  // Mặc định trả về null nếu có lỗi
        } finally {
            em.close();
        }

        return khachHang;  // Trả về đối tượng KhachHang hoặc null nếu không tìm thấy
    }
    public KhachHang layKhachHangTheoMa(String maKH) {
        EntityManager em = emf.createEntityManager();
        KhachHang khachHang = null;  // Biến mặc định khi không tìm thấy khách hàng

        try {
            // Truy vấn JPQL để lấy khách hàng theo số điện thoại
            String jpql = "SELECT kh FROM KhachHang kh WHERE kh.maKhachHang = :maKH";
            TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
            query.setParameter("maKH", maKH);

            // Lấy kết quả khách hàng
            khachHang = query.getSingleResult();  // Kết quả là đối tượng KhachHang

        } catch (NoResultException e) {
            // Nếu không tìm thấy khách hàng, trả về null
            khachHang = null;
        } catch (Exception e) {
            e.printStackTrace();
            khachHang = null;  // Mặc định trả về null nếu có lỗi
        } finally {
            em.close();
        }

        return khachHang;  // Trả về đối tượng KhachHang hoặc null nếu không tìm thấy
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
        
   
    public String layTenKhachHangByMa(String maKhachHang) {
        EntityManager em = emf.createEntityManager();
        String tenKhachHang = null;

        try {
            // Sử dụng HQL để truy vấn tên khách hàng từ mã khách hàng
            String jpql = "SELECT k.tenKhachHang FROM KhachHang k WHERE k.maKhachHang = :maKhachHang";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("maKhachHang", maKhachHang);

            // Lấy kết quả
            tenKhachHang = query.getSingleResult();
        } catch (NoResultException e) {
            // Nếu không tìm thấy, trả về null hoặc xử lý lỗi theo cách khác
            System.out.println("Không tìm thấy khách hàng với mã: " + maKhachHang);
        } catch (Exception e) {
            // Xử lý các lỗi khác nếu có
            e.printStackTrace();
        } finally {
            em.close();
        }

        return tenKhachHang;
    }
    ///////////
    public List<KhachHang> findKhachHangByPartialId(String partialId) {
        EntityManager em = emf.createEntityManager();
        try {
            String queryStr = "SELECT kh FROM KhachHang kh WHERE kh.maKhachHang LIKE :partialId";
            TypedQuery<KhachHang> query = em.createQuery(queryStr, KhachHang.class);
            query.setParameter("partialId", partialId + "%");
            List<KhachHang> resultList = query.getResultList();
            return resultList != null ? resultList : new ArrayList<>();
        } finally {
            em.close();
        }
    }

    
}