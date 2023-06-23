package martin.framework;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class LeerArchivoJson extends LeerArchivo {

	private int maxThreads;

	public LeerArchivoJson(String path) {
		super(path);
	}

	@Override
	public List<Accion> devolverAcciones() {

		List<Accion> acciones = new ArrayList<>();

		Gson gson = new GsonBuilder().create();

		Reader r = null;
		try {

			var is = this.getClass().getResource(super.path).openStream();
			r = new InputStreamReader(is);

			JsonReader reader = new JsonReader(r);

			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

			/* Obtengo el array de acciones que contiene el archivo de configuracion dado */
			JsonArray jsonArray = jsonObject.getAsJsonArray("acciones");

			/*
			 * Obtengo el valor de hilos maximos que pueden ejecutarse indicados en el
			 * archivo de configuracion
			 */
			this.maxThreads = jsonObject.get("max-threads").getAsInt();

			for (JsonElement element: jsonArray) {

				Accion accionTemp = (Accion) Class.forName(element.getAsString()).getDeclaredConstructor()
						.newInstance();
				
				acciones.add(accionTemp);
			}

			return acciones;

		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			throw new RuntimeException("Ocurrio un error con el archivo .json: " + e.getMessage());
		}

	}

	@Override
	public Integer cantidadHilos() {
		return this.maxThreads;
	}

}
