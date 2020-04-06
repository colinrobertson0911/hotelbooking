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
	private String roomType;

	@Column
	private BigDecimal price;
	
//	@Column
//	private Boolean available; 

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(String roomType, BigDecimal price) {
		super();
		this.roomType = roomType;
		this.price = price;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (roomId ^ (roomId >>> 32));
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (roomId != other.roomId)
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomType=" + roomType + ", price=" + price + "]";
	}

	
}
