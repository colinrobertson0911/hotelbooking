package com.fdmgroup.hotelbookingsystem.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.hotelbookingsystem.model.Bookings;
import com.fdmgroup.hotelbookingsystem.model.Extras;
import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.services.BookingService;
import com.fdmgroup.hotelbookingsystem.services.HotelService;
import com.fdmgroup.hotelbookingsystem.services.RoomService;

@Controller
public class HotelController {

	public static final String SESSION_ATTRIBUTE_BOOKINGID = "BOOKINGID";

	@Autowired
	HotelService hotelService;

	@Autowired
	RoomService roomService;

	@Autowired
	BookingService bookingService;

	@GetMapping("")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("mainScreen.jsp");
		modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
		modelAndView.addObject("visabilityMessage", "All Hotels");
		modelAndView.addObject("allRooms", roomService.findAll());
		return modelAndView;
	}

	@GetMapping("/Home")
	public ModelAndView homeScreen() {
		ModelAndView modelAndView = new ModelAndView("mainScreen.jsp");
		modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
		modelAndView.addObject("visabilityMessage", "All Hotels");
		modelAndView.addObject("allRooms", roomService.findAll());
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
			modelAndView.addObject("allRooms", roomService.findAll());
			modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
			return modelAndView;
		}
		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("visabilityMessage", "Hotels in " + hotel.getCity());
		modelAndView.addObject("hotel", hotelList);
		modelAndView.addObject("allRooms", roomService.findAll());
		return modelAndView;
	}

	@GetMapping("SeeHotel")
	public ModelAndView seeHotel(@RequestParam("hotelId") Long hotelId) {
		return new ModelAndView("WEB-INF/seeHotel.jsp", "hotel", hotelService.retrieveOne(hotelId));
	}

	@GetMapping("bookingPage")
	public ModelAndView bookingPage(ModelMap model, @RequestParam("hotelId") long hotelId,
			@RequestParam("roomId") long roomId) {
		ModelAndView modelAndView = new ModelAndView();
		Hotel hotel = hotelService.retrieveOne(hotelId);
		Room room = roomService.retrieveOne(roomId);
		modelAndView.setViewName("WEB-INF/bookingPage.jsp");
		modelAndView.addObject("hotel", hotel);
		modelAndView.addObject("room", room);
		modelAndView.addObject("bookings", new Bookings());
		if (hotel.isAirportTransfers() == true) {
			modelAndView.addObject("Extras", EnumSet.allOf(Extras.class));
		} else {
			modelAndView.addObject("Extras", EnumSet.of(Extras.NO_EXTRAS));
		}
		return modelAndView;
	}

	@PostMapping("BookingSubmit")
	public ModelAndView bookingSubmit(@ModelAttribute("bookings") Bookings bookings, ModelMap model,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		BigDecimal extraCosts = bookings.getExtras().getPrice();
		bookings.setExtrasPrice(extraCosts);
		BigDecimal finaltotal = bookingService.calculateTotalPrice(bookings);
		bookings.setTotalPrice(finaltotal);
		bookingService.save(bookings);
		Long bookingId1 = bookingService.retrieveOne(bookings.getBookingId()).getBookingId();
		session.setAttribute(SESSION_ATTRIBUTE_BOOKINGID, bookingId1);
		modelAndView.addObject("bookings", bookings);
		modelAndView.setViewName("WEB-INF/bookingConfirmation.jsp");
		return modelAndView;
	}

	@PostMapping("BookingConfirmationSubmit")
	public ModelAndView bookingConfirmationSubmit(@ModelAttribute("bookings") Bookings bookings, ModelMap model,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Object idFromSession = session.getAttribute("BOOKINGID");
		String bookingIdString = idFromSession.toString();
		Long bookingId = Long.parseLong(bookingIdString);
		Bookings bookingFromDataBase = bookingService.retrieveOne(bookingId);

		String hotelName = bookingFromDataBase.getHotel();
		Hotel hotel = hotelService.findByHotelName(hotelName);
		hotel.getBookings().add(bookingFromDataBase);
		hotelService.save(hotel);

		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("ownerMessage", "Booking Confirmed");
		modelAndView.addObject("visabilityMessage", "All Hotels");
		modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
		modelAndView.addObject("allRooms", roomService.findAll());
		return modelAndView;
	}

	@GetMapping("CancelBackToMain")
	public ModelAndView cancelBackToMain(HttpSession session) {
		Object idFromSession = session.getAttribute("BOOKINGID");
		String bookingIdString = idFromSession.toString();
		Long bookingId = Long.parseLong(bookingIdString);

		bookingService.deleteById(bookingId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("ownerMessage", "Booking Cancelled");
		modelAndView.addObject("visabilityMessage", "All Hotels");
		modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
		modelAndView.addObject("allRooms", roomService.findAll());

		return modelAndView;

	}

	@PostMapping("SearchByRoomType")
	public ModelAndView searchByRoomType(@ModelAttribute("room") Room room, ModelMap model) {
		List<Hotel> hotelList = hotelService.findByVerifiedAndRoomType(room.getRoomType());
		ModelAndView modelAndView = new ModelAndView();
		if (hotelList.isEmpty()) {
			modelAndView.setViewName("mainScreen.jsp");
			modelAndView.addObject("errorRoomTypeMessage", "No Rooms of that type");
			modelAndView.addObject("visabilityMessage", "All Hotels");
			modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
			modelAndView.addObject("allRooms", roomService.findAll());
			return modelAndView;
		}
		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("hotel", hotelList);
		modelAndView.addObject("visabilityMessage", "Hotels With " + room.getRoomType() + " rooms");
		modelAndView.addObject("allRooms", roomService.findAll());
		return modelAndView;
	}

	@PostMapping("SearchByAvailability")
	public ModelAndView searchbyAvailability(
			@RequestParam(name = "checkInDate", defaultValue = "") String checkInDateString,
			@RequestParam(name = "checkOutDate", defaultValue = "") String checkOutDateString, ModelMap model) {
		LocalDate checkInDate = LocalDate.parse(checkInDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate checkOutDate = LocalDate.parse(checkOutDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Hotel> hotelList = hotelService.findByAvailabilityAndVerifiedWithSpecifiedDates(checkInDate, checkOutDate);
		ModelAndView modelAndView = new ModelAndView();
		if (hotelList.isEmpty()) {
			modelAndView.setViewName("mainScreen.jsp");
			modelAndView.addObject("errorAvailabilityMessage", "No Rooms available");
			modelAndView.addObject("visabilityMessage", "All Hotels");
			modelAndView.addObject("hotel", hotelService.findByVerifiedEqualsTrue());
			modelAndView.addObject("allRooms", roomService.findAll());
			return modelAndView;
		}

		modelAndView.setViewName("mainScreen.jsp");
		modelAndView.addObject("visabilityMessage", "Hotels available between " + checkInDate + " and " + checkOutDate);
		modelAndView.addObject("hotel", hotelList);
		modelAndView.addObject("allRooms", roomService.findAll());
		return modelAndView;

	}

}
