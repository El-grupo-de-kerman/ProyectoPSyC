package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import fuentes.Cita;
import fuentes.GestorAgenda;
import fuentes.VentanaAgenda;

public class CitaTest {
	
	private Cita cita1;
	private Cita cita2;
	private Cita cita3;
	private GestorAgenda gestor;
	private VentanaAgenda ventana;
	
	
	@Mock
	Date fechaHora;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		gestor = new GestorAgenda();
		ventana = new VentanaAgenda(400,400, gestor);
		  cita1 = new Cita(ventana, fechaHora , 2);
		  cita2  = new Cita(ventana, fechaHora , 2);
		  cita3  = new Cita(ventana, fechaHora , 6);

		  
		 cita1.setPersona("Xabi");
		 cita2.setPersona("Xabi");
		 cita3.setPersona("Nico");
		 
		  }

	@Test
	public void testEqualsObject() {
		assertTrue(cita1.equals(cita2));
		assertFalse(cita2.equals("aa"));
		assertFalse(cita3.equals(cita2));
	}

	@Test
	public void testToString() {
		
		assertEquals(" con Xabi", cita1.toString());
	}



	@Test
	public void testGetPersona() {
		assertEquals("Xabi", cita2.getPersona());
	}

	@Test
	public void testSetPersona() {
		cita2.setPersona("Xabi");
		assertEquals("Xabi", cita2.getPersona());
	}

	@Test
	public void testEditar() {
		cita1.editar();
	}

}
