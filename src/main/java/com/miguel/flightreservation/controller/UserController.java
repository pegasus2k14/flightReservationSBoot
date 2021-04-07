package com.miguel.flightreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //indicamos q es una Clase controladora de Spring MVC
public class UserController {

	//Metodo para direccionar a la pagina de registro de usuarios
	@RequestMapping("/showReg")  //path para acceder al metodo
	public String showRegistrationPage() {
	  return "login/registerUser";	
	}
}
