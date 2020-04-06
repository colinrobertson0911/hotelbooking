package com.fdmgroup.hotelbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@Controller
public class HotelController {

	@Autowired
	HotelService hotelService;

	@GetMapping("")
	public ModelAndView home() {
		return new ModelAndView("mainScreen.jsp", "hotel", hotelService.findByVerifiedEqualsTrue());
	}

	@GetMapping("/Home")
	public ModelAndView homeScreen() {
		return new ModelAndView("mainScreen.jsp", "hotel", hotelService.findByVerifiedEqualsTrue());
	}

	@GetMapping("LoginAsOwner")
	public String login() {
		return "loginOwner.jsp";
	}

	@PostMapping("SearchByCity")
	public ModelAndView searchByCity(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {
		List<Hotel> hotelList = hotelService.findByCityAndVerifiedIsTrue(hotel.getCity());
		if (hotelList.isEmpty()) {
			model.addAttribute("errorMessage", "Not Hotels in that city");
			return new ModelAndView("mainScreen.jsp", "hotel", hotelService.findByVerifiedEqualsTrue());
		}
		return new ModelAndView("mainScreen.jsp", "hotel", hotelList);
	}
	
	@GetMapping("SeeHotel")
	public ModelAndView seeHotel(@RequestParam("hotelId")Long hotelId) {
		return new ModelAndView("WEB-INF/seeHotel.jsp", "hotel", hotelService.retrieveOne(hotelId));
	}
	
	@PostMapping("bookingPage")
	public ModelAndView bookingPage(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {
		Hotel hotel2 = hotelService.retrieveOne(hotel.getHotelId());
		return new ModelAndView("WEB-INF/bookingPage.jsp", "hotel", hotelService.retrieveOne(hotel2.getHotelId()));
		
	}
	
	@PostMapping("SearchByRoomType")
	public ModelAndView searchByRoomType(@ModelAttribute("room")Room room, ModelMap model) {
		List<Hotel> hotelList = hotelService.findByVerifiedAndRoomType(room.getRoomType());
		if(hotelList.isEmpty()) {
			model.addAttribute("errorRoomTypeMessage", "No Rooms of that type");
			return new ModelAndView("mainScreen.jsp", "hotel", hotelService.findByVerifiedEqualsTrue());
		}
		return new ModelAndView("mainScreen.jsp", "hotel", hotelList);
	}
	
//	@PostMapping("SearchByAvailability")
//	public ModelAndView searchbyAvailability(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {
//		List<Hotel> hotelList = hotelService.findByAvailability();
//	}
	
	

}
