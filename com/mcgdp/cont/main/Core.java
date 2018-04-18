package mcgdp.cont.main;

import mcgdp.cont.GUI.Main.Session;
import mcgdp.cont.GUI.Main.Index;
import mcgdp.cont.process.ProcessLogin;

public class Core {
	private Session login;
	private ProcessLogin procesos;
	private Index inicio;

	public void setLogin(Session login) {											// Relaci�n con la sesi�n
		this.login = login;
	}

	public void setProcess(ProcessLogin procesos) {									// Relaci�n con los procesos de login
		this.procesos = procesos;
	}
	
	public void setIndex(Index inicio) {											// Relaci�n con el inicio
		this.inicio = inicio;
	}

	public String validarDatos(String user, char[] password) {						// Env�o y retorno de validaci�n
		procesos.setUser(user);
		procesos.setPass(password);
		return procesos.validarDatos();
	}
	
	// Se valida status de login
	public boolean validarStatus() {
		return procesos.getStatus();
	}
	
	// Cierre de ventana Sesi�n
	public void closeWS() {
		login.dispose();
	}

	// Limpieza de sesi�n
	public void cleanP() {
		procesos.setPass(new char[] {});
		procesos.setUser("");
		procesos.setStatus(false);
	}

	public void setRole(String role) {
		inicio.setRole(role);
	}
}
// ############################################################################# //