package edu.ilstu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputOutput
{
	
	
	/*
	 * 
	 * makeAlarm method is called on a array of strings that contain all
	 * of the info on the alarm. should work for an array of any size. 
	 * the "true" modifier in printwriter means that it should always append
	 * to the end of the .csv file.
	 * 
	 */
	public void makeAlarm(String[] alarmArray) throws IOException
	{
		File file = new File("alarms.csv");
		PrintWriter pw = new PrintWriter(new FileOutputStream("alarms.csv",true));
		StringBuilder sb = new StringBuilder();

		
		for(int i = 0; i < alarmArray.length;i++)
		{
			sb.append(alarmArray[i]);
			sb.append(",");
		
		}
		pw.write(sb.toString());

		pw.write("\n");
		pw.flush();
		pw.close();

	}
	
	
	/*
	 * deletAll method is called on the io object in the main.
	 * deletes all of the contents of the csv file.
	 */
	public void deleteAll() throws IOException
	{
		File file = new File("alarms.csv");
		PrintWriter pw = new PrintWriter(new FileOutputStream("alarms.csv"));
		int row = 0;
		String[] empty = {"","","","","","","","","","","","","","",""};

		try {
			Scanner scanner = new Scanner(file);
			StringBuilder sb = new StringBuilder();

			// now read the file line by line...

			int rowNum = 0;
			int colNum = 0;
			//scanner.nextLine();
			//scanner.nextLine();
			while (scanner.hasNextLine()) 
			{
				for(int i = 0; i < empty.length;i++)
				{
					sb.append(empty[i]);
					sb.append(",");
				
				}
				pw.write(sb.toString());
				String line = scanner.nextLine();

			
			}
		} catch (FileNotFoundException e) {
			System.out.println("No file found");
		}	
		
	}
          
	
	/*
	 * this method does not work yet. 
	 */
    public List<List<String>>  getAlarms()
    {
    	List<List<String>> allAlarms = new ArrayList<>();
    	String[] singleAlarm = null;
    	
    	
    	try 
    	{
    		File file = new File("alarms.csv");
    		PrintWriter pw = new PrintWriter(new FileOutputStream("alarms.csv"));
    		Scanner scanner = new Scanner(file);
			StringBuilder sb = new StringBuilder();
			String line = null;
			int i = 0;
			int j = 0;
			
			
			
			while(scanner.hasNextLine())
			{
				line = scanner.nextLine();
				singleAlarm = line.split(",");
				
				allAlarms.add(Arrays.asList(singleAlarm));
				
				
				
			}

    		
    	} catch(FileNotFoundException e) {
    		System.out.println("No file found");

    	}
    	
    	return allAlarms;
    }
	

    
 
		
	
	
}
