
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.FileNotFoundException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {

	JFrame frame;
	JPanel hoursPanel;
	JPanel minutesPanel;
	JPanel secondsPanel;
	JPanel timePanel;
	JPanel labelsPanel;
	JPanel decimalTimePanel;
	Box numbersBox;
	String[] numbers = {"1", "2", "4", "8", "16", "32"};
	JLabel decimalTimeLabel;
	
	Convert convert = new Convert();

	public void start() throws FileNotFoundException {

		// Creating main frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creating numbers label
		 numbersBox = new Box(BoxLayout.X_AXIS);
		 for (int i=0; i<=5; i++) {
			 JLabel label = new JLabel("<html><p style=\"color:#778899\">"+numbers[5-i]+"</p></html>");
			 label.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
			 numbersBox.add(label);
		 }
		 
		 numbersBox.setBorder(BorderFactory.createEmptyBorder(5,25,25,25));

		// Creating hours panel
		hoursPanel = new JPanel();
		hoursPanel.setLayout(new BoxLayout(hoursPanel, BoxLayout.X_AXIS));

		// Creating minutes panel
		minutesPanel = new JPanel();
		minutesPanel.setLayout(new BoxLayout(minutesPanel, BoxLayout.X_AXIS));

		// Creating seconds panel
		secondsPanel = new JPanel();
		secondsPanel.setLayout(new BoxLayout(secondsPanel, BoxLayout.X_AXIS));

		// Creating time panel
		timePanel = new JPanel();
		timePanel.setBorder(BorderFactory.createEmptyBorder(5,25,0,25));
		timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));

		//Creating panel with graphics binary clock
		decimalTimeLabel = new JLabel(convert.getDecimalTime());
		
		//Creating labels panel
		labelsPanel = new JPanel();
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
		labelsPanel.add(numbersBox);
		
		//Creating decimal time panel
		decimalTimePanel = new JPanel();
		decimalTimePanel.setBorder(BorderFactory.createEmptyBorder(0,180,0,0));
		
		// Adding all components to the frame
		
		frame.getContentPane().add(BorderLayout.SOUTH, labelsPanel);											//Adding numbers labels

		
		//Adding and refreshing timePanel	
		//This part means "run createTimePanel() method and repeat this in every 1000ms"
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();		
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {																					
				createTimePanel();
			}
		}, 0, 1000, TimeUnit.MILLISECONDS);

	}// Start method ends
	


	public void createTimePanel() {
		//cleaning old variable
		hoursPanel.removeAll();
		//getting new variable
		hoursPanel.add(convert.guiHelper("hours"));
		
		//cleaning old variable
		minutesPanel.removeAll();
		//getting new variable
		minutesPanel.add(convert.guiHelper("minutes"));
		
		//cleaning old variable
		secondsPanel.removeAll();
		//getting new variable
		secondsPanel.add(convert.guiHelper("seconds"));
		
		//getting actual time (decimal)
		decimalTimeLabel.setText(convert.getDecimalTime());	
		//remove old value
		decimalTimePanel.removeAll();
		//setting new decimal time value
		decimalTimePanel.add(decimalTimeLabel);
		
		//cleaning old variable
		timePanel.removeAll();
		//adding new variable
		timePanel.add(hoursPanel);
		timePanel.add(minutesPanel);
		timePanel.add(secondsPanel);

		//adding new variable to main frame
		frame.getContentPane().add(BorderLayout.CENTER, timePanel);
		frame.getContentPane().add(BorderLayout.NORTH, decimalTimePanel);
		frame.setSize(250, 250);
		frame.setVisible(true);
	}

}
