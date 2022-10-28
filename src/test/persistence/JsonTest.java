package persistence;
//CITATION: This file is modeled after the provided sample application

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Weapon;

public class JsonTest {

        protected void checkWeapon(String weaponName, int weaponDamage, Weapon weapon) {
            assertEquals(weaponName, weapon.getWeaponName());
            assertEquals(weaponDamage, weapon.getWeaponDamage());
        }
}
