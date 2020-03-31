package com.fdmgroup.hotelbookingsystem.model;

public enum RoomType {
	STANDARD("Standard"), DELUXE("Deluxe"), MASTER("Master"), PRESIDENTIAL("Presidential");

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
