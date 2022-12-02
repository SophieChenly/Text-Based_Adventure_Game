package ui;

import model.Player;
import model.Scene;
import model.Weapon;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

// Creates the main game
public class Game extends Frame implements ActionListener {

    private static final String JSON_STORE = "./data/gameSave.json";

    Weapon machete = new Weapon("machete", 1,2);

    Weapon hatchet = new Weapon("hatchet", 3, 10);

    Weapon gregTheGreat = new Weapon("Photo of Gregor the Great", 100, 999);

    Weapon rustyKnife = new Weapon("rusty knife", 3, 5);

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

    private Scene sceneMoreKnivesTwo = new Scene("Take another knife!",
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
            "Welcome to the game. "
                    + "You are on the UBC campus. \n"
                    + "There is not a living thing in sight, but you sense a danger in the air. \n "
                    + "Before you are three weapons. Choose one.", sceneTwo, sceneThree, sceneFour);

    private String choice1Text = "machete";
    private String choice2Text = "hatchet";
    private String choice3Text = "photo of Gregor the Great";
    private String storyTextBox = "Welcome to the game. "
            + "You are on the UBC campus. "
            + "You sense a danger in the air. \n"
            + "Before you are three weapons. Choose one.";


    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Player newPlayer;

    // EFFECTS: runs the game
    public Game() {
        runGame();
    }

    // EFFECTS: runs the game
    private void runGame() {

        this.newPlayer = new Player();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //arbWeapons();

        playScene(startScene);

    }

