package com.startup.delivery.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CallBackQuery {
	
	@Autowired
	private ResponseMethods methods;

	public void callback(Update update,AbsSender s) throws TelegramApiException{
		String call = update.getCallbackQuery().getData();
		Long id  = update.getCallbackQuery().getMessage().getChatId();
		switch (call) {
		
		case "name":
			methods.name(id,s);
			break;
			
		case "changenumber":
			methods.changeNumber(id,s);
			break;
			
		case "location":
			methods.changeLocation(id,s);
			break;
		}
	}
}
