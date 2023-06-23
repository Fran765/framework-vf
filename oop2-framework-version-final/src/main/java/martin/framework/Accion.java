package martin.framework;

public abstract class Accion {

	public abstract void ejecutar();

	public abstract String nombreItemMenu();

	public abstract String descripcionItemMenu();

	@Override
	public String toString() {
		return (this.nombreItemMenu() + " ( " + this.descripcionItemMenu() + " )");
	}

}
