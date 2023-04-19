package fuentes;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JOptionPane;

/** Clase para generar tareas en la agenda
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Tarea extends EspacioAgenda implements Editable {
	
	//================= Parte static
	private static final long serialVersionUID = 1L; // Versión para la serialización
	
	protected static final Color COLOR_FONDO = new Color( 0, 255, 255, 53 ); // Color cyan traslúcido para fondo
	protected static final Color COLOR_BORDE = Color.MAGENTA; // Color visual del borde de la caja
	protected static final Color COLOR_TEXTO = Color.BLACK;   // Color visual del texto interior de la caja
	protected static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 12 ); // Tipografía de los textos que se muestran
	
	//================= Parte no static

	protected TipoTarea tipoTarea;     // Tipo de la tarea

	/** Crea una nueva cita con descripción y persona vacíos, tipo de tarea OTROS por defecto
	 * @param ventana	Ventana de agenda donde se dibujará este objeto
	 * @param fechaHora	Fecha-hora de inicio
	 * @param duracionMins	Duración en minutos del slot
	 */
	public Tarea( VentanaAgenda ventana, Date fechaHora, int duracionMins ) {
		super( ventana, fechaHora, duracionMins );
		setColorFondo( COLOR_FONDO );     // Por defecto
		tipoTarea = TipoTarea.OTROS;  // Por defecto
		setFont( TIPO_TEXTO );
		setForeground( COLOR_TEXTO );
	}
	
	public TipoTarea getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
		setText( toString() );
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tarea)) return false;
		Tarea t2 = (Tarea) obj;
		return super.equals(obj) && tipoTarea.equals(t2.tipoTarea);
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + tipoTarea;
	}

	@Override
	public void editar() {
		String entrada = JOptionPane.showInputDialog( ventana, "Edita o confirma la descripción de la tarea:", descripcion );
		if (entrada!=null) {
			setDescripcion( entrada );
		}
		Object entrada2 = JOptionPane.showInputDialog( ventana, "Edita o confirma el tipo de tarea:", "Edición", JOptionPane.INFORMATION_MESSAGE, null, TipoTarea.values(), tipoTarea );
		if (entrada2!=null) {
			setTipoTarea( (TipoTarea) entrada2 );
		}
	}
	
}
