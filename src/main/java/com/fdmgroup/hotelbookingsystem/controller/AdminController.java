package com.fdmgroup.hotelbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;

@Controller
public class AdminController {

@Autowired
HotelOwnerService hotelOwnerService;

@GetMapping("AllOwners")
public ModelAndView hotelOwners() {
	return new ModelAndView("WEB-INF/AllOwners.jsp", "hotelOwners", hotelOwnerService.findAll());
}

@GetMapping("SeeHotelOwner")
public ModelAndView editHotelOwners(@RequestParam("hotelOwnerId")long hotelOwnerId) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("WEB-INF/EditOwners.jsp");
	modelAndView.addObject("hotelOwner", hotelOwnerService.retrieveOne(hotelOwnerId));
	return modelAndView;
}

@PostMapping("EditHotelOwnerSubmit")
public ModelAndView hotelOwnersUpdated(@ModelAttribute("hotelOwner")HotelOwner hotelOwner) {
	hotelOwnerService.save(hotelOwner);
	return new ModelAndView("WEB-INF/AllOwners.jsp", "hotelOwners", hotelOwnerService.findAll());
}
}
