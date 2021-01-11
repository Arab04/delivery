package com.startup.delivery.modul;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.startup.delivery.hibernate.UniqueInterface;

@Entity
@Table(name = "user_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements UniqueInterface{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "customer_id")
	private Integer userId;
	
	@Column(name = "longitude")
	private Float longitudeFirst;
	
	@Column(name = "latitude")
	private Float latitudeSecond;
	
}

