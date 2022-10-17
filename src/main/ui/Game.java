package ui;

import model.Player;
import model.Scene;
import model.Weapon;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {
    Weapon tornBook = new Weapon("torn book", 0, 2);

    Weapon machete = new Weapon("machete", 1,10);

    Weapon hatchet = new Weapon("hatchet", 3, 10);

    Weapon gregTheGreat = new Weapon("Photo of Gregor the Great", 100, 999);

    Weapon rustyKnife = new Weapon("rusty knife", 2, 5);

    Weapon rustyKnifeTwo = new Weapon("rusty knife", 2, 5);

    Weapon rustyKnifeThree = new Weapon("rusty knife", 2, 5);

    private Scene sceneDie = new Scene("DIE",
            "Grabbed from all directions, the horde consumed you", true);

/*
    private Scene sceneGetManyKnives = new Scene("DIE?",
            "By sheer force of will, \n"
            + "you stopped yourself from dying and are now free to take as many knives as you want. Go crazy."
            + "unfortunately, since you have defied the laws of the universe you must die after you are satisfied \n"
            + "with your knife count. You grabbed a knife.",
            sceneGetKnives, sceneGetKnives, sceneDie, new Weapon("knife", 2, 10)); */

    private Scene sceneEleven = new Scene(true, "Go to the roof",
            "You reached the roof. A group of survivors notice you. \n"
            + "They wave at you to join them, \n"
            + "and you breathed a sigh of relief as the sound of a rescue helicopter draws near.");

    private Scene sceneTen = new Scene("Explore the third floor", "You reached the third floor. \n"
            + "You reached the Nest area. A lone zombie slips out from behind a couch.\n"
            + "You're not gonna win this fight buddy.",
            true);

    private Scene sceneNine = new Scene("Explore the second floor",
            "As you climbed up the stairs, you were chased down and \n"
            + "killed by sprinting corpses.", true);

    private Scene sceneNoKnife = new Scene("Leave",
            "You exit Honour Roll. You hear flesh slapping against the floor.",
            sceneNine, sceneTen, sceneEleven);

    private Scene sceneMoreKnivesTwo = new Scene("Take another knife",
            "You spent too much time here. It's time to go.",
            sceneDie, sceneDie, sceneDie, rustyKnifeThree);

    private Scene sceneIdle = new Scene("Do nothing", "You fool. You buffoon. \n"
             + "Time was of the essence, and you are now out of it.", true);

    private Scene sceneMoreKnives = new Scene("Take another knife",
            "You grabbed another knife. Another?",
            sceneMoreKnivesTwo, sceneIdle, sceneNoKnife, rustyKnifeTwo);

    private Scene sceneKnife = new Scene("Take a rusty knife",
            "You placed the knife in your inventory. Take another?",
            sceneMoreKnives, sceneIdle, sceneNoKnife, rustyKnife);

    private Scene sceneEight = new Scene("Explore the first floor",
            "You enter an abandoned Honour Roll. \n"
            + "Rusty knives litter the ground. Add one to inventory?", sceneKnife, sceneIdle, sceneNoKnife);

    private Scene sceneSeven = new Scene("The Nest",
            "Disoriented and confused, you made your way to the Nest. \n"
                  + "An eerie silence hung in the air, but something is very wrong. What do you do? \n"
                  + "(YOU ARE NOT ALONE)", sceneEight, sceneNine, sceneTen);

    private Scene sceneSix = new Scene("Main Mall",
            "You turned to look at the empty road. Silence. \n"
                  + "Suddenly, gargling. \n"
                  + "A rotten, decomposed hand grabs your feet, dragging you into the murky fountain.", true);

    private Scene sceneFive = new Scene("Chemistry building",
            "Zombies burst out from the door the moment you open it. They tear you to shreds.", true);

    private Scene sceneTwo = new Scene("machete",
            "You picked up the machete and added it into your inventory. \n"
                  +  "You are at the fountain. Which way do you go?", sceneFive, sceneSix, sceneSeven, machete);


    private Scene sceneThree = new Scene("hatchet",
            "You picked up the hatchet and added it into your inventory. \n"
                   + "You are at the fountain. Which way do you go?", sceneFive, sceneSix, sceneSeven, hatchet);


    private Scene sceneFour = new Scene("photo of Gregor the Great",
            "You picked up the photo and added it into your inventory.\n"
                    + "Gregor's dashing smile fills you with warmth.\n"
                    + "You are at the fountain. Which way do you go?", sceneFive, sceneSix, sceneSeven, gregTheGreat);


    private Scene startScene = new Scene("game start",
            "Welcome to the game. Type 1, 2 or 3 to make your decisions. Type 4 to see inventory."
                    + "You are on the UBC campus. \n"
                    + " There is not a living thing in sight, but you sense a danger in the air. \n "
                    + "Before you are three weapons. Choose one.", sceneTwo, sceneThree, sceneFour);

    private Player newPlayer;

    public Game() {
        runGame();
    }

    private void runGame() {
        this.newPlayer = new Player();
        newPlayer.addWeapons(tornBook);
        arbWeapons();
        playScene(startScene);


    }

    private void arbWeapons() {
        System.out.println("Before we start, may I interest you in an unlimited number of useless weapons? \n"
                + "For no reason whatsoever. You'll be able to find them in your inventory later."
                + "Type yes to add a useless weapon into your inventory!");
        Scanner scanner = new Scanner(System.in);
        String choose = scanner.nextLine();
        if ("yes".equals(choose)) {
            newPlayer.addWeapons(new Weapon("Useless weapon to demonstrate adding objects to list",
                    0,0));
            arbWeapons();

        }
    }


    private void gameOver() {
        System.out.println("You died. Type restart to start over");
        Scanner restart = new Scanner(System.in);
        String restartChoice = restart.nextLine();
        if (restartChoice.equals("restart")) {
            runGame();
        } else {
            gameOver();
        }
    }

    private void gameWin() {
        System.out.println("You won. Type restart to start over");
        Scanner restart = new Scanner(System.in);
        String restartChoice = restart.nextLine();
        if (restartChoice.equals("restart")) {
            runGame();
        } else {
            gameOver();
        }

    }

    private void displayInventory(Scene scene) {
        if (scene.getAddWeapon()) {
            newPlayer.addWeapons(scene.getWeapon());
        }
        for (Weapon weapon: newPlayer.getInventory()) {
            System.out.println(weapon.getWeaponStats());
        }
        playScene(scene);
    }

    private void playScene(Scene scene) {
        System.out.println(scene.getMainText());
        if (scene.getGameOver()) {
            gameOver();
        } else if (scene.getGameWin()) {
            gameWin();
        }
        System.out.println("1. " + scene.getFirstChoice().getOptionName());
        System.out.println("2. " + scene.getSecondChoice().getOptionName());
        System.out.println("3. " + scene.getThirdChoice().getOptionName());
        Scanner decision = new Scanner(System.in);

        int choice = decision.nextInt();
        if (choice == 1) {
            playScene(scene.getFirstChoice());
        } else if (choice == 2) {
            playScene(scene.getSecondChoice());
        } else if (choice == 3) {
            playScene(scene.getThirdChoice());
        } else if (choice == 4) {
            displayInventory(scene);
        } else {
            System.out.println("Invalid. Try again");
            playScene(scene);
        }
    }


}