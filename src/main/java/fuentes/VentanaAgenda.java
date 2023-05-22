package fuentes;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;


@SuppressWarnings("serial")
public class VentanaAgenda extends JFrame {

	//================= Parte static
	private static final Color COLOR_CAB = Color.LIGHT_GRAY;  
	private static final Color COLOR_FEST = new Color( 230, 230, 230 ); 
	private static final Color COLOR_TEXCAB = Color.BLACK;    
	private static final Color COLOR_TABLA = Color.DARK_GRAY; 
	private static final int ANCHO_HORAS = 50;              
	private static final int ALTO_CABECERA = 30;             
	private static final int NUM_DIAS = 7;                  
	private static final int HORA_INICIO = 8;             
	private static final int HORA_FIN = 23;                  
	private static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 14 ); 
	private static final Font TIPO_MENSAJE = new Font( "Arial", Font.BOLD, 14 ); 
	
	
	private static final SimpleDateFormat dmy = new SimpleDateFormat( "dd/MM/yyyy" );
	
	
	
	private JButton bTrash;         
	private JButton bIzq;          
	private JButton bDer;  
	private JButton bUser;
	private JButton bJump;
	private JPanel pAgenda;       
	private JLabel lMensaje;        
	private int inicioXAgenda;     
	private int inicioYAgenda;     
	private int anchoColumna;       
	private int altoHora;          
	private Date fechaInicial;      
	private GestorAgenda gestor;  
	
	
	private JLabel lMensajeInferior;
	private JLabel lHoraInferior;
	
	/** Crea una nueva ventana de agenda
	 * @param anchura	Anchura de la ventana en píxels
	 * @param altura	Altura de la ventana en píxels
	 * @param gestor	Gestor de agenda asociado a la ventana 
	 */
	public VentanaAgenda( int anchura, int altura, GestorAgenda gestor ) {
		this.gestor = gestor;
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Agenda Deusto" );
		setSize( anchura, altura );
		// Creación contenedores
		JPanel pIzquierda = new JPanel();
		pAgenda = new PanelGrafico();
		JPanel pSuperior = new JPanel();
		JPanel pInferior = new JPanel( new BorderLayout() ); // Tarea 4
		// Formato contenedores
		pIzquierda.setLayout( new BoxLayout( pIzquierda, BoxLayout.Y_AXIS ) );
		pAgenda.setLayout( null );
		// Creación componentes
		lMensaje = new JLabel( " " );
		lMensajeInferior = new JLabel( " ", JLabel.CENTER );  // Tarea 4
		lHoraInferior = new JLabel( " " );
		try {  // Botones gráficos
			bIzq = new JButton( new ImageIcon("src/main/java/fuentes/img/flechaIzqda.png" ));
			bDer = new JButton( new ImageIcon("src/main/java/fuentes/img/flechaDcha.png"));
			bTrash = new JButton( new ImageIcon("src/main/java/fuentes/img/trash.png"));
			bUser = new JButton( new ImageIcon("src/main/java/fuentes/img/user.png"));
			bJump = new JButton( new ImageIcon("src/main/java/fuentes/img/texto.png"));
		} catch (Exception e1) {  // Si hay error, botones texto
			bIzq = new JButton( "Antes" );
			bDer = new JButton( "Después" );
			bTrash = new JButton( "Borrar" );
			bUser = new JButton("Eventos");
			bJump = new JButton("Ir a fecha");
		}
		
		lMensaje.setFont( TIPO_MENSAJE );
		lMensajeInferior.setFont( TIPO_MENSAJE ); // Tarea 4
		lHoraInferior.setFont( TIPO_MENSAJE );
	
		pIzquierda.add( bIzq );
		pIzquierda.add( bDer );
		pIzquierda.add(bUser);
		pIzquierda.add(bJump);
		pIzquierda.add( bTrash );
		getContentPane().add( pIzquierda, BorderLayout.WEST );
		pSuperior.add( lMensaje );
		getContentPane().add( pSuperior, BorderLayout.NORTH );
		
		pInferior.add( lHoraInferior, BorderLayout.WEST );
		pInferior.add( lMensajeInferior, BorderLayout.CENTER );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		getContentPane().add( pAgenda, BorderLayout.CENTER );
		// Eventos
		pAgenda.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
				inicioXAgenda = ANCHO_HORAS;
				inicioYAgenda = ALTO_CABECERA;
				anchoColumna = (pAgenda.getWidth() - inicioXAgenda) / NUM_DIAS;
				altoHora = (pAgenda.getHeight() - inicioYAgenda) / (HORA_FIN - HORA_INICIO);
				for (EspacioAgenda espacio : gestor.listaAgenda) {
					espacio.recalculaPosicion();
				}
			}
		});
		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Date fechaEnClick = getFechaDePunto( e.getX(), e.getY() );
				gestor.crearEspacioInteractivo( fechaEnClick );
			}
		};
		pAgenda.addMouseListener( ma );
		bDer.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mueveDias( +1 );
				recalculaPosiciones();
				repaint();
			}
		});
		bIzq.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mueveDias( -1 );
				recalculaPosiciones();
				repaint();
			}
		});
		bUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object entrada = JOptionPane.showInputDialog( VentanaAgenda.this, "Eventos:", "Mis Eventos", JOptionPane.INFORMATION_MESSAGE, null, gestor.getListaAgenda().toArray(), null );
				
				if(entrada != null) {
					gestor.espacioSeleccionado(String.valueOf(entrada));
				}
			}
		});
		bJump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String dia;
					while(true) {
						dia = JOptionPane.showInputDialog(VentanaAgenda.this, "Día", "Elige el día (1 - 31)", JOptionPane.INFORMATION_MESSAGE);
						if(Integer.parseInt(dia) < 1 || Integer.parseInt(dia) > 31) {
							JOptionPane.showMessageDialog(VentanaAgenda.this, "Día Incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
							continue;
						}
						break;
					}
					String mes;
					while(true) {
						mes = JOptionPane.showInputDialog(VentanaAgenda.this, "Mes", "Elige el mes (1 - 12)", JOptionPane.INFORMATION_MESSAGE);
						if(Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12) {
							JOptionPane.showMessageDialog(VentanaAgenda.this, "Mes Incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
							continue;
						}
						break;
					}
					String anyo;
					while(true) {
						anyo = JOptionPane.showInputDialog(VentanaAgenda.this, "Año", "Elige el año (1940 - 2200)", JOptionPane.INFORMATION_MESSAGE);
						if(Integer.parseInt(anyo) < 1940 || Integer.parseInt(anyo) > 2200) {
							JOptionPane.showMessageDialog(VentanaAgenda.this, "Año Incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
							continue;
						}
						break;
					}
					Date date = new Date(Integer.parseInt(anyo) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
					fechaInicial = date;
					recalculaPosiciones();
					repaint();
				} catch (Exception ex) {}
			}
		});
		bTrash.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gestor.borrarAgendaActual( true );
			}
		});
	
		lMensaje.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isControlDown()) {
					(new Thread() {
						public void run() {
							String mensAnterior = lMensaje.getText();
							lMensaje.setForeground( Color.BLUE );
							for (EspacioAgenda ea : gestor.getListaAgenda()) {
								lMensaje.setText( "Revisión de toda la agenda: " + ea.getFechaYDuracion() + " - " + ea.toString() );
								try {
									Thread.sleep( 2000 );
								} catch (InterruptedException e) {}
							}
							lMensaje.setText( mensAnterior );
							lMensaje.setForeground( Color.BLACK );
						}
					}).start();
				}
			}
		});
		repaint();
	}

	public void anyadirGestorRaton( EspacioAgenda espacio ) {
		for (MouseListener ml : espacio.getMouseListeners()) { espacio.removeMouseListener( ml ); }  // Borra todos los escuchadores previos MouseListener
		for (MouseMotionListener mml : espacio.getMouseMotionListeners()) { espacio.removeMouseMotionListener( mml ); }  // Borra todos los escuchadores previos MouseMotionListener
		MouseAdapter ma = new MouseAdapter() {
			private EspacioAgenda miEspacio = espacio;
			private Point enDrag = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				gestor.clickEnEspacio( espacio );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				if (miEspacio instanceof Movible) {
					if (enDrag != null) {
						gestor.dragEnEspacio( espacio, e.getLocationOnScreen().x - enDrag.x, e.getLocationOnScreen().y - enDrag.y );
					}
					enDrag = e.getLocationOnScreen();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (enDrag != null) {
					gestor.finDragEnEspacio( espacio, e.getLocationOnScreen() );
				}
				enDrag = null;
			}
		};
		espacio.addMouseListener( ma );
		espacio.addMouseMotionListener( ma );
		// Tarea 4
		espacio.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lHoraInferior.setText( espacio.getFechaYDuracion() );
				lMensajeInferior.setText( espacio.toString() );
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lMensajeInferior.setText( " " );
			}
		});
	}
	

	public void recalculaPosiciones() {
		for (EspacioAgenda sa : gestor.getListaAgenda()) {
			sa.recalculaPosicion();
		}
	}
	

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public JPanel getPanel() {
		return pAgenda;
	}


	public JButton getBotonTrash() {
		return bTrash;
	}
	
	
	public Date getFechaFinal() {
		return new Date( fechaInicial.getTime() + 24*3600000L * NUM_DIAS - 60000L );
	}

	public void setFechaInicial(Date fechaInicial) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( fechaInicial );
		gc.set( GregorianCalendar.HOUR_OF_DAY, 0 );
		gc.set( GregorianCalendar.MINUTE, 0 );
		gc.set( GregorianCalendar.SECOND, 0 );
		this.fechaInicial = gc.getTime();
	}
	

	public void mueveDias( int diasDif ) {
		setFechaInicial( new Date( fechaInicial.getTime() + diasDif * 24*3600000L ) );
	}
	

	public void setMensajeSuperior( String mensaje ) {
		lMensaje.setText( mensaje );
	}
	
	
	public void cerrar() {
		dispose();
	}
	

	public Date getFechaDePunto( int x, int y ) {
		if (fechaInicial==null || x<inicioXAgenda || y<inicioYAgenda || x>=pAgenda.getWidth()-5 || y>pAgenda.getHeight()) {
			return null;
		} else {
			long fechaEnClick = fechaInicial.getTime();  // Fecha del primer día
			int diaClick = (x - inicioXAgenda) / anchoColumna;  // día de la coordenada (0-n)
			int minutosClick = (y - inicioYAgenda) * 60 / altoHora + HORA_INICIO * 60;
			fechaEnClick += (diaClick * 24*3600000L);  // Sumamos los días a la derecha
			fechaEnClick += (minutosClick * 60000L);  // Sumamos los minutos en vertical
			return new Date( fechaEnClick );
		}
	}

	
	public int getXFecha( Date fecha ) {
		if (fechaInicial==null) return -1;
		else if (fecha.before( fechaInicial )) return -1;
		else if (fecha.after( getFechaFinal() )) return -1;
		else {
			return inicioXAgenda + (int) ((fecha.getTime() - fechaInicial.getTime()) / 24/3600000L * anchoColumna);   // Horizontal dependiendo del número de día
		}
	}
	
	
	public int getYFecha( Date fecha ) {
		if (fechaInicial==null) return -1;
		else if (fecha.before( fechaInicial )) return -1;
		else if (fecha.after( getFechaFinal() )) return -1;
		else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime( fecha );
			int minutos = gc.get( GregorianCalendar.HOUR_OF_DAY ) * 60 + gc.get( GregorianCalendar.MINUTE );
			return inicioYAgenda + (minutos - HORA_INICIO * 60) * altoHora / 60;  // Convertimos de minutos a píxels
		}
	}
	

	public int getAlturaPixels( int minutos ) {
		return minutos * altoHora / 60;
	}
	
	
	public int getAnchoColumna() {
		return anchoColumna;
	}

	
	class PanelGrafico extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;  
			
			g2.setColor( Color.WHITE );
			g2.fillRect( 0, 0, getWidth(), getHeight() );

			if (fechaInicial!=null) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime( fechaInicial );
				for (int dia=0; dia<NUM_DIAS; dia++) {
					if (gc.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY || gc.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
						g2.setColor( COLOR_FEST );
						g2.fillRect( inicioXAgenda + dia*anchoColumna, inicioYAgenda, anchoColumna, getHeight() );
					}
					gc.setTimeInMillis( gc.getTimeInMillis() + 24*3600000L );
				}
			}
		
			g2.setColor( COLOR_CAB );
			g2.fillRect( inicioXAgenda, 1, anchoColumna * NUM_DIAS, inicioYAgenda-1 );
			g2.setColor( COLOR_TABLA );
			g2.drawRect( inicioXAgenda, 1, anchoColumna * NUM_DIAS, inicioYAgenda-1 );
			
			for (int dia=0; dia<=NUM_DIAS; dia++) {
				g2.drawRect( inicioXAgenda + dia*anchoColumna, 0, anchoColumna, getHeight() );
			}
			
			g2.drawRect( inicioXAgenda, getHeight(), inicioXAgenda + anchoColumna * NUM_DIAS, getHeight() );
			
			g2.setFont( TIPO_TEXTO );
			g2.setColor( COLOR_TEXCAB );
			for (int hora=HORA_INICIO; hora<HORA_FIN; hora++) {
				g2.drawString( hora + ":00", inicioXAgenda-ANCHO_HORAS + 5, inicioYAgenda + (hora-HORA_INICIO)*altoHora );
			}
		
			if (fechaInicial!=null) {
				Date dia = new Date( fechaInicial.getTime() );
				for (int numDia=0; numDia<NUM_DIAS; numDia++) {
					g2.drawString( dmy.format( dia ), inicioXAgenda + numDia*anchoColumna + 10, 20 );
					dia.setTime( dia.getTime() + 24*3600000L );  // Avanza un día
				}
			}
		}
	}
	
}
