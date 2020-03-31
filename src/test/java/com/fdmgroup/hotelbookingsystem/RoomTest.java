package com.fdmgroup.hotelbookingsystem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.model.RoomType;
import com.fdmgroup.hotelbookingsystem.services.HotelService;
import com.fdmgroup.hotelbookingsystem.services.RoomService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoomTest {

	@Autowired
	RoomService roomService;

	@Autowired
	HotelService hotelService;

	@Test
	public void test_ThatANewRoomCanBeAdded() {
		Room room = new Room();
		room.setRoomNumber(101);
		room.setBeds(2);
		room.setRoomType(RoomType.STANDARD);
		room.setPrice(new BigDecimal("80.00"));
		room.setAmenities("TV, WiFi");
		room.setAvailability(true);
		int numBeforeAdding = roomService.findAll().size();
		roomService.save(room);
		int numAfterAdding = roomService.findAll().size();
		assertNotEquals(numBeforeAdding, numAfterAdding);

	}

	@Test
	public void test_ThatAListOfRoomsCanBeRetrieved() {
		List<Room> allRooms = roomService.findAll();
		int numOfRooms = allRooms.size();
		assert (numOfRooms > 0);
	}

	@Test
	public void test_FindByRoomType() {
		List<Room> allRooms = roomService.findByRoomType(RoomType.DELUXE);
		int numOfRooms = allRooms.size();
		assert (numOfRooms > 0);

	}

	@Test
	public void test_ThatPriceCanBeChanged() {
		Room room = roomService.findByRoomNumber(2);
		BigDecimal roomPrice = room.getPrice();
		room.setPrice(new BigDecimal("99"));
		assertNotEquals(roomPrice, room.getPrice());
	}

	@Test
	public void test_ThatRoomsCanBefoundByExactPrice() {
		List<Room> allRooms = roomService.findByPrice(new BigDecimal("120"));
		int numOfRooms = allRooms.size();
		assert (numOfRooms > 0);
	}
	

}
