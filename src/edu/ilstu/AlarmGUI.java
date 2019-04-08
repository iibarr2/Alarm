/**
 * 
 */
package edu.ilstu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
/**
 * @author israe
 *
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class AlarmGUI{
	
	private JFrame frame;
	
	/*
	 * list that holds the alarms after they are created and throws them in combo box layout
	 */
	private JComboBox<String> alarmList;
	
	private JLabel currentTime, alarmSetTime;
	
	private final MyActionListener actionListener;
	
	/*
	 * create main JPanel for the GUI
	 */
	private JPanel mainPanel()
	{
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
		main.add(timePanel());
		main.add(alarmList());
		main.add(buttons());
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return main;
	}
	
	/*
	 * creates panel to hold the current time and the time the alarm created is set at
	 */
	private JPanel timePanel() {
		JPanel timePanel = new JPanel();
		
		currentTime = new JLabel(String.format("%tT", new Date()),SwingConstants.CENTER);
        currentTime.setFont(currentTime.getFont().deriveFont(20.0f));
        currentTime.setPreferredSize(new Dimension(200,76));
        currentTime.setOpaque(false);
        currentTime.setBorder(BorderFactory.createTitledBorder("Current Time"));
        alarmSetTime = new JLabel("00:00:00", SwingConstants.CENTER);
        alarmSetTime.setFont(alarmSetTime.getFont().deriveFont(20.0f));
        alarmSetTime.setPreferredSize(new Dimension(200,76));
        alarmSetTime.setOpaque(false);
        alarmSetTime.setBorder(BorderFactory.createTitledBorder("Alarm Time"));
        
        timePanel.add(currentTime);
        timePanel.add(alarmSetTime);
        return timePanel;
	}
	
	private final boolean mode = false;
	
	private void updateTime() {
        String tempTime = String.format("%tT", new Date());
        if (mode) {
            currentTime.setText(tempTime);
        } else {
            currentTime.setText(String.format("%tr", new Date()));
        }
    }
	
	private void createTimer() {
        Timer timer = new Timer(1000, new MyActionListener());
        timer.setActionCommand(null);
        timer.setInitialDelay(0);
        timer.start();
    }
	
	/*
	 * creates a separate panel to hold the buttons to create a new alarm
	 * and make GUI look cleaner
	 */
	private JPanel buttons() {
		JPanel panel = new JPanel();
		panel.add(createButton("Create New Alarm", actionListener));
		return panel;
	}
	
	
	/*
	 * creates combo box that holds the list of alarms just created
	 */
	private JComboBox alarmList() {
        alarmList = new JComboBox<>();
        //alarmList.addActionListener(actionListener);
        alarmList.setActionCommand("Alarm List");
        alarmList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return alarmList;
    }
	
	
	
	/*
	 * created the buttons used in the GUI
	 */
	private JButton createButton(String button, ActionListener actionListener) {
        JButton jButton = new JButton(button);
        jButton.setActionCommand(button);
        jButton.addActionListener(actionListener);
        return jButton;
    }
	
	
	/*
	 * constructor to create a new Alarm clock frame
	 */
	public AlarmGUI() {
        actionListener = new MyActionListener(); //Handles GUI events
        createAlarm();
    }
	
	/*
	 * creates the frame for the alarm clock
	 */
	private void createAlarm() {
        frame = new JFrame("Alarm Clock");
        frame.getContentPane().add(mainPanel());
        //createTimer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AlarmGUI alarm = new AlarmGUI();
            }
        });
    }
	
	
	/*
	 * class that handle creating the alarms and calls the components commented underneath for now
	 * also starts the timer on the clock to do in real time telling
	 */
	class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	/*
        	 * triggers the event after clicking create a new alarm and brings up GUI to create a new alarm
        	 * should then trigger another listener to create the alarm object and add it to the array list and start the alarm to go off at the time set
        	 */
        	popUpFrame();
        }
    }
	
	/*
	 * popup menu triggered from clicking on the create a new alrm button
	 */
	public void popUpFrame() {
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String[] hours = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String[] mins = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24",
				"25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
				"49","50","51","52","53","54","55","56","57","58","59"};
		String[] zone = {"AM","PM"};
		
		JFrame frame = new JFrame("Create Alarm");
		frame.setSize(700, 100);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JLabel alarmLabel = new JLabel("Set Alarm - ");
		panel.add(alarmLabel);
		
		
		JComboBox monthCombo = new JComboBox(months);
		JLabel monthLabel = new JLabel("Months");
		monthCombo.setSelectedIndex(0);
		monthCombo.add(monthLabel, JLabel.NORTH);
		panel.add(monthCombo);
		
		JTextField days = new JTextField();
		days.setText("00");
		days.setBackground(Color.LIGHT_GRAY);
		days.setEditable(true);
		panel.add(days);
		
		JComboBox hourCombo = new JComboBox(hours);
		JLabel hourLabel = new JLabel("Hours");
		hourCombo.setSelectedIndex(0);
		hourCombo.add(hourLabel, JLabel.NORTH);
		panel.add(hourCombo);
		
		JComboBox minsCombo = new JComboBox(mins);
		JLabel minsLabel = new JLabel("Minutes");
		minsCombo.setSelectedIndex(0);
		minsCombo.add(minsLabel, JLabel.NORTH);
		panel.add(minsCombo);
		
		JComboBox zoneCombo = new JComboBox(zone);
		zoneCombo.setSelectedIndex(0);
		panel.add(zoneCombo);
		
		JTextField messageTF = new JTextField();
		messageTF.setText("Enter optional message here");
		panel.add(messageTF);
		
		JButton createAlarm = new JButton("Create alarm");
		panel.add(createAlarm);
	}
}