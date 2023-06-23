package martin.framework;

import java.util.List;

public abstract class LeerArchivo {
	
	String path;
	
	public LeerArchivo(String path) {
		this.path = path;
	}
	
	abstract List<Accion> devolverAcciones();
	
	abstract Integer cantidadHilos();

}
