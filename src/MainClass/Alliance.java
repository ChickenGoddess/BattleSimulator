/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Alliance {
    
    private List<Player> players = new ArrayList<>();
    
    public Alliance(){
        
    }
    
    public void recruit(Player player){
        this.players.add(player);
    }
    
}
