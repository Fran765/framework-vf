package martin.utilizacion;

import martin.framework.Accion;

public class AccionUno extends Accion {

	public void ejecutar() {
		System.out.print("Ejecutando Accion Uno...");
	}

	public String nombreItemMenu() {
		return "Accion 1";
	}

	public String descripcionItemMenu() {
		return "Esto es para traer los twitts de Maradona...";
	}

}
