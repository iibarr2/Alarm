/**
 * 
 */
package edu.ilstu;

import java.awt.Color;

/**
 * @author israe
 *
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AlarmGUI extends JFrame {
	
	/*
	 * creates combo boxes for a drop down menu to create an alarm
	 */
	
	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	String[] hours = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] mins = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24",
			"25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
			"49","50","51","52","53","54","55","56","57","58","59","60"};
	String[] zone = {"AM","PM"};
	
	public AlarmGUI() {
		JFrame frame = new JFrame("Alarm Clock");
		//frame.setSize(1000,1000);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		days.setText("0");
		days.setBackground(Color.MAGENTA);
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
		
		frame.pack();
	}
	
	public static void main (String args[]) {
		AlarmGUI alarm = new AlarmGUI();
	}

}
