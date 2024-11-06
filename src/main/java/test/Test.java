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
=======

<<<<<<< HEAD
>>>>>>> 3b825b18e2200079663905344317fd5b049b017a
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new TrangChu_GUI();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
=======
=======
>>>>>>> 58a2c0183435d4efe2aa94a2d10fd8cb0fece662
>>>>>>> 3b825b18e2200079663905344317fd5b049b017a
	}

}
