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
<<<<<<< HEAD
=======
>>>>>>> e3702192c2c633eb379f186bc8de965d8ff0cf65
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new DangNhap_GUI().setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

<<<<<<< HEAD
>>>>>>> 725cf1c0b390bc31562d8096d73f8d39ad549e99
=======
>>>>>>> e3702192c2c633eb379f186bc8de965d8ff0cf65
	}

}
