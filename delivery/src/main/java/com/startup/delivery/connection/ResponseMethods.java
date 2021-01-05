package com.startup.delivery.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.startup.delivery.modul.Order;
import com.startup.delivery.modul.UserData;
import com.startup.delivery.reprository.UserDataReprository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ResponseMethods extends Send{
	
	private UserData user = new UserData();
	private Order order = new Order();
	
	
	UserDataReprository repo;
	
	public ResponseMethods(AbsSender s) {
		super(s);
	}
	
	public ResponseMethods (UserDataReprository repo) {
		this.repo = repo;
	}
	
	public void requestName(Long id) throws TelegramApiException {
		super.requestName(id);
		UpdateState.state.put(id, State.REQUEST_LOCATION);
	}
	
	public void requestLocation(Long id,Message m) throws TelegramApiException {
		user.setName(m.getText());
		super.requestLocation(id);
		UpdateState.state.put(id, State.REQUEST_PHONE);
	}
	
	public void requestContact(Long id,Message m) throws TelegramApiException {
		if(m.hasLocation()) {
			user.setLongitudeFirst(m.getLocation().getLongitude());
			user.setLatitudeSecond(m.getLocation().getLatitude());
			
			super.requestNumber(id);
			UpdateState.state.put(id, State.MAIN_MENU);
			}
			else {
				super.askPermission(id);
			}
	}
	
	public void mainMenu(Long id,Message m) throws TelegramApiException {
		if(m.hasContact()) {

			user.setLastName(m.getContact().getLastName());
			user.setPhoneNumber(m.getContact().getPhoneNumber());
			user.setUserId(m.getContact().getUserID());
			user.setUserName(m.getChat().getUserName());
			
			repo.save(user);
			super.sendMainMenu(id);
			UpdateState.state.put(id, State.COMMANDS);
			}
			
			else {
				super.askPermission(id);
			}
	}
	
	public void updateName(Long id,Message m) throws TelegramApiException {
		user.setName(m.getText());
		super.updateName(id);
		UpdateState.state.put(id, State.COMMANDS);
	}
	
	public void updateContact(Long id,Message m) throws TelegramApiException {
		if(m.hasContact()) {

			user.setLastName(m.getContact().getLastName());
			user.setPhoneNumber(m.getContact().getPhoneNumber());
			user.setUserId(m.getContact().getUserID());
			user.setUserName(m.getChat().getUserName());
			
			UpdateState.state.put(id, State.COMMANDS);
			super.updateContact(id);
			super.sendMainMenu(id);
			}
			
			else {
				super.askPermission(id);
			}
	}
	
	public void updateLocation(Long id,Message m) throws TelegramApiException {
		if(m.hasLocation()) {
			user.setLongitudeFirst(m.getLocation().getLongitude());
			user.setLatitudeSecond(m.getLocation().getLatitude());
			
			UpdateState.state.put(id, State.COMMANDS);
			super.updateLocation(id);
			super.sendMainMenu(id);
		}
		else {
			super.askPermission(id);
		}
	}
	
	public void commands(Long id,Message m) throws TelegramApiException {
		if(m.getText().equals("Order")) {
			requestCustomerNumber(id);
		}
		
		else if(m.getText().equals("Settings")) {
			super.settings(id);
		}
		
		else if(m.getText().equals(null)) {
			
		}
	}
	
	public void requestCustomerNumber(Long id) throws TelegramApiException {
		super.requestCustomerNumber(id);
		super.cancelOrderButton(id);
		UpdateState.state.put(id, State.REQUEST_CUSTOMER_NUMBER);
	}
	
	public void requestCustomerLocation(Long id,Message m) throws TelegramApiException {
		if(m.hasContact()) {
			order.setCustomerNumber(m.getContact().getPhoneNumber());
			super.requestCustomerLocation(id);
			UpdateState.state.put(id, State.REQUEST_CUSTOMER_LOCATION);
		}
		
		else if(m.getText().equals("Cancel Order")) {
			super.orderCanceled(id);
			super.sendMainMenu(id);
			UpdateState.state.put(id, State.COMMANDS);
		}
		
		else {
		order.setCustomerNumber(m.getText());
		super.requestCustomerLocation(id);
		UpdateState.state.put(id, State.REQUEST_CUSTOMER_LOCATION);
		}
	}
	
	public void requestOrderList(Long id,Message m) throws TelegramApiException {
		if(m.hasLocation()) {
			order.setLongitudeFirst(m.getLocation().getLongitude());
			order.setLatitudeSecond(m.getLocation().getLatitude());
			
			super.requestProductType(id);
			UpdateState.state.put(id, State.REQUEST_ORDER_LIST);
		}
		
		else if(m.getText().equals("Cancel Order")) {
			super.orderCanceled(id);
			super.sendMainMenu(id);
			UpdateState.state.put(id, State.COMMANDS);
		}
		
		else {
		order.setCustomerLocation(m.getText());
		
		super.requestProductType(id);
		UpdateState.state.put(id, State.REQUEST_ORDER_LIST);
		}
	}
	
	public void finishingOrder(Long id,Message m) throws TelegramApiException {
		order.setCustomerOrderList(m.getText());
		super.finishProcess(id);
		super.sendMainMenu(id);
		UpdateState.state.put(id, State.COMMANDS);
	}
	
	
	//CallBackQuery methods
	
	public void name(Long id) throws TelegramApiException {
		super.requestName(id);
		UpdateState.state.put(id, State.UPDATE_NAME);
	}
	
	public void changeNumber(Long id) throws TelegramApiException {
		super.requestNumber(id);
		UpdateState.state.put(id, State.UPDATE_CONTACT);
	}
	
	public void changeLocation(Long id) throws TelegramApiException {
		super.requestLocation(id);
		UpdateState.state.put(id, State.UPDATE_LOCATION);
	}
	
}
