package com.startup.delivery.connection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component

public class UpdateState {
	

	public static HashMap<Long,State> state;

	@Autowired
	public void setState(HashMap<Long, State> state) {
		UpdateState.state = state;
	}
	
	
}
