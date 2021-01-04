package com.startup.delivery.language;

import org.springframework.stereotype.Component;

@Component
public class Text {

	public static TextGetters t = new TextGetters();
	
	public static void setUzb() {
		t.setRequestName("Iltimos ismingizni kiriting");
		t.setRequestLocation("Locatsiyani kiriting");
		t.setRequestNumber("Telefon raqamingizni jonating");
	}
	
}
