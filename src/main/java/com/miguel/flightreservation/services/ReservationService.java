package com.miguel.flightreservation.services;

import com.miguel.flightreservation.dto.ReservationRequest;
import com.miguel.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
