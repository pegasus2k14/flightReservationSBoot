package com.miguel.flightreservation.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



//Designa una clase cuya información de asignación se aplica a las entidades que heredan de ella. 
//Una superclase asignada no tiene ninguna tabla independiente definida para ella
@MappedSuperclass
public class AbstractEntity {
	@Id   //indicamos q este atributo es la llave primaria
	@GeneratedValue(strategy = GenerationType.AUTO) //indicamos q este campo se incrementara automaticamente en la BD
	private long id;

	//Getters y Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

