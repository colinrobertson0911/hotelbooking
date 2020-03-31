package com.fdmgroup.hotelbookingsystem.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_gen")
	@SequenceGenerator(name = "room_gen", sequenceName = "ROOM_SEQ", allocationSize = 1)
	private long roomId;

	@Column
	private int roomNumber;

	@Column
	private int beds;

	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	@Column
	private BigDecimal price;

	@Column
	private String amenities;

	@Column
	private boolean availability;

	public Room(int roomNumber, int beds, RoomType roomType, BigDecimal price, String amenities, boolean availability) {
		super();
		this.roomNumber = roomNumber;
		this.beds = beds;
		this.roomType = roomType;
		this.price = price;
		this.amenities = amenities;
		this.availability = availability;
	}

	public Room() {
		super();
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amenities == null) ? 0 : amenities.hashCode());
		result = prime * result + (availability ? 1231 : 1237);
		result = prime * result + beds;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (roomId ^ (roomId >>> 32));
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (amenities == null) {
			if (other.amenities != null)
				return false;
		} else if (!amenities.equals(other.amenities))
			return false;
		if (availability != other.availability)
			return false;
		if (beds != other.beds)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (roomId != other.roomId)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (roomType != other.roomType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", beds=" + beds + ", roomType=" + roomType
				+ ", price=" + price + ", amenities=" + amenities + ", availability=" + availability + "]";
	}

}
