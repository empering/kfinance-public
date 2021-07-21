package kr.co.kfinance.configs;

import kr.co.kfinance.inquiries.Inquiry;
import kr.co.kfinance.inquiries.InquiryRepository;
import kr.co.kfinance.managers.Manager;
import kr.co.kfinance.managers.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Profile("default")
@Configuration
public class ApplicationMockData {

	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {

			@Autowired
			ManagerService managerService;

			@Autowired
			InquiryRepository inquiryRepository;

			@Override
			public void run(ApplicationArguments args) {
				this.generateManager();
				IntStream.range(0, 50).forEach(this::generateInquiry);
			}

			private void generateManager() {
				Manager manager = Manager.builder()
						.name("manager")
						.password("password")
						.build();

				managerService.saveManager(manager);
			}

			private void generateInquiry(int index) {
				Inquiry inquiry = Inquiry.builder()
						.name("테스트" + index)
						.type("가입")
						.card("보유")
						.hp("000-0000-0000")
						.build();

				inquiryRepository.save(inquiry);
			}
		};
	}
}
