package com.fdmgroup.hotelbookingsystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;
import com.fdmgroup.hotelbookingsystem.services.HotelService;
import com.fdmgroup.hotelbookingsystem.services.RoomService;

@Controller
public class HotelOwnerController {	
	
	
	@Autowired
	HotelOwnerService hotelOwnerService;

	@Autowired
	HotelService hotelService;
	
	@Autowired
	RoomService roomService;

	@RequestMapping("OwnerHotels")
	public ModelAndView ownerHotels() {
		return new ModelAndView("WEB-INF/ownerHotels.jsp", "hotels", hotelService.findAll());
	}

	@GetMapping("AddHotel")
	public ModelAndView addHotels(@RequestParam("hotelOwnerId")Long hotelOwnerId) {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/addHotel.jsp");
		modelAndView.addObject("hotel", new Hotel());
		modelAndView.addObject("allRooms", roomService.findAll());
		modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		return modelAndView;
	}

	@PostMapping("AddHotelSubmit")
	public ModelAndView addHotelSubmit(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {		
		Optional<Hotel> hotelFromDB = hotelService.findByAddress(hotel.getAddress());
		Long hotelOwnerId = hotel.getOwnerId();
		ModelAndView modelAndView = new ModelAndView();
		if(hotelFromDB.isPresent()) {
			modelAndView.addObject("errorMessage", "Hotel at that address already exists");
			modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
			modelAndView.setViewName("WEB-INF/addHotel.jsp");
			//modelAndView.addObject("hotel", new Hotel());
			//modelAndView.addObject("allRooms", roomService.findAll());
			//modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));			
			return modelAndView;  
		}
		hotelService.save(hotel);
		modelAndView.addObject("successMessage", "Hotel has been added to system, Hotel will be visible once processed by an Administrator");
		modelAndView.setViewName("WEB-INF/ownerHotels.jsp");
		modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		return modelAndView;
	}
	
	@GetMapping("EditHotel")
	public ModelAndView editHotel(@RequestParam("hotelId") long hotelId) {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/editHotel.jsp"); 
		modelAndView.addObject("allRooms", roomService.findAll());
		modelAndView.addObject("hotel", hotelService.retrieveOne(hotelId));
		return modelAndView;
		}
	
	@PostMapping("EditHotelSubmit")
	public ModelAndView editHotelSubmit(@ModelAttribute("hotel") Hotel hotel) {
		hotelService.save(hotel);
		return new ModelAndView("mainScreen.jsp", "hotel", hotelService.findByVerifiedEqualsTrue());
	}
	
	@GetMapping("ReturnToOwnerScreen")
	public ModelAndView returnToOwnerScreen(@RequestParam("hotelOwnerId")Long hotelOwnerId) {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/ownerHotels.jsp");
		modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		return modelAndView;
	}
	
	@GetMapping("NewRoomType")
	public ModelAndView newRoomType(@RequestParam("hotelOwnerId")Long hotelOwnerId) {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/newRoomType.jsp");
		modelAndView.addObject("room", new Room());
		modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		return modelAndView;
	}
	
	@PostMapping("AddNewRoomTypeSubmit")
	public ModelAndView newRoomTypeSubmit(@ModelAttribute("room")Room room, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		Optional<Room> roomFromDB = roomService.findByRoomTypeAndPrice(room.getRoomType(), room.getPrice());
		if(roomFromDB.isPresent()) {
			modelAndView.setViewName("WEB-INF/newRoomType.jsp");
			modelAndView.addObject("errorMessage", "Room type and Price already exist");
			modelAndView.addObject("room", new Room());
		//	modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
			return modelAndView;
		}
		roomService.save(room);
		modelAndView.setViewName("WEB-INF/addHotel.jsp");
		//modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		modelAndView.addObject("hotel", new Hotel());
		modelAndView.addObject("allRooms", roomService.findAll());
		return modelAndView;
	}

}
