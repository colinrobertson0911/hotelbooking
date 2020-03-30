package com.fdmgroup.hotelbookingsystem;

import static org.junit.jupiter.api.Assertions.*;

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
	
		HotelOwner hotelOwner = new HotelOwner("FancyHair29", "password", "fancyhair29@myownpresonalserver.com", "Mr Fancy", null);
		int numberBeforeAdding = hotelOwnerService.findAll().size();
		hotelOwnerService.save(hotelOwner);
		int numberAfterAdding = hotelOwnerService.findAll().size();
		assertNotEquals(numberBeforeAdding,numberAfterAdding);
	}

}
