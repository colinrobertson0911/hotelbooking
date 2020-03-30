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
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@Controller
public class HotelController {

	@Autowired
	HotelService hotelService;

	@GetMapping("")
	public ModelAndView home() {
		return new ModelAndView("MainScreen.jsp", "hotel", hotelService.findAll());
	}

	@GetMapping("/Home")
	public ModelAndView homeScreen() {
		return new ModelAndView("MainScreen.jsp", "hotel", hotelService.findAll());
	}

	@GetMapping("LoginAsOwner")
	public String login() {
		return "loginOwner.jsp";
	}

	@PostMapping("SearchByCity")
	public ModelAndView searchByCity(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {
		List<Hotel> hotelList = hotelService.findByCity(hotel.getCity());
		if (hotelList.isEmpty()) {
			model.addAttribute("errorMessage", "Not Hotels in that city");
			return new ModelAndView("MainScreen.jsp", "hotel", hotelService.findAll());
		}
		return new ModelAndView("MainScreen.jsp", "hotel", hotelList);
	}
	
	@GetMapping("SeeHotel")
	public ModelAndView seeHotel(@RequestParam("hotelId")Long hotelId) {
		return new ModelAndView("WEB-INF/seeHotel.jsp", "hotel", hotelService.retrieveOne(hotelId));
	}

}
