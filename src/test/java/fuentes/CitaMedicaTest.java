package fuentes;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fuentes.Cita;
import fuentes.CitaMedica;
import fuentes.EspecialidadMedica;
import fuentes.GestorAgenda;
import fuentes.VentanaAgenda;


public class CitaMedicaTest {
	
	private CitaMedica cita1;
	private CitaMedica  cita2;
	private CitaMedica  cita3;
	
	@Mock
	VentanaAgenda mockVentanaAgenda;
	@Mock
	JOptionPane jOp;
	@Mock
	Date fechaHora;
	
	@Before
	public void setUp() {
	 MockitoAnnotations.openMocks(this);
	 MockitoAnnotations.openMocks(this);
	GestorAgenda gestor = new GestorAgenda();
	VentanaAgenda ventana = new VentanaAgenda(400,400, gestor);
		cita1 = new CitaMedica(ventana, fechaHora , 2, EspecialidadMedica.CARDIOLOGIA);
		 cita2 = new CitaMedica(ventana, fechaHora , 2, EspecialidadMedica.CARDIOLOGIA);
		 cita3  = new CitaMedica(ventana, fechaHora , 6,EspecialidadMedica.CARDIOLOGIA);

		  
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
		// Crear una cita médica y comprobar su representación en cadena
				VentanaAgenda ventana = Mockito.mock(VentanaAgenda.class);
				Date fechaHora = new Date(0);
				int duracionMins = 30;
				EspecialidadMedica especialidad = EspecialidadMedica.PEDIATRIA;
				CitaMedica cita = new CitaMedica(ventana, fechaHora, duracionMins, especialidad);
				String expected = " con  {PEDIATRIA}";
				assertEquals(expected, cita.toString());
				
	}

	@Test
	public void testEditar() {
	
      cita1.editar();
        
				
	}

	@Test
	public void testCitaMedica() {
		VentanaAgenda ventana = new VentanaAgenda(0, 0, null);
		Date fechaHora = new Date(0);
		int duracionMins = 30;
		EspecialidadMedica tipoMedico = EspecialidadMedica.PEDIATRIA;
		CitaMedica cita = new CitaMedica(ventana, fechaHora, duracionMins, tipoMedico);
		assertEquals(ventana, cita.getVentana());
		assertEquals(fechaHora, cita.getFechaHora());
		assertEquals(duracionMins, cita.getDuracionMins());
		assertEquals(CitaMedica.COLOR_FONDO, cita.getColorFondo());
		assertEquals(tipoMedico, cita.getEspecialidad());
	}

	@Test
	public void testGetEspecialidad() {
		assertEquals(EspecialidadMedica.CARDIOLOGIA, cita1.getEspecialidad());
	}

	@Test
	public void testSetEspecialidad() {
		VentanaAgenda ventana = new VentanaAgenda(0, 0, null);
		Date fechaHora = new Date(0);
		EspecialidadMedica tipoMedico = EspecialidadMedica.CARDIOVASCULAR;
		CitaMedica cita = new CitaMedica(ventana, fechaHora, 60, tipoMedico);
		 tipoMedico = EspecialidadMedica.PEDIATRIA;
		cita.setEspecialidad(tipoMedico);
		assertEquals(tipoMedico, cita.getEspecialidad());
	}

}
