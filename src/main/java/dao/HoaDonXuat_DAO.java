package dao;

import entity.HoaDonXuat;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.ChiTietHoaDon;
import dao.SanPham_DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HoaDonXuat_DAO {
    private EntityManagerFactory emf;

    public HoaDonXuat_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }
    
    public String taoMaHD(LocalDate date, String maNV, String maKH, String loaiHD ) {
    	// Lấy ngày lập hóa đơn theo định dạng DDMMYY (ngày, tháng, năm)
        String ngayLapHD = date.format(DateTimeFormatter.ofPattern("ddMMyy")); // Ví dụ: 051123 cho ngày 5 tháng 11 năm 2023
        
        // Lấy 4 chữ số cuối của năm hiện tại (XXXX)
        String namLapHD = date.format(DateTimeFormatter.ofPattern("yyyy")); // Ví dụ: 23 cho năm 2023
        String thangLapHD = date.format(DateTimeFormatter.ofPattern("MM")); // Ví dụ: 11 cho tháng 11
        String ngayLapHD_full = date.format(DateTimeFormatter.ofPattern("dd")); // Ví dụ: 05 cho ngày 5
        
        // Lấy 5 số cuối của mã nhân viên (ZZZZZ)
        String maNVCuoi = maNV.length() >= 5 ? maNV.substring(maNV.length() - 5) : maNV;
        
        // Lấy 4 ký tự cuối của mã khách hàng (BBBB)
        String bbbb = maKH.length() >= 4 ? maKH.substring(maKH.length() - 4) : "0000";  // Nếu mã khách hàng nhỏ hơn 4 ký tự, ta gán "0000"
        
        // Lấy số thứ tự hóa đơn trong ngày (AAAA)
        int soThuTuHD = getSoThuTuHoaDon(date);  // Giả lập hàm lấy số thứ tự hóa đơn
        
        // Chuyển số thứ tự hóa đơn thành chuỗi 4 ký tự (ví dụ: 0001, 0012)
        String aaaa = String.format("%04d", soThuTuHD);
        
        // Tạo mã hóa đơn với định dạng: HDXXDDMMYYXXXXZZZZZBBBBAAAA
        String maHD = "HD" + loaiHD + ngayLapHD_full + thangLapHD + namLapHD + maNVCuoi + bbbb + aaaa;
        
        return maHD;
    }
    public int getSoThuTuHoaDon(LocalDate ngayTao) {
        EntityManager em = emf.createEntityManager();
        int result = 0;

        try {
            // Xây dựng câu truy vấn SQL chỉ để đếm số lượng hóa đơn theo ngày
            String sql = "SELECT COUNT(hdx.maHoaDonXuat) " +
                         "FROM HoaDonXuat hdx " +
                         "WHERE hdx.ngayTao = :ngayTao";

            // Tạo truy vấn
            Query query = em.createNativeQuery(sql);
            query.setParameter("ngayTao", java.sql.Date.valueOf(ngayTao));  // Chuyển LocalDate sang java.sql.Date

            // Thực hiện truy vấn và lấy kết quả (số lượng hóa đơn)
            result = ((Number) query.getSingleResult()).intValue();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result; // Trả về số lượng hóa đơn trong ngày
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
        public ArrayList<Object[]> layDanhSachHoaDonTrong3Ngay(String searchTerm, String searchType) { 
            EntityManager em = emf.createEntityManager();
            ArrayList<Object[]> result = new ArrayList<>();

            try {
                // Bắt đầu xây dựng câu truy vấn SQL
                String sql = "SELECT " +
                             "hdx.maHoaDonXuat, " +
                             "hdx.maNhanVien, " +
                             "hdx.maKhachHang, " +
                             "hdx.ngayTao, " +
                             "mg.triGia AS giamGia, " +  // Lấy giá trị giảm giá từ bảng magiamgia
                             "ROUND(SUM((cthd.soLuong * sp.giaBan) * (1 + sp.thueGTGT * 0.01)), 2) AS tongTien " +
                             "FROM HoaDonXuat hdx " +
                             "JOIN chitiethoadon cthd ON hdx.maHoaDonXuat = cthd.maHoaDonXuat " +
                             "JOIN SanPham sp ON cthd.maSanPham = sp.maSanPham " +
                             "LEFT JOIN magiamgia mg ON hdx.maGiamGia = mg.maGiamGia " +  // Liên kết với bảng magiamgia
                             "WHERE hdx.ngayTao >= CURRENT_DATE - INTERVAL 3 DAY "; // Điều kiện 3 ngày qua

                // Xây dựng điều kiện tìm kiếm dựa trên loại tìm kiếm
                switch (searchType) {
                    case "Mã nhân viên":
                        sql += "AND hdx.maNhanVien LIKE :searchTerm ";
                        break;
                    case "Mã hóa đơn":
                        sql += "AND hdx.maHoaDonXuat LIKE :searchTerm ";
                        break;
                    case "Mã khách hàng":
                        sql += "AND hdx.maKhachHang LIKE :searchTerm ";
                        break;
                    default:
                        throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ");
                }

                sql += "GROUP BY " +
                       "hdx.maHoaDonXuat, " +
                       "hdx.maNhanVien, " +
                       "hdx.maKhachHang, " +
                       "hdx.ngayTao, " +
                       "hdx.maGiamGia, " +
                       "mg.triGia";  // Cần thêm vào nhóm theo giảm giá và trị giá giảm giá

                // Tạo truy vấn
                Query query = em.createNativeQuery(sql);
                query.setParameter("searchTerm", "%" + searchTerm + "%");

                // Thực hiện truy vấn và lấy kết quả
                result = new ArrayList<>(query.getResultList());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }

            return result; // Trả về danh sách các hóa đơn tìm thấy
        }


        public ArrayList<Object[]> layDanhSachHoaDonTheoNgay(String searchTerm, String searchType, LocalDate date) {
            EntityManager em = emf.createEntityManager();
            ArrayList<Object[]> result = new ArrayList<>();

            try {
                // Bắt đầu xây dựng câu truy vấn SQL
                String sql = "SELECT " +
                             "hdx.maHoaDonXuat, " +
                             "hdx.maNhanVien, " +
                             "hdx.maKhachHang, " +
                             "hdx.ngayTao, " +
                             "hdx.maGiamGia, " +
                             "ROUND(SUM((cthd.soLuong * sp.giaBan) * (1 + sp.thueGTGT * 0.01)), 2) AS tongTien " +
                             "FROM HoaDonXuat hdx " +
                             "JOIN chitiethoadon cthd ON hdx.maHoaDonXuat = cthd.maHoaDonXuat " +
                             "JOIN SanPham sp ON cthd.maSanPham = sp.maSanPham " +
                             "WHERE ";

                // Xây dựng điều kiện tìm kiếm theo loại tìm kiếm
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

                // Nếu có ngày tìm kiếm, thêm điều kiện lọc theo ngày
                if (date != null) {
                    sql += " AND hdx.ngayTao = :date";
                }

                sql += " GROUP BY " +
                       "hdx.maHoaDonXuat, " +
                       "hdx.maNhanVien, " +
                       "hdx.maKhachHang, " +
                       "hdx.ngayTao, " +
                       "hdx.maGiamGia";  // Sửa lại tên cột ở đây

                // Tạo truy vấn
                Query query = em.createNativeQuery(sql);

                // Thêm tham số tìm kiếm
                query.setParameter("searchTerm", "%" + searchTerm + "%");  // Thêm ký tự đại diện cho tìm kiếm chứa

                // Nếu có ngày tìm kiếm, thêm tham số ngày vào truy vấn
                if (date != null) {
                    // Chuyển LocalDate thành java.sql.Date
                    query.setParameter("date", java.sql.Date.valueOf(date));
                }

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
        
        public ArrayList<Object[]> layDanhSachChiTietSanPhamTheoMaHoaDonXuat2(String maHoaDonXuat) {
            EntityManager em = emf.createEntityManager();
            ArrayList<Object[]> result = new ArrayList<>(); // Khởi tạo ArrayList để lưu kết quả

            try {
                // Câu truy vấn để lấy chi tiết sản phẩm dựa trên mã hóa đơn xuất
            	String sql = "SELECT " +
                        "sp.maSanPham, " +
                        "sp.tenSanPham, " +
                        "cthd.soLuong, " +
                        "sp.giaBan, " +
                        "sp.thueGTGT, " +
                        "ROUND(SUM(cthd.soLuong * sp.giaBan + sp.thueGTGT/100*cthd.soLuong * sp.giaBan), 2) AS thanhTien " +
                        "FROM ChiTietHoaDon cthd " +
                        "JOIN cthd.hoaDonXuat hdx " +
                        "JOIN cthd.sanPham sp " +
                        "WHERE hdx.maHoaDonXuat LIKE :maHoaDonXuat " +
                        "GROUP BY sp.maSanPham, sp.tenSanPham, cthd.soLuong, sp.thueGTGT, sp.giaBan";




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
                                           ", Số lượng: " + row[2] + 
                                           ", Đơn giá: " + row[3] + 
                                           ", Thuế: " + row[4] + 
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
                Query query = em.createQuery("SELECT h.maHoaDonXuat FROM HoaDonXuat h ORDER BY h.maHoaDonXuat DESC");
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

        public void luuHoaDonXuat(HoaDonXuat hoaDon, DefaultTableModel model) {
            EntityManager em = emf.createEntityManager();

            try {
                // Bắt đầu giao dịch
                em.getTransaction().begin();

                // Lưu hóa đơn chính vào bảng HoaDonXuat
                em.persist(hoaDon);

                 // Giả sử bạn có một JTable với DefaultTableModel
                String maHoaDonXuat = hoaDon.getMaHoaDonXuat();

                // Lặp qua từng dòng trong bảng JTable và lưu vào ChiTietHoaDon
                for (int i = 0; i < model.getRowCount(); i++) {
                    String maSanPham = (String) model.getValueAt(i, 0);  // Mã sản phẩm từ cột 0
                    SanPham_DAO sanPhamDAO = new SanPham_DAO();
                    SanPham sanPham = sanPhamDAO.findSanPhamById(maSanPham);
                    int soLuong = Integer.parseInt(model.getValueAt(i, 2).toString());  // Số lượng từ cột 2

                    // Tạo đối tượng ChiTietHoaDon
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon();
                    chiTiet.setHoaDonXuat(hoaDon);
                    chiTiet.setSanPham(sanPham);
                    chiTiet.setSoLuong(soLuong);

                    // Persist chi tiết vào cơ sở dữ liệu
                    em.persist(chiTiet);
                }

                // Commit giao dịch sau khi tất cả đều thành công
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Hóa đơn và chi tiết đã được lưu thành công!");

            } catch (Exception e) {
                // Nếu có lỗi, rollback giao dịch
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);

            } finally {
                // Đảm bảo đóng EntityManager sau khi hoàn thành
                em.close();
            }
        }


        

        // Đóng EntityManagerFactory
        public void close() {
            if (emf != null) emf.close();
        }

       
    


}

    

