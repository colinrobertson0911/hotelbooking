package com.fdmgroup.hotelbookingsystem.model;

import java.math.BigDecimal;

public enum Extras {
	
	AIRPORTTRANSFER("airportTransfer",new BigDecimal ("20.00"));
	
	private final String service;
	private final BigDecimal price;
	
	private Extras(String service, BigDecimal price) {
		this.service = service;
		this.price = price;
	}
	
	public String getService() {
		return service +" £" + price;
	}
	

	public static String getExtra(int index) {
		return Extras.values()[index].toString();
	}
}
