package com.fdmgroup.hotelbookingsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.repository.RoomDao;

@Service
public class RoomService {
	
	@Autowired
	RoomDao roomDao;
	

}
