package model;

import java.util.ArrayList;
import java.util.List;

// player, represents the user and their stats
public class Player {

    private String playerName;
    private int playerHealth;
    private int playerPower;
    private List<Weapon> inventory;
    private Scene lastScene;
    private int strongestWeaponDamage = 0;
    private String strongestWeaponName;

    // initializes the player
    public Player() {
        this.playerHealth = 100;
        this.playerPower = 1;
        this.inventory = new ArrayList<>();
    }

    public int getPlayerHealth() {
        return this.playerHealth;
    }

    public int getPlayerPower() {
        return this.playerPower;
    }

    public List<Weapon> getInventory() {
        return this.inventory;
    }

    public int getStrongestWeaponDamage() {
        return this.strongestWeaponDamage;
    }

    public String getStrongestWeaponName() {
        return this.strongestWeaponName;
    }

    public void setStrongestWeaponDamage(int damage) {
        this.strongestWeaponDamage = damage;
    }

    public void setStrongestWeaponName(String name) {
        this.strongestWeaponName = name;
    }

    public void setPlayerInventory(List<Weapon> listOfWeapon) {
        this.inventory = listOfWeapon;
    }

    //EFFECTS: player performs an attack based on power and weapon damage
    public int playerAttack(int playerPower, int weaponDamage) {
        return playerPower * weaponDamage;
    }

    //EFFECTS: calculates whether the player will win or lose against the enemy
    public Boolean playerWin(int enemyHealth) {
        if (this.playerAttack(this.playerPower, this.strongestWeaponDamage) >= enemyHealth) {
            return true;
        }
        return false;
    }

    // adds a weapon in the player's inventory
    public void addWeapons(Weapon weapon)   {
        this.inventory.add(weapon);
    }


}
