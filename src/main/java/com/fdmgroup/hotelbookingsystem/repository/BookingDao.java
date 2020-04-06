package com.fdmgroup.hotelbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.Bookings;
import com.fdmgroup.hotelbookingsystem.model.Room;

public interface BookingDao  extends JpaRepository<Bookings, Long>{


}
