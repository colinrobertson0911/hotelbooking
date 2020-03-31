package com.fdmgroup.hotelbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.model.RoomType;
import com.fdmgroup.hotelbookingsystem.repository.RoomDao;

@Service
public class RoomService {
	
	@Autowired
	RoomDao roomDao;

	public List<Room> findAll() {
		return roomDao.findAll();
	}


	public Room save(Room room) {
		return roomDao.save(room);
	}


	public List<Room> findByRoomType(RoomType roomType) {
		return roomDao.findByRoomType(roomType);
	}


	public Room findByRoomNumber(int roomNumber) {
		return roomDao.findByRoomNumber(roomNumber);
	}
	

}
