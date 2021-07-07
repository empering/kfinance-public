package kr.co.kfinance.telegram;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "chatId")
public class TelegramChat {
	@Id
	private Long chatId;
	private String userName;
	private String firstName;
	private String lastName;
	private boolean start;
}
