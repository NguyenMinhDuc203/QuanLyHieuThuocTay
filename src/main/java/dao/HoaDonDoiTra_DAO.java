package dao;

import entity.HoaDonDoiTra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class HoaDonDoiTra_DAO {

	   private EntityManagerFactory emf;

	    public HoaDonDoiTra_DAO() {
	        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
	    }
	    
	    

	    public boolean luuHoaDonDoiTra(HoaDonDoiTra hoaDonDoiTra) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();

	            // Lưu đối tượng HoaDonDoiTra vào cơ sở dữ liệu
	            em.persist(hoaDonDoiTra);

	            transaction.commit();
	            return true;  // Thành công
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return false;  // Lỗi
	        } finally {
	            em.close();
	        }
	    }

}
