package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String playerName;
    private int playerHealth;
    private int playerPower;
    private List<Weapon> inventory = new ArrayList<>();

    public Player() {
        this.playerHealth = 100;
        this.playerPower = 1;
    }

    public void addWeapons(Weapon weapon)   {
        this.inventory.add(weapon);
    }


}
