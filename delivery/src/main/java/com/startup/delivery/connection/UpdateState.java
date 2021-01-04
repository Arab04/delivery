package com.startup.delivery.connection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateState {

	private Update update;
	public static Map<Long,State> state = new HashMap<Long,State>();
	
	
}
