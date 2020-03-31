package com.fdmgroup.hotelbookingsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_gen")
	@SequenceGenerator(name = "hotel_gen", sequenceName = "HOTEL_SEQ", allocationSize = 1)
	private long hotelId;

	@Column
	private String hotelName;

	@Column
	private int numOfRooms;
	
	@Column
	private String address;
	
	@Column
	private  String postcode;

	@Column
	private String city;
	
	@Column(nullable = false, length = 8000)
	private String ammenities;
	
	@Column
	private int starRating;
	
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel(String hotelName, int numOfRooms, String address, String postcode, String city, String ammenities,
			int starRating) {
		super();
		this.hotelName = hotelName;
		this.numOfRooms = numOfRooms;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.ammenities = ammenities;
		this.starRating = starRating;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAmmenities() {
		return ammenities;
	}

	public void setAmmenities(String ammenities) {
		this.ammenities = ammenities;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((ammenities == null) ? 0 : ammenities.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (hotelId ^ (hotelId >>> 32));
		result = prime * result + ((hotelName == null) ? 0 : hotelName.hashCode());
		result = prime * result + numOfRooms;
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + starRating;
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
		Hotel other = (Hotel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (ammenities == null) {
			if (other.ammenities != null)
				return false;
		} else if (!ammenities.equals(other.ammenities))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (hotelId != other.hotelId)
			return false;
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (numOfRooms != other.numOfRooms)
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (starRating != other.starRating)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", numOfRooms=" + numOfRooms + ", address="
				+ address + ", postcode=" + postcode + ", city=" + city + ", ammenities=" + ammenities + ", starRating="
				+ starRating + "]";
	}

	
}