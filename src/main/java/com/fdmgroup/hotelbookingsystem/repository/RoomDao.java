package com.fdmgroup.hotelbookingsystem.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.model.RoomType;

public interface RoomDao extends JpaRepository<Room, Long>{

	List<Room> findByRoomType(@Param("roomType")RoomType roomType);

	Room findByRoomNumber(@Param("roomNumber")int roomNumber);

	List<Room> findByPrice(@Param("price")BigDecimal price);

	
	
}
