/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaySeven;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author dcaa2
 */
public class Solution {
    static Map<Character, ArrayList<Character>> remaining = new HashMap<>();
    static ArrayList<Character> available = new ArrayList<>();
    static ArrayList<Character> complete = new ArrayList<>();
    
    
    public static void main(String[] args) {
        File file = new File("C:\\Users\\dcaa2\\OneDrive\\Documents\\AdventOfCode\\DaySevenInput.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Character from = line.charAt(5);
                Character to = line.charAt(36);
                if (!remaining.containsKey(from)) {
                    remaining.put(from, new ArrayList<>());
                }
                if (!remaining.containsKey(to)) {
                    remaining.put(to, new ArrayList<>());                    
                }
                remaining.get(to).add(from);               
            }
            assembleSleigh();
            System.out.println(complete);
        }
        catch(Exception e) {
            System.out.println(e);
            
        }       
        
        
    }
    
    static void assembleSleigh() {

//      find starting point by making any steps without prereqs available
        Iterator it = remaining.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, ArrayList<Character>> item = 
                (Map.Entry<Character, ArrayList<Character>>)it.next();
            if (item.getValue().isEmpty()) {
                available.add(item.getKey());
                it.remove();
            }
         }
        
        
        //repeat assembly process until no available steps are left
        while (!available.isEmpty()){
            //sort the list of available steps to select the next one alphabeticallhy
            available.sort(null);
            Character next = available.remove(0);
            //remove this step from the prereq lists of remaining steps
            if (!remaining.isEmpty()) {
                Iterator it2 = remaining.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<Character, ArrayList<Character>> item = 
                            (Map.Entry<Character, ArrayList<Character>>)it2.next();
                    item.getValue().remove(next);
                    //if the prereq list is not empty, remove the key and add it to the available steps
                    if (item.getValue().isEmpty()) {
                        available.add(item.getKey());
                        it2.remove();
                    }
                }           
                
            }
            //add the current step to the completed list
            complete.add(next);
        }
        
    }
    
}
