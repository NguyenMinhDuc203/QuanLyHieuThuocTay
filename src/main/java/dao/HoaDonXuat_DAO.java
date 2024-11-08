package dao;

import entity.HoaDonXuat;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
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
                       hdx.ngayTao,SUM(cthd.soLuong * sp.giaBan * (1 + sp.thueGTGT))
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
        
        
        public ArrayList<Object[]> layDanhSachHoaDon(String searchTerm, String searchType) {
            EntityManager em = emf.createEntityManager();
            ArrayList<Object[]> result = new ArrayList<>();

            try {
                // Bắt đầu xây dựng câu truy vấn SQL
                String sql = "SELECT " +
                             "hdx.maHoaDonXuat, " +
                             "hdx.maNhanVien, " +
                             "hdx.maKhachHang, " +
                             "hdx.ngayTao, " +
                             "hdx.maGiamGia, " +  // Đã sửa lại ở đây
                             "ROUND(SUM((cthd.soLuong * sp.giaBan) * (1 + sp.thueGTGT * 0.01)), 2) AS tongTien " +
                             "FROM HoaDonXuat hdx " +
                             "JOIN chitiethoadon cthd ON hdx.maHoaDonXuat = cthd.maHoaDonXuat " +
                             "JOIN SanPham sp ON cthd.maSanPham = sp.maSanPham " +
                             "WHERE ";

                // Xây dựng điều kiện tìm kiếm dựa trên loại tìm kiếm
                switch (searchType) {
                    case "Mã nhân viên":
                        sql += "hdx.maNhanVien LIKE :searchTerm";
                        break;
                    case "Mã hóa đơn":
                        sql += "hdx.maHoaDonXuat LIKE :searchTerm";
                        break;
                    case "Mã khách hàng":
                        sql += "hdx.maKhachHang LIKE :searchTerm";
                        break;
                    default:
                        throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ");
                }

                sql += " GROUP BY " +
                       "hdx.maHoaDonXuat, " +
                       "hdx.maNhanVien, " +
                       "hdx.maKhachHang, " +
                       "hdx.ngayTao, " +
                       "hdx.maGiamGia";  // Sửa lại tên cột ở đây

                // Tạo truy vấn
                Query query = em.createNativeQuery(sql); // Sử dụng createNativeQuery cho SQLquery.setParameter("searchTerm", "%" + searchTerm + "%"); // Thêm ký tự đại diện cho tìm kiếm chứa

                // Thực hiện truy vấn và lấy kết quả
                result = new ArrayList<>(query.getResultList());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }

            return result; // Trả về danh sách các hóa đơn tìm thấy
        }

        public ArrayList<Object[]> layDanhSachChiTietSanPhamTheoMaHoaDonXuat(String maHoaDonXuat) {
            EntityManager em = emf.createEntityManager();
            ArrayList<Object[]> result = new ArrayList<>(); // Khởi tạo ArrayList để lưu kết quả

            try {
                // Câu truy vấn để lấy chi tiết sản phẩm dựa trên mã hóa đơn xuất
            	String sql = "SELECT " +
                        "sp.maSanPham, " +
                        "sp.tenSanPham, " +
                        "lsp.tenLoai, " +
                        "cthd.soLuong, " +
                        "sp.giaBan, " +
                        "ROUND(SUM(cthd.soLuong * sp.giaBan), 2) AS thanhTien " +
                        "FROM ChiTietHoaDon cthd " +
                        "JOIN cthd.hoaDonXuat hdx " +
                        "JOIN cthd.sanPham sp " +
                        "JOIN sp.loaiSanPham lsp " + // Sử dụng thuộc tính loaiSanPham
                        "WHERE hdx.maHoaDonXuat LIKE :maHoaDonXuat " +
                        "GROUP BY sp.maSanPham, sp.tenSanPham, lsp.tenLoai, cthd.soLuong, sp.giaBan";




                // Tạo câu truy vấn
                Query query = em.createQuery(sql);
                query.setParameter("maHoaDonXuat", "%" + maHoaDonXuat + "%"); // Sử dụng ký tự đại diện cho tìm kiếm

                // Thực thi câu truy vấn và lấy kết quả
                result = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả thành ArrayList
                System.out.println("Câu truy vấn: " + sql);

                // In kết quả ra console
                if (result.isEmpty()) {
                    System.out.println("Không tìm thấy sản phẩm cho mã hóa đơn: " + maHoaDonXuat);
                } else {
                    for (Object[] row : result) {
                        System.out.println("Mã Sản Phẩm: " + row[0] + 
                                           ", Tên Sản Phẩm: " + row[1] + 
                                           ", Loại Sản Phẩm: " + row[2] + 
                                           ", Số Lượng: " + row[3] + 
                                           ", Giá Bán: " + row[4] + 
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

        public String generateNewInvoiceCode() {
            EntityManager em = emf.createEntityManager();
            String newCode = "HD001"; // Mã mặc định nếu chưa có hóa đơn nào

            try {
                // Truy vấn mã hóa đơn cuối cùng trong bảng HoaDonXuat
                Query query = em.createQuery("SELECT h.maHoaDon FROM HoaDonXuat h ORDER BY h.maHoaDon DESC");
                query.setMaxResults(1); // Chỉ lấy mã hóa đơn gần nhất
                String lastCode = (String) query.getSingleResult();

                if (lastCode != null && !lastCode.isEmpty()) {
                    // Trích xuất phần số từ mã hóa đơn
                    String numberPart = lastCode.substring(2); // Lấy phần sau "HD"
                    int number = Integer.parseInt(numberPart); // Chuyển phần số sang integer

                    // Tạo mã mới tăng lên 1
                    number++;
                    newCode = "HD" + String.format("%03d", number); // Tạo mã mới với định dạng 3 chữ số
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }
            return newCode;
        }

        public void luuHoaDonXuat(HoaDonXuat hoaDon) {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(hoaDon);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw e;
            } finally {
                em.close();
            }
        }

        

        // Đóng EntityManagerFactory
        public void close() {
            if (emf != null) emf.close();
        }

       
    


}

    

