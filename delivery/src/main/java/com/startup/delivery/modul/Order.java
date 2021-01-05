package com.startup.delivery.modul;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	
	@Column(name = "customer_number")
	private String customerNumber;
	
	@Column(name = "customer_location")
	private String customerLocation;
	
	@Column(name = "customer_order_list")
	private String customerOrderList;
	
	@Column(name = "longitude")
	private Float longitudeFirst;
	
	@Column(name = "latitude")
	private Float latitudeSecond;
}

