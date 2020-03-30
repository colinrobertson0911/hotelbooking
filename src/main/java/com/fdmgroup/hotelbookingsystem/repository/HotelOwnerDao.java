package com.fdmgroup.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.HotelOwner;

public interface HotelOwnerDao extends JpaRepository<HotelOwner, Long> {

}
