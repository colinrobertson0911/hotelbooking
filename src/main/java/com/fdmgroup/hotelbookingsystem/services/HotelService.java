package com.fdmgroup.hotelbookingsystem.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.model.Bookings;
import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.repository.HotelDao;

@Service
public class HotelService {

	@Autowired
	HotelDao hotelDao;

	public List<Hotel> findAll() {
		return hotelDao.findAll();
	}

	public Optional<Hotel> findByAddress(String address) {
		return hotelDao.findByAddress(address);
	}

	public Hotel save(Hotel hotel) {
		return hotelDao.save(hotel);
	}

	public Hotel retrieveOne(long hotelId) {
		return hotelDao.findByHotelId(hotelId);

	}

	public List<Hotel> findByCity(String city) {

		return hotelDao.findByCity(city);
	}

	public List<Hotel> findByRoomType(String roomType) {
		return hotelDao.findByRoomType(roomType);
	}

	public List<Hotel> findByVerifiedEqualsTrue() {
		return hotelDao.findByVerifiedIsTrue();
	}

	public List<Hotel> findByCityAndVerifiedIsTrue(String city) {
		return hotelDao.findByCityAndVerifiedIsTrue(city);
	}

	public List<Hotel> findByVerifiedAndRoomType(String roomType) {

		return hotelDao.findByVerifiedAndRoomType(roomType);
	}

	public List<Hotel> findByAvailability() {
		List<Hotel> availableHotels = new ArrayList<Hotel>();
		List<Hotel> hotels = findAll();
		for (Hotel hotel : hotels) {
			if (hotel.getNumOfRooms() > hotel.getBookings().size()) {
				availableHotels.add(hotel);
			}
		}
		return availableHotels;
	}

	public List<Hotel> findByAvailabilityAndVerified() {
		List<Hotel> availableHotels = new ArrayList<Hotel>();
		List<Hotel> hotels = findByVerifiedEqualsTrue();
		for (Hotel hotel : hotels) {
			if (hotel.getNumOfRooms() > hotel.getBookings().size()) {
				availableHotels.add(hotel);
			}
		}
		return availableHotels;
	}

	public List<Hotel> findByAvailabilityAndVerifiedWithCurrentDate() {
		List<Hotel> availableHotelsByDate = new ArrayList<Hotel>();
		List<Bookings> bookingsInDateWindow = new ArrayList<Bookings>();
		List<Hotel> hotels = findByVerifiedEqualsTrue();
		for (Hotel hotel : hotels) {
			List<Bookings> hotelBookings = hotel.getBookings();
			for (Bookings booking : hotelBookings) {
				if (booking.getCheckInDate().isBefore(LocalDate.now())
						&& booking.getCheckOutDate().isAfter(LocalDate.now())) {
					bookingsInDateWindow.add(booking);
				}
			}
			if (hotel.getNumOfRooms() > bookingsInDateWindow.size()) {
				availableHotelsByDate.add(hotel);
			}
		}
		return availableHotelsByDate;
	}

	public List<Hotel> findByAvailabilityAndVerifiedWithSpecifiedDates(LocalDate startDate, LocalDate endDate) {
		List<Hotel> availableHotelsByDate = new ArrayList<Hotel>();
		List<Bookings> bookingsInDateWindow = new ArrayList<Bookings>();
		List<Hotel> hotels = findByVerifiedEqualsTrue();
		for (Hotel hotel : hotels) {
			List<Bookings> hotelBookings = hotel.getBookings();
			for (Bookings booking : hotelBookings) {
				if (!booking.getCheckInDate().isAfter(endDate)
						&& !booking.getCheckOutDate().isBefore(startDate)) {
					bookingsInDateWindow.add(booking);
				}
			}
			if (hotel.getNumOfRooms() > bookingsInDateWindow.size()) {
				availableHotelsByDate.add(hotel);
			}
		}
		return availableHotelsByDate;
	}

	public Hotel findByHotelName(String hotelName) {
		return hotelDao.findByHotelName(hotelName);
	}
	
	
}
