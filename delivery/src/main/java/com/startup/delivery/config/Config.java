package com.startup.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.bots.AbsSender;

import com.startup.delivery.connection.CallBackQuery;
import com.startup.delivery.modul.Order;
import com.startup.delivery.modul.UserData;
import com.startup.delivery.reprository.UserDataReprository;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "telegrambot")
public class Config {
	
	private String botUserName;
	private String botToken;

}
