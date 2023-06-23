package martin.utilizacion;

import martin.framework.Accion;

public class AccionTres extends Accion{

	@Override
	public void ejecutar() {
		System.out.print("Ejecutando Accion Tres...");
		
	}

	@Override
	public String nombreItemMenu() {
		// TODO Auto-generated method stub
		return "Accion 3";
	}

	@Override
	public String descripcionItemMenu() {
		// TODO Auto-generated method stub
		return "Esto genera un archivo rar con todas las acciones...";
	}

}
