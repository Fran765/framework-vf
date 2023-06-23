package martin.framework;

import java.util.concurrent.Callable;

public class AccionAdapter implements Callable<Integer> {

	private Accion miAccion;

	public AccionAdapter(Accion unaAccion) {
		this.miAccion = unaAccion;
	}

	@Override
	public Integer call() throws Exception {

		this.miAccion.ejecutar();
		Thread.sleep(700);

		return null;
	}

}
