package com.startup.delivery.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.startup.delivery.config.Config;

@Component
public class LongPolling extends TelegramLongPollingBot {
	
	@Autowired
	private Config config;
	
	@Autowired
	private UpdateUser user;
	
	@Autowired
	private CallBackQuery query;
	
	@Autowired
	private Send send;
	
	
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
		return config.getBotUserName();
	}

	@Override
	public String getBotToken() {
		return config.getBotToken();
	}
	
	public void directMessage(Long id,Message m) {
		try {
			user.getInfoUser(id, m,this);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void callBackQuery(Update update) {
		try {
			query.callback(update,this);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void getId(Long id) {
		try {
			send.greeting(id,this);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
