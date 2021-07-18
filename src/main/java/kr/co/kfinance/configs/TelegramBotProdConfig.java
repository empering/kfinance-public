package kr.co.kfinance.configs;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import kr.co.kfinance.telegram.TelegramChat;
import kr.co.kfinance.telegram.TelegramChatRepository;
import kr.co.kfinance.telegram.TelegramMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@RequiredArgsConstructor
@Configuration
public class TelegramBotProdConfig {

	final ApplicationProperties appProperties;

	final TelegramChatRepository telegramChatRepository;


	@Bean
	public TelegramBot telegramBot() {
		return new TelegramBot(appProperties.getTelegrambotToken());
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {

			@Autowired
			TelegramBot telegramBot;

			@Autowired
			TelegramMessageSender telegramMessageSender;

			@Override
			public void run(ApplicationArguments args) {
				telegramBot.setUpdatesListener(updates -> {
					for (Update update : updates) {
						if (update.channelPost() == null) {
							continue;
						}

						String text = update.channelPost().text();
						Chat chat = update.channelPost().chat();

						if (chat.type() == Chat.Type.channel && chat.title().equals("kfinance365")) {
							if (text.equals("/start") || text.equals("/stop")) {
								TelegramChat telegramChat = TelegramChat.builder()
										.chatId(chat.id())
										.title(chat.title())
										.type(chat.type().toString())
										.start(text.equals("/start"))
										.build();

								telegramChatRepository.save(telegramChat);

								telegramMessageSender.sendMessage(
										String.format("채무통합 신청 알림 %s 되었습니다.", text.equals("/start") ? "시작" : "정지"),
										chat.id(), true
								);
							}
						}
					}

					return UpdatesListener.CONFIRMED_UPDATES_ALL;
				});
			}
		};
	}
}
