package model;

import model.Player;

import java.util.Scanner;

// scenarios/modular storytelling and choice branching
public class Scene {

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
    public Scene(String optionname, String main, Scene fc, Scene sc, Scene tc) {
        this.mainText = main;
        this.optionName = optionname;
        this.firstChoice = fc;
        this.secondChoice = sc;
        this.thirdChoice = tc;

    }

    // creates a scene that adds a weapon to your inventory
    public Scene(String optionname, String main, Scene fc, Scene sc, Scene tc, Weapon weapon) {
        this.mainText = main;
        this.optionName = optionname;
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

    // creates a scene showing you won
    public Scene(Boolean gameWin, String optionName, String main) {
        this.optionName = optionName;
        this.mainText = main;
        this.gameWin = true;
    }

    // EFFECTS: makes a message that tells the player to make a choice
    public String choiceMessage() {
        return "make your choice.";
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




}
