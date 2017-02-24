/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import MainClass.AilmentType;
import MainClass.GameState;
import MainClass.Player;
import Rooms.DamageType;
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
    private boolean rarm;
    private boolean larm;
    private boolean rleg;
    private boolean lleg;
    private boolean head;
    private boolean body;
    private static int turn = 0;
    
    public BattleEvent(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public void rollInitiative(Player player){
        if(player.getAilments().contains(AilmentType.Bleeding)){
            int dmg = rand.nextInt(25 - (player.getHardiness()/4) + 1);
            player.damage(dmg);
            System.out.println("BLEED!!!");
        }
        int initiative = rand.nextInt(100);
        if(player.getAilments().contains(AilmentType.Pinned)){
            initiative = 0;
            int remove = rand.nextInt(100);
            if(remove < 40){
                player.getAilments().remove(AilmentType.Pinned);
                initiative = rand.nextInt(30);
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
        }
        int mod = rand.nextInt(player.getAthletics() + 1/10);
        initiative += mod;
        mod = rand.nextInt(player.getDexterity()/5 + 1);
        initiative += mod;
        initiative -= player.getAttackNum() * 2;
        player.setInitiative(initiative);
    }
    
    public String execute(Player p1, Player p2){
        
        if(!p1.isFocus()){
            p1.setFocus(true);
            p1.setAttackNum(0);
        }
        
        EventText et = new EventText();
        
        this.powerAtk = false;
        this.scratch = false;
        this.rarm = false;
        this.rleg = false;
        this.larm = false;
        this.lleg = false;
        this.head = false;
        this.body = false;
        
        this.p1 = p1;
        this.p2 = p2;
        
        //System.out.println(this.turn);
        if(this.turn == 0){
            System.out.print(et.firstInitiativeText(p1, p2));
        } else{
            System.out.print(et.initiativeText(p1, p2));
        }
        
        p1.setAttackNum(p1.getAttackNum() + 1);
        
        int limb = rand.nextInt(100);
        if(p1.getAilments().contains(AilmentType.Pinned)){
            if(limb < 30){
                this.rleg = true;
            } else if(limb < 60){
                this.lleg = true;
            } else if(limb < 90){
                this.body = true;
            } else if(limb < 95){
                this.rarm = true;
            } else{
                this.larm = true;
            }
        } else if(p2.getAilments().contains(AilmentType.Pinned)){
            if(limb < 20 && p1.getGood() < 40){
                this.rarm = true;
            } else if(limb < 40 && p1.getGood() < 40){
                this.rleg = true;
            } else if(limb < 60 && p1.getGood() < 40){
                this.larm = true;
            } else if(limb < 80 && p1.getGood() < 40){
                this.lleg = true;
            } else{
                this.head = true;
            }
        } else{
            if(limb < (30 - (p1.getIntelligence()/10))){
                this.head = true;
            }
            else if(limb < (80 - (p1.getIntelligence()/5))){
                this.body = true;
            }
            else if(limb < (85 - (p1.getIntelligence()/6))){
                this.lleg = true;
            }
            else if(limb < (90 - (p1.getIntelligence()/10))){
                this.rleg = true;
            }
            else if(limb < (95 - (p1.getIntelligence()/20))){
                this.larm = true;
            }
            else{
                this.rarm = true;
            }
            if(p2.getCurHeadHealth() < p2.getHeadHealth()){
                int strike = rand.nextInt(100);
                int check = rand.nextInt(p1.getIntelligence()/5 + 1);
                if(strike < check){
                    this.head = true;
                }
            }
            if(p2.getCurLeftArmHealth() < p2.getLeftArmHealth() && p2.getCurLeftArmHealth() > 0){
                int strike = rand.nextInt(100);
                int check = rand.nextInt(p1.getIntelligence()/5 + 1);
                if(strike < check){
                    this.larm = true;
                }
            }
            if(p2.getCurRightArmHealth() < p2.getRightArmHealth() && p2.getCurRightArmHealth() > 0){
                int strike = rand.nextInt(100);
                int check = rand.nextInt(p1.getIntelligence()/5 + 1);
                if(strike < check){
                    this.rarm = true;
                }
            }
            if(p2.getCurLeftLegHealth() < p2.getLeftLegHealth() && p2.getCurLeftLegHealth() > 0){
                int strike = rand.nextInt(100);
                int check = rand.nextInt(p1.getIntelligence()/5 + 1);
                if(strike < check){
                    this.lleg = true;
                }
            }
            if(p2.getCurRightLegHealth() < p2.getRightLegHealth() && p2.getCurRightLegHealth() > 0){
                int strike = rand.nextInt(100);
                int check = rand.nextInt(p1.getIntelligence()/5 + 1);
                if(strike < check){
                    this.rleg = true;
                }
            }
        }
        
        int chance = p1.getAtk();
        int evade = p2.getDef();
        String s = "";
        chance += p1.getWeapon().getAtk();
        if(p1.getWeapon().getType().equals(WeaponType.Fisticuffs)){
            chance += rand.nextInt(p1.getHandtohand()/5 + 1);
        } else if(p1.getWeapon().getType().equals(WeaponType.Axe) || 
                p1.getWeapon().getType().equals(WeaponType.Dagger) || 
                p1.getWeapon().getType().equals(WeaponType.Sword) || 
                p1.getWeapon().getType().equals(WeaponType.Spear)){
            chance += rand.nextInt(p1.getMelee()/5 + 1);
        } else{
            chance += rand.nextInt(p1.getRanged()/5 + 1);
        }
        if(p1.getWeapon().getType().equals(p1.getPreferredWeapon())){
            chance += 5;
            if(p1.getPreferredWeapon().equals(WeaponType.Fisticuffs)){
                chance += 5;
            }
        }
        evade += p2.getWeapon().getDef();
        if(p2.getWeapon().getType().equals(WeaponType.Fisticuffs)){
            evade += rand.nextInt(p2.getHandtohand()/15 + 1);
        } else if(p2.getWeapon().getType().equals(WeaponType.Axe) || 
                p2.getWeapon().getType().equals(WeaponType.Dagger) || 
                p2.getWeapon().getType().equals(WeaponType.Sword) || 
                p2.getWeapon().getType().equals(WeaponType.Spear)){
            evade += rand.nextInt(p2.getMelee()/10 + 1);
        } else{
            evade += rand.nextInt(p2.getRanged()/20 + 1);
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
        
        if(this.head){
            evade += rand.nextInt((p2.getIntelligence()/20) + (p2.getDexterity()/10) + 2);
        } else if(this.larm || this.rarm){
            evade += rand.nextInt((p2.getIntelligence()/20) + 1);
        }
        
        if(p1.getAilments().contains(AilmentType.Concussed)){
            chance -= 25 - (p1.getHardiness()/4);
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
                s += p1.getName() + " strikes with " + p1.getPossessivePronoun() + " " + p1.getWeapon().getName() + " and misses! ";
            }
        } else{
            s += this.attack();
        }
        this.setTurn(this.getTurn()+1);
        
        //System.out.println(chance);
        //System.out.println(evade);
        
        return s;
    }
    
    public String attack(){
        
        String s = "";
        if(this.rarm && p2.getAilments().contains(AilmentType.LostArms)){
            return this.execute(p1, p2);
        } else if(this.larm && p2.getAilments().contains(AilmentType.LostArm)){
            return this.execute(p1, p2); 
        } else if(this.rleg && p2.getAilments().contains(AilmentType.LostLegs)){
            return this.execute(p1,p2);
        } else if(this.lleg && p2.getAilments().contains(AilmentType.LostLeg)){
            return this.execute(p1,p2);
        }
        int bounds = this.p1.getWeapon().getDmgHigh() - this.p1.getWeapon().getDmgLow();
        int roll = rand.nextInt(bounds + 1);
        int damage = roll + this.p1.getWeapon().getDmgLow();
        
        if(p2.getWeapon().getType().equals(WeaponType.Fisticuffs)){
            damage += rand.nextInt(p1.getHandtohand()/10 + 1);
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
                damage += 7;
            }
        }
        if(this.scratch == true){
            damage = (damage/2);
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
        
        
        
        if(this.body){
            System.out.println("Body");
            p2.damage(damage);
        } else if(this.head){
            System.out.println("Head");
            p2.damage(damage*2);
            p2.setCurHeadHealth(p2.getCurHeadHealth() - damage);
        } else if(this.rarm){
            System.out.println("Right Arm");
            p2.damage((damage*2)/3);
            p2.setCurRightArmHealth(p2.getCurRightArmHealth() - damage);
        } else if(this.larm){
            System.out.println("Left Arm");
            p2.damage((damage*2)/3);
            p2.setCurLeftArmHealth(p2.getCurLeftArmHealth() - damage);
        } else if(this.rleg){
            System.out.println("Right Leg");
            p2.damage(damage/2);
            p2.setCurRightLegHealth(p2.getCurRightLegHealth() - damage);
        } else if(this.lleg){
            System.out.println("Left Leg");
            p2.damage(damage/2);
            p2.setCurLeftLegHealth(p2.getCurLeftLegHealth() - damage);
        } else{
            System.out.println("Something went very wrong");
        }
        
        s += " dealing " + damage + " damage! ";
        if(!(p2.getAilments().contains(AilmentType.Concussed))){
            if(p2.getCurHeadHealth() <= 0){
                System.out.println("The head was concussed!");
                p2.addAilment(AilmentType.Concussed);
                p2.setIntelligence(p2.getIntelligence()/2);
                p2.setDexterity(p2.getDexterity()/2);
            }
        }
        if(!(p2.getAilments().contains(AilmentType.LostArm))){
            if(p2.getCurLeftArmHealth() <= 0){
                System.out.println("The left arm was cut off!");
                p2.addAilment(AilmentType.LostArm);
                p2.addAilment(AilmentType.Bleeding);
            }
        }
        if(!(p2.getAilments().contains(AilmentType.LostArms))){
            if(p2.getCurRightArmHealth() <= 0){
                System.out.println("The right arm was cut off!");
                p2.addAilment(AilmentType.LostArms);
                p2.addAilment(AilmentType.Bleeding);
            }
         }
        if(!(p2.getAilments().contains(AilmentType.LostLeg))){
            if(p2.getCurLeftLegHealth() <= 0){
                System.out.println("The left leg was cut off!");
                p2.addAilment(AilmentType.LostLeg);
                p2.addAilment(AilmentType.Bleeding);
            }
        }
        if(!(p2.getAilments().contains(AilmentType.LostLegs))){
            if(p2.getCurRightLegHealth() <= 0){
                System.out.println("The right leg was cut off!");
                p2.addAilment(AilmentType.LostLegs);
                p2.addAilment(AilmentType.Bleeding);
            }
        }
         
        if(p1.getWeapon().getDmgType().equals(DamageType.Puncture)){
            if(!p2.getAilments().contains(AilmentType.Pinned)){
                int status = rand.nextInt(50);
                int intmodifier = rand.nextInt(p1.getIntelligence()/2 + 1);
                int stdef = rand.nextInt(50);
                int hardmod = rand.nextInt(p2.getHardiness() + 1);
                if((status + intmodifier) > (stdef + hardmod)){
                    p2.addAilment(AilmentType.Pinned);
                    System.out.println("Pinned!");
                }
            }
        }
        if(p1.getWeapon().getDmgType().equals(DamageType.Slash)){
            if(!p2.getAilments().contains(AilmentType.Bleeding)){
                int status = rand.nextInt(50);
                int intmodifier = rand.nextInt(p1.getIntelligence()/2 + 1);
                int stdef = rand.nextInt(50);
                int hardmod = rand.nextInt(p2.getHardiness()/2 + 1);
                if((status + intmodifier) > (stdef + hardmod)){
                    p2.addAilment(AilmentType.Bleeding);
                    System.out.println("Bleeding!");
                }
            }
            else if(p2.getAilments().contains(AilmentType.Bleeding)){
                int status = rand.nextInt(50);
                int intmodifier = rand.nextInt(p1.getIntelligence()/2 + 1);
                int stdef = rand.nextInt(50);
                int hardmod = rand.nextInt(p2.getHardiness()/2 + 1);
                if((status + intmodifier) > (stdef + hardmod)){
                    p2.damage(intmodifier);
                    System.out.println("More bleeding!");
                }
            }
        }
         
        if(p2.getHealth() <= 0){
            s += p2.getName() + " died!";
            GameState.instance().kill(p2);
            this.setTurn(0);
            //for(int i = 0; i < GameState.instance().getAlive().size(); i++){
                //System.out.println(GameState.instance().getAlive().get(i).getName());
            //}
        }
        
        return s;
    }
    
    public int getTurn(){
        return turn;
    }
    
    public void setTurn(int turn){
        this.turn = turn;
    }
    
}
