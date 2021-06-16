package com.miguel.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.miguel.flightreservation.entities.Reservation;

@Component //indicamos que sera un componente de Spring
public class PDFGenerator {
  
	public void generateItinerary(Reservation reservation, String filePath) {
		//creamos una instancia de Document de 'pdfItext' 
		Document document = new Document();
		
			try {
				//Indicamos que el documento sera escrito como PDF en la ruta indicada
				PdfWriter.getInstance(document, new FileOutputStream(filePath));
				
				//Abrimos el documento, una vez que el documento es abierto se podra escribir en el
				document.open();
				//agregamos al documento una tabla mediante un metodo que la generara con la
				//informacion del vuelo y del pasajero
				document.add(generateTable(reservation));
				
				//Cerramos el documento
				document.close();
			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private PdfPTable generateTable(Reservation reservation) {
		  //Creamos una instancia de PdfPTable con dos columnas
		  PdfPTable table = new PdfPTable(2);
		  //Creamos la primera celda con un texto
		  PdfPCell cell = new PdfPCell(new Phrase("Flight Itinerary"));
		  //indicamos que la celda ocupe las dos columnas disponibles
		  cell.setColspan(2);
		  //agregamos la celda a la tabla
		  table.addCell(cell);
		  
		  //Agregamos los detalles del vuelo
		  table.addCell("Airlines:");
		  table.addCell(reservation.getFlightId().getOperatingAirlines());
		  
		  table.addCell("Departure City:");
		  table.addCell(reservation.getFlightId().getDepartureCity());
		  
		  table.addCell("Arrival City:");
		  table.addCell(reservation.getFlightId().getArrivalCity());
		  
		  table.addCell("Flight number:");
		  table.addCell(reservation.getFlightId().getFlightNumber());
		  
		  table.addCell("Date of departure:");
		  table.addCell(reservation.getFlightId().getDateOfDeparture().toString());
		  
		  table.addCell("Date time:");
		  table.addCell(reservation.getFlightId().getEstimatedDepartureTime().toString());
		  
		  //Agregamos los detalles del pasajero
		  cell = new PdfPCell(new Phrase("Passenger Details"));
		  cell.setColspan(2);  //indicamos q la celda ocupe 2 columnas
		  table.addCell(cell);
		  
		  table.addCell("Name:");
		  table.addCell(reservation.getPassengerId().getFirstName()+" "+reservation.getPassengerId().getLastName());
		  table.addCell("Email:");
		  table.addCell(reservation.getPassengerId().getEmail());
		  table.addCell("Phone:");
		  table.addCell(reservation.getPassengerId().getPhone());
		return table;
	}
}
