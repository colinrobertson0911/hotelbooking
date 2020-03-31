package com.fdmgroup.hotelbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.repository.RoomDao;

@Service
public class RoomService implements GeneralServiceRepository<Room> {
	
	@Autowired
	RoomDao roomDao;

	public List<Room> findAll() {
		return roomDao.findAll();
	}

	

	@Override
	public Room findByUsernameAndPassword(String username, String password) {
		return null;
	}

	@Override
	public Room findByUsername(String username) {
		return null;
	}

	@Override
	public Room save(Room room) {
		return roomDao.save(room);
	}
	

}
