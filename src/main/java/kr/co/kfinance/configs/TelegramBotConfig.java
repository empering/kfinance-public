package kr.co.kfinance.configs;

import com.pengrad.telegrambot.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("default")
@RequiredArgsConstructor
@Configuration
public class TelegramBotConfig {

	final AppProperties appProperties;

	@Bean
	public TelegramBot telegramBot() {
		return new TelegramBot(appProperties.getTelegrambotToken());
	}
}
