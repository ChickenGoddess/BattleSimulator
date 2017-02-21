/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import Rooms.WeaponType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class GameState {
    
    private static GameState gameState = new GameState();
    private Dungeon dungeon;
    private static List<Player> alive = new ArrayList<>();
    private static List<Player> dead = new ArrayList<>();
    
    public static GameState instance() {
        return gameState;
    }

    public static void initialize(Dungeon dungeon) {
        gameState.dungeon = dungeon;
    }
    
    public Dungeon getDungeon(){
        return this.dungeon;
    }
    
    public List getPlayers(){
        return this.getAlive();
    }
    
    public List getDead(){
        return this.dead;
    }
    
    public void kill(Player player){
        this.getAlive().remove(player);
        this.dead.add(player);
    }
    
    public void spawn(Player player){
        this.getAlive().add(player);
        if(this.dead.contains(player)){
            this.dead.remove(player);
        }
    }    

    /**
     * @return the alive
     */
    public static List<Player> getAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(List<Player> alive) {
        this.alive = alive;
    }

    /**
     * @param dead the dead to set
     */
    public void setDead(List<Player> dead) {
        this.dead = dead;
    }
    
}
