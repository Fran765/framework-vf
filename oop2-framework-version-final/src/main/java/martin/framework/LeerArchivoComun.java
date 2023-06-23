package martin.framework;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LeerArchivoComun extends LeerArchivo {

	public LeerArchivoComun(String path) {
		super(path);
	}

	@Override
	public List<Accion> devolverAcciones() {

		Properties prop = new Properties();

		List<Accion> acciones = new ArrayList<>();

		try (InputStream configFile = getClass().getResourceAsStream(super.path);) {

			prop.load(configFile);
			String clase = prop.getProperty("accions");
			String[] clases = clase.split(";");

			for (String element : clases) {

				Accion accionTemp = (Accion) Class.forName(element).getDeclaredConstructor().newInstance();
				acciones.add(accionTemp);
			}

			return acciones;

		} catch (Exception e) {
			throw new RuntimeException("Ocurrio un error con el archivo .config: " + e.getMessage());
		}
	}

	@Override
	public Integer cantidadHilos() {
		return 1;
	}

}
