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
import com.fdmgroup.hotelbookingsystem.model.User;
import com.fdmgroup.hotelbookingsystem.services.AdminService;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;
import com.fdmgroup.hotelbookingsystem.services.UserService;

@Controller
public class LoginController {

	public static final String SESSION_ATTRIBUTE_ADMIN = "ADMIN";
	public final static String SESSION_ATTRIBUTE_HOTELOWNER = "HOTELOWNER";

	@Autowired
	AdminService adminService;

	@Autowired
	HotelOwnerService hotelOwnerService;

	@Autowired
	UserService userService;

	@GetMapping("LoginAsAdmin")
	public String adminLogin() {
		return "adminLogin.jsp";
	}

	@PostMapping("LoginOwnerSubmit")
	public ModelAndView loginSubmit(@ModelAttribute("HotelOwner") HotelOwner hotelOwner, ModelMap model,
			HttpSession session) {
		HotelOwner hotelOwnerFromDB = hotelOwnerService.findByUsernameAndPassword(hotelOwner.getUsername(),
				hotelOwner.getPassword());

		if (hotelOwnerFromDB == null) {
			model.addAttribute("errorMessage", "Incorrect username or password");
			return new ModelAndView("loginOwner.jsp");
		}
		
		session.setAttribute(SESSION_ATTRIBUTE_HOTELOWNER, hotelOwnerFromDB);
		return new ModelAndView("WEB-INF/OwnerHotels.jsp", "hotelOwner", hotelOwnerFromDB);

	}

	@PostMapping("LoginAdminSubmit")
	public ModelAndView loginAdminSubmit(@ModelAttribute("User") User user, ModelMap model, HttpSession session) {
		User userfromdatabase = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());

		if (userfromdatabase == null) {
			model.addAttribute("errorMessage", "Incorrect username or password");
			return new ModelAndView("adminLogin.jsp");
		}

		session.setAttribute(SESSION_ATTRIBUTE_HOTELOWNER, userfromdatabase);
		return new ModelAndView("adminHome.jsp");

	}

}