package local;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class DonTam implements java.io.Serializable {
	private String maHoaDon;
	private String sdtKhachHang;
    private String tenKhachHang;
    private LocalDate ngayTaoHoaDon;
    private java.util.List<ChiTietDonTam> donHang;
    
    
    public DonTam(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}


	public DonTam(String maHoaDon, String sdtKhachHang, String tenKhachHang, LocalDate ngayTaoHoaDon,
			List<ChiTietDonTam> donHang) {
		super();
		this.maHoaDon = maHoaDon;
		this.sdtKhachHang = sdtKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.ngayTaoHoaDon = ngayTaoHoaDon;
		this.donHang = donHang;
	}


	public DonTam(String maHoaDon, String sdtKhachHang, String tenKhachHang, LocalDate ngayTaoHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.sdtKhachHang = sdtKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.ngayTaoHoaDon = ngayTaoHoaDon;
	}
}
