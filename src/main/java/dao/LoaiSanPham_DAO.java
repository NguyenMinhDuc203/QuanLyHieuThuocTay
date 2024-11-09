package dao;

import java.lang.reflect.Field;

import entity.LoaiSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
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
	public boolean addLoaiSanPham(LoaiSanPham loaiSanPham) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    boolean isSuccess = false;

	    try {
	        transaction.begin();
	        
	        // Sử dụng reflection để truy cập và kiểm tra mã loại sản phẩm
	        Field maLoaiField = LoaiSanPham.class.getDeclaredField("maLoai");
	        maLoaiField.setAccessible(true);
	        String maLoai = (String) maLoaiField.get(loaiSanPham);

	        // Kiểm tra nếu mã loại sản phẩm chưa tồn tại trong cơ sở dữ liệu
	        if (!checkLoaiSanPhamExists(maLoai)) {
	            em.persist(loaiSanPham);  // Lưu đối tượng LoaiSanPham vào cơ sở dữ liệu
	            isSuccess = true;
	        }
	        
	        transaction.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	    } finally {
	        em.close();
	    }

	    return isSuccess;
	}

	public boolean checkLoaiSanPhamExists(String maLoaiSanPhamMoi) {
	    EntityManager em = emf.createEntityManager();
	    boolean exists = false;

	    try {
	        // Sử dụng phương thức find để tìm LoaiSanPham theo maLoaiSanPhamMoi
	        LoaiSanPham loaiSanPham = em.find(LoaiSanPham.class, maLoaiSanPhamMoi);
	        
	        // Nếu loaiSanPham không phải null, nghĩa là loại sản phẩm đã tồn tại
	        if (loaiSanPham != null) {
	            exists = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In ra lỗi nếu có
	    } finally {
	        em.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
	    }

	    return exists; // Trả về true nếu tồn tại, false nếu không
	}


}
