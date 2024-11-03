package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SanPham_DAO {
    private EntityManagerFactory emf;

    public SanPham_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
    }

    // Phương thức lấy tất cả sản phẩm
    public List<SanPham> getAllSanPhams() {
        EntityManager em = emf.createEntityManager();
        List<SanPham> sanPhams = null;

        try {
            TypedQuery<SanPham> query = em.createQuery("SELECT sp FROM SanPham sp", SanPham.class);
            sanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return sanPhams;
    }

    // Phương thức tìm kiếm sản phẩm theo mã
    public SanPham findSanPhamById(int maSanPham) {
        EntityManager em = emf.createEntityManager();
        SanPham sanPham = null;

        try {
            sanPham = em.find(SanPham.class, maSanPham);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return sanPham;
    }

    // Phương thức tìm kiếm sản phẩm theo tên
    public List<SanPham> findSanPhamByName(String tenSanPham) {
        EntityManager em = emf.createEntityManager();
        List<SanPham> result = null;

        try {
            String jpql = "SELECT sp FROM SanPham sp WHERE sp.tenSanPham LIKE :ten";
            TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
            query.setParameter("ten", "%" + tenSanPham + "%");
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    // Phương thức thêm sản phẩm
    public void addSanPham(SanPham sp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Phương thức cập nhật sản phẩm
    public void updateSanPham(SanPham sp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(sp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Phương thức xóa sản phẩm theo ID
    public void deleteSanPham(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            SanPham sp = em.find(SanPham.class, id);
            if (sp != null) {
                em.remove(sp);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Đóng EntityManagerFactory
    public void close() {
        if (emf != null) emf.close();
    }
}
