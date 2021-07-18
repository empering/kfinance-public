package kr.co.kfinance.managers;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Manager {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	private String password;
}
