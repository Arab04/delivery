package com.startup.delivery.connection;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

public enum State {

	REQUEST_NAME,
	REQUEST_LOCATION,
	REQUEST_PHONE,
	START,
	MAIN_MENU,
	COMMANDS,
	UPDATE_NAME,
	UPDATE_CONTACT,
	UPDATE_LOCATION,
	
	REQUEST_CUSTOMER_NUMBER,
	REQUEST_CUSTOMER_LOCATION,
	REQUEST_ORDER_LIST
}
