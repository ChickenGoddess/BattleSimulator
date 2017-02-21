/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import MainClass.AilmentType;
import MainClass.GameState;
import MainClass.Player;
import Rooms.WeaponType;
import java.util.Random;

/**
 *
 * @author User
 */
public class BattleEvent extends Event{
    
    private Player p1;
    private Player p2;
    private Random rand = new Random();
    private boolean powerAtk;
    private boolean scratch;
    private boolean crit;
    
    public BattleEvent(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public void rollInitiative(Player player){
        player.setFocus(false);
        int initiative = rand.nextInt(100);
        if(player.getAilments().contains(AilmentType.Pinned)){
            initiative = 0;
            int remove = rand.nextInt(100);
            if(remove < 40){
                player.getAilments().remove(AilmentType.Pinned);
                initiative = rand.nextInt(30);
                player.setFocus(true);
                System.out.print(player.getName() + " unpinned " + player.getObjPronoun() + "self! ");
            }
        }
        if(player.getAilments().contains(AilmentType.Burning)){
            int affect = rand.nextInt(100);
            if(affect < 30){
                initiative -= 10;
            }
        }
        if(player.getAilments().contains(AilmentType.CritWeakness)){
            player.removeAilment(AilmentType.CritWeakness);
        }
        if(player.getAilments().contains(AilmentType.LostLeg)){
            initiative -= 40;
        }
        if(player.getAilments().contains(AilmentType.LostLegs)){
            initiative -= 80;
        }
        if(player.getAilments().contains(AilmentType.Flatfooted)){
            initiative -= 50;
            player.addAilment(AilmentType.CritWeakness);
            player.removeAilment(AilmentType.Flatfooted);
            System.out.print(player.getName() + " was caught flatfooted! ");
            player.setFocus(true);
        }
        int mod = rand.nextInt(player.getAthletics() + 1/10);
        initiative += mod;
        mod = rand.nextInt(player.getDexterity()/5 + 1);
        initiative += mod;
        player.setInitiative(initiative);
    }
    
    public String execute(Player p1, Player p2){
        
        this.powerAtk = false;
        this.scratch = false;
        
        this.p1 = p1;
        this.p2 = p2;
        
        int chance = p1.getAtk();
        int evade = p2.getDef();
        String s = "";
        chance += p1.getWeapon().getAtk();
        if(p1.getWeapon().getType().equals(WeaponType.Fisticuffs)){
            chance += rand.nextInt(p1.getHandtohand()/5);
        } else if(p1.getWeapon().getType().equals(WeaponType.Axe) || 
                p1.getWeapon().getType().equals(WeaponType.Dagger) || 
                p1.getWeapon().getType().equals(WeaponType.Sword) || 
                p1.getWeapon().getType().equals(WeaponType.Spear)){
            chance += rand.nextInt(p1.getMelee()/5);
        } else{
            chance += rand.nextInt(p1.getRanged()/5);
        }
        if(p1.getWeapon().getType().equals(p1.getPreferredWeapon())){
            chance += 5;
            if(p1.getPreferredWeapon().equals(WeaponType.Fisticuffs)){
                chance += 5;
            }
        }
        evade += p2.getWeapon().getDef();
        if(p2.getWeapon().getType().equals(WeaponType.Fisticuffs)){
            evade += rand.nextInt(p2.getHandtohand()/15);
        } else if(p2.getWeapon().getType().equals(WeaponType.Axe) || 
                p2.getWeapon().getType().equals(WeaponType.Dagger) || 
                p2.getWeapon().getType().equals(WeaponType.Sword) || 
                p2.getWeapon().getType().equals(WeaponType.Spear)){
            evade += rand.nextInt(p2.getMelee()/10);
        } else{
            evade += rand.nextInt(p2.getRanged()/20);
        }
        if(p1.getWeapon().getType().equals(p1.getPreferredWeapon())){
            chance += 3;
        }
        int roll1 = rand.nextInt(20) + 1;
        chance += roll1;
        evade += rand.nextInt(20) + 1;
        crit = false;
        if(roll1 >= p1.getWeapon().getCrit()){
            crit = true;
        }
        
        if(chance > evade + 15 || roll1 == 20){
            this.powerAtk = true;
            s += this.attack();
        } else if(chance > evade - 4 && chance < evade + 5){
            this.scratch = true;
            s += this.attack();
        } else if(chance <= evade - 2){
            if(p1.isFocus() == true){
                s += p1.getSubPronoun() + " strikes with " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + " and misses! ";
            } else{
                s += p2.getName() + " strikes with " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + " and misses! ";
            }
        } else{
            s += this.attack();
        }
        
        //System.out.println(chance);
        //System.out.println(evade);
        
        return s;
    }
    
    public String attack(){
        
        String s = "";
        int bounds = this.p1.getWeapon().getDmgHigh() - this.p1.getWeapon().getDmgLow();
        int roll = rand.nextInt(bounds + 1);
        int damage = roll + this.p1.getWeapon().getDmgLow();
        
        if(p2.getWeapon().getType().equals(WeaponType.Fisticuffs)){
            damage += rand.nextInt(p1.getHandtohand()/10);
        } else if(p2.getWeapon().getType().equals(WeaponType.Axe) || 
                p2.getWeapon().getType().equals(WeaponType.Dagger) || 
                p2.getWeapon().getType().equals(WeaponType.Sword) || 
                p2.getWeapon().getType().equals(WeaponType.Spear)){
            damage += rand.nextInt(p1.getMelee()/5);
        } else{
            damage += rand.nextInt(p1.getRanged()/5);
        }
        if(p1.getWeapon().getType().equals(p1.getPreferredWeapon())){
            damage += 5;
            if(p1.getPreferredWeapon().equals(WeaponType.Fisticuffs)){
                damage += 5;
            }
        }
        if(this.scratch == true){
            damage = (damage * 2)/3;
            s += p1.getName() + " swung and grazed " + p2.getName() + " with his " + p1.getWeapon().getName();
        } else if(this.powerAtk == true){
            damage = (damage * 3)/2;
            s += p1.getName() + " deeply gutted " + p2.getName() + " with his " + p1.getWeapon().getName();
        } else{
            s += p1.getName() + " struck "  + p2.getName() + " with his " + p1.getWeapon().getName();
        }
        if(this.crit == true){
            damage = damage * p1.getWeapon().getCritMult();
            s += " doing critical damage and ";
        } 
        
        s += " dealing " + damage + " damage! ";
        
        p2.damage(damage);
        
        if(p2.getHealth() <= 0){
            s += p2.getName() + " died!";
            GameState.instance().kill(p2);
            //for(int i = 0; i < GameState.instance().getAlive().size(); i++){
                //System.out.println(GameState.instance().getAlive().get(i).getName());
            //}
        }
        
        return s;
    }
    
}
