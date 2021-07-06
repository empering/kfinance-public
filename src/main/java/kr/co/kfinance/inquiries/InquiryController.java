package kr.co.kfinance.inquiries;

import kr.co.kfinance.telegram.TelegramMessageSender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inquiry")
public class InquiryController {

	final InquiryRepository inquiryRepository;

	final TelegramMessageSender telegramMessageSender;

	final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody InquiryDto inquiryDto) {
		Inquiry inquiry = modelMapper.map(inquiryDto, Inquiry.class);

		inquiryRepository.save(inquiry);

		telegramMessageSender.sendMessage(inquiryDto);

		return ResponseEntity.ok("ok");
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Inquiry> all = inquiryRepository.findAll();

		return ResponseEntity.ok(all);
	}
}
