package com.fdmgroup.hotelbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.services.HotelService;

@Controller
public class HotelController {

	@Autowired
	HotelService hotelService;
	
	@GetMapping("/")
	public String main() {
		return "MainScreen.jsp";
	}
	
	@GetMapping("LoginAsOwner")
	public String login() {
		return "loginOwner.jsp";
	}
	@GetMapping("")
	public ModelAndView home() {
		return new ModelAndView("MainScreen.jsp", "hotel", hotelService.findAll());
	}
	
}
