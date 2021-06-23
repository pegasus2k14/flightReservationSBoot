package com.miguel.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.flightreservation.dto.ReservationRequest;
import com.miguel.flightreservation.entities.Flight;
import com.miguel.flightreservation.entities.Reservation;
import com.miguel.flightreservation.repos.FlightRepository;
import com.miguel.flightreservation.services.ReservationService;


@Controller   //indicamos q es una Clase controladora de Spring MVC
public class ReservationController {
	
  @Autowired	
  private FlightRepository flightRepository;
  
  //inyectando la Clase Service ReservationService
  @Autowired
  private ReservationService reservationService;
  
  //instancia de Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
  @RequestMapping("showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") long flightId, ModelMap modelMap) {
	    
	    LOGGER.info("Inside method showCompleteReservation() flightId "+flightId);
		
		//Recuperamos el vuelo que fue seleccionado en el formulario
		Flight flight = flightRepository.findById(flightId).get();
		
		//Agregando el Vuelo al ModelMap para que se comparta a la vista
		modelMap.addAttribute("flight", flight);
		
		LOGGER.info("Flight is: " + flight);
		//retornamos la vista
		return "completeReservation";
	}
  
  
  @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
  public String completeReservation(@ModelAttribute("reservation") ReservationRequest request, ModelMap modelMap ) {
	  
	  LOGGER.info("Inside method completeReservation() request: "+request);
	  
	  Reservation reservation = reservationService.bookFlight(request);
	  
	  //Pasamos a la vista el Id de la Reservacion creada
	  modelMap.addAttribute("msg","Reservation Created Successfully and the id is: "+reservation.getId());
	  
	  //retornamos el nombre de la vista a la q se direccionara
	  return "reservationConfirmacion";
  }

}
