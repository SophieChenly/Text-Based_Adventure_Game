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

    //EFFECTS: message telling players it's game over
    public String endGameText() {
        return "You died. Game over.";
    }




}
