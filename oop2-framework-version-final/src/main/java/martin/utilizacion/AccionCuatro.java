package martin.utilizacion;

import martin.framework.Accion;

public class AccionCuatro extends Accion {

	@Override
	public void ejecutar() {
		System.out.print("Ejecutando Accion Cuatro...");
	}

	@Override
	public String nombreItemMenu() {

		return "Accion 4";
	}

	@Override
	public String descripcionItemMenu() {

		return "Esto es para traer las fotos de Maradona en el 86'...";
	}

}
