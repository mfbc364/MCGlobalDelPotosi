// ############################################################################################################################# //
package mcgdp.cont.process;

import mcgdp.cont.main.Core;
import mcgdp.cont.db.dao.Users;
import mcgdp.cont.db.vo.User;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProcessLogin {
	// Par�metros de procesos
	private Core nucleo;
	private String user;
	private Integer index;
	private String password;
	private String role;
	public String message;
	private boolean status;
	private final String sel = "Selecciona...";
	private final Users data = new Users();
	private ArrayList <User> usersList = data.consListaUsuarios();

	// Relaci�n con el n�cleo
	public void setCore(Core nucleo) {
		this.nucleo = nucleo;
	}
	
	// Validando sesi�n
	public String validarDatos() {
		try {
			System.out.println("Login [INFO]: Intento de inicio de sesi�n por: " + getUser());
			System.out.println("Login [INFO]: Iniciando sesi�n...");
			
			if(getUser().equals(sel)) {																// Si el combo indica
				System.out.println("Login [ERROR]: " + sel + " no es aplicable.");					// Selecciona...
				setMessage("No hay una selecci�n. Intenta de nuevo.");
				setStatus(false);
			}
			else if(!getUser().equals(sel)) {														// Si el combo no indica
				System.out.println("Login [INFO]: Procesando usuario...");							// Selecciona...
				validarUsuario();																	// Validar usuario
				System.out.println("Login [INFO]: Usuario procesado.");
				
				if(getIndex() != null) {															// Si el usuario existe
					System.out.println("Login [INFO]: Usuario " + getUser() + " encontrado.");		// en la base de datos
					System.out.println("Login [INFO]: Procesando contrase�a...");
					validarPassword();																// Validar la contrase�a
					
					if(getStatus() == true) {														// Si se validaron los datos
						setMessage("Usuario y Contrase�a v�lidos. Bienvenido " + getUser());		// correctamente
						nucleo.setRole(getRole());													// Asignando rol
						System.out.println("Login [INFO]: Inicio de sesi�n exitoso.");
					}
					else {
						System.out.println("Login [ERROR]: Inicio de sesi�n fallido.");
					}
				}
				else if(getIndex() == null) {														// Si el usuario no existe
					System.out.println("Login [ERROR]: Usuario inv�lido.");							// en la base de datos
					setMessage("Usuario inv�lido. Intenta de nuevo.");
					setStatus(false);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Login [FATAL]: Error inesperado en login: "+e.getMessage());		// Errores
			JOptionPane.showMessageDialog(null, "Error Fatal al iniciar sesi�n: "+e.getMessage(), 
					"Auxiliar Contable", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return getMessage();
	}
	
	// Validando contrase�a ingresada
	private void validarPassword() {
		if(getPass().equals("")) {																	// Si la contrase�a es
			System.out.println("Login [ERROR]: No hay contrase�a ingresada.");						// null o vac�a
			setMessage("Ingresa una contrase�a.");
			setStatus(false);
		}
		else if(getPass().equals(usersList.get(getIndex()).getPassword())) {						// Si existe la contrase�a
			System.out.println("Login [INFO]: Contrase�a v�lida.");									// compararla con la DB
			validarRol();																			// Validando rol
		}
		else {
			System.out.println("Login [ERROR]: Contrase�a inv�lida.");								// Si existe la contrase�a
			setMessage("Contrase�a inv�lida. Intenta de nuevo.");									// pero no es igual a la
			setStatus(false);																		// almacenada en la DB
		}
	}
	
	// Validando si existe el usuario ingresado
	private void validarUsuario() {
		for(int i = 0; i < usersList.size(); i++) {													// Comparando usuario ingresado
			if(usersList.get(i).getUsuario().equals(getUser())) {									// con los datos de la DB
				setIndex(i);																		// Usuario encontrado
			}
		}
	}
	
	// Validando y asignando rol de usuario
	private void validarRol() {
		System.out.println("Login [INFO]: Validando rol...");
		if(usersList.get(getIndex()).getRol() != null) {											// Obteniendo rol en base
			System.out.println("Login [INFO]: Rol validado.");										// al usuario ingresado
			System.out.println("Login [INFO]: Asignando rol...");
			setRole(usersList.get(getIndex()).getRol());											// Asignando rol
			System.out.println("Login [INFO]: Rol asignado.");
			setStatus(true);
		}
		else {
			System.out.println("Login [ERROR]: No hay rol asignado para este usuario.");			// Rol no existente
			setMessage("Rol no encontrado, imposible iniciar sesi�n con este usuario. "
					+ "Contacte a su administrador");
			setStatus(false);
		}
	}

	// Getters de par�metros
	private String getUser() {
		return user;
	}
	
	private Integer getIndex() {
		return index;
	}
		
	private String getPass() {
		return password;
	}
	
	private String getRole() {
		return role;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	// Setters de par�metros
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public void setPass(char[] password) {
		this.password = new String(password);
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
// ############################################################################################################################# //