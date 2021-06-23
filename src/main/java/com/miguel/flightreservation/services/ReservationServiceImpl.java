package com.miguel.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.miguel.flightreservation.dto.ReservationRequest;
import com.miguel.flightreservation.entities.Flight;
import com.miguel.flightreservation.entities.Passenger;
import com.miguel.flightreservation.entities.Reservation;
import com.miguel.flightreservation.repos.FlightRepository;
import com.miguel.flightreservation.repos.PassengerRepository;
import com.miguel.flightreservation.repos.ReservationRepository;
import com.miguel.flightreservation.util.EmailUtil;
import com.miguel.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	//Inyectamos una propiedad desde application.properties
	@Value("${com.miguel.flightreservation.itinerary.dir}")
	private String ITINERARY_DIR;

	//inyectamos el repositorio del Vuelo
	@Autowired
	private FlightRepository flightRepository;
	
	//inyectamos el repositorio del pasajeroç
	@Autowired
	private PassengerRepository passengerRepository;
	
	//Inyectamos el respositorio de Reservaciones
	@Autowired
	private ReservationRepository reservationRepository;
	
	//Inyectamos la Clase PDFGenerator.java
	@Autowired
	private PDFGenerator pdfGenerator;
	//Inyectamos la Clase EmailUtil.java
    @Autowired
	private EmailUtil emailUtil;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
    
	//Metodo que se ocupa de crear una reservacion
    @Override
	public Reservation bookFlight(ReservationRequest request) {
    	LOGGER.info("Inside method bookFlight() ");
    	
		//Realizar Pago mediante una pasarela de Pagos
		   //Aqui se podria recuperar la informacion de la targeta de credito y apoyarse en un 
		   //Servicio Web de terceros para realizar el pago, si el pago fallara se realizaria una excepcion aqui
		
		//Recuperarmos el identificador del vuelo
		long flightId = request.getFlightId();
		
		LOGGER.info("Fetching flight for: "+flightId);
		//recuperamos el Vuelo
		Flight flight = flightRepository.findById(flightId).get();
		
		
		//Creamos un pasajero en base a los datos recuperados del Formulario
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		
		LOGGER.info("Saving the passenger: "+passenger);
		//Persitimos al pasajero
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		//Creando la reserva (relacionando el pasajero con el vuelo)
		Reservation reservation = new Reservation();
		reservation.setPassengerId(savedPassenger);  //asinamos a la reservacion el pasajero
		reservation.setFlightId(flight);             //asignamos a la reservacion el vuelo
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving reservation: "+reservation);
		
		//Persistimos la Reservacion
		Reservation savedReservation = reservationRepository.save(reservation);
		
		//Generamos el PDF con el itinerario
		String filePath = ITINERARY_DIR+savedReservation.getId()+".pdf";
		
		LOGGER.info("Generating the Itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Sending Itinerary for Email");
		//Enviamos el archivo con el itinerario por Email
		emailUtil.sendItinerary(savedPassenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
