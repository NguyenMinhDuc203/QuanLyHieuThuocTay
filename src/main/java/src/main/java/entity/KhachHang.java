package entity;

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
@Table(name = "KhachHang")


public class KhachHang {
	@Id
	private String maKhachHang;
	private String tenKhachHang;
	private String sDT;
}
