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

	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	@Column
	private BigDecimal price;

	public Room(RoomType roomType, BigDecimal price) {
		super();
		this.roomType = roomType;
		this.price = price;
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
		if (roomType != other.roomType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomType=" + roomType + ", price=" + price + "]";
	}

}
