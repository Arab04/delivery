package com.startup.delivery.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class Send extends Menus{

	private AbsSender send;
	
	public Send(AbsSender s) {
		this.send = s;
	}
	
	public void requestName(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setChatId(id).setText("Type name"));
	}
	
	public void requestNumber(Long id) throws TelegramApiException {
		send.execute(super.requestNumber("Send your contact", "Contact", id));
	}
	
	public void sendMainMenu(Long id) throws TelegramApiException {
		send.execute(super.mainMenu("Main menu", "Order", "Settings", id));
	} 
	
	public void askPermission(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setText("Please press the button and give a permission"));
	}
	
	public void order(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setText("Not ready yet").setChatId(id));
	}
	
	public void settings(Long id) throws TelegramApiException {
		send.execute(super.settings("Settings", "Change Name", "Change Number", "Change Location", id));
	}
	
	public void updateName(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setText("Name was updated").setChatId(id));
	}
	
	public void updateContact(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setText("Number was updated").setChatId(id));
	}
	
	public void requestLocation(Long id) throws TelegramApiException {
		send.execute(super.requestLocation("Send your business Location", "Location", id));
	}
	
	public void updateLocation(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setText("Location was updated").setChatId(id));
	}
	
	public void greeting(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setText("Hello").setChatId(id));
	}
	
	public void requestCustomerNumber(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setChatId(id).setText("Send customer number"));
	}
	
	public void requestCustomerLocation(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setChatId(id).setText("Send Customer Location"));
	}
	
	public void requestProductType(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setChatId(id).setText("Send product order list"));
	}
	
	public void finishProcess(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setChatId(id).setText("Thanks Our Drivers on the way"));
	}
	
	public void cancelOrderButton(Long id) throws TelegramApiException {
		send.execute(super.cancelOrder("You may cancel order by pressing below button", "Cancel Order", id));
	}
	
	public void orderCanceled(Long id) throws TelegramApiException {
		send.execute(new SendMessage().setChatId(id).setText("Order was cancelled"));
	}
}
