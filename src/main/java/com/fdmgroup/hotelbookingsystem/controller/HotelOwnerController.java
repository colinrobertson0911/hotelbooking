package com.fdmgroup.hotelbookingsystem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;

@Controller
public class HotelOwnerController {

public final static String SESSION_ATTRIBUTE_HOTELOWNER = "HOTELOWNER";
	
	@Autowired
	HotelOwnerService hotelOwnerService;
	
	@GetMapping("/")
	public String main() {
		return "MainScreen.jsp";
	}
	
	@GetMapping("LoginAsOwner")
	public String login() {
		return "loginOwner.jsp";
	}
	
	@PostMapping("LoginSubmit")
	public ModelAndView loginSubmit(@ModelAttribute("HotelOwner")HotelOwner hotelOwner, 
			ModelMap model, HttpSession session) { 
		HotelOwner hotelownerfromdatabase = hotelOwnerService.findByUsernameAndPassword(hotelOwner.getUsername(),
				hotelOwner.getPassword());
		
		if (hotelownerfromdatabase == null) {
			model.addAttribute("errorMessage", "Incorrect username or password");
			return new ModelAndView("loginOwner.jsp");
		}
		
		session.setAttribute(SESSION_ATTRIBUTE_HOTELOWNER, hotelownerfromdatabase);
		return new ModelAndView("WEB-INF/OwnerHotels.jsp");
		
	}
	
	
	}
	

