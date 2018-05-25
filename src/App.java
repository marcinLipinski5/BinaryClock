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
		

        System.out.println( convert.binaryConvert(time.getHours()));
        

        System.out.println( convert.binaryConvert(time.getMinutes()));
        

        System.out.println( convert.binaryConvert(time.getSeconds()));
       
	}

}
