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
	private String tel;
	private String email;
}
