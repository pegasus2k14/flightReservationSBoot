package com.miguel.flightreservation.controller;

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

	//Metodo para direccionar a la pagina de registro de usuarios
	@RequestMapping("/showReg")  //path para acceder al metodo
	public String showRegistrationPage() {
	  return "/login/registerUser";	
	}
	
	//Metodo para guardar un nuevo User
	@RequestMapping(value = "/registerUser",method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		//Guardando el nuevo usuario
        repository.save(user); 	
		return "/login/login";
	}
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		
		//Recuperamos una instancia de User en base a su Eamil
		User user = repository.findByEmail(email);
		
		//comprobamos la  contraseña q viene de la BD con la q viene del formulario
		if(user.getPassword().equals(password)) {
			return "findflights";
		}else {
			modelMap.addAttribute("mensaje", "Invalid username or password. Please try again..");
		}
		
		return "/login/login";
	}
}
