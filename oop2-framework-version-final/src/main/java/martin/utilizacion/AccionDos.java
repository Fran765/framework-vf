package martin.utilizacion;

import martin.framework.Accion;

public class AccionDos extends Accion {

	@Override
	public void ejecutar() {
		System.out.print("Ejecutando Accion Dos...");
	}

	@Override
	public String nombreItemMenu() {
		
		return "Accion 2";
	}

	@Override
	public String descripcionItemMenu() {
		
		return "Esto trae las PRIMERAS diez personas de la BD...";
	}

}
