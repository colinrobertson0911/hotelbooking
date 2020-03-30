package com.fdmgroup.hotelbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.repository.HotelDao;

@Service
public class HotelService {
	
	@Autowired
	HotelDao hotelDao;

	public List<Hotel> findAll() {
		return hotelDao.findAll();
	}

	public Hotel save(Hotel hotel) {
		return hotelDao.save(hotel);
	}

}
