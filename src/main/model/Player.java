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
    private Boolean dead;

    // initializes the player
    public Player() {
        this.playerHealth = 100;
        this.playerPower = 1;
        this.inventory = new ArrayList<>();
        this.dead = false;
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

    public Scene getLastScene() {
        return this.lastScene;
    }

    public Boolean getPlayerDead() {
        return this.dead;
    }

    public void setPlayerHealth(int newHealth) {
        this.playerHealth = newHealth;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerPower(int playerPower) {
        this.playerPower = playerPower;
    }

    public void setPlayerInventory(List<Weapon> listOfWeapon) {
        this.inventory = listOfWeapon;
    }

    public void setPlayerLastScene(Scene scene) {
        this.lastScene = scene;
    }

    public void setPlayerDead(Boolean bool) {
        this.dead = bool;
    }


    //EFFECTS: player performs an attack based on power and weapon damage
    public int playerAttack(int playerPower, int weaponDamage) {
        return 0; //stub
    }

    //MODIFIES: this
    //EFFECTS: player gets hurt by damage, if player health drops below 0, player dies
    public void playerHurt(int damage) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: player gets healed
    public void playerHeal(int heal) {
        //stub
    }

    // adds a weapon in the player's inventory
    public void addWeapons(Weapon weapon)   {
        this.inventory.add(weapon);
    }

}
