package local;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChiTietDonTam {
	private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;
	public ChiTietDonTam(String maSanPham, String tenSanPham, int soLuong, double donGia) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
    

}
