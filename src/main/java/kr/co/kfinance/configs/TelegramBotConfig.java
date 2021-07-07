package kr.co.kfinance.configs;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import kr.co.kfinance.telegram.TelegramChat;
import kr.co.kfinance.telegram.TelegramChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class TelegramBotConfig {

	final AppProperties appProperties;

	final TelegramChatRepository telegramChatRepository;


	@Bean
	public TelegramBot telegramBot() {
		TelegramBot telegramBot = new TelegramBot(appProperties.getTelegrambotToken());

		telegramBot.setUpdatesListener(updates -> {
			for (Update update : updates) {
				if (update.message() == null) {
					continue;
				}

				String text = update.message().text();
				Chat chat = update.message().chat();

				if (text.equals("/start") || text.equals("/stop")) {
					TelegramChat telegramChat = TelegramChat.builder()
							.chatId(chat.id())
							.userName(chat.username())
							.firstName(chat.firstName())
							.lastName(chat.lastName())
							.start(text.equals("/start"))
							.build();

					telegramChatRepository.save(telegramChat);
				}
			}

			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});

		return telegramBot;
	}
}
