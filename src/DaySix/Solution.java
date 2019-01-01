/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaySix;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dcaa2
 */
public class Solution {
    
        static ArrayList<Integer> xcoord = new ArrayList<Integer>();
        static ArrayList<Integer> ycoord = new ArrayList<Integer>();
        static int minX;
        static int maxX;
        static int minY;
        static int maxY;
        final static int THRESHOLD = 10000;
        static HashMap<Integer, Integer> area = new HashMap<Integer, Integer>();
        
       
    
    public static void main(String[] args) {
		File file = new File("C:\\Users\\dcaa2\\OneDrive\\Documents\\AdventOfCode\\DaySixInput.txt");
		Pattern pattern = Pattern.compile("(\\d+), (\\d+)");
                
                try (Scanner sc = new Scanner(file)) {
                        while (sc.hasNextLine()) {
                            Matcher m;
                            m = pattern.matcher(sc.nextLine());
                            if (m.find()) {
                            xcoord.add(Integer.parseInt(m.group(1)));                            
                            ycoord.add(Integer.parseInt(m.group(2)));
                            }
                        }
                        findBoundaries();
                        for (int i = minX; i <= maxX; i++ ) {
                            for (int j = minY; j <= maxY; j++) {
                                int closeNode = findClosestNode(i,j);
                                if (closeNode != -1) {
                                    updateMap(closeNode);
                                }
                            }
                        }
                        Map.Entry<Integer, Integer> maxNode = findMaxArea();
                        System.out.println(String.format("minX: %s, maxX: %s, minY: %s, maxY: %s"
                        , minX, maxX, minY, maxY));
                        System.out.println(String.format("Node %s has the greatest area at "
                                + "%s point", maxNode.getKey(), maxNode.getValue()));   
                        
                        //Part Two
                        int safeArea = 0;
                        int limit = THRESHOLD / xcoord.size();
                        for (int i = minX - limit; i <= maxX + limit; i++) {
                            for (int j = minY - limit; j <= maxY + limit; j++) {
                                if (findSumOfDistances(i,j) < THRESHOLD) safeArea++;
                            }
                        }
                        System.out.println(String.format("The safe area for a threshold of %s is %s",
                                THRESHOLD, safeArea));
                    }			
				
		catch(Exception e) {
			System.out.println(e);	
		}
		

	}
    
    static void findBoundaries() {
        int[] xsorted = xcoord.stream().mapToInt(i -> i).toArray();
        int[] ysorted = ycoord.stream().mapToInt(i -> i).toArray();
        Arrays.sort(xsorted);
        Arrays.sort(ysorted);
        minX = xsorted[0];
        maxX = xsorted[xsorted.length - 1];
        minY = ysorted[0];
        maxY = ysorted[ysorted.length - 1];        
    }
    
    static int findTaxiDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 -x1) + Math.abs(y2 - y1);
    }
    
    static int findClosestNode(int x, int y) {
        int minDistance = Integer.MAX_VALUE;
        int closeNode = -1;
        boolean tie = false;
        for (int i = 0; i < xcoord.size(); i++) {
            int currDist = findTaxiDistance(x,y,xcoord.get(i), ycoord.get(i));
            //ties don't count
            if (currDist == minDistance) {
                tie = true;
            } 
            else if (currDist < minDistance) {
                minDistance = currDist;
                closeNode = i;
                tie = false;
            }        
        }
        if (tie) return -1;
        return closeNode;
    }
    static void updateMap(int closeNode) {
        if (area.containsKey(closeNode)) {
            area.put(closeNode, area.get(closeNode) + 1);
        }
        else {
            area.put(closeNode, 1);
        }
    }
    
    static boolean isBoundaryPoint(int index) {
//        boolean extremePoint = (xcoord.get(index) == minX || xcoord.get(index) == maxX
//                || ycoord.get(index) == minY || ycoord.get(index) == maxY);
        
        for (int i = minX; i <= maxX; i++) {
            if (findClosestNode(i, minY) == index || findClosestNode(i, maxY) == index){
                return true;
            }
        }
        for (int j = minY; j <= maxY; j++) {
            if (findClosestNode(minX, j) == index || findClosestNode(maxX, j) == index) {
                return true;
            }
        }
        return false;
    }
        
    
    static Map.Entry<Integer, Integer> findMaxArea() {
        int maxArea = 0;
        Map.Entry<Integer, Integer> max = null;
        for (Map.Entry<Integer, Integer> node : area.entrySet()) {
            if (node.getValue() > maxArea && !isBoundaryPoint(node.getKey())) {
                max = node;
                maxArea = node.getValue();
            }
        }
        return max;
    }
    
    static int findSumOfDistances(int x, int y) {
        int totalDistance = 0;        
        for (int i = 0; i < xcoord.size(); i++) {
            totalDistance += findTaxiDistance(x,y,xcoord.get(i), ycoord.get(i));               
        }
        return totalDistance;
    }
    
	 
}
