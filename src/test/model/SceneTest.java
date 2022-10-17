package model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SceneTest {

    Scene testSceneOne = new Scene("test", "main", true);
    Scene testSceneTwo = new Scene("test", "main", true);
    Scene testSceneThree = new Scene("test", "main", true);
    Scene testScene = new Scene("test", "main", testSceneOne, testSceneTwo, testSceneThree);



    @Test
    public void testSceneGameOverText() {
        assertEquals(testScene.endGameText(), "You died. Game over.");
    }

    @Test
    public void testConstructors() {
        Weapon testWeapon = new Weapon("test", 0, 0);
        Scene testSceneVar = new Scene("test", "main", testSceneOne, testSceneTwo, testSceneThree);
        Scene testSceneWea = new Scene("test", "main", testSceneOne, testSceneTwo, testSceneThree, testWeapon);
        assertEquals("test", testSceneVar.getOptionName());
        assertEquals("main", testSceneVar.getMainText());
        assertEquals(testSceneOne, testSceneVar.getFirstChoice());
        assertEquals(testSceneTwo, testSceneVar.getSecondChoice());
        assertEquals(testSceneThree, testSceneVar.getThirdChoice());
        Scene testSceneFour = new Scene(true, "yum", "boss battle time queens");
        assertTrue(testSceneFour.getGameWin());
        assertEquals("yum", testSceneFour.getOptionName());
        assertEquals("boss battle time queens", testSceneFour.getMainText());
        assertEquals("test", testSceneWea.getOptionName());
        assertEquals("main", testSceneWea.getMainText());
        assertEquals(testSceneOne, testSceneWea.getFirstChoice());
        assertEquals(testSceneTwo, testSceneWea.getSecondChoice());
        assertEquals(testSceneThree, testSceneWea.getThirdChoice());
        assertEquals(testWeapon, testSceneWea.getWeapon());
    }
}
