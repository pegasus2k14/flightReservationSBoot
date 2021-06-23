package com.miguel.flightreservation.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.flightreservation.dto.UpdateReservationRequest;
import com.miguel.flightreservation.entities.Reservation;
import com.miguel.flightreservation.repos.ReservationRepository;
import com.miguel.flightreservation.util.EmailUtil;

@RestController    //indicamos q es un Controlador REST de Spring
@CrossOrigin  
public class ReservationRESTController {
	
	//inyectamos el repositorio de la Reservacion
	@Autowired
	private ReservationRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRESTController.class);
  
	//indicamos el path para acceder al metodo y en este incluimos como parametro el ID de la
	//reservacion a recuperar
	@RequestMapping("/reservations/{id}") 
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside method findReservation() for id: "+id);
		
		//Recuperamos la reservacion en base a su Id
		 Reservation reservation = repository.findById(id).get();
		
		 return reservation;
	}
	
	
	//Metodo para actualizar una Reservacion, retornara la reservacion ya actualizada
	@RequestMapping("/reservations")   //path para acceder al metodo
	public Reservation  updateReservation(@RequestBody UpdateReservationRequest request) {
		LOGGER.info("Inside method updateReservation()  for: "+ request);
		
		//recuperamos la Reservacion en base a su Id
		Reservation reservation = repository.findById(request.getIdReservation()).get();
		//modificamos los valores de la reservacion
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		
		LOGGER.info("Saving changes on reservation");
		//Persistiendo los cambios en la BD
		 Reservation updatedReservation = repository.save(reservation);
		 return updatedReservation;
		
	}
	
	
}
