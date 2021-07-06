package kr.co.kfinance.configs;

import com.pengrad.telegrambot.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class TelegramBotConfig {

	final AppProperties appProperties;

	@Bean
	public TelegramBot telegramBot() {
		TelegramBot telegramBot = new TelegramBot(appProperties.getTelegrambotToken());
		// TODO: 대화항목을 읽어서 chatid 저장 후 메세지 전송하는 방식으로 구현 필요함
//		telegramBot.setUpdatesListener(updates -> {
//			for (Update update : updates) {
//				System.out.println(update.message());
//			}
//
//			return UpdatesListener.CONFIRMED_UPDATES_ALL;
//		});
		return telegramBot;
	}
}
