package com.miguel.flightreservation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.flightreservation.entities.Flight;
import com.miguel.flightreservation.repos.FlightRepository;

@Controller  //indicamos q es una Clase controladora de Spring MVC
public class FlightController {
	
	//Inyectando repositorio
	@Autowired
	private FlightRepository repository;
  
	@RequestMapping("findflights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, ModelMap modelMap) {
		System.out.println("DATE >>"+departureDate);
		List<Flight> flights = repository.findFlights(from,to,departureDate);
		System.out.println("LONGITUD: "+flights.size());
		
		modelMap.addAttribute("flights", flights);
		
		return "displayFlights";
	}
}
