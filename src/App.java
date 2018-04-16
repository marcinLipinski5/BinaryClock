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
		
		//Get hours

        System.out.println( convert.binaryConvert(time.getHours()));
        
        //Get minutes

        System.out.println( convert.binaryConvert(time.getMinutes()));
        
        //Get seconds

        System.out.println( convert.binaryConvert(time.getSeconds()));
       
	}

}
