package test;

import javax.swing.UIManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import gui.DangNhap_GUI;
import gui.TrangChu_GUI;
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
try {
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
new TrangChu_GUI();
}
catch (Exception e) {
e.printStackTrace();
}
=======
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new TrangChu_GUI();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> 8a5e7ca0e54c2aa5094817154bec17b4521acf25
	}

}
