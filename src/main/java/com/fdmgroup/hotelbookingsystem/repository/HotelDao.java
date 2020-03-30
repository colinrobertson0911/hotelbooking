package com.fdmgroup.hotelbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Long> {

	Hotel findByHotelId(long hotelId);
	
	List<Hotel> findByCity(String city);
	

}
