/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import Rooms.DamageType;
import Rooms.Exit;
import Rooms.Item;
import Rooms.NoExitException;
import Rooms.NoItemException;
import Rooms.NoRoomException;
import Rooms.Room;
import Rooms.WeaponItem;
import Rooms.WeaponType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Dungeon {
    
    private String name;
    private Room start;
    private boolean initializingState;
    private String filename;
    private Hashtable<Integer, Room> rooms = new Hashtable<>();
    private Hashtable<String, Item> items = new Hashtable<>();
    private Hashtable<String, WeaponItem> weapons = new Hashtable();
    private List<WeaponType> types = new ArrayList<>();
    
    public Dungeon(String name, Room start){
        this.name = name;
        this.start = start;
    }
    
    public Dungeon(String filename, boolean initState) throws FileNotFoundException{
        this.initializingState = initState;
        Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
        this.initializeWeaponTypes();
        this.filename = filename;
        String line;
        
        this.name = scan.nextLine();
        line = scan.nextLine(); //Items:
        while(true){
            try{
                Item item = new Item(scan, this);
                this.addItem(item);
            }
            catch(NoItemException e){
                break;
            }
        }
        line = scan.nextLine(); //WeaponItems:
        while(true){
            try{
                WeaponItem weapon = new WeaponItem(scan, this);
                this.addItem(weapon);
                this.addWeapon(weapon);
            }
            catch(NoItemException e){
                break;
            }
        }
        WeaponItem fisticuffs = new WeaponItem("fisticuffs");
        fisticuffs.setItemID("fst");
        fisticuffs.setWeight(0);
        fisticuffs.setAtk(0);
        fisticuffs.setDef(0);
        fisticuffs.setDmgType(DamageType.Impact);
        fisticuffs.setType(WeaponType.Fisticuffs);
        fisticuffs.setDmgLow(4);
        fisticuffs.setDmgHigh(16);
        fisticuffs.setCrit(19);
        fisticuffs.setCritMult(2);
        this.addItem(fisticuffs);
        this.addWeapon(fisticuffs);
        line = scan.nextLine(); //Rooms:
        while(true){
            try{
                Room room = new Room(scan, this);
                this.addRoom(room);
                if(this.getStart() == null){
                    this.setStart(room);
                }
            }
            catch(NoRoomException e){
                break;
            }
        }
        line = scan.nextLine(); //Exits:
        while(true){
            try{
                Exit exit = new Exit(scan, this);
            }
            catch(NoExitException e){
                break;
            }
        }
        line = scan.nextLine(); //Players:
        while(true){
            try{
                Player player = new Player(scan, this);
                player.refactorCharacter();
                GameState.instance().spawn(player);
                player.setCurrentRoom(this.getStart());
            }
            catch(NoPlayerException e){
                break;
            }
        }
    }
    
    public void addRoom(Room room){
        this.rooms.put(room.getId(), room);
    }
    
    public void addItem(Item item){
        this.items.put(item.getItemID(), item);
    }
    
    public void removeItem(String itemID){
        this.items.remove(itemID);
    }
    
    public void addWeapon(WeaponItem weapon){
        this.weapons.put(weapon.getItemID(), weapon);
    }
    
    public void removeWeapon(String weaponID){
        this.weapons.remove(weaponID);
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
     * @return the start
     */
    public Room getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Room start) {
        this.start = start;
    }

    /**
     * @return the initializingState
     */
    public boolean isInitializingState() {
        return initializingState;
    }

    /**
     * @param initializingState the initializingState to set
     */
    public void setInitializingState(boolean initializingState) {
        this.initializingState = initializingState;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the rooms
     */
    public Hashtable<Integer, Room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(Hashtable<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the items
     */
    public Hashtable<String, Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Hashtable<String, Item> items) {
        this.items = items;
    }

    /**
     * @return the weapons
     */
    public Hashtable<String, WeaponItem> getWeapons() {
        return weapons;
    }

    /**
     * @param weapons the weapons to set
     */
    public void setWeapons(Hashtable<String, WeaponItem> weapons) {
        this.weapons = weapons;
    }

    /**
     * @return the types
     */
    public List<WeaponType> getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<WeaponType> types) {
        this.types = types;
    }
    
    public void initializeWeaponTypes(){
        this.types.add(WeaponType.Axe);
        this.types.add(WeaponType.Spear);
        this.types.add(WeaponType.Fisticuffs);
        this.types.add(WeaponType.Sword);
        this.types.add(WeaponType.Dagger);
    }
    
}
