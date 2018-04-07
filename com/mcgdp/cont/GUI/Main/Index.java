package mcgdp.cont.GUI.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

import mcgdp.cont.GUI.tasks.POs;
import mcgdp.cont.main.Core;
import mcgdp.cont.main.Sys;

public class Index extends JFrame implements ActionListener {
	/**
	 * Interfaz gr�fica del inicio
	 */
	
	// Par�metros de inicio
	private Container cont;
	private JLabel lblTitle, lblPerm, lblPOs, lblBills, lblPays;
	private JButton btnUser, btnInfo, btnPOs, btnBills, btnPays, btnLogout;
	private Image icon, user, info, pos, bills, pays, logout; 
	private Sys sis;
	private Core nucleo;
	private POs oc;
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public Index() {
		initialize();
		setTitle("Auxiliar Contable");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		System.out.println("Auxiliar Contable iniciado.");
	}
	
	// Inicializaci�n de componentes
	private void initialize() {
		System.out.println("Iniciando Auxiliar Contable...");
		cont = getContentPane();																	// Contenedor instanciado
		cont.setLayout(null);																		// Layout absoluto
		
		icon = new ImageIcon(this.getClass().getResource("/icon-sm.png")).getImage();				// �cono encontrado
		setIconImage(icon);																			// �cono seleccionado
		
		btnUser = new JButton("");
		user = new ImageIcon(this.getClass().getResource("/user-info.png")).getImage();				// Propiedades del bot�n
		btnUser.setIcon(new ImageIcon(user));														// Usuario
		btnUser.setBounds(50, 15, 50, 50);
		btnUser.addActionListener(this);
		
		lblTitle = new JLabel("MC Global del Potos� S.A. de C.V.");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));										// Propiedades de la etiqueta
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);										// T�tulo
		lblTitle.setBounds(129, 11, 540, 54);
		
		btnInfo = new JButton("");
		info = new ImageIcon(this.getClass().getResource("/info.png")).getImage();					// Propiedades del bot�n
		btnInfo.setIcon(new ImageIcon(info));														// Info
		btnInfo.addActionListener(this);
		btnInfo.setBounds(691, 15, 50, 50);
		
		lblPerm = new JLabel("Sistema");
		lblPerm.setHorizontalAlignment(SwingConstants.CENTER);										// Propiedades de la etiqueta
		lblPerm.setBounds(347, 60, 107, 14);														// Permisos
		
		lblPOs = new JLabel("");
		lblPOs.setHorizontalAlignment(SwingConstants.CENTER);										// Propiedades de la etiqueta
		pos = new ImageIcon(this.getClass().getResource("/pos.png")).getImage();					// Ordenes de compra
		lblPOs.setIcon(new ImageIcon(pos));
		lblPOs.setBounds(99, 98, 72, 94);
		
		btnPOs = new JButton("Ordenes de compra");
		btnPOs.setBounds(50, 203, 170, 65);															// Propiedades del bot�n
		btnPOs.addActionListener(this);																// Ordenes de compra
		
		lblBills = new JLabel("");
		bills = new ImageIcon(this.getClass().getResource("/bills.png")).getImage();				// Propiedades de la etiqueta
		lblBills.setIcon(new ImageIcon(bills));														// Cuentas por pagar
		lblBills.setHorizontalAlignment(SwingConstants.CENTER);
		lblBills.setBounds(359, 98, 78, 94);
		
		btnBills = new JButton("Cuentas por pagar");
		btnBills.setBounds(319, 204, 159, 65);														// Propiedades del bot�n
		btnBills.addActionListener(this);															// Cuentas por pagar
		
		lblPays = new JLabel("");
		pays = new ImageIcon(this.getClass().getResource("/pays.png")).getImage();					// Propiedades de la etiqueta
		lblPays.setIcon(new ImageIcon(pays));														// Cuentas por cobrar
		lblPays.setHorizontalAlignment(SwingConstants.CENTER);
		lblPays.setBounds(582, 98, 159, 94);
		
		btnPays = new JButton("Cuentas por cobrar");
		btnPays.setBounds(582, 204, 159, 65);														// Propiedades del bot�n
		btnPays.addActionListener(this);															// Cuentas por cobrar
		
		btnLogout = new JButton("Cerrar Sesi�n");
		logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();				// Propiedades del bot�n
		btnLogout.setIcon(new ImageIcon(logout));													// Cerrar sesi�n
		btnLogout.setBounds(330, 293, 136, 39);
		btnLogout.addActionListener(this);
		
		// Componentes agregados al contenedor
		cont.add(btnUser);
		cont.add(lblTitle);
		cont.add(btnInfo);
		cont.add(lblPerm);
		cont.add(lblPOs);
		cont.add(btnPOs);
		cont.add(lblBills);
		cont.add(btnBills);
		cont.add(lblPays);
		cont.add(btnPays);
		cont.add(btnLogout);
	}
	
	/**
	 * M�todos de acciones l�gicas
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPOs) {
			oc = new POs();
			oc.main(null);
			close();
		}
		if(e.getSource() == btnLogout) {															// Ejecuci�n del bot�n
			logout();																				// Cerrar sesi�n
		}
	}
	
	public void logout() {
		if(JOptionPane.showConfirmDialog(this, 														// Cerrando sesi�n
				"�Seguro que deseas cerrar la sesi�n?", "Auxiliar Contable", 						// Ventana de confirmaci�n
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			System.out.println("Saliendo del Auxiliar Contable...");
			sis = new Sys();
			sis.init();
			close();																				// Cerrando ventanas
			}
	}
	
	public void close() {																			// Cerrando aplicaci�n
		dispose();
	}
	
	// Setters
	public void setRole(String role) {																// Extrayendo permisos
		System.out.println(role);
		if(role.equals("Administrator")) {
			System.out.println("Usuario reconocido como: Administrador");							// Como Administrador
			lblPerm.setText("Administrador");
		}
		else if(role.equals("Standard")) {
			System.out.println("Usuario reconocido como: Est�ndar");								// Como Usuario
			lblPerm.setText("Editor");
			btnUser.setEnabled(false);
		} 
		else if (role.equals("Guest")) {
			System.out.println("Usuario reconocido como: Invitado");								// Como Invitado
			lblPerm.setText("Invitado");
			btnUser.setEnabled(false);
			btnPOs.setEnabled(false);
			btnBills.setEnabled(false);
			btnPays.setEnabled(false);
		}
		else {
			System.out.println("Error Fatal: Fallo en seguridad. Cierre inmediato");				// Error
			JOptionPane.showMessageDialog(this, "Error Fatal: "
					+ "Fallo en seguridad. Cierre inmediato", "Auxiliar Contable", 
					JOptionPane.INFORMATION_MESSAGE);
			close();																				// Cierre inesperado
		}
	} 
	
	public void setCore(Core nucleo) {																// Relaci�n con el n�cleo
		this.nucleo = nucleo;
	}
}
