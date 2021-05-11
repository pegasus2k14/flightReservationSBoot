package com.miguel.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguel.flightreservation.dto.ReservationRequest;
import com.miguel.flightreservation.entities.Flight;
import com.miguel.flightreservation.entities.Passenger;
import com.miguel.flightreservation.entities.Reservation;
import com.miguel.flightreservation.repos.FlightRepository;
import com.miguel.flightreservation.repos.PassengerRepository;
import com.miguel.flightreservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	//inyectamos el repositorio del Vuelo
	@Autowired
	private FlightRepository flightRepository;
	
	//inyectamos el repositorio del pasajeroç
	@Autowired
	private PassengerRepository passengerRepository;
	
	//Inyectamos el respositorio de Reservaciones
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		//Realizar Pago mediante una pasarela de Pagos
		   //Aqui se podria recuperar la informacion de la targeta de credito y apoyarse en un 
		   //Servicio Web de terceros para realizar el pago, si el pago fallara se realizaria una excepcion aqui
		
		//Recuperarmos el identificador del vuelo
		long flightId = request.getFlightId();
		
		//recuperamos el Vuelo
		Flight flight = flightRepository.findById(flightId).get();
		
		//Creamos un pasajero en base a los datos recuperados del Formulario
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		
		//Persitimos al pasajero
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		//Creando la reserva (relacionando el pasajero con el vuelo)
		Reservation reservation = new Reservation();
		reservation.setPassengerId(savedPassenger);  //asinamos a la reservacion el pasajero
		reservation.setFlightId(flight);             //asignamos a la reservacion el vuelo
		reservation.setCheckedIn(false);
		
		
		//Persistimos la Reservacion
		Reservation savedReservation = reservationRepository.save(reservation);
		
		return savedReservation;
	}

}
