package martin.framework;

import java.io.File;
import java.io.IOException;

public class Menu {

	PantallaLaterna miPantalla;
	String pathAcciones;

	public Menu(String path) {
		this.pathAcciones = path;
	}

	public final void init() throws IOException {
		
		if(isJson())
			this.miPantalla = new PantallaLaterna(new LeerArchivoJson(this.pathAcciones));
		else 
			this.miPantalla = new PantallaLaterna(new LeerArchivoComun(this.pathAcciones));
		
		this.miPantalla.mostrarMenu();
	}
	
	
	private boolean isJson() {

		File file = new File(this.pathAcciones);
		String fileName = file.getName();

		if (fileName.endsWith(".json"))
			return true;
		else
			return false;
	}

}
