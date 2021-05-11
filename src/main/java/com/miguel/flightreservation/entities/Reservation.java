package com.miguel.flightreservation.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reservation extends AbstractEntity{
  //Atributos
	private Boolean checkedIn;
	private int numberOfBags;
    @OneToOne         //relacion uno a uno entre Reservacion y pasajero
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
	private Passenger passengerId;
    @OneToOne         //relacion uno a uno entre Reservacion y vuelo
	@JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flightId;
	private Timestamp created;
	
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}
	public Passenger getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Passenger passengerId) {
		this.passengerId = passengerId;
	}
	public Flight getFlightId() {
		return flightId;
	}
	public void setFlightId(Flight flightId) {
		this.flightId = flightId;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
}
