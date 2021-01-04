package com.startup.delivery.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CallBackQuery extends ResponseMethods{
	
	
	
	public CallBackQuery(AbsSender s) {
		super(s);
	}

	public void callback(Update update) throws TelegramApiException{
		
		String call = update.getCallbackQuery().getData();
		Long id = update.getMessage().getChatId();
		
		switch (call) {
		
		case "name":
			super.name(id);
			break;
			
		case "changnumber":
			super.changeNumber(id);
			break;
			
		case "location":
			super.changeLocation(id);
			break;
		}
	}
}
