package com.startup.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import com.startup.delivery.connection.LongPolling;

@SpringBootApplication
@EnableJpaAuditing
public class DeliveryApplication implements CommandLineRunner {
	
	@Autowired
	private static LongPolling longpolling;
	
	@Autowired
	public DeliveryApplication(LongPolling longpolling) {
		this.longpolling = longpolling;
	}
	
	@Override
	public void run(String... args) {
		TelegramBotsApi api = new TelegramBotsApi();
		try {
			api.registerBot(longpolling);
		}
		catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		SpringApplication.run(DeliveryApplication.class, args);
	}

	

}
