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
			this.sendMessage(message, chat.getChatId(), chat.isStart());
		}
	}

	public void sendMessage(String message, Long chatId, boolean isStart) {
		if (isStart) {
			SendMessage sendMessage =
					new SendMessage(chatId, message).disableNotification(false);

			telegramBot.execute(sendMessage);
		}
	}
}
