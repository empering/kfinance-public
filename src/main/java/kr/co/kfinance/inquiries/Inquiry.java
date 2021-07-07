package kr.co.kfinance.inquiries;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Inquiry {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String hp;
	private String email;
	private String type;
	private String house;
	private String card;
	private LocalDateTime createAt;

}
