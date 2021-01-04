package com.startup.delivery.connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import com.startup.delivery.buttons.Inline;
import com.startup.delivery.buttons.KButton;

@Component
public class Menus {
	
	public SendMessage requestNumber(String text,String button, Long id) {
		SendMessage a = KButton.create(id)
				.setText(text)
				.row()
				.Kbutton(new KeyboardButton().setRequestContact(true).setText(button))
				.endRow()
				.build();
		return a;
	}
	
	public SendMessage requestLocation(String text,String button,Long id) {
		SendMessage location = KButton.create(id)
				.setText(text)
				.row()
				.Kbutton(new KeyboardButton().setRequestLocation(true).setText(button))
				.endRow()
				.build();
		return location;
	}
	
	public SendMessage mainMenu(String text,String order,String settings,Long id) {
		SendMessage main = KButton.create(id)
				.setText(text)
				.row()
				.button(order)
				.button(settings)
				.endRow()
				.build();
		return main;
	}
	
	public SendMessage cancelOrder(String text,String button,Long id) {
		SendMessage cancelOrder = KButton.create(id)
				.setText(text)
				.row()
				.button(button)
				.endRow()
				.build();
		return cancelOrder;
	}
	
	public SendMessage settings(String text,String name,String number,String location,Long id) {
		SendMessage settings = Inline.create(id)
				.setText(text)
				.row()
				.button(name, "name")
				.endRow()
				.row()
				.button(number, "changenumber")
				.endRow()
				.row()
				.button(location, "location")
				.endRow()
				.build();
		return settings;
				
	}
}
