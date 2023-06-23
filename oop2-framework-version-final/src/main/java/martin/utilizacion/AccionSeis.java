package martin.utilizacion;

import martin.framework.Accion;

public class AccionSeis extends Accion {

	@Override
	public void ejecutar() {
		System.out.print("Ejecutando Accion Seis...");
	}

	@Override
	public String nombreItemMenu() {
		
		return "Accion 6";
	}

	@Override
	public String descripcionItemMenu() {
		
		return "Esta accion lista los 5 mejores vinos de Argentina...";
	}

}
