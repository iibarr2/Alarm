/**
 * 
 */
package edu.ilstu;

/**
 * @author israe
 *
 */

/*
 * constructor class to create alarms from the AlarmGUI class
 */
public class Alarm {
	
	public String month, day, hour, min, zone, message;
	
	public Alarm(String month, String day, String hour, String min, String zone, String message) {
		super();
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.min = min;
		this.zone = zone;
		this.message = message;
	}

	

	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public String getHour() {
		return hour;
	}



	public void setHour(String hour) {
		this.hour = hour;
	}



	public String getMin() {
		return min;
	}



	public void setMin(String min) {
		this.min = min;
	}



	public String getZone() {
		return zone;
	}



	public void setZone(String zone) {
		this.zone = zone;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	@Override
	public String toString() {
		return month + " " + day + " " + hour + ":" + min + " " + zone;
	}
	
	
	
	

}
