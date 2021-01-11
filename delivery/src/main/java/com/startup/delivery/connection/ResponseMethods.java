package com.startup.delivery.connection;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.startup.delivery.hibernate.Connection;
import com.startup.delivery.modul.Order;
import com.startup.delivery.modul.UserData;
import com.startup.delivery.reprository.UserDataReprository;

import lombok.NoArgsConstructor;

@Component
public class ResponseMethods {
	
	@Autowired
	private Send send;
	
	@Autowired
	private UserData user;
	
	@Autowired
	private Order order;
	
	@Autowired
	private UserDataReprository repo;
	
	
	public void requestName(Long id,AbsSender s) throws TelegramApiException {
		send.requestName(id,s);
		UpdateState.state.put(id, State.REQUEST_LOCATION);
	}
	
	public void requestLocation(Long id,Message m,AbsSender s) throws TelegramApiException {
		user.setName(m.getText());
		send.requestLocation(id,s);
		UpdateState.state.put(id, State.REQUEST_PHONE);
	}
	
	public void requestContact(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.hasLocation()) {
			user.setLongitudeFirst(m.getLocation().getLongitude());
			user.setLatitudeSecond(m.getLocation().getLatitude());
			
			send.requestNumber(id,s);
			UpdateState.state.put(id, State.MAIN_MENU);
			}
			else {
				send.askPermission(id,s);
			}
	}
	
	public void mainMenu(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.hasContact()) {

			user.setLastName(m.getContact().getLastName());
			user.setPhoneNumber(m.getContact().getPhoneNumber());
			user.setUserId(m.getContact().getUserID());
			user.setUserName(m.getChat().getUserName());
			
			//Saving data
			//new Thread(() -> connection.saveData(user)).start();
			
			repo.save(user);
			
			send.sendMainMenu(id,s);
			UpdateState.state.put(id, State.COMMANDS);
			}
			
			else {
				send.askPermission(id,s);
			}
	}
	
	public void updateName(Long id,Message m,AbsSender s) throws TelegramApiException {
		user.setName(m.getText());
		send.updateName(id,s);
		UpdateState.state.put(id, State.COMMANDS);
	}
	
	public void updateContact(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.hasContact()) {

			user.setLastName(m.getContact().getLastName());
			user.setPhoneNumber(m.getContact().getPhoneNumber());
			user.setUserId(m.getContact().getUserID());
			user.setUserName(m.getChat().getUserName());
			
			UpdateState.state.put(id, State.COMMANDS);
			send.updateContact(id,s);
			send.sendMainMenu(id,s);
			}
			
			else {
				send.askPermission(id,s);
			}
	}
	
	public void updateLocation(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.hasLocation()) {
			user.setLongitudeFirst(m.getLocation().getLongitude());
			user.setLatitudeSecond(m.getLocation().getLatitude());
			
			UpdateState.state.put(id, State.COMMANDS);
			send.updateLocation(id,s);
			send.sendMainMenu(id,s);
		}
		else {
			send.askPermission(id,s);
		}
	}
	
	public void commands(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.getText().equals("Order")) {
			requestCustomerNumber(id,s);
		}
		
		else if(m.getText().equals("Settings")) {
			send.settings(id,s);
		}
		
		else if(m.getText().equals(null)) {
			
		}
	}
	
	public void requestCustomerNumber(Long id,AbsSender s) throws TelegramApiException {
		send.requestCustomerNumber(id,s);
		send.cancelOrderButton(id,s);
		UpdateState.state.put(id, State.REQUEST_CUSTOMER_NUMBER);
	}
	
	public void requestCustomerLocation(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.hasContact()) {
			order.setCustomerNumber(m.getContact().getPhoneNumber());
			send.requestCustomerLocation(id,s);
			UpdateState.state.put(id, State.REQUEST_CUSTOMER_LOCATION);
		}
		
		else if(m.getText().equals("Cancel Order")) {
			send.orderCanceled(id,s);
			send.sendMainMenu(id,s);
			UpdateState.state.put(id, State.COMMANDS);
		}
		
		else {
		order.setCustomerNumber(m.getText());
		send.requestCustomerLocation(id,s);
		UpdateState.state.put(id, State.REQUEST_CUSTOMER_LOCATION);
		}
	}
	
	public void requestOrderList(Long id,Message m,AbsSender s) throws TelegramApiException {
		if(m.hasLocation()) {
			order.setLongitudeFirst(m.getLocation().getLongitude());
			order.setLatitudeSecond(m.getLocation().getLatitude());
			
			send.requestProductType(id,s);
			UpdateState.state.put(id, State.REQUEST_ORDER_LIST);
		}
		
		else if(m.getText().equals("Cancel Order")) {
			send.orderCanceled(id,s);
			send.sendMainMenu(id,s);
			UpdateState.state.put(id, State.COMMANDS);
		}
		
		else {
		order.setCustomerLocation(m.getText());
		
		send.requestProductType(id,s);
		UpdateState.state.put(id, State.REQUEST_ORDER_LIST);
		}
	}
	
	public void finishingOrder(Long id,Message m,AbsSender s) throws TelegramApiException {
		order.setCustomerOrderList(m.getText());
		send.finishProcess(id,s);
		send.sendMainMenu(id,s);
		UpdateState.state.put(id, State.COMMANDS);
	}
	
	
	//CallBackQuery methods
	
	public void name(Long id,AbsSender s) throws TelegramApiException {
		send.requestName(id,s);
		UpdateState.state.put(id, State.UPDATE_NAME);
	}
	
	public void changeNumber(Long id,AbsSender s) throws TelegramApiException {
		send.requestNumber(id,s);
		UpdateState.state.put(id, State.UPDATE_CONTACT);
	}
	
	public void changeLocation(Long id,AbsSender s) throws TelegramApiException {
		send.requestLocation(id,s);
		UpdateState.state.put(id, State.UPDATE_LOCATION);
	}
	
}
