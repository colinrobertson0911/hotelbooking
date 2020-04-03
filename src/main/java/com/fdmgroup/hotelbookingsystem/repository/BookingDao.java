package com.fdmgroup.hotelbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.Booking;
import com.fdmgroup.hotelbookingsystem.model.Room;

public interface BookingDao  extends JpaRepository<Booking, Long>{


}
