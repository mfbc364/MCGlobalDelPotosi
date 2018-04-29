// ################################################################################################################### //
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
				System.out.println("SQL [INFO]: Conexi�n exitosa.");
			}
			else {																					// Conexi�n es null
				System.out.println("SQL [ERROR]: Conexi�n fallida. DB no localizada.");
			}
		} 
		catch (ClassNotFoundException e) {															// Errores de clase
			JOptionPane.showMessageDialog(frmSQL, "Error Fatal en clase: " + e.getMessage() 
			+ ", contacte a soporte t�cnico.", "Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			System.out.println("SQL [FATAL]: Error Fatal: " + e.getMessage());
			e.printStackTrace();
		} 
		catch (SQLException e) {																	// Errores de BD
			JOptionPane.showMessageDialog(frmSQL, "Error Fatal en DB: " + e.getMessage() 
			+ ", contacte a soporte t�cnico.", "Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			System.out.println("SQL [FATAL] Error Fatal: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Desconexi�n de base de datos
		public void disconnect() {
			System.out.println("SQL [INFO]: Conexi�n cerrada.");
			conn = null;
		}
	
	// Getter de conexi�n
	public Connection getConnection() {
		return conn;
	}
}
// ################################################################################################################### //