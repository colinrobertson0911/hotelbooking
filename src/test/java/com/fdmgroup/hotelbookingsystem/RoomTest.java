package com.fdmgroup.hotelbookingsystem;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.model.RoomType;
import com.fdmgroup.hotelbookingsystem.services.RoomService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoomTest {
	
	@Autowired
	RoomService roomService;

	@Test
	void test_ThatANewRoomCanBeAdded() {
		Room room = new Room();
		room.setRoomNumber(101);
		room.setBeds(2);
		room.setRoomType(RoomType.STANDARD);
		room.setPrice(new BigDecimal ("80.00"));
		room.setAmenities("TV, WiFi");
		room.setAvailability(true);
		int numBeforeAdding = roomService.findAll().size();
		roomService.save(room);
		int numAfterAdding = roomService.findAll().size();
		assertNotEquals(numBeforeAdding, numAfterAdding);
		
	}

}
