
import java.awt.BorderLayout;
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
	String[] numbers = { "1", "2", "4", "8", "16", "32" };
	JLabel decimalTimeLabel;

	Convert convert = new Convert();

	public void start() throws FileNotFoundException {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		numbersBox = new Box(BoxLayout.X_AXIS);
		
		for (int i = 0; i <= 5; i++) {
			JLabel label = new JLabel("<html><p style=\"color:#778899\">" + numbers[5 - i] + "</p></html>");
			label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			numbersBox.add(label);
		}

		numbersBox.setBorder(BorderFactory.createEmptyBorder(5, 25, 25, 25));
		hoursPanel = new JPanel();
		hoursPanel.setLayout(new BoxLayout(hoursPanel, BoxLayout.X_AXIS));
		
		minutesPanel = new JPanel();
		minutesPanel.setLayout(new BoxLayout(minutesPanel, BoxLayout.X_AXIS));

		secondsPanel = new JPanel();
		secondsPanel.setLayout(new BoxLayout(secondsPanel, BoxLayout.X_AXIS));

		timePanel = new JPanel();
		timePanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 0, 25));
		timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));

		decimalTimeLabel = new JLabel(convert.getDecimalTime());

		labelsPanel = new JPanel();
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
		labelsPanel.add(numbersBox);

		decimalTimePanel = new JPanel();
		decimalTimePanel.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 0));

		frame.getContentPane().add(BorderLayout.SOUTH, labelsPanel);

		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				createTimePanel();
			}
		}, 0, 1000, TimeUnit.MILLISECONDS);
	}

	public void createTimePanel() {

		hoursPanel.removeAll();
		hoursPanel.add(convert.guiHelper("hours"));

		minutesPanel.removeAll();
		minutesPanel.add(convert.guiHelper("minutes"));

		secondsPanel.removeAll();
		secondsPanel.add(convert.guiHelper("seconds"));

		decimalTimeLabel.setText(convert.getDecimalTime());
		decimalTimePanel.removeAll();
		decimalTimePanel.add(decimalTimeLabel);

		timePanel.removeAll();
		timePanel.add(hoursPanel);
		timePanel.add(minutesPanel);
		timePanel.add(secondsPanel);

		frame.getContentPane().add(BorderLayout.CENTER, timePanel);
		frame.getContentPane().add(BorderLayout.NORTH, decimalTimePanel);
		frame.setSize(250, 250);
		frame.setVisible(true);
	}

}
