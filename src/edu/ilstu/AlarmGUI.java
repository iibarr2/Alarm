/**
 * 
 */
package edu.ilstu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public String[] alarmListArray = new String[500];
	public int alarmSize = 0;

	private JLabel currentTime, alarmSetTime;
	
	private final MyActionListener actionListener;
	private final DeleteListener deleteListener;
	private final TimeListener timeListener;
	
	public String pickedHour, pickedDay, pickedMinute, pickedMonth, pickedAMPM, pickedMessage;
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
	
//	private final boolean mode = false;
//	
//	private void updateTime() {
//        String tempTime = String.format("%tT", new Date());
//        if (mode) {
//            currentTime.setText(tempTime);
//        } else {
//            currentTime.setText(String.format("%tr", new Date()));
//        }
//    }
	
	private void createTimer() {
        Timer timer = new Timer(1000, new TimeListener());
        //timer.setActionCommand(null);
        timer.setInitialDelay(0);
        timer.start();
    }
	
	/*
	 * action listener for the current time
	 */
	class TimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Date date = new Date();
			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			String time = timeFormat.format(date);
			currentTime.setText(time);
		}
		
	}
	
	/*
	 * creates a separate panel to hold the buttons to create a new alarm
	 * and make GUI look cleaner
	 */
	private JPanel buttons() {
		JPanel panel = new JPanel();
		panel.add(createButton("Create New Alarm", actionListener));
		panel.add(createButton("Delete Alarms", deleteListener));
		return panel;
	}
	
	
	/*
	 * creates combo box that holds the list of alarms just created
	 */
	private JComboBox alarmList() {
        alarmList = new JComboBox<>();
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
        deleteListener = new DeleteListener();
        timeListener = new TimeListener();
        createAlarm();
    }
	
	/*
	 * creates the frame for the alarm clock
	 */
	private void createAlarm() {
        frame = new JFrame("Alarm Clock");
        frame.getContentPane().add(mainPanel());
        createTimer();
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
	 * listener that is triggered when the delete alarm button is pressed
	 * will delete alarm from the arraylist
	 */
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
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
		ActionListener actionListenerMonth = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  alarmSize ++;
		    	   alarmListArray[alarmSize] = (String) monthCombo.getSelectedItem();
		        
		      }
		    };
		monthCombo.setSelectedIndex(0);
		monthCombo.add(monthLabel, JLabel.NORTH);
		monthCombo.addActionListener(actionListenerMonth);
		panel.add(monthCombo);
		
		
		JTextField days = new JTextField();
		ActionListener actionListenerDay = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	
		    	  alarmListArray[alarmSize] = (String) days.getSelectedText();
		      }
		    };
		days.setText("00");
		days.setBackground(Color.LIGHT_GRAY);
		days.setEditable(true);
		days.addActionListener(actionListenerDay);
		panel.add(days);
		
		   
		
		JComboBox hourCombo = new JComboBox(hours);
		JLabel hourLabel = new JLabel("Hours");
		ActionListener actionListenerHour = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        
		    	  alarmListArray[alarmSize] = (String) hourCombo.getSelectedItem();
		      }
		    };
		   
		hourCombo.setSelectedIndex(0);
		hourCombo.add(hourLabel, JLabel.NORTH);
		hourCombo.addActionListener(actionListenerHour);
		panel.add(hourCombo);
		
		 
		
		JComboBox minsCombo = new JComboBox(mins);
		JLabel minsLabel = new JLabel("Minutes");
		ActionListener actionListenerMinute = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  
		    	  alarmListArray[alarmSize] = (String) minsCombo.getSelectedItem();
		        
		      }
		    };
		minsCombo.setSelectedIndex(0);
		minsCombo.add(minsLabel, JLabel.NORTH);
		minsCombo.addActionListener(actionListenerMinute);
		panel.add(minsCombo);
		
		JComboBox zoneCombo = new JComboBox(zone);
		ActionListener actionListenerZone = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  alarmListArray[alarmSize] = (String) zoneCombo.getSelectedItem();

		        
		      }
		    };
		zoneCombo.setSelectedIndex(0);
		zoneCombo.addActionListener(actionListenerZone);
		panel.add(zoneCombo);
		
		JTextField messageTF = new JTextField();
		ActionListener actionListenerMessage = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  System.out.println("asdasd");
		    	  alarmListArray[alarmSize] = (String) messageTF.getSelectedText();
		    	  System.out.println(alarmListArray[0].toString());
		      }
		    };
		messageTF.setText("Enter optional message here");
		messageTF.addActionListener(actionListenerMessage);
		panel.add(messageTF);
		
		JButton createAlarm = new JButton("Create alarm");
		panel.add(createAlarm);
		
	}
}