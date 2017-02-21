/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import MainClass.Dungeon;
import MainClass.GameState;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Exit {
    
    private Room destination;
    private Room origin;
    private String direction;
    private boolean isLocked;
    
    public Exit(Room origin, Room destinatin, String direction){
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        origin.addExit(this);
    }
    
    public Exit(Scanner scan, Dungeon dungeon) throws NoExitException{
        String line = scan.nextLine();
        if(line.equals("===")){
            throw new NoExitException();
        }
        this.origin = dungeon.getRooms().get(Integer.parseInt(line));
        this.direction = scan.nextLine();
        this.destination = dungeon.getRooms().get(Integer.parseInt(scan.nextLine()));
        
        origin.addExit(this);
        
        line = scan.nextLine();
        
    }

    /**
     * @return the destination
     */
    public Room getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(Room destination) {
        this.destination = destination;
    }

    /**
     * @return the origin
     */
    public Room getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(Room origin) {
        this.origin = origin;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the isLocked
     */
    public boolean isIsLocked() {
        return isLocked;
    }

    /**
     * @param isLocked the isLocked to set
     */
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
    
}
