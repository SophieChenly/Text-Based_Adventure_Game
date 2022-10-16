package model;

// scenarios/modular storytelling and choice branching
public class Scene {

    private String mainText;
    private Scene firstChoice;
    private Scene secondChoice;
    private Scene thirdChoice;
    private Boolean gameOver;

    // creates a modular branch of the story
    public Scene(String main, Scene fc, Scene sc, Scene tc) {
        this.mainText = main;
        this.firstChoice = fc;
        this.secondChoice = sc;
        this.thirdChoice = tc;

    }

    // creates a scene showing a game over
    public Scene(String main, Boolean gameOver) {
        this.mainText = main;
        this.gameOver = true;
    }

    // EFFECTS: makes a message that tells the player to make a choice
    public String choiceMessage() {
        return "stub"; //stub
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

    //EFFECTS: game over
    public void endGame() {
        //stub
    }

    //EFFECTS: displays main text
    public void displayMainText() {
        //stub
    }

    //EFFECTS: displays first choice
    public void displayFirstChoice() {
        //stub
    }

    //EFFECTS: displays second choice
    public void displaySecondChoice() {
        //stub
    }

    //EFFECTS: displays third choice
    public void displayThirdChoice() {
        //stub
    }



}
