package dao;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.ChucVu;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;

public class NhanVien_DAO {

	private EntityManagerFactory emf;
	private Object gioiTinh;
	private Object trangThai;
	// Thay thế bằng thông tin của bạn
	public static final String ACCOUNT_SID = "AC3001d074e4aed285b21710f05e6a3693"; // SID tài khoản của bạn
	public static final String AUTH_TOKEN = "06377e030ce06f90ef5ae947e4165be8"; // Mã xác thực của bạn
	public static final String FROM_PHONE_NUMBER = "84969852409"; // Số điện thoại Twilio của bạn (được chuyển sang định dạng quốc tế)

    public NhanVien_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay"); 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); // Khởi tạo Twilio
    }

    public ArrayList<Object[]> layDanhSachNhanVien(String searchTerm, String searchType) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Object[]> result = new ArrayList<>();

        try {
            String jpql = "SELECT nv.maNhanVien, nv.tenNhanVien, nv.sDT, nv.trinhDo, nv.chucVu, nv.email " +
                          "FROM NhanVien nv WHERE ";

            // Construct the JPQL query based on the search type
            switch (searchType) {
                case "Mã nhân viên":
                    jpql += "nv.maNhanVien LIKE :searchTerm";
                    break;
                case "Tên nhân viên":
                    jpql += "nv.tenNhanVien LIKE :searchTerm";
                    break;
                case "Số điện thoại":
                    jpql += "nv.sDT LIKE :searchTerm";
                    break;
                case "Email":
                    jpql += "nv.email LIKE :searchTerm";
                    break;
                default:
                    throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ");
            }

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("searchTerm", "%" + searchTerm + "%"); // Add wildcards for contains search
            result = new ArrayList<>(query.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result; // Return the list of found employees
    }

    public NhanVien layThongTinNhanVienTheoMa(String maNhanVien) {
        EntityManager em = emf.createEntityManager();
        NhanVien nhanVien = null; // Initialize the employee object

        try {
            String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNhanVien LIKE :maNhanVien";
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("maNhanVien", "%" + maNhanVien + "%"); // Use wildcards for contains search

            // Get the first result (if any)
            nhanVien = query.getResultStream().findFirst().orElse(null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return nhanVien; // Return the found employee or null if not found
    }

    public String kiemTraDangNhap(String maNhanVien, String matKhau) {
        EntityManager em = emf.createEntityManager();

        try {
            String jpqlCheck = "SELECT COUNT(nv) FROM NhanVien nv WHERE nv.maNhanVien = :maNhanVien";
            TypedQuery<Long> queryCheck = em.createQuery(jpqlCheck, Long.class);
            queryCheck.setParameter("maNhanVien", maNhanVien);

            Long count = queryCheck.getSingleResult();

            if (count == 0) {
                return "Nhân viên không tồn tại.";
            }

            String jpqlPassword = "SELECT COUNT(nv) FROM NhanVien nv WHERE nv.maNhanVien = :maNhanVien AND nv.matKhau = :matKhau";
            TypedQuery<Long> queryPassword = em.createQuery(jpqlPassword, Long.class);
            queryPassword.setParameter("maNhanVien", maNhanVien);
            queryPassword.setParameter("matKhau", matKhau);

            Long passwordCount = queryPassword.getSingleResult();

            if (passwordCount == 0) {
                return "Sai mật khẩu.";
            }

            return "Đăng nhập thành công.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi.";
        } finally {
            em.close();
        }
    }

    public String guiMaVeSDT(String maNhanVien) {
        EntityManager em = emf.createEntityManager();
        String responseMessage = "Đã xảy ra lỗi.";

        try {
            // Kiểm tra tồn tại nhân viên và lấy số điện thoại
            String jpql = "SELECT nv.sDT FROM NhanVien nv WHERE nv.maNhanVien = :maNhanVien";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("maNhanVien", maNhanVien);

            // Lấy số điện thoại nhân viên
            String sdt = query.getSingleResult();

            if (sdt == null) {
                return "Nhân viên không tồn tại.";
            }

            // Chuyển đổi số điện thoại sang định dạng E.164
            String formattedPhoneNumber = formatPhoneNumber(sdt);
            if (formattedPhoneNumber == null) {
                return "Số điện thoại không hợp lệ.";
            }

            // Tạo mã xác nhận
            String maXacNhan = generateVerificationCode(); // Hàm tạo mã xác nhận

            // Gửi tin nhắn SMS
            try {
                Message message = Message.creator(
                    new PhoneNumber(formattedPhoneNumber),  // Số điện thoại nhận đã được định dạng
                    new PhoneNumber(FROM_PHONE_NUMBER),     // Số điện thoại Twilio của bạn
                    "Mã xác nhận của bạn là: " + maXacNhan  // Nội dung tin nhắn
                ).create();

                responseMessage = "Mã xác nhận đã được gửi đến số điện thoại của bạn.";
            } catch (com.twilio.exception.ApiException apiEx) {
                // Xử lý lỗi API của Twilio
                apiEx.printStackTrace();
                return "Lỗi khi gửi tin nhắn: " + apiEx.getMessage() + " (Mã lỗi: " + apiEx.getCode() + ")";
            } catch (com.twilio.exception.TwilioException twilioEx) {
                // Xử lý lỗi kết nối đến Twilio
                twilioEx.printStackTrace();
                return "Lỗi kết nối đến Twilio: " + twilioEx.getMessage();
            } catch (Exception sendMessageException) {
                // Xử lý lỗi chung
                sendMessageException.printStackTrace();
                return "Đã xảy ra lỗi khi gửi tin nhắn: " + sendMessageException.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi xử lý thông tin nhân viên: " + e.getMessage();
        } finally {
            em.close();
        }

        return responseMessage; // Trả về thông điệp phản hồi
    }

    private String formatPhoneNumber(String sdt) {
        if (sdt != null) {
            // Kiểm tra số điện thoại Việt Nam (bắt đầu bằng 0, dài 10 ký tự)
            if (sdt.startsWith("0") && sdt.length() == 10) {
                return "+84" + sdt.substring(1); // Chuyển đổi thành định dạng +84XXXXXXXXX
            } else if (sdt.startsWith("+84") && sdt.length() == 12) {
                return sdt; // Nếu đã có mã quốc gia +84
            } else if (sdt.startsWith("+") && sdt.length() > 3) {
                return sdt; // Nếu đã có mã quốc gia hợp lệ khác
            }
        }
        return null; // Trả về null nếu số điện thoại không hợp lệ
    }

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // Tạo mã 6 chữ số
        return String.valueOf(code);
    }

    //
    public String maTuSinhNhanVien() {
        EntityManager em = emf.createEntityManager();
        String maNhanVien = null;

        try {
            // Đếm số lượng nhân viên hiện có trong bảng
            String jpql = "SELECT COUNT(nv) FROM NhanVien nv";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            Long count = query.getSingleResult();

            // Tạo mã nhân viên mới dựa trên số lượng nhân viên hiện tại
            int nextId = count.intValue() + 1;
            maNhanVien = String.format("NV%03d", nextId); // Định dạng lại thành "NV001", "NV002", ...

        } catch (Exception e) {
            System.err.println("Lỗi khi tự sinh mã nhân viên: " + e.getMessage());
            e.printStackTrace();
            maNhanVien = "NV001"; // Trong trường hợp lỗi, bắt đầu lại từ "NV001"
        } finally {
            em.close();
        }

        return maNhanVien;
    }
    public boolean clearAllNhanVien() {
        EntityManager entityManager = emf.createEntityManager();
        boolean isCleared = false;

        try {
            entityManager.getTransaction().begin(); // Bắt đầu giao dịch

            // Truy vấn để xóa tất cả khách hàng
            String jpql = "DELETE FROM NhanVien";
            Query query = entityManager.createQuery(jpql);
            query.executeUpdate();

            entityManager.getTransaction().commit(); // Cam kết giao dịch
            isCleared = true; // Đánh dấu là xóa thành công
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hoàn tác nếu có lỗi
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Đóng EntityManager
        }

        return isCleared;
    }
    //
    public boolean saveNhanVien(String maNV, String tenNV, String sdt, LocalDate ngaySinhDate, LocalDate ngayVaoLamDate, double luongCanBan, String chucVu, String cmnd, String trinhDo, String diaChi, Boolean gioiTinh, String email, String matKhau, String trangThai) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isSaved = false;

        try {
            entityManager.getTransaction().begin();

            NhanVien nv = new NhanVien();

            // Đặt giá trị cho các trường khác
            Field maNhanVienField = NhanVien.class.getDeclaredField("maNhanVien");
            maNhanVienField.setAccessible(true);
            maNhanVienField.set(nv, maNV);

            Field tenNhanVienField = NhanVien.class.getDeclaredField("tenNhanVien");
            tenNhanVienField.setAccessible(true);
            tenNhanVienField.set(nv, tenNV);

            Field sdtField = NhanVien.class.getDeclaredField("sDT");
            sdtField.setAccessible(true);
            sdtField.set(nv, sdt);

            // Đặt giá trị cho các trường ngày
            Field ngaySinhField = NhanVien.class.getDeclaredField("ngaySinh");
            ngaySinhField.setAccessible(true);
            ngaySinhField.set(nv, ngaySinhDate);

            Field ngayVaoLamField = NhanVien.class.getDeclaredField("ngayVaoLam");
            ngayVaoLamField.setAccessible(true);
            ngayVaoLamField.set(nv, ngayVaoLamDate);

            Field gioiTinhField = NhanVien.class.getDeclaredField("gioiTinh");
            gioiTinhField.setAccessible(true);
            gioiTinhField.set(nv, gioiTinh);

            Field luongCanBanField = NhanVien.class.getDeclaredField("luongCanBan");
            luongCanBanField.setAccessible(true);
            luongCanBanField.set(nv, luongCanBan);

            Field chucVuField = NhanVien.class.getDeclaredField("chucVu");
            chucVuField.setAccessible(true);
            ChucVu chucVuEnum = ChucVu.valueOf(chucVu);
            chucVuField.set(nv, chucVuEnum);

            Field cmndField = NhanVien.class.getDeclaredField("cMND");
            cmndField.setAccessible(true);
            cmndField.set(nv, cmnd);

            Field trinhDoField = NhanVien.class.getDeclaredField("trinhDo");
            trinhDoField.setAccessible(true);
            trinhDoField.set(nv, trinhDo);

            Field diaChiField = NhanVien.class.getDeclaredField("diaChi");
            diaChiField.setAccessible(true);
            diaChiField.set(nv, diaChi);

            Field emailField = NhanVien.class.getDeclaredField("email");
            emailField.setAccessible(true);
            emailField.set(nv, email);

            Field matKhauField = NhanVien.class.getDeclaredField("matKhau");
            matKhauField.setAccessible(true);
            matKhauField.set(nv, matKhau);

            Field trangThaiField = NhanVien.class.getDeclaredField("trangThai");
            trangThaiField.setAccessible(true);
            trangThaiField.set(nv, trangThai);

            entityManager.persist(nv);
            entityManager.getTransaction().commit();
            isSaved = true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return isSaved;
    }
}

