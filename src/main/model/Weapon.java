package model;
//CITATION: This file is modeled after the provided sample application

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// weapon for use in combat
public class Weapon implements Writable {

    private String weaponName;
    private int weaponDamage;
    private int minDamage;
    private int maxDamage;

    // weapon for combat, used by the player and deals damage to enemies
    public Weapon(String name, int min, int max) {
        this.weaponName = name;
        this.minDamage = min;
        this.maxDamage = max;
        this.weaponDamage = weaponDamage(min,max);
    }

    // weapon with preset name and damage
    public Weapon(String name, int damage) {
        this.weaponName = name;
        this.weaponDamage = damage;
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public String getWeaponName() {
        return this.weaponName;
    }

    //EFFECTS: displays weapon name and damage
    public String getWeaponStats() {
        return "Weapon name: " + this.weaponName + "\n Damage: " + this.weaponDamage;
    }

    //MODIFIES: this
    //EFFECTS: sets weapon damage
    public int weaponDamage(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("weapon name", weaponName);
        json.put("weapon damage", weaponDamage);
        return json;
    }

    @Override
    public String toString() {
        return weaponName;
    }


}
