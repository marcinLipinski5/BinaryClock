import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import java.util.*;
import javax.swing.*;

public class Convert {
	JPanel panel;
	GetTime time = new GetTime();

	public String binaryConvert(String numbers) {
		int number = 0;
		try {
			number = Integer.parseInt(numbers);
		} catch (NumberFormatException ex) {
			System.out.println("Can't convert time to int format");
		}
		String timeBinaryForm;
		timeBinaryForm = Integer.toBinaryString(number);
		return timeBinaryForm;
	}

	public JPanel guiHelper(String value) {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		setDiode(value);
		return panel;
	}

	
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
	
	
	public String getDecimalTime() {
		return time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
	}

	private abstract class Diode extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
		}
	}
	
	private class OnDiode extends Diode {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon("on.jpg").getImage();
			g.drawImage(image, 1, 1, this);
		}
	}

	private class OffDiode extends Diode {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon("off.jpg").getImage();
			g.drawImage(image, 1, 1, this);
		}
	}

	class InactiveDiode extends Diode {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon("inactive.jpg").getImage();
			g.drawImage(image, 1, 1, this);
		}
	}
	
}
