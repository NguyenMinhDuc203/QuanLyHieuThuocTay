package dao;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
<<<<<<< HEAD
import jakarta.persistence.NoResultException;
=======
import jakarta.persistence.EntityTransaction;
>>>>>>> 0b57b80b5e940e6d57ca1a12bb9c8948b4980041
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
    public String maTuSinhNhanVien(String chucVu) {
        EntityManager em = emf.createEntityManager();
        String maNhanVien = null;

        try {
            // Đếm số lượng nhân viên hiện tại dựa trên chức vụ
            String jpql = "SELECT COUNT(nv) FROM NhanVien nv WHERE nv.chucVu = :chucVu";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("chucVu", chucVu);
            Long count = query.getSingleResult();

            // Tạo mã nhân viên mới dựa trên số lượng nhân viên hiện tại
            int nextId = count.intValue() + 1;
            String prefix = chucVu.equalsIgnoreCase("QuanLy") ? "QL" : "NV";
            maNhanVien = String.format("%s%03d", prefix, nextId); // Định dạng thành "NV001", "QL001", ...

        } catch (Exception e) {
            System.err.println("Lỗi khi tự sinh mã nhân viên: " + e.getMessage());
            e.printStackTrace();
            maNhanVien = "NV001"; // Bắt đầu lại từ "NV001" nếu có lỗi
        } finally {
            em.close();
        }

        return maNhanVien;
    }

    public boolean clearAllNhanVien() {
        EntityManager entityManager = emf.createEntityManager();
        boolean isCleared = false;

        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

            // Xóa toàn bộ dữ liệu trong bảng NhanVien
            String jpql = "DELETE FROM NhanVien";
            Query query = entityManager.createQuery(jpql);
            query.executeUpdate();

            entityManager.getTransaction().commit();
            isCleared = true; // Đánh dấu là xóa thành công
            //JOptionPane.showMessageDialog(null, "Đã xóa toàn bộ nhân viên thành công!");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return isCleared;
    }


    //
    public List<NhanVien> getAllNhanViens() {
        EntityManager em = emf.createEntityManager();
        List<NhanVien> nhanViens = null;

        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class);
            nhanViens = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return nhanViens;
    }

    
    public boolean saveNhanVien(List<NhanVien> danhSachNhanVien) {
        EntityManager entityManager = emf.createEntityManager();
        boolean isSaved = false;
        clearAllNhanVien();
        try {
          //  JOptionPane.showMessageDialog(null, "Bắt đầu giao dịch", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            entityManager.getTransaction().begin();

            for (NhanVien nv : danhSachNhanVien) {
          //      JOptionPane.showMessageDialog(null, "Đang lưu nhân viên: " , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
                // Lưu nhân viên vào cơ sở dữ liệu
                entityManager.persist(nv);
            }

          //  JOptionPane.showMessageDialog(null, "Giao dịch đã được flush", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            entityManager.flush();

         //   JOptionPane.showMessageDialog(null, "Cam kết giao dịch", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            entityManager.getTransaction().commit();
            System.out.println("Giao dịch thành công!");

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
 // Phương thức tìm kiếm nhân viên theo mã nhân viên
    public NhanVien findNhanVienById(String maNhanVien) {
        EntityManager em = emf.createEntityManager();
        NhanVien nhanVien = null;

        try {
            nhanVien = em.find(NhanVien.class, maNhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return nhanVien;
    }
<<<<<<< HEAD
    
    
 // Hàm lấy tên nhân viên theo mã nhân viên
    public String layTenNhanVienByMa(String maNhanVien) {
        EntityManager em = emf.createEntityManager();
        String tenNhanVien = null;

        try {
            // Sử dụng HQL để truy vấn tên nhân viên từ mã nhân viên
            String jpql = "SELECT n.tenNhanVien FROM NhanVien n WHERE n.maNhanVien = :maNhanVien";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("maNhanVien", maNhanVien);

            // Lấy kết quả
            tenNhanVien = query.getSingleResult();
        } catch (NoResultException e) {
            // Nếu không tìm thấy, trả về null hoặc xử lý lỗi theo cách khác
            System.out.println("Không tìm thấy nhân viên với mã: " + maNhanVien);
        } catch (Exception e) {
            // Xử lý các lỗi khác nếu có
=======
    public String layMaNhanVienTheoTenTK(String tenTK) {
	    EntityManager entityManager = emf.createEntityManager();
	    String maNhanVien = null;

	    try {
	        // Bắt đầu giao dịch
	        entityManager.getTransaction().begin();

	        // Tạo truy vấn để lấy maNhanVien dựa trên tenTK
	        String jpql = "SELECT nv.maNhanVien FROM NhanVien nv WHERE nv.tenTK = :tenTK";
	        Query query = entityManager.createQuery(jpql);
	        query.setParameter("tenTK", tenTK);

	        // Lấy kết quả (nếu có)
	        maNhanVien = (String) query.getSingleResult();

	        // Kết thúc giao dịch
	        entityManager.getTransaction().commit();
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        entityManager.close();
	    }

	    return maNhanVien;
	}
// Phương thức tìm kiếm nhân viên theo mã nhân viên
public NhanVien findNhanVienById(String maNhanVien) {
    EntityManager em = emf.createEntityManager();
    NhanVien nhanVien = null;

    try {
        nhanVien = em.find(NhanVien.class, maNhanVien);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        em.close();
    }

    return nhanVien;
}
//Thêm
public boolean createNhanVien(NhanVien nv) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    boolean isSuccess = false;
    try {
        transaction.begin(); // Bắt đầu giao dịch
        em.persist(nv); // Thêm đối tượng NhanVien vào cơ sở dữ liệu
        transaction.commit(); // Cam kết giao dịch
        isSuccess = true; // Đánh dấu thành công
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback(); // Hoàn tác giao dịch nếu có lỗi
        }
        System.err.println("Lỗi khi thêm nhân viên trong createNhanVien: " + e.getMessage());
        e.printStackTrace();
    } finally {
        em.close(); // Đóng EntityManager
    }
    return isSuccess;
}
// xóa
public boolean deleteNhanVien(String maNhanVien) {
    EntityManager em = emf.createEntityManager();
    boolean isDeleted = false;

    try {
        em.getTransaction().begin();

    

        // Xóa nhân viên khỏi bảng NhanVien
        int deletedCount = em.createQuery("DELETE FROM NhanVien nv WHERE nv.maNhanVien = :maNhanVien")
                             .setParameter("maNhanVien", maNhanVien)
                             .executeUpdate();

        if (deletedCount > 0) {
            isDeleted = true; // Xóa thành công
        } else {
            System.out.println("Không tìm thấy nhân viên với mã: " + maNhanVien);
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
//sửa
public boolean updateNhanVien(NhanVien nhanVien) {
    EntityManager em = emf.createEntityManager();
    boolean isUpdated = false;

    try {
        em.getTransaction().begin();

        // Truy vấn JPQL để cập nhật thông tin nhân viên dựa vào maNhanVien
        int updatedCount = em.createQuery(
                "UPDATE NhanVien nv SET nv.tenNhanVien = :tenNhanVien, nv.sDT = :sDT, nv.ngaySinh = :ngaySinh, " +
                "nv.ngayVaoLam = :ngayVaoLam, nv.luongCanBan = :luongCanBan, nv.chucVu = :chucVu, " +
                "nv.cMND = :cMND, nv.trinhDo = :trinhDo, nv.diaChi = :diaChi, nv.email = :email, " +
                "nv.matKhau = :matKhau, nv.trangThai = :trangThai WHERE nv.maNhanVien = :maNhanVien")
            .setParameter("tenNhanVien", nhanVien.getTenNhanVien())
            .setParameter("sDT", nhanVien.getSDT())
            .setParameter("ngaySinh", nhanVien.getNgaySinh())
            .setParameter("ngayVaoLam", nhanVien.getNgayVaoLam())
            .setParameter("luongCanBan", nhanVien.getLuongCanBan())
            .setParameter("chucVu", nhanVien.getChucVu()) // Giả sử chucVu là Enum
            .setParameter("cMND", nhanVien.getCMND()) // Kiểm tra tên thuộc tính
            .setParameter("trinhDo", nhanVien.getTrinhDo())
            .setParameter("diaChi", nhanVien.getDiaChi())
            .setParameter("email", nhanVien.getEmail())
            .setParameter("matKhau", nhanVien.getMatKhau())
            .setParameter("trangThai", nhanVien.isTrangThai())
            .setParameter("maNhanVien", nhanVien.getMaNhanVien())
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
///
public List<NhanVien> findNhanVienByPartialId(String partialId) {
    EntityManager em = emf.createEntityManager();
    try {
        String queryStr = "SELECT nv FROM NhanVien nv WHERE nv.maNhanVien LIKE :partialId";
        TypedQuery<NhanVien> query = em.createQuery(queryStr, NhanVien.class);
        query.setParameter("partialId", partialId + "%"); // Tìm kiếm với từ khóa bắt đầu bằng partialId
        List<NhanVien> resultList = query.getResultList();
        return resultList != null ? resultList : new ArrayList<>(); // Trả về danh sách trống nếu không có kết quả
    } finally {
        em.close();
    }
}
}
