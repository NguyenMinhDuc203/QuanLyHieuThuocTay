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
@Table(name = "NhaPhanPhoi")


public class NhaPhanPhoi {
	@Id
	private String maNhaPhanPhoi;
	private String tenNhaPhanPhoi;
	private String diaChi;
	private String soDienThoai;
	private String email;

}
