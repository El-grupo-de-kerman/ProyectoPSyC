package fuentes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

/** Clase principal del gestor de agenda
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class GestorAgenda {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		GestorAgenda mgt = new GestorAgenda();
		mgt.lanza();
	}
	
	// =================== Constantes static
	
	public static final int ANCHURA_VENTANA = 1000;   // Píxels de ancho
	public static final int ALTURA_VENTANA = 800;     // Píxels de alto
	public static final String NOM_FICHERO = "GestorAgenda.dat";  // Fichero de datos de agenda
	
	// =================== Parte no static
	
	VentanaAgenda ventana;
	String nick;
	ArrayList<EspacioAgenda> listaAgenda;
	// Tarea 2
	HashMap<String,ArrayList<EspacioAgenda>> mapaAgendas = new HashMap<>();
	
	/** Crea el gestor de agenda con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 */
	public GestorAgenda() {
		ventana = new VentanaAgenda( ANCHURA_VENTANA, ALTURA_VENTANA, this );
		listaAgenda = new ArrayList<>();
	}
	
	/** Devuelve la ventana de la agenda
	 * @return	ventana que corresponde
	 */
	public VentanaAgenda getVentana() {
		return ventana;
	}

	/** Devuelve la lista de elementos de agenda gestionada
	 * @return	Lista de slots de agenda
	 */
	public ArrayList<EspacioAgenda> getListaAgenda() {
		return listaAgenda;
	}

	/** Ejecuta el gestor de agenda, lanzando el proceso de ejecución principal
	 */
	public void lanza() {
		//initDatos();
		login();
		if (nick!=null && !nick.isEmpty()) {
			ventana.setFechaInicial( new Date() );
			ventana.setMensajeSuperior( "Pulsa los botones de flecha para mover días en la agenda, arrastra para cambiar de fecha, a la papelera para borrar, click para crear" );
			ventana.setVisible( true );
			ventana.repaint();
			// Tarea 3
			ventana.addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					//guardaDatosFichero( new File( NOM_FICHERO ) );
				}
			});
		}
	}

	/** Proceso de login - pide el nombre de usuario
	 */
	public void login() {
		//nick = JOptionPane.showInputDialog( "Bienvenido/a al gestor de agenda. ¿Nombre de usuario?" );
		// Tarea 2
		nick = "a";
		borrarAgendaActual( false );  // Borra agenda sin pedir confirmación para cargar o crear la agenda nueva
		if (mapaAgendas.containsKey(nick)) {  // Usuario ya existente - carga su agenda en la ventana
			listaAgenda = mapaAgendas.get(nick);
			reiniciaVentanaDeAgenda();
		} else { // Usuario nuevo - crea una agenda vacía
			mapaAgendas.put( nick, listaAgenda );
		}
		// Segunda parte
		/*
		System.out.println( "Usuarios actualmente en agenda:" );
		for (String usu : mapaAgendas.keySet()) {
			ArrayList<EspacioAgenda> l = mapaAgendas.get(usu);
			System.out.println( usu + " - " + l.size() + " espacios: " + l );
		}
		*/
	}
	
	/*
	// Proceso de inicializar datos de la ventana de trabajo
	private void initDatos() {
		// Tarea 3
		// Se añaden los datos de agenda, o desde fichero o unos pocos por defecto para empezar si no hay ficheros
		File fichero = new File( NOM_FICHERO );
		if (fichero.exists()) { // Si hay guardado de datos previo
			//cargaDatosFichero( fichero );
		} else { // Si no crea unos datos de prueba en el día actual y cercanos
			Cita cita = new Cita( ventana, new Date( System.currentTimeMillis() ), 60 );
			cita.setDescripcion( "Trabajo de mates" );
			cita.setPersona( "Andoni" );
			Tarea tarea = new Tarea( ventana, new Date( System.currentTimeMillis() - 6*3600000L + 2*24*3600000L), 75 ); // Dos días después 6 horas antes
			tarea.setDescripcion( "Padel con Eva" );
			tarea.setTipoTarea( TipoTarea.DEPORTE );
			Tarea tarea2 = new Tarea( ventana, new Date( System.currentTimeMillis() - 5*3600000L + 3600000L/2 + 2*24*3600000L), 120 );  // Dos días después, 4:30 horas antes
			tarea2.setDescripcion( "Paseo con Luis" );
			tarea2.setTipoTarea( TipoTarea.OCIO );
			anyadirEspacio( cita );
			anyadirEspacio( tarea );
			anyadirEspacio( tarea2 );
			// Tarea 1
			Cita citaM = new CitaMedica( ventana, new Date( System.currentTimeMillis() + 2*3600000L ), 45, EspecialidadMedica.DERMATOLOGIA ); // Hora actual + 2 h
			citaM.setDescripcion( "Reconocimiento" );
			citaM.setPersona( "Sandra" );
			anyadirEspacio( citaM );
		}
	}
	*/
	
	/** Añade un espacio a la agenda
	 * @param espacio	Espacio a añadir
	 */
	public void anyadirEspacio( EspacioAgenda espacio ) {
		listaAgenda.add( espacio );
		ventana.getPanel().add( espacio );
		ventana.repaint();
	}
	
	public void borrarAgendaActual( boolean pideConfirmacion ) {
		if (pideConfirmacion) {
			int resp = JOptionPane.showConfirmDialog( ventana, "¿Confirmas el borrado de toda esta agenda?" );
			if (resp==JOptionPane.OK_OPTION) {
				pideConfirmacion = false;
			}
		}
		if (!pideConfirmacion) {  // Si se ha confirmado o si no se necesita confirmación de usuario se borra
			listaAgenda = new ArrayList<>();
			ventana.getPanel().removeAll();
			ventana.repaint();
		}
	}
	
	/**	Crea un espacio en la agenda pidiendo los datos interactivamente al usuario
	 * @param fecha	Fecha y hora en la que quiere crearse el espacio
	 */
	public void crearEspacioInteractivo( Date fecha ) {
		if (fecha != null) {  // Se puede crear
			// 1.- Elegir qué espacio se genera
			//Object entrada = JOptionPane.showInputDialog( ventana, "Elige color a generar:", "Creación", JOptionPane.INFORMATION_MESSAGE, null, EspacioAgenda.TIPOS_DE_COLOR, "Azul" );
			
				// 2.- Definir duración
			String duracion = JOptionPane.showInputDialog( "Introduce duración en minutos: ", 60 );
			try {
				int dur = Integer.parseInt( duracion );
				// 3.- Crear espacio
				EspacioAgenda nuevo = EspacioAgenda.crearNuevoEspacio(ventana, fecha, dur );
				// 4.- Pedir resto de datos (personalizados)
				if (nuevo instanceof Editable) {
					((Editable)nuevo).editar();
				}
				anyadirEspacio( nuevo );
			} catch (Exception e) {
				// Error en entrada - no se genera slot
			}
		}
	}
	
	public void clickEnEspacio( EspacioAgenda espacio ) {
		if (espacio instanceof Editable) {
			((Editable) espacio).editar();
			ventana.repaint();
		}
	}
	
	public void espacioSeleccionado(String espacio) {
		int i = 0;
		for(EspacioAgenda ea: listaAgenda) {
			if(ea.toString().equals(espacio)) {
				break;
			}
			i++;
		}
		clickEnEspacio(listaAgenda.get(i));
	}
	
	public void dragEnEspacio( Movible espacioMovible, int difX, int difY ) {
		espacioMovible.mover( espacioMovible.getX()+difX, espacioMovible.getY()+difY );
	}
	
	public void finDragEnEspacio( EspacioAgenda espacio, Point puntoFinal ) {
		Date fechaEnEspacio = ventana.getFechaDePunto( espacio.getX() + espacio.getWidth()/2, espacio.getY() );
		if (fechaEnEspacio == null) { // Está fuera de la pantalla
			Rectangle rectanguloBoton = new Rectangle( ventana.getBotonTrash().getLocationOnScreen(), ventana.getBotonTrash().getSize() );
			if (rectanguloBoton.contains(puntoFinal)) { // Encima de botón trash: el espacio se borra
				listaAgenda.remove( espacio );
				ventana.getPanel().remove( espacio );
				ventana.repaint();
			} else {  // Fuera de ventana en otro lugar: el espacio se recoloca en su lugar inicial
				recolocaEspacioEnSuOrigen( espacio );
			}
		} else {
			espacio.setFechaHora( fechaEnEspacio );
			espacio.recalculaPosicion();
			ventana.repaint();
		}
	}
	
	public void recolocaEspacioEnSuOrigen( EspacioAgenda espacio ) {
		// Tarea 5 - opción A - hilo de animación
		// Posible inicialización de variables para los movimimentos a hacer en la animación
		// double x = espacio.getX();  // x actual
		// double y = espacio.getY();  // y actual
		// double xFin = ventana.getXFecha( espacio.getFechaHora() );  // x a la que hay que llegar
		// double yFin = ventana.getYFecha( espacio.getFechaHora() );  // y a la que hay que llegar
		// double pasoX = (xFin - x) / 50;  // incremento de x a mover cincuenta veces
		// double pasoY = (yFin - y) / 50;  // incremento de y a mover cincuenta veces
		(new Thread() {
			public void run() {
				double x = espacio.getX();  // x actual
				double y = espacio.getY();  // y actual
				double xFin = ventana.getXFecha( espacio.getFechaHora() );  // x a la que hay que llegar
				double yFin = ventana.getYFecha( espacio.getFechaHora() );  // y a la que hay que llegar
				double pasoX = (xFin - x) / 50;  // incremento de x a mover cincuenta veces
				double pasoY = (yFin - y) / 50;  // incremento de y a mover cincuenta veces
				for (int i=0; i<50; i++) {
					x += pasoX;
					y += pasoY;
					espacio.setLocation( (int) x, (int) y );
					try {
						Thread.sleep( 20 );
					} catch (InterruptedException e) {}
				}
				espacio.recalculaPosicion();
			}
		}).start();
		// Código original del método:
		// espacio.recalculaPosicion();  // Hay que llamar a este método para volver a poner el espacio en su fecha-hora original 
	}
	
	// Reinicia la ventana y panel visual de la agenda (listaAgenda) actual
	public void reiniciaVentanaDeAgenda() {
		for (EspacioAgenda espacio : listaAgenda) {
			espacio.setVentana( ventana );
			ventana.getPanel().add( espacio );
			espacio.recalculaPosicion();
			ventana.anyadirGestorRaton( espacio );
		}
	}
	
	/*
	@SuppressWarnings("unchecked")
	private void cargaDatosFichero( File f ) {
		// Tarea 3
		try {
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream( f ) );
			Object leido = ois.readObject();
			if (leido instanceof ArrayList) {  // Fichero antiguo
				listaAgenda = (ArrayList<EspacioAgenda>) leido;
			} else {  // Fichero nuevo
				mapaAgendas = (HashMap<String,ArrayList<EspacioAgenda>>) leido;
			}
			ois.close();
			reiniciaVentanaDeAgenda();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void guardaDatosFichero( File f ) {
		// Tarea 3
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( f ) );
			oos.writeObject( mapaAgendas );
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

}
