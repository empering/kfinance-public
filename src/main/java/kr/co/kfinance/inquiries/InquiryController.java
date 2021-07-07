package kr.co.kfinance.inquiries;

import kr.co.kfinance.telegram.TelegramMessageSender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.time.LocalDateTime;
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
		inquiry.setCreateAt(LocalDateTime.now());

		inquiryRepository.save(inquiry);

		telegramMessageSender.sendMessage(this.getInquiryMessage(inquiryDto));

		return ResponseEntity.ok("ok");
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Inquiry> all = inquiryRepository.findAll();

		return ResponseEntity.ok(all);
	}

	private String getInquiryMessage(InquiryDto inquiryDto) {
		return MessageFormat.format(
				"이름: {0}\n전화번호: {1}\n4대가입: {2}\n자산보유내용: {3}\n신용카드보유: {4}",
				inquiryDto.getName(),
				inquiryDto.getHp(),
				inquiryDto.getType(),
				inquiryDto.getType().equals("미가입") ? inquiryDto.getHouse() : "",
				inquiryDto.getCard()
		);
	}
}
