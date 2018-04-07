package mcgdp.cont.db;

import java.io.File;
import java.sql.*;
import javax.swing.*;

public class ConnUser {
	// Par�metros de conexi�n
	private String bdName = "\\db\\users.db";
	private final String basePath = new File("").getAbsolutePath();
	private final String url = "jdbc:sqlite:" + basePath + bdName;
	private static JFrame frmSQL;
	
	// Objeto de conexi�n
	Connection conn = null;
	
	// Constructor
	public ConnUser() {
		try {
			Class.forName("org.sqlite.JDBC");														// Se obtiene driver
			
			conn = DriverManager.getConnection(url);												// Se obtiene conexi�n
			if(conn != null) {																		// Conexi�n hallada
				System.out.println("Conexi�n exitosa.");
			}
			else {																					// Conexi�n es null
				System.out.println("Conexi�n fallida. Datos no localizados");
			}
		} 
		catch (ClassNotFoundException e) {															// Errores de clase
			JOptionPane.showMessageDialog(frmSQL, "Error Fatal: " + e.getMessage() 
			+ ", contacte a soporte t�cnico.", "Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error Fatal: " + e.getMessage());
		} 
		catch (SQLException e) {																	// Errores de BD
			JOptionPane.showMessageDialog(frmSQL, "Error Fatal: " + e.getMessage() 
			+ ", contacte a soporte t�cnico.", "Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error Fatal: " + e.getMessage());
		}
	}
	
	// Desconexi�n de base de datos
		public void disconnect() {
			conn = null;
		}
	
	// Getter de conexi�n
	public Connection getConnection( ) {
		return conn;
	}
}
