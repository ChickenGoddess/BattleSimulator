/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import MainClass.Dungeon;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class WeaponItem extends Item{
    
    private WeaponType type;
    private DamageType dmgType;
    private int def;
    private int atk;
    private int dmgLow;
    private int dmgHigh;
    private int crit;
    private int critMult;
    
    public WeaponItem(String name){
        super(name);
    }
    
    public WeaponItem(Scanner scan, Dungeon dungeon) throws NoItemException {
        super(scan, dungeon);
        String line = scan.nextLine();
        this.type = type.valueOf(line);
        this.dmgType = dmgType.valueOf(scan.nextLine());
        
        line = scan.nextLine(); //Def:
        this.def = Integer.parseInt(scan.nextLine());
        line = scan.nextLine(); //Atk:
        this.atk = Integer.parseInt(scan.nextLine());
        line = scan.nextLine(); //Dmg:
        line = scan.nextLine(); //Low:
        this.dmgLow = Integer.parseInt(scan.nextLine());
        line = scan.nextLine(); //High:
        this.dmgHigh = Integer.parseInt(scan.nextLine());
        line = scan.nextLine(); //Crit:
        this.crit = Integer.parseInt(scan.nextLine());
        line = scan.nextLine(); //CritMult:
        this.critMult = Integer.parseInt(scan.nextLine());
        line = scan.nextLine(); //---
    }

    /**
     * @return the type
     */
    public WeaponType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(WeaponType type) {
        this.type = type;
    }

    /**
     * @return the dmgType
     */
    public DamageType getDmgType() {
        return dmgType;
    }

    /**
     * @param dmgType the dmgType to set
     */
    public void setDmgType(DamageType dmgType) {
        this.dmgType = dmgType;
    }

    /**
     * @return the def
     */
    public int getDef() {
        return def;
    }

    /**
     * @param def the def to set
     */
    public void setDef(int def) {
        this.def = def;
    }

    /**
     * @return the atk
     */
    public int getAtk() {
        return atk;
    }

    /**
     * @param atk the atk to set
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * @return the dmgLow
     */
    public int getDmgLow() {
        return dmgLow;
    }

    /**
     * @param dmgLow the dmgLow to set
     */
    public void setDmgLow(int dmgLow) {
        this.dmgLow = dmgLow;
    }

    /**
     * @return the dmgHigh
     */
    public int getDmgHigh() {
        return dmgHigh;
    }

    /**
     * @param dmgHigh the dmgHigh to set
     */
    public void setDmgHigh(int dmgHigh) {
        this.dmgHigh = dmgHigh;
    }

    /**
     * @return the crit
     */
    public int getCrit() {
        return crit;
    }

    /**
     * @param crit the crit to set
     */
    public void setCrit(int crit) {
        this.crit = crit;
    }

    /**
     * @return the critMult
     */
    public int getCritMult() {
        return critMult;
    }

    /**
     * @param critMult the critMult to set
     */
    public void setCritMult(int critMult) {
        this.critMult = critMult;
    }
    
}
