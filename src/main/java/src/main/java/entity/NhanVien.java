package entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "NhanVien")

public class NhanVien {

	@Id
	private String maNhanVien;
	private String tenNhanVien;
	private String sDT;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private LocalDate ngayVaoLam;
	private double luongCanBan;
	private ChucVu chucVu;
	private String cMND;
	private String trinhDo;
	private String diaChi;
	private String email;
	private String matKhau;
	
	
}
