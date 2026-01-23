/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.data;



import java.util.ArrayList;
import model.entities.Clerk;

/**
 *
 * @author Lab07 Guiselle López
 */
public class ClerkData {
    ArrayList<Clerk> clerks = new ArrayList<>();
    
 public ArrayList<Clerk> getAllClerks() {
        
        clerks.add(new Clerk(1, "", 0, null, "3", "", "est", "123"));
        clerks.add(new Clerk(2, "", 0, null, "3", "", "abc", "456"));
        clerks.add(new Clerk(3, "", 0, null, "3", "", "efg", "789"));

        
        return clerks;
    }
    
}
