package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.sql.Date;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fuentes.Cita;
import fuentes.CitaMedica;
import fuentes.EspacioAgenda;
import fuentes.EspecialidadMedica;
import fuentes.GestorAgenda;
import fuentes.Tarea;
import fuentes.VentanaAgenda;

public class EspacioAgendaTest {

	private GestorAgenda gestor;
	private VentanaAgenda ventana;
	private EspacioAgenda espacioTarea;
	private EspacioAgenda espacioCita;
	private EspacioAgenda espacioCitaMedica;
	private EspacioAgenda espacioAgenda;

	@Mock
	Date fechaHoraMock;
	@Mock
	Color colorFondoMock;
	

	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		gestor = new GestorAgenda();
		ventana = new VentanaAgenda( 100, 100 , gestor);
 // Creamos una ventana para el espacio
	     
	    
	    // Creamos un objeto Date para la fecha
	    Date fecha = new Date(0);
	    
	    // Duración de 60 minutos
	    int duracion = 60;
	    
	    // Creamos un espacio de tipo "Tarea"
	 espacioTarea = EspacioAgenda.crearNuevoEspacio("Tarea", ventana, fecha, duracion);
	   espacioCita = EspacioAgenda.crearNuevoEspacio("Cita", ventana, fecha, duracion);
	   espacioCitaMedica = EspacioAgenda.crearNuevoEspacio("Cita médica", ventana, fecha, duracion);
	   espacioAgenda = EspacioAgenda.crearNuevoEspacio("Cita médica", ventana, fecha, duracion);
		 
		  }

	@Test
	public void testCrearNuevoEspacio() {
		
	    assertNotNull(espacioTarea);
	    assertTrue(espacioTarea instanceof Tarea);
	    
	    // Creamos un espacio de tipo "Cita"
	  
	    assertNotNull(espacioCita);
	    assertTrue(espacioCita instanceof Cita);
	    
	    // Creamos un espacio de tipo "Cita médica"
	   
	    assertNotNull(espacioCitaMedica);
	    assertTrue(espacioCitaMedica instanceof CitaMedica);
	    assertEquals(EspecialidadMedica.GENERAL, ((CitaMedica)espacioCitaMedica).getEspecialidad());
	    
	
	}

	
	@Test
	public void testGetVentana() {
		
		assertEquals(ventana, espacioTarea.getVentana());
	}

	@Test
	public void testSetVentana() {
		espacioTarea.setVentana(ventana);
		assertEquals(ventana, espacioTarea.getVentana());
	
	}

	@Test
	public void testSetX() {
		espacioTarea.setX(40);
		assertEquals(40, espacioTarea.getX());

	}

	@Test
	public void testSetY() {
		espacioTarea.setY(40);
		assertEquals(40, espacioTarea.getY());
	}

	@Test
	public void testGetFechaHora() {
	
        espacioTarea.setFechaHora(fechaHoraMock);
        assertEquals(fechaHoraMock, espacioTarea.getFechaHora());

	}

	@Test
	public void testSetFechaHora() {
		 espacioTarea.setFechaHora(fechaHoraMock);
	     assertEquals(fechaHoraMock, espacioTarea.getFechaHora());
	}

	@Test
	public void testGetDuracionMins() {
		assertEquals(60, espacioTarea.getDuracionMins());
	}

	@Test
	public void testSetDuracionMins() {
		espacioTarea.setDuracionMins(50);
		assertEquals(50, espacioTarea.getDuracionMins());
	
	}

	@Test
	public void testGetColorFondo() {
		 espacioTarea.setColorFondo(colorFondoMock);
	     assertEquals(colorFondoMock, espacioTarea.getColorFondo());
	}

	@Test
	public void testSetColorFondo() {
		espacioTarea.setColorFondo(colorFondoMock);
	     assertEquals(colorFondoMock, espacioTarea.getColorFondo());

	}

	@Test
	public void testGetDescripcion() {
		String descripcion = "Descripción de prueba";
        espacioAgenda.setDescripcion(descripcion);
        assertEquals(descripcion, espacioAgenda.getDescripcion());
	}

	@Test
	public void testSetDescripcion() {
		String descripcion = "Descripción de prueba";
        espacioAgenda.setDescripcion(descripcion);
        assertEquals(descripcion, espacioAgenda.getDescripcion());
		
	}

	@Test
	public void testRecalculaPosicion() {
		 
		 espacioAgenda.recalculaPosicion();
		

		    assertEquals(-1, espacioAgenda.getX());
		    assertEquals(-1, espacioAgenda.getY());
	}

	@Test
	public void testContienePunto() {
		

	    assertFalse(espacioAgenda.contienePunto(15, 15));
	    assertFalse(espacioAgenda.contienePunto(5, 5));
	    
	    
	
	}

	@Test
	public void testGetFechaYDuracion() {
		
		
		assertEquals("01/01/1970 01:00 [60]", espacioAgenda.getFechaYDuracion());
	}

	@Test
	public void testEqualsObject() {
		assertEquals(espacioAgenda, espacioCitaMedica);
	}

	@Test
	public void testToString() {
		
		assertEquals(" con  {GENERAL}", espacioAgenda.toString());
	}

	@Test
	public void testMover() {
		
		 espacioTarea.mover(5, 5);
		 assertEquals(5, espacioTarea.getX());
		 assertEquals(5, espacioTarea.getY());
		
	}

}
