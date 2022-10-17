package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeaponTest {

    private static Weapon testWeapon;
    private static Weapon testWeaponTwo;

    @BeforeEach
    public void setUp() {
        testWeapon = new Weapon("test weapon", 1, 5);
        testWeapon = new Weapon("test weapon two", 0, 0);
}

    @Test
    public void testWeaponDamage() {
        assertTrue(testWeapon.getWeaponDamage() <= 5 && testWeapon.getWeaponDamage() >= 1);
        assertEquals(testWeaponTwo.getWeaponDamage(), 0);
    }

    @Test
    public void testDisplayWeaponStats() {
        assertEquals(testWeapon.getWeaponStats(), "Weapon name: test weapon\n Damage: " + testWeapon.getWeaponDamage());
        assertEquals(testWeaponTwo.getWeaponStats(), "Weapon name: test weapon\n Damage: " + testWeaponTwo.getWeaponDamage());
    }

}
