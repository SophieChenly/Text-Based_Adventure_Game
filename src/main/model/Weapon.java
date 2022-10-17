package model;

// weapon for use in combat
public class Weapon {

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

    //MODIFIES: this
    //EFFECTS: sets weapon damage
    public int weaponDamage(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    public String getWeaponName() {
        return this.weaponName;
    }

    //EFFECTS: displays weapon name and damage
    public String displayWeaponStats() {
        System.out.println("Weapon name: " + this.weaponName + "\n Damage: " + this.weaponDamage);
        return "Weapon name: " + this.weaponName + "\n Damage: " + this.weaponDamage;
    }

    

}
