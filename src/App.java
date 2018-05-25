import java.io.FileNotFoundException;


public class App {
	public static void main(String[] args) {
		
		GetTime time = new GetTime();
		Convert convert = new Convert();
		GUI gui = new GUI();
		
		try {
			gui.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}    
	}
}
