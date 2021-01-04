package com.startup.delivery.language;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class TextGetters {

	//request user info
	private String requestName;
	private String requestNumber;
	private String requestLocation;
	
}
