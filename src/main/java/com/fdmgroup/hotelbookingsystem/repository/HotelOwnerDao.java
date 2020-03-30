package com.fdmgroup.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.HotelOwner;

public interface HotelOwnerDao extends JpaRepository<HotelOwner, Long> {

	HotelOwner findByHotelOwnerId(Long hotelOwnerId);

	HotelOwner findByEmail(String email);

	HotelOwner findByUsernameAndPassword(String username, String password);

}
