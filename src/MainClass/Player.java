/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

import Rooms.Item;
import Rooms.Room;
import Rooms.WeaponItem;
import Rooms.WeaponType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Player {
    
    private GameState gamestate;
    private Random rand = new Random();
    
    //Player info
    private String name;
    private String gender;
    private int fame;
    private boolean randx = true;
    
    //Health
    private int health;
    private int maxHealth; //athletics + medicine + hardiness
    private int headHealth;
    private int rightArmHealth;
    private int leftArmHealth;
    private int rightLegHealth;
    private int leftLegHealth;
    private int curHeadHealth;
    private int curRightArmHealth;
    private int curLeftArmHealth;
    private int curRightLegHealth;
    private int curLeftLegHealth;
    
    //Affected Stats
    private String subPronoun;
    private String objPronoun;
    private String possessivePronoun;
    private int carryingCapacity; //25 - 75
    private int weight;
    private int atk;
    private int def; //base 5 dex + 0 - 10
    private int headDef;
    private int limbDef;
    private int hunger;
    private int maxHunger; // 50-75
    
    //Alignment (0 - 10)
    private int lawful;
    private int good;
    
    //Stats (can be 1 to 100)
    private int intelligence; //Planning, striking places (battle)
    private int strength; //Carrying Capacity (battle)
    private int stealth; //First Strike + avoiding battle
    private int handtohand; //Hand-to-hand fighting (battle)
    private int melee; //melee fighting (battle)
    private int ranged; //ranged fighting (battle)
    private int survival; //Scavenging for materials and more food per item (battle)
    private int medicine; //Healing ability and health (battle)
    private int athletics; //Movement ability and health (battle)
    private int hardiness; //Debuff resistance and health (battle)
    private int swimming; //Crossing bodies of water
    private int climbing; //Movement on hills, cliffs, and mountains
    private int courage; //Chance to confront dangers
    private int crafting; //Combining materials
    private int charisma; //Ability to convince and lead
    private int trapping; //Ability to create traps
    private int dexterity; //Chance to dodge and strike (battle)
    
    //Player state
    private WeaponItem weapon;
    private WeaponType preferredWeapon;
    private List<Item> inventory = new ArrayList<>();
    private Room beforePrevRoom;
    private Room previousRoom;
    private Room currentRoom;
    private List<AilmentType> ailments = new ArrayList<>();
    private boolean focus = false;
    private int attackNum;
    private int headhit;
    private int armhit;
    private int leghit;
    private int bodyhit;
    private int initiative;
    
    public Player(String name){
        this.name = name;
    }
    
    public Player(String name, String gender){
        this.gender = gender;
        this.name = name;
        this.randx = false;
    }
    
    public Player(Scanner scan, Dungeon dungeon) throws NoPlayerException{
        String line = scan.nextLine();
        if(line.equals("===")){
            throw new NoPlayerException();
        }
    }
    
    public void setDefaultWeapon(){
        this.setWeapon(GameState.instance().getDungeon().getWeapons().get("fst"));
    }
    
    public void refactorCharacter(){
        this.maxHealth = this.medicine + this.athletics + this.hardiness;
        this.setHealth(this.getMaxHealth());
        this.setHeadHealth((this.maxHealth/10) + (this.hardiness/5));
        this.setLeftArmHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setLeftLegHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setRightArmHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setRightLegHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setCurHeadHealth((this.maxHealth/10) + (this.hardiness/5));
        this.setCurLeftArmHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setCurLeftLegHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setCurRightArmHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setCurRightLegHealth((this.maxHealth/5) + (this.hardiness/3));
        this.setCarryingCapacity(25 + (this.strength/2));
        this.setMaxHunger(50 + (this.survival + this.hardiness)/5);
        this.setDef(this.dexterity / 10);
        this.setAtk((this.getStrength()/25) + (this.getDexterity()/20));
        this.setHeadDef(this.getDef() * 2);
        this.setLimbDef((this.getDef() * 3)/2);
        if(this.getGender().equals("male")){
            this.setObjPronoun("him");
            this.setSubPronoun("he");
            this.setPossessivePronoun("his");
        } else{
            this.setObjPronoun("her");
            this.setSubPronoun("she");
            this.setPossessivePronoun("her");
        }
        this.setDefaultWeapon();
    }
    
    public void randomize(){
        int q = rand.nextInt(5);
        this.setPreferredWeapon(GameState.instance().getDungeon().getTypes().get(q));
        int gen = rand.nextInt(2);
        if(this.randx = true){
            if(gen == 0){
                this.setGender("male");
            } else{
                this.setGender("female");
            }
        }
        this.setStrength(rand.nextInt(100) + 1);
        this.setIntelligence(rand.nextInt(100) + 1);
        this.setAthletics(rand.nextInt(100) + 1);
        this.setCharisma(rand.nextInt(100) + 1);
        this.setClimbing(rand.nextInt(100) + 1);
        this.setCourage(rand.nextInt(100) + 1);
        this.setCrafting(rand.nextInt(100) + 1);
        this.setDexterity(rand.nextInt(100) + 1);
        this.setHandtohand(rand.nextInt(100) + 1);
        this.setHardiness(rand.nextInt(100) + 1);
        this.setMedicine(rand.nextInt(100) + 1);
        this.setMelee(rand.nextInt(100) + 1);
        this.setRanged(rand.nextInt(100) + 1);
        this.setStealth(rand.nextInt(100) + 1);
        this.setSurvival(rand.nextInt(100) + 1);
        this.setSwimming(rand.nextInt(100) + 1);
        this.setTrapping(rand.nextInt(100) + 1);
    }
    
    public void showStats(){
        System.out.println("Name: " + this.getName());
        System.out.println("Gender: " + this.getGender());
        System.out.println("Health: " + this.health + "/" + this.maxHealth);
        System.out.println("Preferred Weapon: " + this.getPreferredWeapon());
        System.out.println("Weapon: " + this.getWeapon().getName());
        System.out.println("Carrying Capacity: " + this.getCarryingCapacity());
        System.out.println("Inventory Space Left: " + (this.getCarryingCapacity() - this.getWeight()));
        System.out.println("Athletics: " + this.getAthletics());
        System.out.println("Strength: " + this.getStrength());
        System.out.println("Intelligence: " + this.getIntelligence());
        System.out.println("Charisma: " + this.getCharisma());
        System.out.println("Climbing: " + this.getClimbing());
        System.out.println("Courage: " + this.getCourage());
        System.out.println("Crafting: " + this.getCrafting());
        System.out.println("Dexterity: " + this.getDexterity());
        System.out.println("Hand-to-hand: " + this.getHandtohand());
        System.out.println("Hardiness: " + this.getHardiness());
        System.out.println("Medicine: " + this.getMedicine());
        System.out.println("Melee: " + this.getMelee());
        System.out.println("Ranged: " + this.getRanged());
        System.out.println("Stealth: " + this.getStealth());
        System.out.println("Survival: " + this.getSurvival());
        System.out.println("Swimming: " + this.getSwimming());
        System.out.println("Trapping: " + this.getTrapping());
    }
    
    public void addAilment(AilmentType ailment){
        this.getAilments().add(ailment);
    }
    
    public void removeAilment(AilmentType ailment){
        this.getAilments().remove(ailment);
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
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the fame
     */
    public int getFame() {
        return fame;
    }

    /**
     * @param fame the fame to set
     */
    public void setFame(int fame) {
        this.fame = fame;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
        if(this.health > this.maxHealth){
            this.health = this.maxHealth;
        }
    }
    
    public void damage(int health){
        this.health -= health;
    }
    
    public void heal(int health){
        this.health += health;
        if(this.health > this.maxHealth){
            this.health = this.maxHealth;
        }
    }

    /**
     * @return the lawful
     */
    public int getLawful() {
        return lawful;
    }

    /**
     * @param lawful the lawful to set
     */
    public void setLawful(int lawful) {
        this.lawful = lawful;
    }

    /**
     * @return the good
     */
    public int getGood() {
        return good;
    }

    /**
     * @param good the good to set
     */
    public void setGood(int good) {
        this.good = good;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * @return the stealth
     */
    public int getStealth() {
        return stealth;
    }

    /**
     * @param stealth the stealth to set
     */
    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    /**
     * @return the handtohand
     */
    public int getHandtohand() {
        return handtohand;
    }

    /**
     * @param handtohand the handtohand to set
     */
    public void setHandtohand(int handtohand) {
        this.handtohand = handtohand;
    }

    /**
     * @return the melee
     */
    public int getMelee() {
        return melee;
    }

    /**
     * @param melee the melee to set
     */
    public void setMelee(int melee) {
        this.melee = melee;
    }

    /**
     * @return the ranged
     */
    public int getRanged() {
        return ranged;
    }

    /**
     * @param ranged the ranged to set
     */
    public void setRanged(int ranged) {
        this.ranged = ranged;
    }

    /**
     * @return the survival
     */
    public int getSurvival() {
        return survival;
    }

    /**
     * @param survival the survival to set
     */
    public void setSurvival(int survival) {
        this.survival = survival;
    }

    /**
     * @return the medicine
     */
    public int getMedicine() {
        return medicine;
    }

    /**
     * @param medicine the medicine to set
     */
    public void setMedicine(int medicine) {
        this.medicine = medicine;
    }

    /**
     * @return the athletics
     */
    public int getAthletics() {
        return athletics;
    }

    /**
     * @param athletics the athletics to set
     */
    public void setAthletics(int athletics) {
        this.athletics = athletics;
    }

    /**
     * @return the hardiness
     */
    public int getHardiness() {
        return hardiness;
    }

    /**
     * @param hardiness the hardiness to set
     */
    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    /**
     * @return the swimming
     */
    public int getSwimming() {
        return swimming;
    }

    /**
     * @param swimming the swimming to set
     */
    public void setSwimming(int swimming) {
        this.swimming = swimming;
    }

    /**
     * @return the climbing
     */
    public int getClimbing() {
        return climbing;
    }

    /**
     * @param climbing the climbing to set
     */
    public void setClimbing(int climbing) {
        this.climbing = climbing;
    }

    /**
     * @return the courage
     */
    public int getCourage() {
        return courage;
    }

    /**
     * @param courage the courage to set
     */
    public void setCourage(int courage) {
        this.courage = courage;
    }

    /**
     * @return the crafting
     */
    public int getCrafting() {
        return crafting;
    }

    /**
     * @param crafting the crafting to set
     */
    public void setCrafting(int crafting) {
        this.crafting = crafting;
    }

    /**
     * @return the charisma
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * @param charisma the charisma to set
     */
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    /**
     * @return the trapping
     */
    public int getTrapping() {
        return trapping;
    }

    /**
     * @param trapping the trapping to set
     */
    public void setTrapping(int trapping) {
        this.trapping = trapping;
    }

    /**
     * @return the maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @param maxHealth the maxHealth to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * @return the weapon
     */
    public WeaponItem getWeapon() {
        return weapon;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the beforePrevRoom
     */
    public Room getBeforePrevRoom() {
        return beforePrevRoom;
    }

    /**
     * @param beforePrevRoom the beforePrevRoom to set
     */
    public void setBeforePrevRoom(Room beforePrevRoom) {
        this.beforePrevRoom = beforePrevRoom;
    }

    /**
     * @return the previousRoom
     */
    public Room getPreviousRoom() {
        return previousRoom;
    }

    /**
     * @param previousRoom the previousRoom to set
     */
    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
    }

    /**
     * @return the currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom the currentRoom to set
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * @return the headHealth
     */
    public int getHeadHealth() {
        return headHealth;
    }

    /**
     * @param headHealth the headHealth to set
     */
    public void setHeadHealth(int headHealth) {
        this.headHealth = headHealth;
    }

    /**
     * @return the rightArmHealth
     */
    public int getRightArmHealth() {
        return rightArmHealth;
    }

    /**
     * @param rightArmHealth the rightArmHealth to set
     */
    public void setRightArmHealth(int rightArmHealth) {
        this.rightArmHealth = rightArmHealth;
    }

    /**
     * @return the leftArmHealth
     */
    public int getLeftArmHealth() {
        return leftArmHealth;
    }

    /**
     * @param leftArmHealth the leftArmHealth to set
     */
    public void setLeftArmHealth(int leftArmHealth) {
        this.leftArmHealth = leftArmHealth;
    }

    /**
     * @return the rightLegHealth
     */
    public int getRightLegHealth() {
        return rightLegHealth;
    }

    /**
     * @param rightLegHealth the rightLegHealth to set
     */
    public void setRightLegHealth(int rightLegHealth) {
        this.rightLegHealth = rightLegHealth;
    }

    /**
     * @return the leftLegHealth
     */
    public int getLeftLegHealth() {
        return leftLegHealth;
    }

    /**
     * @param leftLegHealth the leftLegHealth to set
     */
    public void setLeftLegHealth(int leftLegHealth) {
        this.leftLegHealth = leftLegHealth;
    }

    /**
     * @return the carryingCapacity
     */
    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    /**
     * @param carryingCapacity the carryingCapacity to set
     */
    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
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
     * @return the hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * @param hunger the hunger to set
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     * @return the maxHunger
     */
    public int getMaxHunger() {
        return maxHunger;
    }

    /**
     * @param maxHunger the maxHunger to set
     */
    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    /**
     * @return the dexterity
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * @param dexterity the dexterity to set
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * @return the headDef
     */
    public int getHeadDef() {
        return headDef;
    }

    /**
     * @param headDef the headDef to set
     */
    public void setHeadDef(int headDef) {
        this.headDef = headDef;
    }

    /**
     * @return the limbDef
     */
    public int getLimbDef() {
        return limbDef;
    }

    /**
     * @param limbDef the limbDef to set
     */
    public void setLimbDef(int limbDef) {
        this.limbDef = limbDef;
    }

    /**
     * @return the ailments
     */
    public List<AilmentType> getAilments() {
        return ailments;
    }

    /**
     * @param ailments the ailments to set
     */
    public void setAilments(List<AilmentType> ailments) {
        this.ailments = ailments;
    }

    /**
     * @return the subPronoun
     */
    public String getSubPronoun() {
        return subPronoun;
    }

    /**
     * @param subPronoun the subPronoun to set
     */
    public void setSubPronoun(String subPronoun) {
        this.subPronoun = subPronoun;
    }

    /**
     * @return the objPronoun
     */
    public String getObjPronoun() {
        return objPronoun;
    }

    /**
     * @param objPronoun the objPronoun to set
     */
    public void setObjPronoun(String objPronoun) {
        this.objPronoun = objPronoun;
    }

    /**
     * @return the focus
     */
    public boolean isFocus() {
        return focus;
    }

    /**
     * @param focus the focus to set
     */
    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    /**
     * @return the attackNum
     */
    public int getAttackNum() {
        return attackNum;
    }

    /**
     * @param attackNum the attackNum to set
     */
    public void setAttackNum(int attackNum) {
        this.attackNum = attackNum;
    }

    /**
     * @return the initiative
     */
    public int getInitiative() {
        return initiative;
    }

    /**
     * @param initiative the initiative to set
     */
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    /**
     * @return the randx
     */
    public boolean isRandx() {
        return randx;
    }

    /**
     * @param randx the randx to set
     */
    public void setRandx(boolean randx) {
        this.randx = randx;
    }

    /**
     * @return the preferredWeapon
     */
    public WeaponType getPreferredWeapon() {
        return preferredWeapon;
    }

    /**
     * @param preferredWeapon the preferredWeapon to set
     */
    public void setPreferredWeapon(WeaponType preferredWeapon) {
        this.preferredWeapon = preferredWeapon;
    }

    /**
     * @return the possessivePronoun
     */
    public String getPossessivePronoun() {
        return possessivePronoun;
    }

    /**
     * @param possessivePronoun the possessivePronoun to set
     */
    public void setPossessivePronoun(String possessivePronoun) {
        this.possessivePronoun = possessivePronoun;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @return the headhit
     */
    public int getHeadhit() {
        return headhit;
    }

    /**
     * @param headhit the headhit to set
     */
    public void setHeadhit(int headhit) {
        this.headhit = headhit;
    }

    /**
     * @return the armhit
     */
    public int getArmhit() {
        return armhit;
    }

    /**
     * @param armhit the armhit to set
     */
    public void setArmhit(int armhit) {
        this.armhit = armhit;
    }

    /**
     * @return the leghit
     */
    public int getLeghit() {
        return leghit;
    }

    /**
     * @param leghit the leghit to set
     */
    public void setLeghit(int leghit) {
        this.leghit = leghit;
    }

    /**
     * @return the bodyhit
     */
    public int getBodyhit() {
        return bodyhit;
    }

    /**
     * @param bodyhit the bodyhit to set
     */
    public void setBodyhit(int bodyhit) {
        this.bodyhit = bodyhit;
    }

    /**
     * @return the curHeadHealth
     */
    public int getCurHeadHealth() {
        return curHeadHealth;
    }

    /**
     * @param curHeadHealth the curHeadHealth to set
     */
    public void setCurHeadHealth(int curHeadHealth) {
        this.curHeadHealth = curHeadHealth;
    }

    /**
     * @return the curRightArmHealth
     */
    public int getCurRightArmHealth() {
        return curRightArmHealth;
    }

    /**
     * @param curRightArmHealth the curRightArmHealth to set
     */
    public void setCurRightArmHealth(int curRightArmHealth) {
        this.curRightArmHealth = curRightArmHealth;
    }

    /**
     * @return the curLeftArmHealth
     */
    public int getCurLeftArmHealth() {
        return curLeftArmHealth;
    }

    /**
     * @param curLeftArmHealth the curLeftArmHealth to set
     */
    public void setCurLeftArmHealth(int curLeftArmHealth) {
        this.curLeftArmHealth = curLeftArmHealth;
    }

    /**
     * @return the curRightLegHealth
     */
    public int getCurRightLegHealth() {
        return curRightLegHealth;
    }

    /**
     * @param curRightLegHealth the curRightLegHealth to set
     */
    public void setCurRightLegHealth(int curRightLegHealth) {
        this.curRightLegHealth = curRightLegHealth;
    }

    /**
     * @return the curLeftLegHealth
     */
    public int getCurLeftLegHealth() {
        return curLeftLegHealth;
    }

    /**
     * @param curLeftLegHealth the curLeftLegHealth to set
     */
    public void setCurLeftLegHealth(int curLeftLegHealth) {
        this.curLeftLegHealth = curLeftLegHealth;
    }
    
}
