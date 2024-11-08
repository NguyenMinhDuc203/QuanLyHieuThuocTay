package dao;

import entity.LoaiSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LoaiSanPham_DAO {
	 private EntityManagerFactory emf;

	    public  LoaiSanPham_DAO(){
			// TODO Auto-generated constructor stub
		
	        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
	    }

	public LoaiSanPham findLoaiSanPhamByMa(String maLoaiSanPham) {
	    EntityManager em = emf.createEntityManager();
	    LoaiSanPham loaiSanPham = null;

	    try {
	        loaiSanPham = em.find(LoaiSanPham.class, maLoaiSanPham);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }

	    return loaiSanPham;
	}

}
