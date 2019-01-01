/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaySeven;

import java.util.ArrayList;

/**
 *
 * @author dcaa2
 */
public class Step {
    char label;
    ArrayList<Step> prereqs;
    
    public Step(char x) {
        label = x;
        prereqs = new ArrayList<Step>();
    }
    
    
}
