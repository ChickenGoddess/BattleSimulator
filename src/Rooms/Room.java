/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import Events.Event;
import MainClass.Dungeon;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Room {
    
    private List<Exit> exits;
    private RoomID tile;
    private List<Event> events;
    private int id;
    private List<Item> items;
    private List<Item> permItems;
    private Hashtable<String, Room> exittable = new Hashtable<>();
    private Dungeon dungeon;
    
    public Room(int id, RoomID tile){
        this.id = id;
        this.tile = tile;
        exits = new ArrayList<>();
        events = new ArrayList<>();
        items = new ArrayList<>();
        permItems = new ArrayList<>();
    }
    
    public Room(Scanner scan, Dungeon dungeon) throws NoRoomException{
        exits = new ArrayList<>();
        events = new ArrayList<>();
        items = new ArrayList<>();
        permItems = new ArrayList<>();
        this.dungeon = dungeon;
        String line = scan.nextLine();
        if(line.equals("===")){
            throw new NoRoomException();
        }
        this.id = Integer.parseInt(line);
        this.tile = tile.valueOf(scan.nextLine());
        line = scan.nextLine();
        int x = 0;
        for(int i = 0; i < line.length(); i++){
            String s = line.substring(i, i+1);
            if(s.equals(",")){
                x++;
            }
        }
        for(int i = 0; i <= x; i++){
            String s = "";
            if(x > 0 && i < x){
                s = line.substring(0, line.indexOf(","));
                line = line.replaceFirst(s + ",", "");
                this.addPermItems(dungeon.getItems().get(s));
            }
            else{
                s = line;
                this.addPermItems(dungeon.getItems().get(s));
            }
        }
        line = scan.nextLine();
    }
    
    public void addExit(Exit exit){
        getExits().add(exit);
    }
    
    public void addEvent(Event event){
        getEvents().add(event);
    }
    
    public void addPermItems(Item item){
        this.permItems.add(item);
    }

    /**
     * @return the exits
     */
    public List<Exit> getExits() {
        return exits;
    }

    /**
     * @param exits the exits to set
     */
    public void setExits(List<Exit> exits) {
        this.exits = exits;
    }

    /**
     * @return the tile
     */
    public RoomID getTile() {
        return tile;
    }

    /**
     * @param tile the tile to set
     */
    public void setTile(RoomID tile) {
        this.tile = tile;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @return the permItems
     */
    public List<Item> getPermItems() {
        return permItems;
    }

    /**
     * @param permItems the permItems to set
     */
    public void setPermItems(List<Item> permItems) {
        this.permItems = permItems;
    }

    /**
     * @return the exittable
     */
    public Hashtable<String, Room> getExittable() {
        return exittable;
    }

    /**
     * @param exittable the exittable to set
     */
    public void setExittable(Hashtable<String, Room> exittable) {
        this.exittable = exittable;
    }

    /**
     * @return the dungeon
     */
    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * @param dungeon the dungeon to set
     */
    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
    
}
