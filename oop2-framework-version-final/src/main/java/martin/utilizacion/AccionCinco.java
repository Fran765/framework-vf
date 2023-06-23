package martin.utilizacion;

import martin.framework.Accion;

public class AccionCinco extends Accion {

	@Override
	public void ejecutar() {
		
		System.out.print("Ejecutando Accion Cinco...");
	}

	@Override
	public String nombreItemMenu() {
		
		return "Accion 5";
	}

	@Override
	public String descripcionItemMenu() {
		
		return "Esto trae las ULTIMAS diez personas de la BD...";
	}


}
