package entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "HoaDonNhap")

public class HoaDonNhap {
	@Id
	private String maHoaDonNhap;
	private LocalDate ngayNhap;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhaPhanPhoi")
	private NhaPhanPhoi nhaPhanPhoi;
	
	@OneToMany(mappedBy = "hoaDonNhap", fetch = FetchType.LAZY)
	private Set<SanPham> sanPham;
}
