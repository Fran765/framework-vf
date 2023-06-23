package martin.main;

import java.io.IOException;

import martin.framework.Menu;

public class Main {

	public static void main(String[] args) throws IOException {
//		Menu m = new Menu("/accions.propierties");
		Menu m = new Menu("/accions.json");
		m.init();
	}

}
