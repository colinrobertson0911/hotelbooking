package com.fdmgroup.hotelbookingsystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_gen")
	@SequenceGenerator(name = "booking_gen", sequenceName = "BOOKING_SEQ", allocationSize = 1)
	private long bookingId;
	
	@Column
	private int numberOfguests;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	
	
	
	
	
	
}
