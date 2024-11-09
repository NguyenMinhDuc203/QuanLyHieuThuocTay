package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import entity.ChiTietHoaDon;

public class ChiTietHoaDon_DAO {

    private EntityManagerFactory emf;

    public ChiTietHoaDon_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }

    // Hàm lưu ChiTietHoaDon vào cơ sở dữ liệu
    public void save(ChiTietHoaDon chiTietHoaDon) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Bắt đầu transaction
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Thực hiện lưu đối tượng vào database
            entityManager.persist(chiTietHoaDon);

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction nếu có lỗi
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Đóng EntityManager sau khi hoàn tất
            entityManager.close();
        }
    }

    // Phương thức để đóng EntityManagerFactory khi không cần dùng nữa
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
