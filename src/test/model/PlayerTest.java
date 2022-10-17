package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Player;
//check

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private static Player testPlayer;
    private static Player testPlayerTwo;

    @BeforeAll
    public static void setUp() {
        testPlayer = new Player();
        testPlayerTwo = new Player();
    }

    @Test
    public void testPlayerAttack() {
        assertEquals(testPlayer.playerAttack(testPlayer.getPlayerPower(), 3), testPlayer.getPlayerPower() * 3);
        assertEquals(testPlayer.playerAttack(testPlayer.getPlayerPower(), 0), 0);
        assertEquals(testPlayerTwo.playerAttack(testPlayerTwo.getPlayerPower(), 3), testPlayerTwo.getPlayerPower() * 3);
        assertEquals(testPlayerTwo.playerAttack(testPlayerTwo.getPlayerPower(), 0), 0);
    }

    @Test
    public void testAddWeaponsToEmptyInventory() {
        Weapon crowbar = new Weapon("crowbar", 2, 3);
        Weapon hacksaw = new Weapon("hacksaw", 10, 15);
        ArrayList<Weapon> testInventory = new ArrayList<>();
        testInventory.add(crowbar);
        testInventory.add(hacksaw);
        testPlayer.addWeapons(crowbar);
        testPlayer.addWeapons(hacksaw);
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

    @Test
    public void testPlayerWin() {
        testPlayer.setStrongestWeaponDamage(10);
        assertTrue(testPlayer.playerWin(10));
        assertTrue(testPlayer.playerWin(5));
        assertTrue(testPlayer.playerWin(0));
        assertFalse(testPlayer.playerWin(1000));
        assertFalse(testPlayer.playerWin(11));
    }

    @Test
    public void testPlayerConstructor() {
        assertEquals(100, testPlayer.getPlayerHealth());
        assertEquals(1, testPlayer.getPlayerPower());
    }

    @Test
    public void testSetters() {
        testPlayer.setStrongestWeaponName("big");
        assertEquals("big", testPlayer.getStrongestWeaponName());
        testPlayer.setStrongestWeaponDamage(40);
        assertEquals(40 , testPlayer.getStrongestWeaponDamage());

    }

}