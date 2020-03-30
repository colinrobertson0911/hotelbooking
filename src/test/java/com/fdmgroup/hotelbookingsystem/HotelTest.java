package com.fdmgroup.hotelbookingsystem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HotelTest {

	@Autowired
	HotelService hotelService;

	@Test
	void test_thatANewHotelCanBeAdded() {
		Hotel hotel = new Hotel();
		hotel.setHotelName("Hotel1");
		hotel.setCity("Place");
		hotel.setNumOfRooms(10);
		hotel.setStarRating(5);
		int numberBeforeAdding = hotelService.findAll().size();
		hotelService.save(hotel);
		int numberAfterAdding = hotelService.findAll().size();
		assertNotEquals(numberBeforeAdding, numberAfterAdding);
	}

	@Test
	void test_thatAHotelCanBeCalledById() {
		Hotel hotel = hotelService.retrieveOne(1L);
		long hotelId = hotel.getHotelId();
		Hotel hotelFromDatabase = hotelService.retrieveOne(hotelId);
		assertEquals(hotel, hotelFromDatabase);
		
	}

	@Test
	void test_thatAListOfHotelsFromACertainCityCanBeCalled() {
		List<Hotel> hotelsByCity = hotelService.findByCity("Glasgow");
		assert (hotelsByCity.size() > 1);
	}

	@Test
	void test_thatTheNumberOfRoomsIsCalledFromAHotel() {
		Hotel hotel = hotelService.retrieveOne(1L);
		int roomNumber = hotel.getNumOfRooms();
		assertEquals(roomNumber, 53);
		//TODO second half where if hotel is fully booked, an error is called.
	}
}
