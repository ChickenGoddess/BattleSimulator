/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import MainClass.Player;
import java.util.Random;

/**
 *
 * @author User
 */
public class EventText {
    
    Random rand = new Random();
    private int p1;
    private int p2;
    
    public EventText(){
        
    }
    
    public String normalAttackText(Player p1, Player p2){
        
        String s = "";
        
        int choose = rand.nextInt(100);
        
        if(p1.isFocus()){
            
        } else{
            
        }
        
        return s;
    }
    
    public String missedAttackText(Player p1, Player p2){
        String s = "";
        return s;
    }
    
    public String firstInitiativeText(Player p1, Player p2){
        String s = "";
        
        int choose = rand.nextInt(100);
        s += p1.getName() + " ";
        String adj = "";
        if(p1.getDexterity() >= 70){
            adj = "deftly";
        } else if(p1.getStrength() >= 70){
            adj = "monstrously";
        } else if(p1.getAthletics()>= 70){
            adj = "quickly";
        } else if(p1.getDexterity() <= 30){
            adj = "clumsily";
        } else if(p1.getIntelligence() <= 30){
            adj = "brashly";
        } else{
            adj = "angrily";
        }
        if(choose <= 20){
            s += "attacks " + adj + " with " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + ", ";
        } else if(choose <= 40){
            s += adj + " strikes " + p2.getName() + " with " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + ", ";
        } else if(choose <= 60){
            s += "attacks " + p2.getName() + " with " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + ", ";
        } else if(choose <= 80){
            s += "charges forwards " + adj + ", brandishing " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + " and strikes, ";
        } else{
            s += "jumps into battle " + adj + ", thrusting " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + " forwards, ";
        }
        
        return s;
    }
    
    public String initiativeText(Player p1, Player p2){
        
        String s = "";
        
        int choose = rand.nextInt(100);
        
        if(p1.getAttackNum() == 0){
            if(choose < 20){
                s += p1.getName() + " counterattacks with his " + p1.getWeapon().getName();
            } else if(choose < 40){
                s += p1.getName() + " returns the onslaught with " + p1.getPossessivePronoun() + " own strike";
            } else if(choose < 60){
                s += p1.getName() + " strikes back quickly";
            } else if(choose < 80){
                s += p1.getName() + " breaks away from " + p2.getName() + " and lunges forward, " + p1.getWeapon().getName() + " ready";
            } else{
                s += p1.getName() + " moves aside just as " + p2.getName() + " stikes again, narrowly avoiding a hit, and launches his own strike";
            }
        } else if(p1.getAttackNum() == 1){
            if(choose < 20){
                s += p1.getName() + " strikes again with vigor";
            } else if(choose < 40){
                s += p1.getName() + " follows up with another swing of " + p1.getPossessivePronoun() + p1.getWeapon().getName();
            } else if(choose < 60){
                s += "and immediately attacks again";
            } else if(choose < 80){
                s += p1.getName() + " attacks again";
            } else{
                s += p1.getName() + " strikes a second time";
            }
        } else if(p1.getAttackNum() == 2){
            if(choose < 20){
                s += p1.getName() + " quickly goes in for a third attack";
            } else if(choose < 40){
                s += p1.getName() + " strikes a third time";
            } else if(choose < 60){
                s += p1.getName() + " continues " + p1.getGender() + " assault";
            } else if(choose < 80){
                s += p1.getName() + " dashes in a third time";
            } else{
                s += p1.getName() + " strikes a third time!";
            }
        } else if(p1.getAttackNum() == 3){
            if(choose < 20){
                s += p1.getName() + " strikes again with vigor!";
            } else if(choose < 40){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 60){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 80){
                s += p1.getName() + " strikes a third time!";
            } else{
                s += p1.getName() + " strikes a third time!";
            }
        } else if(p1.getAttackNum() == 4){
            if(choose < 20){
                s += p1.getName() + " strikes again with vigor!";
            } else if(choose < 40){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 60){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 80){
                s += p1.getName() + " strikes a third time!";
            } else{
                s += p1.getName() + " strikes a third time!";
            }
        } else if(p1.getAttackNum() == 5){
            if(choose < 20){
                s += p1.getName() + " strikes again with vigor!";
            } else if(choose < 40){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 60){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 80){
                s += p1.getName() + " strikes a third time!";
            } else{
                s += p1.getName() + " strikes a third time!";
            }
        } else if(p1.getAttackNum() >= 6){
            if(choose < 20){
                s += p1.getName() + " strikes again with vigor!";
            } else if(choose < 40){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 60){
                s += p1.getName() + " strikes a third time!";
            } else if(choose < 80){
                s += p1.getName() + " strikes a third time!";
            } else{
                s += p1.getName() + " strikes a third time!";
            }
        }
        
        return s;
    }
    
}
