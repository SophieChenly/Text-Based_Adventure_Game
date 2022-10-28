package persistence;
//CITATION: This file is modeled after the provided sample application

import model.Scene;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Game;
import model.Player;
import model.Weapon;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Things JSON has to store: - Player stuff: last scene, inventory, strongest item damage and name

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return loadGameSave(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Player loadGameSave(JSONObject jsonObject) {
        Player newPlayer = new Player();
        addWeaponsToInventory(newPlayer, jsonObject);
        newPlayer.setStrongestWeaponName(jsonObject.getString("strongest weapon name"));
        newPlayer.setStrongestWeaponDamage(jsonObject.getInt("strongest weapon damage"));
        newPlayer.setLastScene(jsonObject.getString("scene name"));
        return newPlayer;
    }

    private void setScene(JSONObject jsonObject) {

        String optionName = jsonObject.getString("option name");

    }

    // MODIFIES: wr
    // EFFECTS: parses weapons from JSON object and adds them to inventory
    private void addWeaponsToInventory(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject nextWeapon = (JSONObject) json;
            addWeaponToInventory(player, nextWeapon);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses weapon from JSON object and adds it to inventory
    private void addWeaponToInventory(Player player, JSONObject jsonObject) {
        String weaponName = jsonObject.getString("weapon name");
        int weaponDamage = jsonObject.getInt("weapon damage");
        Weapon weapon = new Weapon(weaponName, weaponDamage);
        player.addWeapons(weapon);
    }
}
