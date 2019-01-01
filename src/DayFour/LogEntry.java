package DayFour;

import java.time.LocalDateTime;

public class LogEntry implements Comparable<LogEntry>{
	private LocalDateTime dateTime;
	private String action;
	private String guardId = "";
	
	public LogEntry(LocalDateTime dateTime, String content) {
		this.dateTime = dateTime;
		this.action = content.substring(0, 5);
		if (action.equals("Guard")) {
			String[] words = content.split(" ");
			this.guardId = words[1];
		}
		
		
	}
	
	public int compareTo(LogEntry otherEntry) {
		return this.dateTime.compareTo(otherEntry.dateTime);		
	}
	
	public int getMinutes() {
		if (dateTime.getHour() == 23) return 0;
		return dateTime.getMinute();
	}
	
	public String getAction() {
		return action;
	}
	
	public String getGuardId() {
		return guardId;
	}
	
	public LocalDateTime getTime() {
		return dateTime;
	}

	

}
