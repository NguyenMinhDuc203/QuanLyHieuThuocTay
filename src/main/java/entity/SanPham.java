package entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SanPham")


public class SanPham {
	@Id
	private String maSanPham ;
	private String tenSanPham;
	private double giaBan;
	private String congDung;
	private LocalDate hanSuDung;
	private String baoQuan;
	private String chongChiDinh;
	private LocalDate ngaySanXuat;
	private String thanhPhan;
	private int soLuongTonkho;
	private String ghiChu;
	private String nhaSanXuat;
	private DonViTinh donViTinh;
	private double thueGTGT;
	private double giaNhap;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maLoaiSanPham")
	private LoaiSanPham loaiSanPham;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDonNhap")
	private HoaDonNhap hoaDonNhap;
	
	
}
