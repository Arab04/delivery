package com.startup.delivery.connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UpdateUser {
	
	@Autowired
	private ResponseMethods methods;

	
	public void getInfoUser(Long id,Message m,AbsSender s) throws TelegramApiException {
		
		switch (UpdateState.state.get(id)) {
		
		case REQUEST_NAME:
			methods.requestName(id,s);
			break;
		
		case REQUEST_LOCATION:
			methods.requestLocation(id, m,s);
			break;
			
		case REQUEST_PHONE:
			methods.requestContact(id, m,s);
			break;
			
		case MAIN_MENU:
			methods.mainMenu(id, m,s);
			break;
			
		case COMMANDS:
			methods.commands(id, m,s);
			break;
		
		case UPDATE_NAME:
			methods.updateName(id, m,s);
			break;
			
		case UPDATE_CONTACT:
			methods.updateContact(id, m,s);
			break;
			
		case UPDATE_LOCATION:
			methods.updateLocation(id, m,s);
			break;
			
		case REQUEST_CUSTOMER_NUMBER:
			methods.requestCustomerLocation(id, m,s);
			break;
			
		case REQUEST_CUSTOMER_LOCATION:
			methods.requestOrderList(id, m,s);
			break;
			
		case REQUEST_ORDER_LIST:
			methods.finishingOrder(id, m,s);
			break;
			
			default:
				break;
			
		}
    }
}
