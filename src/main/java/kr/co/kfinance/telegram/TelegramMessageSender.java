package kr.co.kfinance.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramMessageSender {
	final TelegramBot telegramBot;

	final TelegramChatRepository telegramChatRepository;

	public void sendMessage(String message) {
		List<TelegramChat> chats = telegramChatRepository.findAll();

		for (TelegramChat chat : chats) {
			if (chat.isStart()) {
				SendMessage sendMessage =
						new SendMessage(chat.getChatId(), message)
								.disableNotification(false);

				telegramBot.execute(sendMessage);
			}
		}
	}
}
