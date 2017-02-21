/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import MainClass.Player;
import Rooms.Room;
import java.util.Random;

/**
 *
 * @author User
 */
public class MoveEvent extends Event{
    
    private Player player;
    private Room previous;
    private Room next;
    private Room current;
    private String direction;
    private Random rand = new Random();
    
    public MoveEvent(Player player){
        this.player = player;
    }
    
    public String execute(){
        String prevdir = "";
        this.current = player.getCurrentRoom();
        if(this.player.getPreviousRoom() != null){
            this.previous = player.getPreviousRoom();
            this.player.setBeforePrevRoom(previous);
        }
        
        if(this.player.getPreviousRoom() != null){
            for(int i = 0; i < player.getPreviousRoom().getExits().size(); i++){
                if(this.previous.getExits().get(i).getDestination() == this.current){
                    prevdir = this.previous.getExits().get(i).getDirection();
                }
            }
            if(this.current.getExittable().get(prevdir) != null){
                int chance = rand.nextInt(100);
                if(chance <= 40){
                    player.setCurrentRoom(current.getExittable().get(prevdir));
                    this.direction = player.getName() + " went " + prevdir;
                }
            }
            for(int i = 0; i < current.getExits().size(); i++){
                int change = rand.nextInt(100);
                int size = current.getExits().size();
                if(change < 100/size){
                    player.setCurrentRoom(current.getExits().get(i).getDestination());
                    this.direction = player.getName() + " went " + current.getExits().get(i).getDirection();
                    break;
                }
                if(i == current.getExits().size() - 1){
                    player.setCurrentRoom(current.getExits().get(i).getDestination());
                    this.direction = player.getName() + " went " + current.getExits().get(i).getDirection();
                }
            }
        }
        else{
            for(int i = 0; i < current.getExits().size(); i++){
                int change = rand.nextInt(100);
                int size = current.getExits().size();
                if(change < 100/size){
                    player.setCurrentRoom(current.getExits().get(i).getDestination());
                    this.direction = player.getName() + " went " + current.getExits().get(i).getDirection();
                    break;
                }
                if(i == current.getExits().size() - 1){
                    player.setCurrentRoom(current.getExits().get(i).getDestination());
                    this.direction = player.getName() + " went " + current.getExits().get(i).getDirection();
                }
            }
        }
        this.player.setPreviousRoom(current);
        
        return this.direction;
    } 
}