    // EFFECTS: Creates a new window for the GUI

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void initializeGraphicalUserInterface(Scene scene) {

        JFrame frame = new JFrame("UBC");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1300, 1000);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ca.ubc.cpsc210.alarm.model.EventLog e = ca.ubc.cpsc210.alarm.model.EventLog.getInstance();
                for (ca.ubc.cpsc210.alarm.model.Event event : e) {
                    System.out.println(event.toString() + "\n");
                }
                System.exit(0);
            }
        });

        setChoice1Text(scene.getFirstChoice().getOptionName());
        setChoice2Text(scene.getSecondChoice().getOptionName());
        setChoice3Text(scene.getThirdChoice().getOptionName());
        setStoryText(scene.getMainText());

        JPanel panel = new JPanel();
        JPanel textPanel = new JPanel();
        JLabel storyText;
        storyText = new JLabel(storyTextBox);
        JLabel label = new JLabel("Please select option");
        JButton choice1 = new JButton(choice1Text);
        JButton choice2 = new JButton(choice2Text);
        JButton choice3 = new JButton(choice3Text);
        JButton save = new JButton("save game");
        JButton load = new JButton("load game");
        JButton inventory = new JButton("open inventory");

        panel.add(label);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(inventory);
        panel.add(save);
        panel.add(load);

        BufferedImage myPicture = null;

        try {
            myPicture = ImageIO.read(new File("/Users/sophie/IdeaProjects/project_l1x6p/src/main/ui/img_1.png"));
        } catch (IOException e) {
            System.out.println("oops");
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));



        textPanel.add(storyText);
        textPanel.add(picLabel);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, textPanel);
        frame.setVisible(true);

        choice1.addActionListener(this);
        choice1.setActionCommand(scene.getFirstChoice().getOptionName());

        choice2.addActionListener(this);
        choice2.setActionCommand(scene.getSecondChoice().getOptionName());

        choice3.addActionListener(this);
        choice3.setActionCommand(scene.getThirdChoice().getOptionName());

        inventory.addActionListener(this);
        inventory.setActionCommand("inventory");

        save.addActionListener(this);
        save.setActionCommand("save game");

        load.addActionListener(this);
        load.setActionCommand("load game");


    }



    // MODIFIES: this
    // EFFECTS: allows player to add an arbitraru number of weapons to their inventory
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


    // EFFECTS: indicates a loss and restarts the game
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})    private void gameOver() {
        JFrame frame = new JFrame("UBC");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ca.ubc.cpsc210.alarm.model.EventLog e = ca.ubc.cpsc210.alarm.model.EventLog.getInstance();
                for (ca.ubc.cpsc210.alarm.model.Event event : e) {
                    System.out.println(event.toString() + "\n");
                }
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        JPanel textPanel = new JPanel();
        JLabel storyText;
        storyText = new JLabel(storyTextBox);
        JLabel label = new JLabel("Please select option");
        JButton choice1 = new JButton("restart");
        JButton load = new JButton("load game");

        BufferedImage myPicture = null;

        try {
            myPicture = ImageIO.read(new File("/Users/sophie/IdeaProjects/project_l1x6p/src/main/ui/img_3.png"));
        } catch (IOException e) {
            System.out.println("oops");
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        panel.add(label);
        panel.add(choice1);
        panel.add(load);

        textPanel.add(storyText);
        textPanel.add(picLabel);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, textPanel);
        frame.setVisible(true);

        choice1.addActionListener(this);
        choice1.setActionCommand("game start");

        load.addActionListener(this);
        load.setActionCommand("load game");

    }

    // EFFECTS: puts the player in a boss battle. If won, wins the game, if not, loses the game
    private void gameWin() {
        int damageDealt = newPlayer.playerAttack(newPlayer.getPlayerPower(), newPlayer.getStrongestWeaponDamage());

        System.out.println("Before you got on, a gigantic zombie busted down the door to the balcony! \n"
                 + "You grabbed your strongest weapon " + newPlayer.getStrongestWeaponName() + " and dealt "
                 + damageDealt + " damage.");
        if (newPlayer.playerWin(100)) {
            System.out.println("The power of gregor the great coursed through your veins. You slayed the beast! \n"
                     + "You all escaped safely. You win!");
        } else {
            System.out.println("Your attack was too weak. The zombie ripped you to shreds. So close yet so far.");
        }

        System.out.println("Type restart to start over");
        Scanner restart = new Scanner(System.in);
        String restartChoice = restart.nextLine();
        if (restartChoice.equals("restart")) {
            runGame();
        } else {
            gameOver();
        }

    }

    // EFFECTS: prints out the player's inventory,
    // showing the names of collected weapons as well as the strongest weapon name and damage
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void displayInventory() {
        /* ArrayList<Weapon> inventory = new ArrayList<>();
        for (Weapon weapon : newPlayer.getInventory()) {
            inventory.add(weapon);
        }

        JList<Weapon> weaponList = new JList();*/
        JFrame frame = new JFrame("Inventory");
        JList<Weapon> list = new JList<>();
        DefaultListModel<Weapon> model = new DefaultListModel<>();

        JButton addArbWeapon = new JButton("Add a useless weapon");

        JLabel label = new JLabel();
        JPanel panel = new JPanel();
        JSplitPane splitPane = new JSplitPane();
        JLabel strongestWeapon = new JLabel("Strongest weapon: " + newPlayer.getStrongestWeaponName());
        JLabel strongestWeaponDamage = new JLabel("Damage: " + newPlayer.getStrongestWeaponDamage());

        list.setModel(model);

        for (Weapon weapon : newPlayer.getInventory()) {
            model.addElement(weapon);
        }


        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        panel.add(addArbWeapon);
        panel.add(strongestWeapon);
        panel.add(strongestWeaponDamage);
        splitPane.setRightComponent(panel);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.setSize(500,1000);
        frame.setVisible(true);

        addArbWeapon.addActionListener(this);
        addArbWeapon.setActionCommand("add arb");


    }

    // EFFECTS: prints out possible options for the player to choose from
    private void displayOptions(Scene scene) {
        System.out.println("1. " + scene.getFirstChoice().getOptionName());
        System.out.println("2. " + scene.getSecondChoice().getOptionName());
        System.out.println("3. " + scene.getThirdChoice().getOptionName());
    }

    // EFFECTS: runs through a scene, adds weapons if found, lose if chose wrong option, goes to boss battle if all the
    // correct options are chosen, allows the player to select which option they'd like to choose, sets last scene.
    private void playScene(Scene scene) {
        initializeGraphicalUserInterface(scene);
        System.out.println(scene.getMainText());
        newPlayer.setLastScene(scene.getOptionName());
        if (scene.getGameOver()) {
            gameOver();
        } else if (scene.getGameWin()) {
            gameWin();
        }

        if (scene.getAddWeapon()) {
            newPlayer.addWeapons(scene.getWeapon());
            if (scene.getWeapon().getWeaponDamage() > newPlayer.getStrongestWeaponDamage()) {
                newPlayer.setStrongestWeaponDamage(scene.getWeapon().getWeaponDamage());
                newPlayer.setStrongestWeaponName(scene.getWeapon().getWeaponName());
                scene.getWeapon().logStrongestWeapon();
            }
        }

        //scanChoice(scene);

    }

    // EFFECTS: allows the player to input their choice to select their desired path
    private void scanChoice(Scene scene) {
        Scanner decision = new Scanner(System.in);

        int choice = decision.nextInt();
        if (choice == 1) {
            playScene(scene.getFirstChoice());
        } else if (choice == 2) {
            playScene(scene.getSecondChoice());
        } else if (choice == 3) {
            playScene(scene.getThirdChoice());
        } else if (choice == 4) {
            displayInventory();
        } else if (choice == 5) {
            saveGame();
        } else if (choice == 6) {
            loadGame();
        } else {
            System.out.println("Invalid. Try again");
            playScene(scene);
        }
    }

    // EFFECTS: saves the game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(newPlayer);
            jsonWriter.close();
            System.out.println("Saved current game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void loadGame() {
        try {
            newPlayer = jsonReader.read();
            System.out.println("Loaded old save from " + JSON_STORE);
            switch (newPlayer.getLastScene()) {
                case "game start":
                    playScene(startScene);
                    break;
                case "photo of Gregor the Great":
                    playScene(sceneFour);
                    break;
                case "hatchet":
                    playScene(sceneThree);
                    break;
                case "machete":
                    playScene(sceneTwo);
                    break;
                case "Chemistry building":
                    playScene(sceneFive);
                    break;
                case "Main Mall":
                    playScene(sceneSix);
                    break;
                case "The Nest":
                    playScene(sceneSeven);
                    break;
                case "Explore the first floor":
                    playScene(sceneEight);
                    break;
                case "Take a rusty knife":
                    playScene(sceneKnife);
                    break;
                case "Take another knife":
                    playScene(sceneMoreKnives);
                    break;
                case "Do nothing":
                    playScene(sceneIdle);
                    break;
                case "Take another knife!":
                    playScene(sceneMoreKnivesTwo);
                    break;
                case "Leave":
                    playScene(sceneNoKnife);
                    break;
                case "Explore the second floor":
                    playScene(sceneNine);
                    break;
                case "Explore the third floor":
                    playScene(sceneTen);
                    break;
                case "Go to the roof":
                    playScene(sceneEleven);
                    break;
                case "DIE":
                    playScene(sceneDie);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    @Override
    // EFFECTS: Reads user input from pressing buttons and plays the appropriate scene
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {

        if ("machete".equals(e.getActionCommand())) {
            setChoice1Text("Chemistry Building");
            setChoice2Text("Main Mall");
            setChoice3Text("The Nest");
            setStoryText("You picked up the machete and added it to your inventory. You are at the fountain."
                    + " Where do you go?");
            playScene(sceneTwo);
        } else if ("hatchet".equals(e.getActionCommand())) {
            setChoice1Text("Chemistry Building");
            setChoice2Text("Main Mall");
            setChoice3Text("The Nest");
            setStoryText("You picked up the hatchet and added it to your inventory. You are at the fountain."
                    + " Where do you go?");
            playScene(sceneThree);
        } else if ("photo of Gregor the Great".equals(e.getActionCommand())) {
            setChoice1Text("Chemistry Building");
            setChoice2Text("Main Mall");
            setChoice3Text("The Nest");
            setStoryText("You picked up the photo and added it to your inventory. "
                    + "Gregor's dashing smile fills you with warmth. You are at the fountain."
                    + " Where do you go?");
            playScene(sceneFour);
        } else if ("Chemistry building".equals(e.getActionCommand())) {
            setStoryText("Zombies burst out of the doors and killed you. Game over.");
            gameOver();
        } else if ("game start".equals(e.getActionCommand())) {
            setChoice1Text("machete");
            setChoice2Text("hatchet");
            setChoice3Text("photo of Gregor the Great");
            setStoryText("Welcome to the game. "
                    + "You are on the UBC campus. "
                    + "You sense a danger in the air. \n"
                    + "Before you are three weapons. Choose one.");
            runGame();
        } else if ("The Nest".equals(e.getActionCommand())) {
            setChoice1Text("Explore the first floor");
            setChoice2Text("Explore the second floor");
            setChoice3Text("Explore the third floor");
            setStoryText("You make your way to the nest (THERE IS NO IMPLEMENTATION BEYOND THIS POINT)");
            playScene(sceneSeven);
        } else if ("Main Mall".equals(e.getActionCommand())) {
            setStoryText("You died lol");
            gameOver();
        } else if ("inventory".equals(e.getActionCommand())) {
            displayInventory();
        } else if ("save game".equals(e.getActionCommand())) {
            saveGame();
        } else if ("load game".equals(e.getActionCommand())) {
            loadGame();
        } else if ("Explore the first floor".equals(e.getActionCommand())) {
            playScene(sceneEight);
        } else if ("Explore the second floor".equals(e.getActionCommand())) {
            setStoryText("dead");
            gameOver();
        } else if ("Explore the third floor".equals(e.getActionCommand())) {
            setStoryText("ehe you died");
            gameOver();
        } else if ("Take a rusty knife".equals(e.getActionCommand())) {
            playScene(sceneKnife);
        } else if ("Take another knife".equals(e.getActionCommand())) {
            playScene(sceneMoreKnives);
        } else if ("Do nothing".equals(e.getActionCommand())) {
            setStoryText("You think this is a game? You think you have time to dawdle? Well you dont and ur dead now");
            gameOver();
        } else if ("Take another knife!".equals(e.getActionCommand())) {
            playScene(sceneMoreKnivesTwo);
        } else if ("DIE".equals(e.getActionCommand())) {
            setStoryText("welp");
            gameOver();
        } else if ("Leave".equals(e.getActionCommand())) {
            playScene(sceneNoKnife);
        } else if ("add arb".equals(e.getActionCommand())) {
            newPlayer.addWeapons(new Weapon("the tears of the one who coded this", 999));
            displayInventory();
        }




    }

    public void setChoice1Text(String s) {
        this.choice1Text = s;
    }

    public void setChoice2Text(String s) {
        this.choice2Text = s;
    }

    public void setChoice3Text(String s) {
        this.choice3Text = s;
    }

    public void setStoryText(String s) {
        this.storyTextBox = s;
    }

}
