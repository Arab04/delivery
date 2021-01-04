package com.startup.delivery.connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UpdateUser extends ResponseMethods{
	
	public UpdateUser(AbsSender s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	
	public void getInfoUser(Long id,Message m) throws TelegramApiException {
		
		switch (UpdateState.state.get(id)) {
		
		case REQUEST_NAME:
			super.requestName(id);
			break;
		
		case REQUEST_LOCATION:
			super.requestLocation(id, m);
			break;
			
		case REQUEST_PHONE:
			super.requestContact(id, m);
			break;
			
		case MAIN_MENU:
			super.mainMenu(id, m);
			break;
			
		case COMMANDS:
			super.commands(id, m);
			break;
		
		case UPDATE_NAME:
			super.updateName(id, m);
			break;
			
		case UPDATE_CONTACT:
			super.updateContact(id, m);
			break;
			
		case UPDATE_LOCATION:
			super.updateLocation(id, m);
			break;
			
		case REQUEST_CUSTOMER_NUMBER:
			super.requestCustomerLocation(id, m);
			break;
			
		case REQUEST_CUSTOMER_LOCATION:
			super.requestOrderList(id, m);
			break;
			
		case REQUEST_ORDER_LIST:
			super.finishingOrder(id, m);
			break;
			
			default:
				break;
			
		}
    }
}
