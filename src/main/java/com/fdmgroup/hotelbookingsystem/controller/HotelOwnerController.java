package com.fdmgroup.hotelbookingsystem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@Controller
public class HotelOwnerController {

public final static String SESSION_ATTRIBUTE_HOTELOWNER = "HOTELOWNER";
	
	@Autowired
	HotelOwnerService hotelOwnerService;
	
	@Autowired
	HotelService hotelService;
	


	
	@RequestMapping("OwnerHotels")
	public ModelAndView ownerHotels() {
		return new ModelAndView("WEB-INF/OwnerHotels.jsp", "hotels", hotelService.findAll());
	}
	
	@GetMapping("AddHotel")
	public ModelAndView addHotels() {
		ModelAndView modelAndView = new ModelAndView ("WEB-INF/addHotel.jsp");
		modelAndView.addObject("hotel", new Hotel());
		return modelAndView;
	}
	
	@PostMapping("AddHotelSubmit")
	public ModelAndView addHotelSubmit(@ModelAttribute("hotel")Hotel hotel) {
		hotelService.save(hotel);
		return new ModelAndView("WEB-INF/OwnerHotels.jsp", "hotel", hotelService.findAll());
	}
	
	
}
	

