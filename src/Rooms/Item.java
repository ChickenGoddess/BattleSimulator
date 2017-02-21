/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import MainClass.Dungeon;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Item {
    
    protected String name;
    protected String itemID;
    protected int weight;
    
    public Item(String name){
        this.name = name;
    }
    
    public Item(Scanner scan, Dungeon dungeon) throws NoItemException{
        String line = scan.nextLine();
        if(line.equals("===")){
            throw new NoItemException();
        }
        this.name = line;
        this.itemID = scan.nextLine();
        this.weight = Integer.parseInt(scan.nextLine());
        line = scan.nextLine();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}
