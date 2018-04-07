package mcgdp.cont.process;

import mcgdp.cont.main.Core;

public class ProcessLogin {
	// Par�metros de procesos
	@SuppressWarnings("unused")
	private Core nucleo;
	private String user;
	private String password;
	private String role = "Administrator";
	public String message;
	private boolean status = false;
	private final String sel = "Selecciona...";
	private final String pass = "MCgdp2018*";

	// Relaci�n con el n�cleo
	public void setCore(Core nucleo) {
		this.nucleo = nucleo;
	}
	
	// Validando sesi�n
	public String validarDatos() {
		System.out.println("Iniciando sesi�n...");
		System.out.println("Procesando usuario...");
		if(getUser().equals(sel)) {
			System.out.println("La selecci�n " + sel + " es inv�lida.");
			setMessage("No hay una selecci�n. Intenta de nuevo.");
		}
		if(getPass().equals("")) {
			System.out.println("Ingresa una contrase�a.");
			setMessage("Ingresa una contrase�a.");
		}
		if(getUser().equals("admin")) {
			System.out.println("Usuario " + getUser() + " encontrado.");
			validarContra();
			if((getRole().equals("Administrator") || getRole().equals("Standard")  
					|| getRole().equals("Guest")) == false) {
				System.out.println("No hay rol asignado para este usuario.");
				setMessage("Rol no v�lido, imposible iniciar sesi�n con este usuario.");
				setStatus(false);
			}
			else {
				setMessage("Usuario y/o Contrase�a inv�lidos. Intenta de nuevo.");
				}
		}
		if(getStatus() == true) {
			setMessage("Usuario y Contrase�a aceptados. Bienvenido " + getUser());
			nucleo.setRole(getRole());
		}
		else {
			System.out.println("Usuario " + getUser() + " no existe.");
			setMessage("Usuario no registrado. Selecciona uno v�lido");
		}
		return getMessage();
	}
	
	// Validando contrase�a ingresada
	private void validarContra() {
		System.out.println("Procesando contrase�a...");
		if(getPass().equals(pass)) {
			System.out.println("Contrase�a v�lida.");
			setStatus(true);
		}
		else {
			System.out.println("Contrase�a inv�lida.");
		}
	}

	// Getters de par�metros
	private String getUser() {
		return user;
	}
		
	private String getPass() {
		return password;
	}
	
	public String getRole() {
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