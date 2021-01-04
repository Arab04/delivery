package com.startup.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import com.startup.delivery.connection.LongPolling;

@SpringBootApplication
@EnableJpaAuditing
public class DeliveryApplication {

	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		TelegramBotsApi api = new TelegramBotsApi();
		try {
		api.registerBot(new LongPolling());
		}
		catch(TelegramApiRequestException e) {
			e.printStackTrace();
		}
		
		System.out.println("started working");
		
		SpringApplication.run(DeliveryApplication.class, args);
	}

}
