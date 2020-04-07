package com.fdmgroup.hotelbookingsystem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.hotelbookingsystem.model.Bookings;
import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.services.BookingService;
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookingTest {

	@Autowired
	HotelService hotelService;
	
	@Autowired
	BookingService bookingService;
	
	@Test
	public void test_ThatABookingCanBeMAde() {		
		LocalDate checkInDate = LocalDate.of(2020, 04, 20);
		LocalDate checkOutDate = LocalDate.of(2020, 04, 27);
		
		Bookings booking = new Bookings();
		booking.setCheckInDate(checkInDate);
		booking.setCheckOutDate(checkOutDate);
		
		int numberBeforeAdding = bookingService.findAll().size();
		bookingService.save(booking);
		int numberAfterAdding = bookingService.findAll().size();
		assertNotEquals(numberBeforeAdding, numberAfterAdding);
	}
	
	@Test
	public void test_ThatABookingCanBeRetrieved() {
		Hotel hotel = hotelService.retrieveOne(1L);
		List<Bookings> bookings = hotel.getBookings();
		assert(bookings.size() > 0);
	}
	
	@Test
	public void test_ToSeeAvailability() {
		Hotel hotel = hotelService.retrieveOne(1L);
		boolean booking = bookingService.findRoomAvailability(hotel);
		assertEquals(booking, false);
		
	}
	
	@Test
	public void test_ToSeeAvailability2() {
		Hotel hotel = hotelService.retrieveOne(2L);
		boolean booking = bookingService.findRoomAvailability(hotel);
		assertEquals(booking, true);
		
	}
	
	
	


}
