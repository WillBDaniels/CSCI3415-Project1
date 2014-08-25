package edu.csci.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.input.KeyCode;

/**
 *
 * 08-2014 
 * @author William
 */
public class Validation {
    private final List<KeyCode> validKeys = new ArrayList(Arrays.asList(KeyCode.LEFT_PARENTHESIS, KeyCode.RIGHT_PARENTHESIS, KeyCode.PLUS
    , KeyCode.MINUS, KeyCode.EQUALS, KeyCode.SLASH, KeyCode.DIGIT1, KeyCode.DIGIT2,
    KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5, KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8, KeyCode.DIGIT9, 
    KeyCode.DIGIT0, KeyCode.ASTERISK, KeyCode.CIRCUMFLEX, KeyCode.PERIOD));
    
    public boolean validateKeyStroke(KeyCode keyStroke){
        if (keyStroke == KeyCode.BACK_SPACE){
            return true;
        }
       return validKeys.contains(keyStroke);
    }
}
