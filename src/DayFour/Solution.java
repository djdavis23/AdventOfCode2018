package DayFour;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {	
	private static ArrayList<LogEntry> log = new ArrayList<LogEntry>();
	private static HashMap<String, int[]> guardSleepRecord = new HashMap<String, int[]>();
	private static String guardOnDuty;
	private static int startMinute;
	private static int endMinute;
	

	public static void main(String[] args) {

		try {
			File file = new File("C:\\Users\\dcaa2\\OneDrive\\Documents\\AdventOfCode\\DayFourInput.txt");
			Scanner sc = new Scanner(file);	
					
		
			while (sc.hasNextLine()) {
				String entry = sc.nextLine();
				String time = entry.substring(1,11) + "T" + entry.substring(12,17);
				LocalDateTime dateTime = LocalDateTime.parse(time);
				String content = entry.substring(19);				
				LogEntry logEntry = new LogEntry(dateTime, content);
				log.add(logEntry);				
				}
			log.sort(null);
			
			buildGuardSleepRecord();
			String sleepyGuard = findSleepiestGuard();
			int guardNum = Integer.parseInt(sleepyGuard.substring(1));
			int sleepyMinute = findSleepiestMinuteByGuard(sleepyGuard);
			System.out.println("The answer is to part one is " + guardNum*sleepyMinute);
			findSleepiestMinute();
		}	
				
		catch(Exception e) {
			System.out.println(e);
	
		}
		

	}
	
	private static void buildGuardSleepRecord() {
		for (LogEntry entry : log) {
			//update active guard at shift change
			if (entry.getAction().equals("Guard")) {
				guardOnDuty = entry.getGuardId();
				System.out.println("Guard coming on duty: " + entry.getGuardId());
			}
			//update start sleep time when guard falls asleep
			else if (entry.getAction().equals("falls")) {
				startMinute = entry.getMinutes();
				System.out.println("Guard " + guardOnDuty + " falls asleep at 00:" + startMinute);
			}
			//update sleepLog when guard wakes up
			else {				
				endMinute = entry.getMinutes();
				System.out.println("Guard " + guardOnDuty + " wakes up at 00:" + endMinute);
				int[] sleepLog;
				if (guardSleepRecord.containsKey(guardOnDuty)) {
					sleepLog = guardSleepRecord.get(guardOnDuty);
				}
				else {
					sleepLog = new int[60];
				}
				for (int i = startMinute; i < endMinute; i++) {
					sleepLog[i] += 1;
				}
				guardSleepRecord.put(guardOnDuty, sleepLog);
			}
		}
	}
	
	private static String findSleepiestGuard() {
		String sleepyGuard = "";
		int sleepTime = 0;
		System.out.println("Guards:");
		for (Map.Entry<String, int[]> entry : guardSleepRecord.entrySet()) {
			String guard = entry.getKey();
			System.out.print(guard + " : ");
			int[] sleepTimes = entry.getValue();
			int sleepMinutes = 0;
			for (int minute : sleepTimes) {
				if (minute > 0) sleepMinutes += minute;
			}
			System.out.print(sleepMinutes + "\n");
			if (sleepMinutes > sleepTime) {
				sleepyGuard = guard;
				sleepTime = sleepMinutes;
			}
		}
		return sleepyGuard;
	}
	
	private static int findSleepiestMinuteByGuard(String guard) {
		int[] sleep = guardSleepRecord.get(guard);
		int minute = 0;
		int sleepNum = 0;
		for (int i = 0; i < sleep.length; i++) {
			if (sleep[i] > sleepNum) {
				sleepNum = sleep[i];
				minute = i;
			}
		}		
		return minute;
	}
	
	private static void findSleepiestMinute() {
		String sleepyGuard = "";
		int sleepFreq = 0;
		int theMinute = 0;
		
		for (Map.Entry<String, int[]> entry : guardSleepRecord.entrySet()) {
			String guard = entry.getKey();			
			int[] sleepTimes = entry.getValue();			
			for (int i = 0; i < sleepTimes.length; i++) {
				if (sleepTimes[i] > sleepFreq) {
					sleepyGuard = guard;
					sleepFreq = sleepTimes[i];
					theMinute = i;
				}
			}			
		}
		int guardNumber = Integer.parseInt(sleepyGuard.substring(1));
		System.out.println("The answer to part two is " + guardNumber * theMinute);
	}

}