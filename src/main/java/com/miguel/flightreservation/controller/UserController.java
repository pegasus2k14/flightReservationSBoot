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

import com.miguel.flightreservation.entities.User;
import com.miguel.flightreservation.repos.UserRepository;

@Controller  //indicamos q es una Clase controladora de Spring MVC
public class UserController {
	//inyectamos una instancia de la clase repositorio
	@Autowired
	private UserRepository repository;
	
	//instancia de Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	//Metodo para direccionar a la pagina de registro de usuarios
	@RequestMapping("/showReg")  //path para acceder al metodo
	public String showRegistrationPage() {
		LOGGER.info("Inside method showRegistrationPage()");
	  return "/login/registerUser";	
	}
	
	//Metodo para guardar un nuevo User
	@RequestMapping(value = "/registerUser",method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside method register() use: "+user);
		//Guardando el nuevo usuario
        repository.save(user); 	
		return "/login/login";
	}
	
	
	//Metodo para direccionar a la pagina de Login de usuarios
		@RequestMapping("/showLogin")  //path para acceder al metodo
		public String showLoginPage() {
			LOGGER.info("Inside method showLoginPage()");
		  return "/login/login";	
		}
		
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
	     
		 LOGGER.info("Inside Method userLogin() and the email is "+email);
		 
		//Recuperamos una instancia de User en base a su Eamil
		User user = repository.findByEmail(email);
		
		//comprobamos la  contrase??a q viene de la BD con la q viene del formulario
		if(user.getPassword().equals(password)) {
			return "findflights";
		}else {
			modelMap.addAttribute("mensaje", "Invalid username or password. Please try again..");
		}
		
		return "/login/login";
	}
}
