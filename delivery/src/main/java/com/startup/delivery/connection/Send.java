package com.startup.delivery.connection;

import java.awt.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.NoArgsConstructor;

@Component
public class Send {

	@Autowired
	private Menus menu;
	
	@Autowired
	private SendMessage message;
	
	public void requestName(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setChatId(id).setText("Type name"));
	}
	
	public void requestNumber(Long id,AbsSender send) throws TelegramApiException {
		send.execute(menu.requestNumber("Send your contact", "Contact", id));
	}
	
	public void sendMainMenu(Long id,AbsSender send) throws TelegramApiException {
		send.execute(menu.mainMenu("Main menu", "Order", "Settings", id));
	} 
	
	public void askPermission(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setText("Please press the button and give a permission"));
	}
	
	public void order(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setText("Not ready yet").setChatId(id));
	}
	
	public void settings(Long id,AbsSender send) throws TelegramApiException {
		send.execute(menu.settings("Settings", "Change Name", "Change Number", "Change Location", id));
	}
	
	public void updateName(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setText("Name was updated").setChatId(id));
	}
	
	public void updateContact(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setText("Number was updated").setChatId(id));
	}
	
	public void requestLocation(Long id,AbsSender send) throws TelegramApiException {
		send.execute(menu.requestLocation("Send your business Location", "Location", id));
	}
	
	public void updateLocation(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setText("Location was updated").setChatId(id));
	}
	
	public void greeting(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setText("Hello").setChatId(id));
	}
	
	public void requestCustomerNumber(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setChatId(id).setText("Send customer number"));
	}
	
	public void requestCustomerLocation(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setChatId(id).setText("Send Customer Location"));
	}
	
	public void requestProductType(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setChatId(id).setText("Send product order list"));
	}
	
	public void finishProcess(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setChatId(id).setText("Thanks Our Drivers on the way"));
	}
	
	public void cancelOrderButton(Long id,AbsSender send) throws TelegramApiException {
		send.execute(menu.cancelOrder("You may cancel order by pressing below button", "Cancel Order", id));
	}
	
	public void orderCanceled(Long id,AbsSender send) throws TelegramApiException {
		send.execute(message.setChatId(id).setText("Order was cancelled"));
	}
}
