package dao;

import entity.HoaDonNhap;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entity.HoaDonNhap;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HoaDonNhap_DAO {
    private EntityManagerFactory emf;

    public HoaDonNhap_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }

    public ArrayList<Object[]> layDanhSachHoaDon(String searchTerm, String searchType) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Object[]> result = new ArrayList<>();

        try {
            // Bắt đầu xây dựng câu truy vấn SQL
            String sql = "SELECT " +
                         "hdn.maHoaDonNhap, " +
                         "hdn.ngayNhap, " + 
                         "hdn.maNhaPhanPhoi, " +
                         "ROUND(SUM(hdn.soLuong * sp.giaBan), 2) AS tongTien "+
                         "FROM HoaDonNhap hdn " +
                         "JOIN SanPham sp ON hdn.maHoaDonNhap = sp.maHoaDonNhap " +
                         "WHERE ";

            // Xây dựng điều kiện tìm kiếm dựa trên loại tìm kiếm
            switch (searchType) {
                case "Mã nhà phân phối":
                    sql += "hdn.maNhaPhanPhoi LIKE :searchTerm";
                    break;
                case "Mã hóa đơn":
                    sql += "hdn.maHoaDonNhap LIKE :searchTerm";
                    break;
                default:
                    throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ");
            }

            sql += " GROUP BY " +
            		"hdn.maHoaDonNhap, " +
                    "hdn.maNhaPhanPhoi, " +
                    "hdn.ngayNhap ";  // Sửa lại tên cột ở đây

            // Tạo truy vấn
            Query query = em.createNativeQuery(sql); // Sử dụng createNativeQuery cho SQL
            query.setParameter("searchTerm", "%" + searchTerm + "%"); // Thêm ký tự đại diện cho tìm kiếm chứa

            // Thực hiện truy vấn và lấy kết quả
            result = new ArrayList<>(query.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return result; // Trả về danh sách các hóa đơn tìm thấy
    }


    public ArrayList<Object[]> layDanhSachChiTietSanPhamTheoMaHoaDonNhap(String maHoaDonNhap) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Object[]> result = new ArrayList<>(); // Khởi tạo ArrayList để lưu kết quả

        try {
            // Câu truy vấn để lấy chi tiết sản phẩm dựa trên mã hóa đơn xuất
        	String sql = "SELECT " +
                    "sp.maSanPham, " +
                    "sp.tenSanPham, " +
                    "lsp.tenLoai, " +
                    "hdn.soLuong, " +
                    "sp.giaBan, " +
                    "ROUND(SUM(hdn.soLuong * sp.giaBan), 2) AS thanhTien " +
                    "FROM HoaDonNhap hdn " +
                    "JOIN hdn.sanPham sp " +
                    "JOIN sp.loaiSanPham lsp " + // Sử dụng thuộc tính loaiSanPham
                    "WHERE hdn.maHoaDonNhap LIKE :maHoaDonNhap " +
                    "GROUP BY sp.maSanPham, sp.tenSanPham, lsp.tenLoai, hdn.soLuong, sp.giaBan";




            // Tạo câu truy vấn
            Query query = em.createQuery(sql);
            query.setParameter("maHoaDonNhap", "%" + maHoaDonNhap + "%"); // Sử dụng ký tự đại diện cho tìm kiếm

            // Thực thi câu truy vấn và lấy kết quả
            result = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả thành ArrayList
            System.out.println("Câu truy vấn: " + sql);

            // In kết quả ra console
            if (result.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm cho mã hóa đơn: " + maHoaDonNhap);
            } else {
                for (Object[] row : result) {
                    System.out.println("Mã Sản Phẩm: " + row[0] + 
                                       ", Tên Sản Phẩm: " + row[1] + 
                                       ", Loại Sản Phẩm: " + row[2] + 
                                       ", Số Lượng: " + row[3] + 
                                       ", Giá Nhập: " + row[4] + 
                                       ", Thành Tiền: " + row[5]);
                }
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách chi tiết sản phẩm: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close(); // Đảm bảo EntityManager được đóng
            }
        }

        return result; // Trả về ArrayList chứa thông tin chi tiết sản phẩm
    }
    public HoaDonNhap findHoaDonNhapByMa(String maHoaDonNhap) {
        EntityManager em = emf.createEntityManager();
        HoaDonNhap hoaDonNhap = null;

        try {
            hoaDonNhap = em.find(HoaDonNhap.class, maHoaDonNhap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return hoaDonNhap;
    }
    // Phương thức lưu HoaDonNhap vào cơ sở dữ liệu
    public boolean save(HoaDonNhap hoaDonNhap) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // Bắt đầu giao dịch
            em.persist(hoaDonNhap); // Lưu hóa đơn nhập vào cơ sở dữ liệu
            em.getTransaction().commit(); // Commit giao dịch
            return true; // Trả về true nếu lưu thành công
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback(); // Rollback giao dịch nếu có lỗi
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi hoàn thành
        }
        return false; // Trả về false nếu có lỗi
    }

    public HoaDonNhap getHoaDonNhapByMa(String maHoaDonNhapMoi) {
        EntityManager em = emf.createEntityManager();
        HoaDonNhap hoaDonNhap = null;

        try {
            // Tìm kiếm HoaDonNhap bằng maHoaDonNhapMoi
            hoaDonNhap = em.find(HoaDonNhap.class, maHoaDonNhapMoi);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
        }

        return hoaDonNhap; // Trả về đối tượng HoaDonNhap hoặc null nếu không tìm thấy
    }


    public boolean checkHoaDonNhapExists(String maHoaDonNhapMoi) {
        EntityManager em = emf.createEntityManager();
        boolean exists = false;

        try {
            // Sử dụng phương thức find để tìm HoaDonNhap theo maHoaDonNhapMoi
            HoaDonNhap hoaDonNhap = em.find(HoaDonNhap.class, maHoaDonNhapMoi);
            
            // Nếu hoaDonNhap không phải null, nghĩa là hóa đơn nhập đã tồn tại
            if (hoaDonNhap != null) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
        }

        return exists; // Trả về true nếu tồn tại, false nếu không
    }
    public boolean addHoaDonNhap(HoaDonNhap hoaDonNhap) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        boolean isSuccess = false;

        try {
            transaction.begin();
            
            // Sử dụng reflection để truy cập và kiểm tra mã hóa đơn nhập
            Field maHoaDonField = HoaDonNhap.class.getDeclaredField("maHoaDonNhap");
            maHoaDonField.setAccessible(true);
            String maHoaDonNhap = (String) maHoaDonField.get(hoaDonNhap);

            // Kiểm tra nếu mã hóa đơn nhập chưa tồn tại trong cơ sở dữ liệu
            if (!checkHoaDonNhapExists(maHoaDonNhap)) {
                em.persist(hoaDonNhap);  // Lưu đối tượng HoaDonNhap vào cơ sở dữ liệu
                isSuccess = true;
            }
            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }

        return isSuccess;
    }

    
}
