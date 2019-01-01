package DayThree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	private final static int sideLength = 1000;
	private final static int numClaims = 1381;
	private static int[][] fabric = new int[sideLength][sideLength];
	private final static Pattern pattern = Pattern.compile("#(\\d+)\\s@\\s(\\d+),(\\d+):\\s(\\d+)x(\\d+)");
	private static int[][] claims = new int[numClaims][5];
	private static int overlap = 0;

	public static void main(String[] args) {

		try {
			File file = new File("C:\\Users\\dcaa2\\OneDrive\\Documents\\AdventOfCode\\DayThreeInput.txt");
			Scanner sc = new Scanner(file);	
			int index = 0;			
		
			while (sc.hasNextLine()) {
				Matcher m = pattern.matcher(sc.nextLine());	
				if (m.find()) {
					int id = Integer.parseInt(m.group(1));
					int x1 = Integer.parseInt(m.group(2));
					int y1 = Integer.parseInt(m.group(3));
					int x2 = Integer.parseInt(m.group(4)) + x1;
					int y2 = Integer.parseInt(m.group(5)) + y1;	
					claims[index][0] = id;
					claims[index][1] = x1;
					claims[index][2] = x2;
					claims[index][3] = y1;
					claims[index][4] = y2;
					index++;
					markFabric(x1,x2,y1,y2);
				}
				
			}
			calcOverlap();
			System.out.println("SquareInches of overlap: " + overlap);
			System.out.println("ID of valid claim: " + findCompleteClaim());
			
		}		
		catch(Exception e) {
			System.out.println(e);
	
	}		

	}
	
	static void markFabric(int x1, int x2, int y1, int y2) {		
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++ ) {
				fabric[i][j] += 1;
			}
		}		
	}
	
	static void calcOverlap() { 
		for (int x = 0; x < sideLength; x++) {
			for (int y = 0; y < sideLength; y++) {
				if (fabric[x][y] > 1) {
					overlap++;
				}
			}
		}
	}
	
	static int findCompleteClaim() {
		for (int i = 0; i < numClaims; i++) {
			boolean noOverLap = true;
			for (int x = claims[i][1]; x < claims[i][2]; x++) {
				for (int y = claims[i][3]; y < claims[i][4]; y++) {
					if (fabric[x][y] > 1) {
						noOverLap = false;
					}
				}
			}
			if (noOverLap) return claims[i][0];
		}
		return 0;
	}

}
