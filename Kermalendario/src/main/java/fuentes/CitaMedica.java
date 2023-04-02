package fuentes;

//Tarea 1

import java.awt.Color;
import java.util.Date;

import javax.swing.JOptionPane;

/** Clase para generar citas en la agenda
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class CitaMedica extends Cita {
	
	//================= Parte static
	private static final long serialVersionUID = 1L; // Versión para la serialización
	protected static final Color COLOR_FONDO = new Color( 0, 255, 0, 53 ); // Color verde traslúcido para fondo
	
	//================= Parte no static

	protected EspecialidadMedica especialidad;     // Especialidad médica

	/** Crea una nueva cita médica con descripción y persona vacíos
	 * @param ventana	Ventana de agenda donde se dibujará este objeto
	 * @param fechaHora	Fecha-hora de inicio
	 * @param duracionMins	Duración en minutos del slot
	 * @param tipoMedico	Tipo de especialidad médica
	 */
	public CitaMedica( VentanaAgenda ventana, Date fechaHora, int duracionMins, EspecialidadMedica tipoMedico ) {
		super( ventana, fechaHora, duracionMins );
		setColorFondo( COLOR_FONDO );  // Por defecto
		especialidad = tipoMedico;
	}
	
	public EspecialidadMedica getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(EspecialidadMedica especialidad) {
		this.especialidad = especialidad;
		setText( toString() );  // Al cambiar la especialidad cambia el texto visualizado
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CitaMedica)) return false;
		CitaMedica t2 = (CitaMedica) obj;
		return super.equals(obj) && especialidad==t2.especialidad;
	}
	
	@Override
	public String toString() {
		return super.toString() + " {" + especialidad + "}";
	}
	
	@Override
	public void editar() {
		super.editar();
		Object entrada = JOptionPane.showInputDialog( ventana, "Edita o confirma la especialidad médica de la cita:", "Edición", JOptionPane.INFORMATION_MESSAGE, null, EspecialidadMedica.values(), especialidad );
		if (entrada!=null) {
			setEspecialidad( (EspecialidadMedica) entrada );
		}
	}

}
