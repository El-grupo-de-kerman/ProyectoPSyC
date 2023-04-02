package fuentes;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JOptionPane;

/** Clase para generar citas en la agenda
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Cita extends EspacioAgenda implements Editable {
	
	//================= Parte static
	private static final long serialVersionUID = 1L; // Versión para la serialización
	
	protected static final Color COLOR_FONDO = new Color( 255, 255, 0, 53 ); // Color amarillo traslúcido para fondo
	protected static final Color COLOR_BORDE = Color.BLUE;    // Color visual del borde de la caja
	protected static final Color COLOR_TEXTO = Color.BLACK;   // Color visual del texto interior de la caja
	protected static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 12 ); // Tipografía de los textos que se muestran
	
	//================= Parte no static

	protected String persona;     // Persona con la que es la cita

	/** Crea una nueva cita con descripción y persona vacíos
	 * @param ventana	Ventana de agenda donde se dibujará este objeto
	 * @param fechaHora	Fecha-hora de inicio
	 * @param duracionMins	Duración en minutos del slot
	 */
	public Cita( VentanaAgenda ventana, Date fechaHora, int duracionMins ) {
		super( ventana, fechaHora, duracionMins );
		setColorFondo( COLOR_FONDO );  // Por defecto
		setPersona( "" );
		setFont( TIPO_TEXTO );
		setForeground( COLOR_TEXTO );
	}
	
	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
		setText( super.toString() + " (" + toString() ); // Al cambiar la persona cambia el texto visualizado
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cita)) return false;
		Cita t2 = (Cita) obj;
		return super.equals(obj) && persona.equals(t2.persona);
	}
	
	@Override
	public String toString() {
		return super.toString() + (persona==null ? " sin persona definida" : " con " + persona);
	}

	@Override
	public void editar() {
		String entrada = JOptionPane.showInputDialog( ventana, "Edita o confirma la persona de la cita:", persona );
		if (entrada!=null) {
			setPersona( entrada );
		}
		entrada = JOptionPane.showInputDialog( ventana, "Edita o confirma la descripción de la cita:", descripcion );
		if (entrada!=null) {
			setDescripcion( entrada );
		}
	}
	
}
