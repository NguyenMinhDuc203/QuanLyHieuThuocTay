package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "HoaDonDoiTra")


public class HoaDonDoiTra {
	@Id
	private String maHoaDonDoiTra;
	
	private LocalDate ngayTao;
	
	@Enumerated(EnumType.STRING)
	private LoaiHoaDon loaiHoaDon;
	
	private String lyDo;
	
	@OneToOne
	@JoinColumn(name = "maHoaDonXuat", unique = true, nullable = false)
	private HoaDonXuat hoaDonXuat;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang")
	private KhachHang khachHang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien")
	private NhanVien nhanVien;
	
	@OneToMany(mappedBy = "hoaDonDoiTra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ChiTietHoaDon> chiTietHoaDon;
	
	private double tienHoan;

}
