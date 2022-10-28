package persistence;
//CITATION: This file is modeled after the provided sample application

import model.Player;
import model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Player testPlayer = new Player();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Player testPlayer = new Player();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(testPlayer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            testPlayer = reader.read();
            assertEquals(0, testPlayer.getInventory().size());
            assertEquals("start scene", testPlayer.getLastScene());
            assertEquals(0, testPlayer.getStrongestWeaponDamage());
            assertEquals("bare fists", testPlayer.getStrongestWeaponName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Player testPlayer = new Player();
            testPlayer.setLastScene("machete");
            testPlayer.addWeapons(new Weapon("bludgeon", 10));
            testPlayer.addWeapons(new Weapon("Blade of the Ruined King", 160));
            testPlayer.setStrongestWeaponDamage(190);
            testPlayer.setStrongestWeaponName("RocketBelt");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(testPlayer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            testPlayer = reader.read();
            assertEquals("machete", testPlayer.getLastScene());
            List<Weapon> Inventory = testPlayer.getInventory();
            assertEquals(2, testPlayer.getInventory().size());
            assertEquals(190, testPlayer.getStrongestWeaponDamage());
            assertEquals("RocketBelt", testPlayer.getStrongestWeaponName());
            checkWeapon("bludgeon", 10 , testPlayer.getInventory().get(0));
            checkWeapon("Blade of the Ruined King", 160, testPlayer.getInventory().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    protected void checkWeapon(String weaponName, int weaponDamage, Weapon weapon) {
        assertEquals(weaponName, weapon.getWeaponName());
        assertEquals(weaponDamage, weapon.getWeaponDamage());
    }
}
