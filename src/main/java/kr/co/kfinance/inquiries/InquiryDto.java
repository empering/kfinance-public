package kr.co.kfinance.inquiries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
	@NotNull
	private String name;
	@NotNull
	private String hp1;
	private String hp2;
	private String hp3;

	private String type;
	private String house;
	private String card;

	public String getHp() {
		return hp1 + "-" + hp2 + "-" + hp3;
	}
}
