package DayFive;

import java.io.File;
import java.util.Scanner;


public class Solution {
	

	public static void main(String[] args) {
		File file = new File("C:\\Users\\dcaa2\\OneDrive\\Documents\\AdventOfCode\\DayFiveInput.txt");
		

		try {
			Scanner sc = new Scanner(file);
			String polymer = sc.next();
			//String polymer = "dabAcCaCBAcCcaDA";
			System.out.println ("Input: " +  polymer.length());
			char[] alphabet = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
			char bestSub = 'a';
			int bestCase = polymer.length();
			for (int i = 0; i < alphabet.length; i++) {
				char letter = alphabet[i];
				String modifiedPolymer = modifyPolymer(letter, polymer);
				System.out.println("Polymer length after removing " + letter + ": " + modifiedPolymer.length());
				String newPolymer = reducePolymer(modifiedPolymer);
				System.out.println("Reduced: " + newPolymer.length());
				if (newPolymer.length() < bestCase) {
					bestCase = newPolymer.length();
					bestSub = letter;
				}
			}
			System.out.println("Best case:  " + bestSub + " : " + bestCase);
			sc.close();			
			
		}		
		catch(Exception e) {
			System.out.println(e);
	
		}
		

	}
	
	private static String reducePolymer(String polymer) {
		boolean reduced;
		do {
			reduced = false;
			StringBuilder modified = new StringBuilder();
			int start = 0;
			for (int i = 0; i < polymer.length()-1; i++) {						
				String left = polymer.substring(i, i+1);
				String right = polymer.substring(i+1, i+2);
				if (!(left.equals(right)) && (left.equals(right.toLowerCase()) || left.equals(right.toUpperCase()))) {
					modified.append(polymer.substring(start, i));
					start = i+2;
					i++;
					reduced = true;
				}
			}
			modified.append(polymer.substring(start, polymer.length()));
			polymer = modified.toString();				
		} while (reduced);		
		return polymer;
	}
	
	private static String modifyPolymer(char letter, String mypolymer) {
		StringBuilder improvedPolymer = new StringBuilder();
		for (int i = 0; i < mypolymer.length(); i++) {			
			if (Character.toLowerCase(mypolymer.charAt(i)) != letter) improvedPolymer.append(mypolymer.charAt(i));
		}
		return improvedPolymer.toString();
	}
	
	
	

}