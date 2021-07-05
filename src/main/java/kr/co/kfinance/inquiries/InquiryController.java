package kr.co.kfinance.inquiries;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inquiry")
public class InquiryController {

	final InquiryRepository inquiryRepository;

	final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody InquiryDto inquiryDto) {

		System.out.println(inquiryDto);

		Inquiry inquiry = modelMapper.map(inquiryDto, Inquiry.class);
		System.out.println(inquiry);

		inquiryRepository.save(inquiry);

		return ResponseEntity.ok("ok");
	}
}
