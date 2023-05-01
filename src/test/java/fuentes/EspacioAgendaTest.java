package fuentes;

import static org.junit.Assert.*;


import java.awt.Color;
import java.sql.Date;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fuentes.EspacioAgenda;
import fuentes.GestorAgenda;
import fuentes.VentanaAgenda;

public class EspacioAgendaTest {

	private GestorAgenda gestor;
	private VentanaAgenda ventana;
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
	espacioAgenda = EspacioAgenda.crearNuevoEspacio(ventana, fecha, duracion);
		 
		  }

	@Test
	public void testCrearNuevoEspacio() {
		
	    assertNotNull(espacioAgenda);
	    assertTrue(espacioAgenda instanceof EspacioAgenda);
	
	}

	
	@Test
	public void testGetVentana() {
		
		assertEquals(ventana, espacioAgenda.getVentana());
	}

	@Test
	public void testSetVentana() {
		espacioAgenda.setVentana(ventana);
		assertEquals(ventana, espacioAgenda.getVentana());
	
	}

	@Test
	public void testSetX() {
		espacioAgenda.setX(40);
		assertEquals(40, espacioAgenda.getX());

	}

	@Test
	public void testSetY() {
		espacioAgenda.setY(40);
		assertEquals(40, espacioAgenda.getY());
	}

	@Test
	public void testGetFechaHora() {
	
		espacioAgenda.setFechaHora(fechaHoraMock);
        assertEquals(fechaHoraMock, espacioAgenda.getFechaHora());

	}

	@Test
	public void testSetFechaHora() {
		espacioAgenda.setFechaHora(fechaHoraMock);
	     assertEquals(fechaHoraMock, espacioAgenda.getFechaHora());
	}

	@Test
	public void testGetDuracionMins() {
		assertEquals(60, espacioAgenda.getDuracionMins());
	}

	@Test
	public void testSetDuracionMins() {
		espacioAgenda.setDuracionMins(50);
		assertEquals(50, espacioAgenda.getDuracionMins());
	
	}

	@Test
	public void testGetColorFondo() {
		espacioAgenda.setColorFondo(colorFondoMock);
	     assertEquals(colorFondoMock, espacioAgenda.getColorFondo());
	}

	@Test
	public void testSetColorFondo() {
		espacioAgenda.setColorFondo(colorFondoMock);
	     assertEquals(colorFondoMock, espacioAgenda.getColorFondo());

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
		assertEquals(espacioAgenda, espacioAgenda);
	}

	@Test
	public void testToString() {
		
		assertEquals(" con  {GENERAL}", espacioAgenda.toString());
	}

	@Test
	public void testMover() {
		
		espacioAgenda.mover(5, 5);
		 assertEquals(5, espacioAgenda.getX());
		 assertEquals(5, espacioAgenda.getY());
		
	}

}
