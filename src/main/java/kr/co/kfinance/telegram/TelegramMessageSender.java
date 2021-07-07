package kr.co.kfinance.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import kr.co.kfinance.inquiries.InquiryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramMessageSender {
	final TelegramBot telegramBot;

	public void sendMessage(String message) {
		List<Update> updates = this.getUpdates();

		for (Update update : updates) {
			SendMessage sendMessage =
					new SendMessage(update.message().chat().id(), message)
							.disableNotification(false);

			telegramBot.execute(sendMessage);
		}
	}

	private List<Update> getUpdates() {
		return telegramBot
				.execute(new GetUpdates().limit(100).offset(0).timeout(0))
				.updates();
	}
}
