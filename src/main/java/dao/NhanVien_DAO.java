package dao;

import java.security.SecureRandom;
import java.util.ArrayList;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;


import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class NhanVien_DAO {
	private EntityManagerFactory emf;
	// Thay thế bằng thông tin của bạn
//	public static final String ACCOUNT_SID = "AC3001d074e4aed285b21710f05e6a3693"; // SID tài khoản của bạn
	//public static final String AUTH_TOKEN = "06377e030ce06f90ef5ae947e4165be8"; // Mã xác thực của bạn
	///public static final String FROM_PHONE_NUMBER = "84969852409"; // Số điện thoại Twilio của bạn (được chuyển sang định dạng quốc tế)



    public NhanVien_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay"); 
   //     Twilio.init(ACCOUNT_SID, AUTH_TOKEN); // Khởi tạo Twilio
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
            String jpql = "SELECT nv FROM NhanVien nv " +
                          "WHERE nv.maNhanVien LIKE :maNhanVien";

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

    //
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
   //             Message message = Message.creator(
    //                new PhoneNumber(formattedPhoneNumber),  // Số điện thoại nhận đã được định dạng
      //              new PhoneNumber(FROM_PHONE_NUMBER),     // Số điện thoại Twilio của bạn
           //         "Mã xác nhận của bạn là: " + maXacNhan  // Nội dung tin nhắn
              //  ).create();

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

}
