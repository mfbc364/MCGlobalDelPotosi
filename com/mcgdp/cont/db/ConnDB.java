// #################################################################################################################### //
package mcgdp.cont.db;

import java.io.File;
import java.sql.*;
import javax.swing.*;

public class ConnDB {
	// Objeto de conexi�n
	private Connection conn = null;
	
	// Directorio padre de directorio db
	private final String basePath = new File("").getAbsolutePath();
	
	// Constructor
	public ConnDB(String bdName) {
		try {
			// Par�metros de conexi�n
			final String url = "jdbc:sqlite:" + getBasePath() + bdName;
			
			Class.forName("org.sqlite.JDBC");														// Se obtiene driver
			
			setConn(DriverManager.getConnection(url));												// Se obtiene conexi�n
			if(getConn() != null) {																	// Conexi�n hallada
				System.out.println("SQL [INFO]: Conexi�n exitosa.");
			}
			else {																					// Conexi�n es null
				System.out.println("SQL [ERROR]: Conexi�n fallida. DB no localizada.");
			}
		} 
		catch (ClassNotFoundException e) {															// Errores de clase
			JOptionPane.showMessageDialog(null, "Error Fatal en clase: " + e.getMessage() 
			+ ", contacte a soporte t�cnico.", "Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			System.out.println("SQL [FATAL]: Error Fatal: " + e.getMessage());
			e.printStackTrace();
		} 
		catch (SQLException e) {																	// Errores de BD
			JOptionPane.showMessageDialog(null, "Error Fatal en DB: " + e.getMessage() 
			+ ", contacte a soporte t�cnico.", "Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			System.out.println("SQL [FATAL] Error Fatal: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Desconexi�n de base de datos
	public void disconnect() {
		System.out.println("SQL [INFO]: Conexi�n cerrada.");
		setConn(null);
	}
	
	// Getter de conexi�n
	public Connection getConnection() {
		return conn;
	}
	
	// Getters
	private Connection getConn() {
		return conn;
	}
	
	private String getBasePath() {
		return basePath;
	}
	
	// Setters
	private void setConn(Connection conn) {
		this.conn = conn;
	}
}
// #################################################################################################################### //