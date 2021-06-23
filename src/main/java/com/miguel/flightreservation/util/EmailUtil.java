package com.miguel.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component   //indicamos que es un componente de Spring
public class EmailUtil {
	
	@Value("${com.miguel.flightreservation.email.body}")
	private String EMAIL_BODY;

	@Value("${com.miguel.flightreservation.email.subject}")
	private String EMAIL_SUBJECT;

	//Inyectamos una instancia de JavaMailSender
	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
  
	//metodo para enviar el archivo pdf con el intinerario por Email
	//recibe dos parametros (destinatario del Email, ubicacion del archivo PDF)
	public void sendItinerary(String toAddress, String filePath) {
		LOGGER.info("Inside method sendItinerary() toAddress: "+toAddress + " filePath: "+filePath);
		
		//Creamos una instancia de MimeMessage
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			//indicamos la direccion de correo a la que se enviara el Email
			messageHelper.setTo(toAddress);
			//indicamos el asunto del correo
			messageHelper.setSubject(EMAIL_SUBJECT);
			//indicamos el texto del cuerpo del correo
			messageHelper.setText(EMAIL_BODY);
			//Agregamos los elementos adjuntos (el archivo)
			messageHelper.addAttachment("Itinerary.pdf", new File(filePath));
			
			//Enviamos el correo
			mailSender.send(message);
			
		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary() "+ e);
			
			//e.printStackTrace();
		}
	}
}
