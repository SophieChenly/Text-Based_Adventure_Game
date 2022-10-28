package persistence;
//CITATION: This file is modeled after the provided sample application

import model.Player;
import model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Player testPlayer = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameSave.json");
        try {
            Player testPlayer = reader.read();
            assertEquals(0, testPlayer.getInventory().size());
            assertEquals("start scene", testPlayer.getLastScene());
            assertEquals(0, testPlayer.getStrongestWeaponDamage());
            assertEquals("bare fists", testPlayer.getStrongestWeaponName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameSave.json");
        try {
            Player testPlayer = reader.read();
            assertEquals("machete", testPlayer.getLastScene());
            List<Weapon> Inventory = testPlayer.getInventory();
            assertEquals(2, testPlayer.getInventory().size());
            assertEquals(190, testPlayer.getStrongestWeaponDamage());
            assertEquals("RocketBelt", testPlayer.getStrongestWeaponName());
            checkWeapon("bludgeon", 10 , testPlayer.getInventory().get(0));
            checkWeapon("Blade of the Ruined King", 160, testPlayer.getInventory().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    protected void checkWeapon(String weaponName, int weaponDamage, Weapon weapon) {
        assertEquals(weaponName, weapon.getWeaponName());
        assertEquals(weaponDamage, weapon.getWeaponDamage());
    }
}
