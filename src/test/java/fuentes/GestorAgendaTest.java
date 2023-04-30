package fuentes;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fuentes.EspacioAgenda;
import fuentes.GestorAgenda;
import fuentes.VentanaAgenda;

public class GestorAgendaTest {


	private GestorAgenda gestor;
	private VentanaAgenda ventana;
	private EspacioAgenda espacioTarea;
	private EspacioAgenda espacioCita;
	private EspacioAgenda espacioCitaMedica;
	private EspacioAgenda espacioAgenda;
	private Date fecha;

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
	     fecha = new Date(0);
	    
	    // Duración de 60 minutos
	    int duracion = 60;
	    
	    // Creamos un espacio de tipo "Tarea"
	 espacioTarea = EspacioAgenda.crearNuevoEspacio("Tarea", ventana, fecha, duracion);
	   espacioCita = EspacioAgenda.crearNuevoEspacio("Cita", ventana, fecha, duracion);
	   espacioCitaMedica = EspacioAgenda.crearNuevoEspacio("Cita médica", ventana, fecha, duracion);
	   espacioAgenda = EspacioAgenda.crearNuevoEspacio("Cita médica", ventana, fecha, duracion);
		 
		  }

	
	@Test
	public void testGetVentana() {
		VentanaAgenda vent = gestor.getVentana();
		assertEquals(vent, gestor.getVentana());
	}
	


	@Test
	public void testGetListaAgenda() {
		ArrayList<EspacioAgenda> lista = gestor.getListaAgenda();
		assertEquals(lista, gestor.getListaAgenda());
	}

	@Test
	public void testLanza() {
		gestor.lanza();
	}

	@Test
	public void testLogin() {
		gestor.login();
	
	}

	@Test
	public void testAnyadirEspacio() {
		gestor.anyadirEspacio(espacioAgenda);
		
	}

	@Test
	public void testReiniciaVentanaDeAgenda() {
		gestor.reiniciaVentanaDeAgenda();
		
	}
	@Test
	public void testRecolocaEspacioEnSuOrigen() {
		gestor.recolocaEspacioEnSuOrigen(espacioAgenda);
		
	}
	@Test
	public void testBorrarAgendaActual() {
		gestor.borrarAgendaActual(false);
		gestor.borrarAgendaActual(true);
		
	}



	@Test
	public void testClickEnEspacio() {
		gestor.crearEspacioInteractivo(fecha);
	}

	@Test
	public void testDragEnEspacio() {
		gestor.dragEnEspacio(espacioAgenda,20,20);
	}

	@Test
	public void testFinDragEnEspacio() {
		Point p = new Point();
	
	}

}
