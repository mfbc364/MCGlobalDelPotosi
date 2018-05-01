// ###################################################################################################################################### //
package mcgdp.cont.GUI.tasks;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import mcgdp.cont.GUI.Main.Index;
import mcgdp.cont.GUI.process.Search;
import mcgdp.cont.process.Load;

public class Pays extends JFrame implements ActionListener {
	/** 
	  * Interfaz gr�fica de las cuentas por cobrar
	 **/
	
	// Par�metros de cuentas por cobrar
	private Container cont;
	private Image icon, back, search, add, edit, del, review, ok;
	private JButton btnBack, btnSearch, btnAdd, btnEdit, btnDel;
	private JLabel lblReview, lblApproved, lblNoReview, lblNoApproved;
	private JPanel panel;
	private JScrollPane scrollPanel, scrollReview, scrollApproved;
	private JSeparator separatorTop, separatorBot;
	private JTable tableReview, tableApproved;
	private ArrayList<String> titles;
	private String role;
	private Index inicio;
	private Load cargar;
	private Search busqueda;
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public Pays() {
		initialize();
		setTitle("Auxiliar Contable");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setTitles();
		construirTableReview();
		construirTableApproved();
		System.out.println("Pays [INFO]: Pays iniciada.");
	}
	
	// Inicializaci�n de componentes
	private void initialize() {
		System.out.println("Pays [INFO]: Iniciando ventana de Pays...");
		cont = getContentPane();																			// Contenedor instanciado
		cont.setLayout(null);																				// Layout absoluto
		
		icon = new ImageIcon(this.getClass().getResource("/icon-sm.png")).getImage();						// �cono encontrado
		setIconImage(icon);																					// �cono seleccionado
		
		// Componentes del contenedor del Frame
		btnBack = new JButton("Atr�s");																		// Propiedades del bot�n
		back = new ImageIcon(this.getClass().getResource("/back.png")).getImage();							// Atr�s
		btnBack.setIcon(new ImageIcon(back));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(0, 0, 119, 40);
		btnBack.addActionListener(this);
		
		btnSearch = new JButton("B�squeda");																// Propiedades del bot�n
		search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();						// B�squeda
		btnSearch.setIcon(new ImageIcon(search));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(176, 0, 144, 40);
		btnSearch.addActionListener(this);
		
		btnAdd = new JButton("A�adir");																		// Propiedades del bot�n
		add = new ImageIcon(this.getClass().getResource("/add.png")).getImage();							// A�adir
		btnAdd.setIcon(new ImageIcon(add));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(360, 0, 119, 40);
		btnAdd.addActionListener(this);
		
		btnEdit = new JButton("Editar");																	// Propiedades del bot�n
		edit = new ImageIcon(this.getClass().getResource("/edit.png")).getImage();							// Editar
		btnEdit.setIcon(new ImageIcon(edit));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(530, 0, 119, 40);
		btnEdit.addActionListener(this);
		
		btnDel = new JButton("Eliminar");																	// Propiedades del bot�n
		del = new ImageIcon(this.getClass().getResource("/delete.png")).getImage();							// Eliminar
		btnDel.setIcon(new ImageIcon(del));
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDel.setBounds(700, 0, 119, 40);
		btnDel.addActionListener(this);
		
		// Se crea panel con su respectivo scroll
		scrollPanel = new JScrollPane();																	// Propiedades del scroll
		scrollPanel.setBounds(0, 40, 1274, 651);															// Panel
		
		
		panel = new JPanel();																				// Generando Panel
		panel.setBorder(null);																				// Propiedades del Panel
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1254, 689));													// Tama�o ubicado con referencia
		scrollPanel.setViewportView(panel);																	// al contenedor
		
			// Componentes del panel
			lblReview = new JLabel("En revisi�n...");														// Propiedades de la etiqueta
			review = new ImageIcon(this.getClass().getResource("/pending.png")).getImage();					// En revisi�n
			lblReview.setIcon(new ImageIcon(review));
			lblReview.setBounds(20, 0, 150, 32);															// Ubicaci�n con referencia
			lblReview.setFont(new Font("Tahoma", Font.PLAIN, 15));											// al panel
			lblReview.setHorizontalAlignment(SwingConstants.LEFT);
			
			scrollReview = new JScrollPane();																// Propiedades del scroll
			scrollReview.setBounds(10, 35, 1234, 300);														// En revisi�n
			
			separatorTop = new JSeparator();																// Propiedades del separador
			separatorTop.setBounds(10, 339, 1234, 2);														// Arriba
			
			lblApproved = new JLabel("Aprobadas");															// Propiedades de la etiqueta
			ok = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();							// Aprobados
			lblApproved.setIcon(new ImageIcon(ok));
			lblApproved.setBounds(20, 340, 150, 32);
			lblApproved.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			scrollApproved = new JScrollPane();																// Propiedades del scroll
			scrollApproved.setBounds(10, 375, 1234, 300);													// Aprobados
			
			separatorBot = new JSeparator();																// Propiedades del separador
			separatorBot.setBounds(10, 679, 1234, 2);														// Abajo
			
		// Componentes agregados al contenedor
		cont.add(btnBack);
		cont.add(btnSearch);
		cont.add(btnAdd);
		cont.add(btnEdit);
		cont.add(btnDel);
		cont.add(scrollPanel);
		
		// Componentes agregados al panel
		panel.add(lblReview);
		panel.add(scrollReview);
		panel.add(separatorTop);
		panel.add(lblApproved);
		panel.add(scrollApproved);
		panel.add(separatorBot);
	}
	
	/**
	 * M�todos de acciones l�gicas
	 */
	
	// Eventos de botones
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == btnBack) {																	// Bot�n Atr�s presionado
				exit();
			}
			if(e.getSource() == btnSearch) {																// Bot�n Buscar presionado
				busqueda = new Search();
				busqueda.setTitles(getTitles());
				busqueda.setCombo();
				busqueda.setKey(2);
				busqueda.setVisible(true);
			}
		}
		catch(Exception err) {
			System.out.println("Pays [FATAL]: Error Fatal: " + err.getMessage() + " " + 					// Errores
					err.getCause());
			err.printStackTrace();
					
			JOptionPane.showMessageDialog(this, "Error Fatal, contacte a soporte t�cnico", 
					"Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Creaci�n de datos de tabla En revisi�n...
	private void construirTableReview() {																	// Tabla de datos
		System.out.println("Pays [INFO]: Creando tabla 'En revisi�n'...");									// En revisi�n
			
		String title[] = getTitles().toArray(new String[getTitles().size()]);								// T�tulos
			
		cargar = new Load();
		String data[][] = cargar.obtenerMatrizPays("In_review");											// Datos
			
		if(data.length != 0) {																			 	// Si existen datos
			tableReview = new JTable(data, title) {															// Propiedades de la tabla
				private static final long serialVersionUID = 1L;											// En revisi�n
				public boolean isCellEditable(int row, int column) {										// Desactivando edici�n
					return false;																			// de tabla
				};
				
				@Override
	            public Class<?> getColumnClass(int column) {												// Obteniendo columnas
	                return getValueAt(0, column).getClass();
	            }
		};
		
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();						// Centrando datos
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);											// de tabla
			for(int i = 0; i < data.length; i++) {
				tableReview.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);					// Centrando columnas
			}
		
			resizeColumnWidth(tableReview);
			tableReview.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollReview.setViewportView(tableReview);
			tableReview.setCellSelectionEnabled(false);
			System.out.println("Pays [INFO]: Tabla creada: 'En revisi�n...'");
		}
		else {																								// Si no existen datos
			lblNoReview = new JLabel("No hay Cuentas de Cobro en Revisi�n");								// Propiedades de la etiqueta
			lblNoReview.setFont(new Font("Tahoma", Font.PLAIN, 25));										// No hay en revisi�n
			lblNoReview.setHorizontalAlignment(SwingConstants.CENTER);
			scrollReview.setViewportView(lblNoReview);
			System.out.println("Pays [INFO]: Tabla 'En revisi�n...' no creada por falta de datos");
		}
	}
	
	// Creaci�n de datos de tabla Aprobados
	private void construirTableApproved() {																	// Tabla de datos
		System.out.println("Pays [INFO]: Creando tabla 'Aprobados'...");									// Aprobados
			
		String title[] = getTitles().toArray(new String[getTitles().size()]);								// T�tulos
			
		cargar = new Load();
		String data[][] = cargar.obtenerMatrizPays("Approved");												// Datos
			
		if(data.length != 0) {																			 	// Si existen datos
			tableApproved = new JTable(data, title) {														// Propiedades de la tabla
				private static final long serialVersionUID = 1L;											// Aprobados
				public boolean isCellEditable(int row, int column) {										// Desactivando edici�n
					return false;																			// de tabla
				};
				
				@Override
	            public Class<?> getColumnClass(int column) {												// Obteniendo columnas
	                return getValueAt(0, column).getClass();
	            }
			};
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();						// Centrando datos
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);											// de tabla
	        for(int i = 0; i < data.length; i++) {
	        	tableApproved.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);				// Centrando columnas
	        }
			
			resizeColumnWidth(tableApproved);
			tableApproved.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollApproved.setViewportView(tableApproved);
			tableApproved.setCellSelectionEnabled(false);
			System.out.println("Pays [INFO]: Tabla creada: 'Aprobados'");
		}
		else {																								// Si no existen datos
			lblNoApproved = new JLabel("No hay Cuentas de Cobro Aprobadas");								// Propiedades de la etiqueta
			lblNoApproved.setFont(new Font("Tahoma", Font.PLAIN, 25));										// No hay aprobados
			lblNoApproved.setHorizontalAlignment(SwingConstants.CENTER);
			scrollApproved.setViewportView(lblNoApproved);
			System.out.println("Pays [INFO]: Tabla 'Aprobados' no creada por falta de datos");
		}
	}
	
	// Modificaci�n de tama�o de columnas
	private void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();										// Obteniendo modelo de tabla
	    for (int column = 0; column < table.getColumnCount(); column++) {									// Recorriendo columnas
	        int width = 130;																				// Anchura m�nima
	        for (int row = 0; row < table.getRowCount(); row++) {											// Recorriendo datos de columna
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 400)
	            width=400;
	        columnModel.getColumn(column).setPreferredWidth(width);											// Asignando tama�o a columna 
	    }
	}
	
	// Setters
	private void setTitles() {
		titles = new ArrayList<String>();
		titles.add("RFC");
		titles.add("Cliente");
		titles.add("Fecha de emisi�n");
		titles.add("Direcci�n");
		titles.add("Colonia");
		titles.add("Ciudad");
		titles.add("C�digo Postal");
		titles.add("Pais");
		titles.add("Descripci�n");
		titles.add("Cantidad");
		titles.add("Unidad de medida");
		titles.add("Precio Unitario");
		titles.add("Impuestos");
		titles.add("Total");
		titles.add("Divisa");
		titles.add("Tipo de Pago");
		titles.add("M�todo de Pago ");
	}	
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setPerm() {																			// Extrayendo permisos
		if(getRole().equals("Administrator") || getRole().equals("Sistema")) {
			System.out.println("POs [INFO]: Usuario reconocido como: Administrador");				// Como Administrador
		}
		else if(getRole().equals("Standard")) {
			System.out.println("POs [INFO]: Usuario reconocido como: Est�ndar");					// Como Usuario
			btnDel.setEnabled(false);
		}
		else {
			System.out.println("POs [ERROR]: Fallo en seguridad. Cierre inmediato");				// Error
			JOptionPane.showMessageDialog(this, "Error Fatal: "
					+ "Fallo en seguridad. Cierre inmediato", "Auxiliar Contable", 
					JOptionPane.INFORMATION_MESSAGE);
			close();																				// Cierre inesperado
		}
	}
	
	// Getters
	private ArrayList<String> getTitles() {
		return titles;
	}
	
	private String getRole() {
		return role;
	}
	
	// Confirmaci�n de retroceso
	private void exit() {																					// Cerrando ventana
		if(JOptionPane.showConfirmDialog(null,																// Ventana de confirmaci�n
				"�Seguro que deseas salir de Cuentas por Cobrar?", "Auxiliar Contable",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				System.out.println("Pos [INFO]: Saliendo de Ordenes de compra...");
				inicio = new Index();
				inicio.setRole(getRole());
				inicio.setPerm();
				inicio.setVisible(true);																	// Abriendo pantalla inicio
				close();																					// Cerrando ventana
		}
	}
	
	// Cierre de la ventana
	private void close() {
		dispose();
	}
}
// ###################################################################################################################################### //