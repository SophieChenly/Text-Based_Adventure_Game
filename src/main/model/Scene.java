package model;

import model.Player;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Scanner;

// scenarios/modular storytelling and choice branching
public class Scene implements Writable {

    private String mainText;
    private String optionName;
    private Scene firstChoice;
    private Scene secondChoice;
    private Scene thirdChoice;
    private Weapon weapon;
    private Boolean gameOver = false;
    private Boolean gameWin = false;
    private Boolean addWeapon = false;

    // creates a modular branch of the story (3 options)
    public Scene(String optionName, String main, Scene fc, Scene sc, Scene tc) {
        this.mainText = main;
        this.optionName = optionName;
        this.firstChoice = fc;
        this.secondChoice = sc;
        this.thirdChoice = tc;

    }

    // creates a scene that adds a weapon to your inventory
    public Scene(String optionName, String main, Scene fc, Scene sc, Scene tc, Weapon weapon) {
        this.mainText = main;
        this.optionName = optionName;
        this.firstChoice = fc;
        this.secondChoice = sc;
        this.thirdChoice = tc;
        this.addWeapon = true;
        this.weapon = weapon;

    }

    // creates a scene showing a game over
    public Scene(String optionName, String main, Boolean gameOver) {
        this.optionName = optionName;
        this.mainText = main;
        this.gameOver = true;
    }

    // brings the player to the boss battle
    public Scene(Boolean gameWin, String optionName, String main) {
        this.optionName = optionName;
        this.mainText = main;
        this.gameWin = true;
    }

    public String getMainText() {
        return this.mainText;
    }

    public Scene getFirstChoice() {
        return this.firstChoice;
    }

    public Scene getSecondChoice() {
        return this.secondChoice;
    }

    public Scene getThirdChoice() {
        return this.thirdChoice;
    }

    public Boolean getGameOver() {
        return this.gameOver;
    }

    public Boolean getGameWin() {
        return this.gameWin;
    }

    public String getOptionName() {
        return this.optionName;
    }

    public Boolean getAddWeapon() {
        return this.addWeapon;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    //EFFECTS: message telling players it's game over
    public String endGameText() {
        return "You died. Game over.";
    }


    // returns scene name as json data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scene name", optionName);
        json.put("main test", mainText);
        json.put("first choice", firstChoice.toJson());
        json.put("second choice", secondChoice.toJson());
        json.put("third choice", thirdChoice.toJson());
        json.put("game over", gameOver);
        json.put("add weapon", weapon.toJson());
        json.put("game win", gameWin);
        return json;
    }
}
