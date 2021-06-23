package com.miguel.flightreservation.dto;

public class UpdateReservationRequest {
	
	private long idReservation;
	private int numberOfBags;
	private Boolean checkedIn;
	
	//Getters y Setters
	public Long getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	@Override
	public String toString() {
		return "UpdateReservationRequest [idReservation=" + idReservation + ", numberOfBags=" + numberOfBags
				+ ", checkedIn=" + checkedIn + "]";
	}
	
}
