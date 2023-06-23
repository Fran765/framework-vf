package martin.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class PantallaLaterna {

	LeerArchivo miLector;
	private List<Accion> accions;
	BasicWindow window;
	Terminal terminal;
	Panel panelGral;

	public PantallaLaterna(LeerArchivo unLector) {

		Objects.requireNonNull(unLector);

		this.miLector = unLector;
		this.accions = new ArrayList<>();

	}

	public void mostrarMenu() throws IOException {

		this.accions = miLector.devolverAcciones();

		this.terminal = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(terminal);
		screen.startScreen();

		this.window = new BasicWindow();

		this.panelGral = new Panel();
		new Label("Bienvenido, estas son sus opciones:").addTo(panelGral);

		window.setComponent(panelGral);

		TerminalSize size = new TerminalSize(70, 10);
		CheckBoxList<Accion> checkBoxList = new CheckBoxList<Accion>(size);

		for (Accion unaAccion : accions) {
			checkBoxList.addItem(unaAccion);
		}

		Button btnContinuar = new Button("Continuar", new Runnable() {
			@Override
			public void run() {
				confirmarEjecucionAcciones(checkBoxList.getCheckedItems());
			}
		});

		Button btnSalir = new Button("Salir", new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		});

		panelGral.addComponent(checkBoxList);
		panelGral.addComponent(btnContinuar);
		panelGral.addComponent(btnSalir);

		MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
				new EmptySpace(TextColor.ANSI.BLUE));
		gui.addWindowAndWait(window);

	}

	private void confirmarEjecucionAcciones(List<Accion> actionsChecks) {

		Panel info = new Panel();
		Label infoAcciones = new Label("Se ejecutaran las acciones: ");

		for (Accion unaAccionElegida : actionsChecks) {
			infoAcciones.setText(infoAcciones.getText() + unaAccionElegida.nombreItemMenu() + " ");
		}
		
		info.addComponent(infoAcciones);

		Button btnOk = new Button("Ok", new Runnable() {
			@Override
			public void run() {

				List<AccionAdapter> misAcciones = new ArrayList<AccionAdapter>();

				for (Accion accion : actionsChecks) {
					misAcciones.add(new AccionAdapter(accion));
				}

				ExecutorService executor = Executors.newFixedThreadPool(miLector.cantidadHilos());

				try {
					executor.invokeAll(misAcciones);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					terminal.close();
					mostrarMenu();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		Button btnCancel = new Button("Cancelar", new Runnable() {
			@Override
			public void run() {
				try {
					terminal.close();
					mostrarMenu();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btnOk.addTo(info);
		btnCancel.addTo(info);

		window.setComponent(info);
	}

}
