/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import Events.BattleEvent;
import Events.MoveEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class NewClass {
    
    public static void main(String[] args){
        GameState gamestate = new GameState();
        Scanner scan = new Scanner(System.in);
        String input = "";
        Dungeon dungeon = null;
        try{
            dungeon = new Dungeon("Test.txt", true);
            dungeon.setInitializingState(true);
            GameState.initialize(dungeon);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(-1);
        }
        Player player1 = new Player("Jon");
        Player player2 = new Player("Adam");
        player1.randomize();
        player2.randomize();
        player1.refactorCharacter();
        player2.refactorCharacter();
        gamestate.spawn(player1);
        gamestate.spawn(player2);
        player1.setCurrentRoom(dungeon.getStart());
        player2.setCurrentRoom(dungeon.getStart());
        player1.addAilment(AilmentType.Pinned);
        
        while(true){
            System.out.println("Enter input: ");
            input = scan.nextLine();
            if(input.equals("q")){
                break;
            } else if(input.equals("c")){
                player1.showStats();
                System.out.println("");
                player2.showStats();
            }
            else{
                BattleEvent be = new BattleEvent(player1, player2);
                be.rollInitiative(player1);
                be.rollInitiative(player2);
                //System.out.println(player1.getInitiative());
                //System.out.println(player2.getInitiative());
                if(player1.getInitiative() > player2.getInitiative()){
                    System.out.println(be.execute(player1, player2));
                    player2.setAttackNum(0);
                    player1.setAttackNum(player1.getAttackNum() + 1);
                } else if(player1.getInitiative() < player2.getInitiative()){
                    System.out.println(be.execute(player2, player1));
                    player1.setAttackNum(0);
                    player2.setAttackNum(player2.getAttackNum() + 1);
                } else{
                    System.out.println(player1.getName() + " and " + player2.getName() + " clash blades!");
                }
            }
            if(GameState.instance().getAlive().size() == 1){
                System.out.println(GameState.instance().getAlive().get(0).getName() + " wins!");
                break;
            }
        }
        
    }
    
}
