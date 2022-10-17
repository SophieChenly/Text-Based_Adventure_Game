package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

    private static Player testPlayer;

    @BeforeAll
    public static void setUp() {
        testPlayer = new Player();
    }

    @Test
    public void testPlayerAttack() {
        assertEquals(testPlayer.playerAttack(testPlayer.getPlayerPower(), 3), testPlayer.getPlayerPower() * 3);
    }

    @Test
    public void testPlayerHurtNotFatal() {
        testPlayer.setPlayerDead(false);
        testPlayer.setPlayerHealth(100);
        testPlayer.playerHurt(50);
        assertEquals(testPlayer.getPlayerHealth(), 50);
        assertFalse(testPlayer.getPlayerDead());
    }

    @Test
    public void testPlayerHurtMultipleNotFatal() {
        testPlayer.setPlayerDead(false);
        testPlayer.setPlayerHealth(100);
        testPlayer.playerHurt(30);
        testPlayer.playerHurt(20);
        assertEquals(testPlayer.getPlayerHealth(), 50);
        assertFalse(testPlayer.getPlayerDead());
    }

    @Test
    public void testPlayerHurtFatal() {
        testPlayer.setPlayerDead(false);
        testPlayer.setPlayerHealth(100);
        testPlayer.playerHurt(100);
        assertEquals(testPlayer.getPlayerHealth(),0);
        Assertions.assertTrue(testPlayer.getPlayerDead());
    }

    @Test
    public void testPlayerHeal() {
        testPlayer.setPlayerHealth(150);
        testPlayer.playerHeal(100);
        assertEquals(testPlayer.getPlayerHealth(), 250);
    }

    @Test
    public void testAddWeaponsToEmptyInventory() {
        Weapon crowbar = new Weapon("crowbar", 2, 3);
        ArrayList<Weapon> testInventory = new ArrayList<>();
        testInventory.add(crowbar);
        testPlayer.addWeapons(crowbar);
        assertEquals(testPlayer.getInventory(), testInventory);
    }

    @Test
    public void testAddWeaponsToNonEmptyInventory() {
        Weapon crowbar = new Weapon("crowbar", 2, 3);
        Weapon hatchet = new Weapon("hatchet", 2, 3);
        Weapon chainsaw = new Weapon("chainsaw", 2, 3);
        Weapon gregor = new Weapon("gregor", 99, 100);
        ArrayList<Weapon> testInventory = new ArrayList<>();
        testInventory.add(crowbar);
        testInventory.add(hatchet);
        testPlayer.setPlayerInventory(testInventory);
        testPlayer.addWeapons(chainsaw);
        testInventory.add(chainsaw);
        assertEquals(testPlayer.getInventory(), testInventory);
        testPlayer.addWeapons(gregor);
        testInventory.add(gregor);
        assertEquals(testPlayer.getInventory(), testInventory);

    }
}