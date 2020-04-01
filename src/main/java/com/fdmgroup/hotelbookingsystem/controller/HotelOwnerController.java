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
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@Controller
public class HotelOwnerController {	

	@Autowired
	HotelOwnerService hotelOwnerService;

	@Autowired
	HotelService hotelService;

	@RequestMapping("OwnerHotels")
	public ModelAndView ownerHotels() {
		return new ModelAndView("WEB-INF/OwnerHotels.jsp", "hotels", hotelService.findAll());
	}

	@GetMapping("AddHotel")
	public ModelAndView addHotels(@RequestParam("hotelOwnerId") Long hotelOwnerId) {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/addHotel.jsp");
		modelAndView.addObject("hotel", new Hotel());
		modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
		return modelAndView;
	}

	@PostMapping("AddHotelSubmit")
	public String addHotelSubmit(@ModelAttribute("hotel") Hotel hotel, ModelMap model) {
		Optional<Hotel> hotelFromDB = hotelService.findByAddress(hotel.getAddress());
		if(hotelFromDB.isPresent()) {
			model.addAttribute("errorMessage", "Hotel at that address already exists");
			return "WEB-INF/addHotel.jsp";  
		}
		hotelService.save(hotel);
		model.addAttribute("successMessage", "Hotel has been added to system, Hotel will be available once processed by an Administrator");
		 return "WEB-INF/addHotel.jsp";
	}

}
