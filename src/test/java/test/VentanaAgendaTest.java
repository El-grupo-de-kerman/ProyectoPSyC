package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fuentes.CitaMedica;
import fuentes.EspacioAgenda;
import fuentes.EspecialidadMedica;
import fuentes.GestorAgenda;
import fuentes.VentanaAgenda;

public class VentanaAgendaTest {

	
	private GestorAgenda gestor;
	private static final int NUM_DIAS = 7;
	private VentanaAgenda ventana; 
	private EspacioAgenda espacioAgenda;
	
	
	
	
	@Before
	public void setUp() {
	 MockitoAnnotations.openMocks(this);
	
	 gestor = new GestorAgenda();
 	 ventana = new VentanaAgenda(400,400, gestor);
 	  // Creamos un objeto Date para la fecha
     Date fecha = new Date(646464644);
     
     // Duración de 60 minutos
     int duracion = 60;
     espacioAgenda = EspacioAgenda.crearNuevoEspacio("Cita médica", ventana, fecha, duracion);
     gestor.anyadirEspacio(espacioAgenda);
	

	}


	@Test
	public void testAnyadirGestorRaton() {
		 
		    ventana.anyadirGestorRaton(espacioAgenda);
		    assertNotNull(ventana.getPanel().getMouseListeners()); // Comprobamos que se ha añadido un gestor de ratón al panel
	}

	@Test
	public void testRecalculaPosiciones() {
		 ventana.recalculaPosiciones();
	
	}

	@Test
	public void testGetFechaInicial() {
		
		Date fecha = Date.valueOf(LocalDate.now().toString());
		ventana.setFechaInicial(fecha);
		assertEquals(fecha, ventana.getFechaInicial());
	
	}

	@Test
	public void testGetPanel() {
		assertNotNull(ventana.getPanel());
	}

	@Test
	public void testGetBotonTrash() {
		assertNotNull(ventana.getBotonTrash());
	}

	@Test
	public void testGetFechaFinal() {
		Date fecha = Date.valueOf(LocalDate.now().toString());
		ventana.setFechaInicial(fecha);
		System.out.println(ventana.getFechaFinal());
		Date expected = new Date( ventana.getFechaInicial().getTime() + 24*3600000L * NUM_DIAS - 60000L );
		assertEquals(expected, ventana.getFechaFinal());
	}

	@Test
	public void testSetFechaInicial() {
		Date fecha = Date.valueOf(LocalDate.now().toString());
		ventana.setFechaInicial(fecha);
		assertEquals(fecha, ventana.getFechaInicial());
	}

	@Test
	public void testMueveDias() {
		Date fecha = Date.valueOf(LocalDate.now().toString());
		ventana.setFechaInicial(fecha);
		Date fecha2 = new Date( ventana.getFechaInicial().getTime() + 7 * 24*3600000L );
		ventana.mueveDias(7);
		assertEquals(fecha2, ventana.getFechaInicial());
		
	}

	@Test
	public void testSetMensajeSuperior() {
		 ventana.setMensajeSuperior("Prueba");

	}

	@Test
	public void testCerrar() {
		ventana.cerrar();
	}

	@Test
	public void testGetFechaDePunto() {
	
        int x = 100;
        int y = 200;
        
      
        java.util.Date fecha = ventana.getFechaDePunto(x, y);

	}

	

	@Test
	public void testGetAlturaPixels() {
	
		assertEquals(0, ventana.getAlturaPixels(60));
	}

	@Test
	public void testGetAnchoColumna() {
		
		assertEquals(0, ventana.getAnchoColumna());
	}

}
