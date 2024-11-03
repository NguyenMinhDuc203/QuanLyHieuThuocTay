package dao;

import java.util.ArrayList;
import java.util.List;


import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class NhanVien_DAO {
	private EntityManagerFactory emf;

    public NhanVien_DAO() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay"); 
    }
    public ArrayList<Object[]> danhSachNhanVienTheoMa(String maNhanVien) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Object[]> result = new ArrayList<>();

        try {
            String jpql = "SELECT nv.maNhanVien, nv.tenNhanVien, nv.sDT, nv.trinhDo, nv.cMND, nv.email " +
                          "FROM NhanVien nv " +
                          "WHERE nv.maNhanVien LIKE :maNhanVien";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("maNhanVien", "%" + maNhanVien + "%"); // Add wildcards for contains search
            result = new ArrayList<>(query.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }
    public NhanVien layThongTinNhanVienTheoMa(String maNhanVien) {
        EntityManager em = emf.createEntityManager();
        NhanVien nhanVien = null; // Initialize the employee object

        try {
            String jpql = "SELECT nv FROM NhanVien nv " +
                          "WHERE nv.maNhanVien LIKE :maNhanVien";

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



}
