package com.fdmgroup.hotelbookingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.HotelOwner;

public interface HotelOwnerDao extends JpaRepository<HotelOwner, Long> {

	HotelOwner findByHotelOwnerId(Long hotelOwnerId);

}
