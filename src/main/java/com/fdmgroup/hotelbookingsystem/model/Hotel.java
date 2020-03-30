package com.fdmgroup.hotelbookingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_gen")
	@SequenceGenerator(name = "hotel_gen", sequenceName = "HOTEL_SEQ", allocationSize = 1)
	private long hotelId;
	
}
