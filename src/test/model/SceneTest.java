package model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SceneTest {

    Scene testSceneOne = new Scene("test", "main", true);
    Scene testSceneTwo = new Scene("test", "main", true);
    Scene testSceneThree = new Scene("test", "main", true);
    Scene testScene = new Scene("test", "main", testSceneOne, testSceneTwo, testSceneThree);


    @Test
    public void testSceneChoice() {
        assertEquals(testScene.choiceMessage(),"make your choice.");
    }

    @Test
    public void testSceneGameOverText() {
        assertEquals(testScene.endGameText(), "You died. Game over.");
    }
}
