package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ChiTietHoaDon")
@EqualsAndHashCode

public class ChiTietHoaDon {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maSanPham")
	private SanPham sanPham;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maHoaDonXuat")
	private HoaDonXuat hoaDonXuat;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maHoaDonDoiTra")
	private HoaDonDoiTra hoaDonDoiTra;
	
	
	
	@EqualsAndHashCode.Exclude
	private int soLuong;
	

}
