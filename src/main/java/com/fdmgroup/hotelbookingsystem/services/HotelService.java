package com.fdmgroup.hotelbookingsystem.services;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<Hotel> findByAddress(String address){
		return hotelDao.findByAddress(address);
	}

	public Hotel save(Hotel hotel) {
		return hotelDao.save(hotel);
	}
	
	public Hotel retrieveOne(long hotelId) {
		return hotelDao.findByHotelId(hotelId);
		
	}

	public List<Hotel> findByCity(String city) {
		
		return hotelDao.findByCity(city);
	}

}
