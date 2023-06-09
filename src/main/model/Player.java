package model;
//CITATION: This file is modeled after the provided sample application

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// player, represents the user and their stats
public class Player implements Writable {

    private String playerName;
    private int playerHealth;
    private int playerPower;
    private List<Weapon> inventory;
    private String lastScene = "start scene";
    private int strongestWeaponDamage = 0;
    private String strongestWeaponName = "bare fists";

    // initializes the player
    public Player() {
        this.playerHealth = 100;
        this.playerPower = 1;
        this.inventory = new ArrayList<>();
    }

    public void setLastScene(String string) {
        this.lastScene = string;
    }

    public String getLastScene() {
        return this.lastScene;
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
        ca.ubc.cpsc210.alarm.model.EventLog e = ca.ubc.cpsc210.alarm.model.EventLog.getInstance();
        e.logEvent(new ca.ubc.cpsc210.alarm.model.Event("Added " + weapon.getWeaponName() + " to inventory"));
        this.inventory.add(weapon);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventory", inventoryToJson());
        json.put("last scene", lastScene);
        json.put("strongest weapon damage", strongestWeaponDamage);
        json.put("strongest weapon name", strongestWeaponName);
        return json;
    }

    // EFFECTS: returns things in this inventory as a JSON array
    private JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Weapon weapon : inventory) {
            jsonArray.put(weapon.toJson());
        }

        return jsonArray;
    }
}
