package com.fdmgroup.hotelbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.model.Booking;
import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.repository.BookingDao;

@Service
public class BookingService {

	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	HotelService hotelService;

	public List<Booking> findAll() {
		return bookingDao.findAll();
	}

	public Booking save(Booking booking) {
		return bookingDao.save(booking);
	}

	public boolean findRoomAvailability(Hotel hotel) {
		int numOfRooms = hotel.getNumOfRooms();
		List<Booking> bookings = hotel.getBookings();
		if(bookings.size() < numOfRooms) {
			return true;
		}else {
			return false;
		}
		
	}

}
