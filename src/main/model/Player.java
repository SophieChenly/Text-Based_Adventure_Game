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

    // initializes the player
    public Player() {
        this.playerHealth = 100;
        this.playerPower = 1;
        this.inventory = new ArrayList<>();
    }

    // adds a weapon in the player's inventory
    public void addWeapons(Weapon weapon)   {
        this.inventory.add(weapon);
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

    public void setPlayerHealth(int newHealth) {
        this.playerHealth = newHealth;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerPower(int playerPower) {
        this.playerPower = playerPower;
    }

    public void setPlayerLastScene(Scene scene) {
        this.lastScene = scene;
    }


    //EFFECTS: player performs an attack based on power and weapon damage
    public int playerAttack(int playerPower, int weaponDamage) {
        return 0; //stub
    }

    //MODIFIES: this
    //EFFECTS: player gets hurt by damage
    public void playerHurt(int damage) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: player gets healed
    public void playerHeal(int heal) {
        //stub
    }

}
