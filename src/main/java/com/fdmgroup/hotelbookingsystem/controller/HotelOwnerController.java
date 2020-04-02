package com.fdmgroup.hotelbookingsystem.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
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
		return new ModelAndView("WEB-INF/OwnerHotels.jsp", "hotels", hotelService.findAll());
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
	public String addHotelSubmit(@ModelAttribute("hotel") Hotel hotel, ModelMap model,HttpSession session) {
		HotelOwner hotelOwner = (HotelOwner)session.getAttribute("HOTELOWNER");
				System.out.println("SYSOUT: " + hotelOwner);
		Optional<Hotel> hotelFromDB = hotelService.findByAddress(hotel.getAddress());
		//ModelAndView modelAndView = new ModelAndView();
		if(hotelFromDB.isPresent()) {
			model.addAttribute("errorMessage", "Hotel at that address already exists");
			//modelAndView.setViewName("WEB-INF/addHotel.jsp");
			//modelAndView.addObject("hotel", new Hotel());
			//modelAndView.addObject("allRooms", roomService.findAll());
			//modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
			return "WEB-INF/addHotel.jsp";  
		}
		hotelService.save(hotel);
		model.addAttribute("successMessage", "Hotel has been added to system, Hotel will be available once processed by an Administrator");
		//model.addAttribute("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		//modelAndView.setViewName("WEB-INF/addHotel.jsp");
		//modelAndView.addObject("hotel", new Hotel());
		//modelAndView.addObject("allRooms", roomService.findAll());
		//.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		return "WEB-INF/addHotel.jsp";
	}
	
	@GetMapping("ReturnToMain")
	public ModelAndView returnToMain() {
		ModelAndView modelAndView = new ModelAndView("MainScreen.jsp");
		modelAndView.addObject("hotel", hotelService.findAll());
		modelAndView.addObject("ownerMessage", "Hotels need to be verified by Administration before customers can view them");
		//modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
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
