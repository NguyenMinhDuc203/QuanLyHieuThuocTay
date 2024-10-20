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
@Table(name = "MaGiamGia")

public class MaGiamGia {

	@Id
	private String maGiamGia;
	private double triGia;
	private LocalDate ngayHetHan;
}
