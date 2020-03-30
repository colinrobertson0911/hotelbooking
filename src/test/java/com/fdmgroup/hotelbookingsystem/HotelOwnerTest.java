package com.fdmgroup.hotelbookingsystem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HotelOwnerTest {

	@Autowired
	HotelOwnerService hotelOwnerService;
	
	
	
	@Test
	void test_ThatANewOwnerCanBeAdded () {
	
		HotelOwner hotelOwner = new HotelOwner();
		hotelOwner.setUsername("user2");
		hotelOwner.setEmail("Use21@email.com");
		hotelOwner.setName("user two");
		hotelOwner.setPassword("password");
		int numberBeforeAdding = hotelOwnerService.findAll().size();
		hotelOwnerService.save(hotelOwner);
		int numberAfterAdding = hotelOwnerService.findAll().size();
		assertNotEquals(numberBeforeAdding,numberAfterAdding);
	}
	
	@Test
	void test_RetrieveAListOfOwners() {
		List<HotelOwner> hotelOwners = hotelOwnerService.findAll();
		int numOfOwners = hotelOwners.size();
		assert(numOfOwners>0);		
	}
	
	@Test
	void test_RetrieveAnOwnerById() {
		HotelOwner hotelOwner = hotelOwnerService.retrieveOne(1L);
		long hotelOwnerId = hotelOwner.getHotelOwnerId();
		HotelOwner hotelOwnerFromDB = hotelOwnerService.retrieveOne(hotelOwnerId);
		assertEquals(hotelOwnerFromDB,hotelOwner);
	}
	
	

}
