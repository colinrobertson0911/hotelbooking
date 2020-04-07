package com.fdmgroup.hotelbookingsystem.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		ModelAndView modelAndView = new ModelAndView("mainScreen.jsp");
		modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
		modelAndView.addObject("visabilityMessage", "All Hotels");
		return modelAndView;
	}

	@GetMapping("/Home")
	public ModelAndView homeScreen() {
		ModelAndView modelAndView = new ModelAndView("mainScreen.jsp");
		modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
		modelAndView.addObject("visabilityMessage", "All Hotels");
		return modelAndView;
	}

	@GetMapping("LoginAsOwner")
	public String login() {
		return "loginOwner.jsp";
	}

	@PostMapping("SearchByCity")
	public ModelAndView searchByCity(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {
		List<Hotel> hotelList = hotelService.findByCityAndVerifiedIsTrue(hotel.getCity());
		ModelAndView modelAndView = new ModelAndView();
		if (hotelList.isEmpty()) {
			modelAndView.setViewName("mainScreen.jsp");
			modelAndView.addObject("errorMessage", "No Hotels in that city");
			modelAndView.addObject("visabilityMessage", "All Hotels");
			modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
			return modelAndView;
		}
		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("visabilityMessage", "Hotels in " + hotel.getCity());
		modelAndView.addObject("hotel", hotelList);
		return modelAndView;
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
		ModelAndView modelAndView = new ModelAndView();
		if(hotelList.isEmpty()) {
			modelAndView.setViewName("mainScreen.jsp");
			modelAndView.addObject("errorRoomTypeMessage", "No Rooms of that type");
			modelAndView.addObject("visabilityMessage", "All Hotels");
			modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
			return modelAndView;
		}
		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("hotel", hotelList);
		modelAndView.addObject("visabilityMessage", "Hotels With " + room.getRoomType() + " rooms");
		return modelAndView;
	}
	
	@PostMapping("SearchByAvailability")
	public ModelAndView searchbyAvailability(@RequestParam(name= "checkInDate", defaultValue="")String checkInDateString, 
			@RequestParam(name= "checkOutDate", defaultValue="")String checkOutDateString,ModelMap model) { 
		LocalDate checkInDate = LocalDate.parse(checkInDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate checkOutDate = LocalDate.parse(checkOutDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Hotel> hotelList = hotelService.findByAvailabilityAndVerifiedWithSpecifiedDates(checkInDate, checkOutDate);
		ModelAndView modelAndView = new ModelAndView();
		if (hotelList.isEmpty()) {
			modelAndView.setViewName("mainScreen.jsp");
			modelAndView.addObject("errorAvailabilityMessage", "No Rooms available");
			modelAndView.addObject("visabilityMessage", "All Hotels");
			modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
			return modelAndView;
		}	
				
		modelAndView.setViewName("mainScreen.jsp");		
		modelAndView.addObject("visabilityMessage", "Hotels available between " + checkInDate + " and " + checkOutDate);
		modelAndView.addObject("hotel", hotelList);
		return modelAndView;

	}
	
	

}
