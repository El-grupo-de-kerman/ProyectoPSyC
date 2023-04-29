package test;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fuentes.CitaMedica;
import fuentes.EspecialidadMedica;
import fuentes.GestorAgenda;
import fuentes.Tarea;
import fuentes.TipoTarea;
import fuentes.VentanaAgenda;

public class TareaTest {
	
	private VentanaAgenda ventana;
	private GestorAgenda gestor;
	private Date fechaHora;
	private Tarea tarea;
	private Tarea tarea2;
	
	
   

   
	
	@Before
	public void setUp() {
		
	 MockitoAnnotations.openMocks(this);
	 gestor = new GestorAgenda();
	 ventana = new VentanaAgenda(400,400, gestor);
	 fechaHora = new Date(0);
	 tarea = new Tarea(ventana, fechaHora, 60);
	 tarea2 = new Tarea(ventana, fechaHora, 60);
	

	}

	@Test
	public void testEqualsObject() {
		assertTrue(tarea.equals(tarea2));
	}

	@Test
	public void testToString() {
		String expected = tarea.toString();
		String actual = tarea.getDescripcion() + " " + tarea.getTipoTarea();
		assertEquals(expected, actual);
	}

	
	@Test
	public void testGetTipoTarea() {
		assertEquals(tarea.getTipoTarea(), TipoTarea.OTROS);
	}

	@Test
	public void testSetTipoTarea() {
		tarea.setTipoTarea(TipoTarea.TRABAJO);
		assertEquals(tarea.getTipoTarea(), TipoTarea.TRABAJO);
	
	}

	@Test
	public void testEditar() {
		tarea.editar();
        TipoTarea nuevoTipo = TipoTarea.ESTUDIO;
        tarea.setTipoTarea(nuevoTipo);
        assertEquals(nuevoTipo, tarea.getTipoTarea());
        
        // Comprueba que la descripción se establece correctamente
        tarea.editar();
        String nuevaDescripcion = "Esta es una nueva tarea";
        tarea.setDescripcion(nuevaDescripcion);
        assertEquals(nuevaDescripcion, tarea.getDescripcion());
		
		
	}

}
