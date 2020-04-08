package com.fdmgroup.hotelbookingsystem.model;

public enum Extras {
	
	AIRPORTTRANSFER("airportTransfer");
	
	private String name;
	
	private Extras(String type) {
		name = type;
	}
	
	public String getName() {
		return name;
	}

	public static String getExtra(int index) {
		return Extras.values()[index].toString();
	}
}
