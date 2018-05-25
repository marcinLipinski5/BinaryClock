import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetTime {
	Calendar cal = Calendar.getInstance();
	
	
	public String getHours() {
		SimpleDateFormat hours = new SimpleDateFormat("HH");
		System.out.println("Hours " + hours.format(cal.getTime()));
		return hours.format(cal.getTime());
	}

	
	public String getMinutes() {
		SimpleDateFormat minutes = new SimpleDateFormat("mm");
		System.out.println("Minutes " + minutes.format(cal.getTime()));
		return minutes.format(cal.getTime());
	}

	
	public String getSeconds() {
		SimpleDateFormat seconds = new SimpleDateFormat("ss");
		System.out.println(("Seconds " + seconds.format(cal.getTime())));
		return seconds.format(cal.getTime());
	}
}
