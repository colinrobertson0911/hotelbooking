package com.fdmgroup.hotelbookingsystem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		assertNotEquals(numberBeforeAdding,numberAfterAdding);
	}
	
	

}
