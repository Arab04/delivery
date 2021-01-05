package com.startup.delivery.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class LongPolling extends TelegramLongPollingBot {
	
	private UpdateUser user = new UpdateUser(this);
	private CallBackQuery query = new CallBackQuery(this);
	private Send send = new Send(this);
	
	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage()) {
			if(UpdateState.state.containsKey(update.getMessage().getChatId())) {
			directMessage(update.getMessage().getChatId(), update.getMessage());
			}
			else {
				getId(update.getMessage().getChatId());
				UpdateState.state.put(update.getMessage().getChatId(), State.REQUEST_NAME);
			}
		}
		else if(update.hasCallbackQuery()) {
			callBackQuery(update);
		}
	}
	

	@Override
	public String getBotUsername() {
		return "delivery04_bot";
	}

	@Override
	public String getBotToken() {
		return "1403141700:AAEJXQAEZV2hyDaldaw_YfjtbURhF20vDZ8";
	}
	
	public void directMessage(Long id,Message m) {
		try {
			user.getInfoUser(id, m);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void callBackQuery(Update update) {
		try {
			query.callback(update);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void getId(Long id) {
		try {
			send.greeting(id);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
