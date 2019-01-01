package DayTwo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		try {
				File file = new File("C:\\Users\\dcaa2\\OneDrive\\Documents\\AdventOfCode\\DayTwoInput.txt");
				Scanner sc = new Scanner(file);			
				ArrayList<String> ids = new ArrayList<String>();
			
				while (sc.hasNextLine()) {
					ids.add(sc.nextLine());
				}				
				System.out.println("Checksum equals " + calcChecksum(ids));
				System.out.println("Common letters are " + findCommonChars(ids));
			}		
		catch(Exception e) {
			System.out.println(e);
		
		}		

	}
	static int calcChecksum(ArrayList<String> ids) {
		int numTwos = 0;
		int numThrees = 0;
		
		for (String id : ids) {
			HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();
			for (int i = 0; i < id.length(); i++) {
				Character myChar = Character.valueOf(id.charAt(i));
				if (charCounts.containsKey(myChar)) {
					charCounts.replace(myChar, charCounts.get(myChar) + 1);
				}
				else {
					charCounts.put(myChar, Integer.valueOf(1));
				}
			}
			if (charCounts.containsValue(Integer.valueOf(2))) numTwos++;
			if (charCounts.containsValue(Integer.valueOf(3))) numThrees++;
		}
		return numTwos * numThrees;
	}
	
	static String findCommonChars(ArrayList<String> ids) {
		String id1 = "";
		String id2 = "";
		
		for (int i = 0; i < ids.size() - 1; i++) {
			int mismatch = 0;
			for (int j = i + 1; j < ids.size(); j++) {
				id1 = ids.get(i);
				id2 = ids.get(j);
				mismatch = 0;
				for (int k = 0; k < id1.length(); k++) {
					if (id2.charAt(k) != id1.charAt(k)) mismatch++;
					if (mismatch > 1) break;
				}
				if (mismatch == 1) break;
			}
			if (mismatch == 1) break;
		}
		StringBuilder answer = new StringBuilder();
		for (int x = 0; x < id1.length(); x++) {
			if (id2.charAt(x) == id1.charAt(x)) {
				answer.append(id1.charAt(x));
			}
		}
		
		return answer.toString();
	}

}


