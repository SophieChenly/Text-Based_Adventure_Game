package model;

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
        this.weaponDamage = calculateWeaponDamage(min,max);
    }

    public int calculateWeaponDamage(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public int showWeaponDamage() {
        System.out.println("Your weapon is a " + this.weaponName + " with " + this.weaponDamage + " power.");
        return 0;
    }
    

}
