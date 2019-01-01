package PuzzleOne;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;

public class Solution {
	

	public static void main(String[] args) {
		int answer = 0;
		try {
			File file = new File("C:\\Users\\dcaa2\\OneDrive\\eclipse-workspace\\AdventOfCode\\src\\PuzzleOne\\PuzzleOneInput.txt");
			
			Scanner sc = new Scanner(file);			
			ArrayList<String> changes = new ArrayList<String>();
			
			while (sc.hasNextLine()) {
				changes.add(sc.nextLine());
			}
			
			HashSet<Integer> freqs = new HashSet<Integer>();
			freqs.add(Integer.valueOf(answer));
			boolean newFreq = true;
			
			while (newFreq) {
				for (String line : changes) {
					answer = update(answer, line);
					System.out.println(answer);
					newFreq = freqs.add(Integer.valueOf(answer));
					if (!newFreq) break;					
				}
			}
			System.out.println("The answer is " + answer);
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		
		}
		

	}
	
	static int update(int current, String next) {
		int increment = Integer.parseInt(next.substring(1));
		if (next.charAt(0) == '+') {
			current += increment;
		}
		else {
			current -= increment;
		}		
		return current;
	}

}
