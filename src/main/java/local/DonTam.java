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
    private String tenKhachHang;
    private LocalDate ngayTaoHoaDon;
    private java.util.List<ChiTietDonTam> donHang;
    public DonTam(String maHoaDon, String tenKhachHang, LocalDate ngayTaoHoaDon, List<ChiTietDonTam> donHang) {
		super();
		this.maHoaDon = maHoaDon;
		this.tenKhachHang = tenKhachHang;
		this.ngayTaoHoaDon = ngayTaoHoaDon;
		this.donHang = donHang;
	}
    public DonTam(String maHoaDon, String tenKhachHang, LocalDate ngayTaoHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.tenKhachHang = tenKhachHang;
		this.ngayTaoHoaDon = ngayTaoHoaDon;
	}
    
    public DonTam(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
}
