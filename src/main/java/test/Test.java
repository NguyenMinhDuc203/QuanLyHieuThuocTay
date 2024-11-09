package test;

import javax.swing.UIManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import gui.DangNhap_GUI;
import dao.NhanVien_DAO;
import entity.NhanVien;
public class Test {
	public static void main(String[] args) {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		
		em.close();
		emf.close();
<<<<<<< HEAD

=======
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new DangNhap_GUI().setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> 67bc86e8b5e75a66a3f8f4711599cebb9e8c8c8a
	}

}
