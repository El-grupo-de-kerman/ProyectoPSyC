package fuentes;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** Clase para generar espacios en la agenda. Cada espacio se asigna a una fecha, hora y duración
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EspacioAgenda extends JLabel implements Movible, Serializable, Editable {
	
	//================= Parte static
	private static final long serialVersionUID = 1L; // Versión para la serialización
	
	protected static final Color COLOR_FONDO = Color.BLUE;  // Azul - Color de fondo por defecto
	protected static final Color COLOR_BORDE = Color.MAGENTA; // Color visual de la caja del espacio
	protected static final float GROSOR = 1.0f;  // Grosor visual del borde del espacio
	protected static final Color COLOR_TEXTO = Color.BLACK;   // Color visual del texto interior de la caja
	protected static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 12 ); // Tipografía de los textos que se muestran
	
	protected static final SimpleDateFormat DMY_HM = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );
	
	
	/** Tipos de espacios que pueden crearse  (corresponden a clases hijas de EspacioAgenda)
	 */
	public static String[] TIPOS_DE_COLOR = { "Azul", "Rojo", "Verde", "Blanco", "Amarillo", "Naranja", "Morado"};
	
	/** Constructor indirecto de espacio de agenda
	 * @param tipoEspacio	Tipo de espacio. Uno de los valores de {@link #TIPOS_DE_ESPACIOS}
	 * @param ventana	Ventana de agenda que se usa
	 * @param fecha	Fecha de inicio del espacio
	 * @param duracion	Duración en minutos del espacio
	 * @return
	 * @throws NullPointerException	Si el tipo de espacio no es uno de los valores correctos
	 */
	public static EspacioAgenda crearNuevoEspacio(VentanaAgenda ventana, Date fecha, int duracion ) throws NullPointerException {
		EspacioAgenda ret = new EspacioAgenda(ventana, fecha, duracion);
		
		return ret;
	}
	
	//================= Parte no static

	protected transient VentanaAgenda ventana;  // Los atributos TRANSIENT no se serializan (no tiene sentido serializar la ventana, porque va a ser distinta en cada ejecución)
	protected Date fechaHora;     // Fecha y hora de inicio del hueco
	protected int duracionMins;   // Duración en minutos del hueco de agenda
	protected Color colorFondo;   // Color de fondo del hueco de agenda
	protected String descripcion; // Descripción del slot

	/** Crea un nuevo slot de agenda sin descripción
	 * @param ventana	Ventana de agenda donde se dibujará este slot
	 * @param fechaHora	Fecha-hora de inicio
	 * @param duracionMins	Duración en minutos del slot
	 */
	public EspacioAgenda( VentanaAgenda ventana, Date fechaHora, int duracionMins ) {
		super( " ", JLabel.CENTER );
		this.ventana = ventana;
		this.fechaHora = fechaHora;
		this.duracionMins = duracionMins;
		setVerticalAlignment( JLabel.CENTER );
		setDescripcion( "" );
		setColorFondo( COLOR_FONDO );  // Por defecto
		setBackground( colorFondo );
		setOpaque( true );
		recalculaPosicion();  // Siempre recalcula posición en función de la fecha y duración (para la visual)
		ventana.anyadirGestorRaton( this );
	}
	
	/** Devuelve la ventana donde está este objeto
	 * @return	ventana de agenda del objeto
	 */
	public VentanaAgenda getVentana() {
		return ventana;
	}
	
	/** Modifica la ventana donde está este objeto
	 * @param ventana
	 */
	public void setVentana( VentanaAgenda ventana ) {
		this.ventana = ventana;
	}
	
	/** Modifica la posición de este objeto
	 * @param x	Nueva posición horizontal en píxels
	 */
	public void setX(int x) {
		setLocation( x, getY() );
	}

	/** Modifica la posición de este objeto
	 * @param y	Nueva posición vertical en píxels
	 */
	public void setY(int y) {
		setLocation( getX(), y );
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getDuracionMins() {
		return duracionMins;
	}

	public void setDuracionMins(int duracionMins) {
		this.duracionMins = duracionMins;
	}

	public Color getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
		setBackground( colorFondo );
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		setText( descripcion ); // Cambia el texto visual
	}

	/** Recalcula la posición en la ventana de este slot en función de su fecha y duración
	 */
	public void recalculaPosicion() {
		setLocation( ventana.getXFecha(fechaHora), ventana.getYFecha(fechaHora) );
		/*
		if(ventana.getFechaInicial().after(fechaHora) || ventana.getFechaFinal().before(fechaHora)) {
			setLocation(-1000, -1000);
		}
		*/
		setSize( ventana.getAnchoColumna(), ventana.getAlturaPixels( duracionMins ) );
	}
	
	public boolean contienePunto(int x, int y) {
		System.out.println( "  Contiene punto " + this );
		if (x<getX()) return false;
		else if (y<getY()) return false;
		else if (x>getX()+getWidth()) return false;
		else if (y>getY()+getHeight()) return false;
		return true;
	}
	
	/** Devuelve la información de fecha y duración del espacio en minutos
	 * @return	Formato string: dd/MM/yyyy hh:mm [dur]
	 */
	public String getFechaYDuracion() {
		return DMY_HM.format(fechaHora) + " [" + String.format( "%02d", duracionMins ) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EspacioAgenda)) return false;
		EspacioAgenda t2 = (EspacioAgenda) obj;
		return descripcion.equals(t2.descripcion) && fechaHora.equals(t2.fechaHora) && duracionMins==t2.duracionMins;
	}
	
	@Override
	public String toString() {
		return "Evento: " + descripcion + ", Fecha: " + fechaHora.toString() + ", Duracion(Mins.): " + duracionMins;
	}

	public void mover( int x, int y ) {
		setLocation( x, y );
//		setX( x );
//		setY( y );
	}
	
	@Override
	public void editar() {
		String entrada = JOptionPane.showInputDialog( ventana, "Edita o confirma la descripción del recordatorio:", descripcion );
		if (entrada!=null) {
			setDescripcion( entrada );
		}
		
		Object obj = JOptionPane.showInputDialog( ventana, "Elige color a generar:", "Creación", JOptionPane.INFORMATION_MESSAGE, null, EspacioAgenda.TIPOS_DE_COLOR, "Azul" );
		String tipoColor = String.valueOf(obj);
		if(tipoColor.equals("Azul") ) {
			setColorFondo(new Color(0, 0, 255, 53));
		} else if(tipoColor.equals("Rojo") ) {
			setColorFondo(new Color(255, 0, 0, 53));
		} else if(tipoColor.equals("Verde") ) {
			setColorFondo(new Color(0, 255, 0, 53));
		} else if(tipoColor.equals("Blanco") ) {
			setColorFondo(new Color(233, 233, 233, 53));
		} else if(tipoColor.equals("Amarillo") ) {
			setColorFondo(new Color(255, 255, 0, 53));
		} else if(tipoColor.equals("Naranja") ) {
			setColorFondo(new Color(255, 128, 0, 53));
		} else if(tipoColor.equals("Morado") ) {
			setColorFondo(new Color(255, 0, 255, 53));
		} else {
			setColorFondo(new Color(0, 102, 204, 53));
		}
	}
	
}
