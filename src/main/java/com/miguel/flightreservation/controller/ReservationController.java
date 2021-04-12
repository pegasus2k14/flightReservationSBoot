package com.miguel.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.flightreservation.entities.Flight;
import com.miguel.flightreservation.repos.FlightRepository;


@Controller   //indicamos q es una Clase controladora de Spring MVC
public class ReservationController {
	
  @Autowired	
  private FlightRepository flightRepository;
	
  @RequestMapping("showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") long flightId, ModelMap modelMap) {
		
		//Recuperamos el vuelo que fue seleccionado en el formulario
		Flight flight = flightRepository.findById(flightId).get();
		
		//Agregando el Vuelo al ModelMap para que se comparta a la vista
		modelMap.addAttribute("flight", flight);
		
		//retornamos la vista
		return "completeReservation";
	}

}
