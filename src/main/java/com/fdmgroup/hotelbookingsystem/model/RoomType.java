package com.fdmgroup.hotelbookingsystem.model;

public enum RoomType {
	STANDARD("Standard"), 
	LUXURY ("Luxury"),
	DELUXE("Deluxe"), 
	SUITE("Suite");

	private String name;
	
	private RoomType(String type) {
		name = type;
	}
	
	public String getName() {
		return name;
		
	}
	
	public static String getUserType(int index) {
		return RoomType.values()[index].toString();
	}
}
