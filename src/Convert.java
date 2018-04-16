import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import java.util.*;
import javax.swing.*;

public class Convert {
	JPanel panel;
	GetTime time = new GetTime();

	//This method convert time value from String to in 
	//and then convert time from decimal to binary form
	public String binaryConvert(String numbers) {
		// Converting time value from String to int
		int number = 0;
		try {
			number = Integer.parseInt(numbers);
		} catch (NumberFormatException ex) {
			System.out.println("Can't convert time to int format");
		}

		// Converting from decimal to binary
		String timeBinaryForm;

		timeBinaryForm = Integer.toBinaryString(number);

		return timeBinaryForm;

	}

	//This method is used to set proper diode to the panel
	public JPanel guiHelper(String value) {

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		setDiode(value);
		return panel;
	}

	//This method check which line of diodes (hours, minutes, seconds) should be now operated
	public String operatorCheck(String value) {
		
		String operator = "0";
		if (value.equals("hours")) {
			operator = binaryConvert(time.getHours());
		} else if (value.equals("minutes")) {
			operator = binaryConvert(time.getMinutes());
		} else if (value.equals("seconds")) {
			operator = binaryConvert(time.getSeconds());
		}

		return operator;
	}

	//In this method diodes from proper line (hours, minutes, seconds) are set to 
	//true or false position
	public JPanel setDiode(String value) {
		time = new GetTime();
		String operator = operatorCheck(value);
		for (int i = 0; i < 6 - operator.length(); i++) {

			Diode diode = new OffDiode();
			panel.add(diode);
		}

		for (int i = 0; i < operator.length(); i++) {

			try {
				if (operator.charAt(i) == '1') {
					Diode diode = new OnDiode();
					panel.add(diode);

				} else if (operator.charAt(i) == '0') {
					Diode diode = new OffDiode();
					panel.add(diode);
				}
			} catch (IndexOutOfBoundsException ex) {
				InactiveDiode diode = new InactiveDiode();
				panel.add(diode);
			}
		}
		return panel;
	}
	
	//Get decimal time
	public String getDecimalTime() {
		return time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
	}

	private abstract class Diode extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {

		}
	}

	//Diode to show "true" value of binary form time
	private class OnDiode extends Diode {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon("on.jpg").getImage();
			g.drawImage(image, 1, 1, this);
		}
	}

	//Diode to show "false" value of binary form time
	private class OffDiode extends Diode {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon("off.jpg").getImage();
			g.drawImage(image, 1, 1, this);
		}
	}

	//Diode to show that something during conversion went wrong
	class InactiveDiode extends Diode {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon("inactive.jpg").getImage();
			g.drawImage(image, 1, 1, this);
		}
	}
	
}
