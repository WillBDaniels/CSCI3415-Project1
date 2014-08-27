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
    
    public boolean validateOrder(String initialOrder, String newItem){
        if (initialOrder.length() < 1){
            return newItem.equals("(") || isNumeric(newItem);
        }
        String lastChar = initialOrder.substring(initialOrder.length()-1, initialOrder.length());
        String secondToLast = "0";
        if (initialOrder.length() > 2){
            secondToLast = initialOrder.substring(initialOrder.length()-2, initialOrder.length()-1);
        }
        //If the last thing typed was a number, we don't really care what comes next as 
        //long as it's a subset of what we allow, which has already been checked for. 
        if (isNumeric(lastChar)){
            return !newItem.equals("(");
        }
        //check to see if there is a period, if there is, and the character being typed
        //is a number, no problem! that's what we want, anything else is not valid.
        if (lastChar.equals(".")){
            return isNumeric(newItem);
        }
        
        //Check the case where the second to last item types and the last items typed were both 
        //a + or -, such as something like ++ or -- This is allowed, since one can be the binary
        //operator, and one can be the unary operator. but the number being typed, MUST be a number. 
        if ((lastChar.equals("+") || lastChar.equals("-")) && ((secondToLast.equals("+") || (secondToLast.equals("-"))))){
            if (!isNumeric(newItem) && !newItem.equals("(")){
                return false;
            }
            //Handle the case where the last character typed was a + or -, but it was in front of a number, 
            //so the form of something like 2+
        }else if ((lastChar.equals("+") || lastChar.equals("-")) && !((secondToLast.equals("+") || (secondToLast.equals("-"))))){
            if (newItem.equals("+") || newItem.equals("-") || isNumeric(newItem) || newItem.equals("(")){
                return true;
            }
        }
        
        
        //Check to see if the character immediately aftre an opening paren is either
        //another opening paren, or a number, the only two types of valid input. 
        //so 2+4+(4 would be valid up to this point, or 2+4+((((4 would be valid, but 
        //2+4+(+4 would not be valid. 
        if (lastChar.equals("(")){
            if (newItem.equals("(")){
                return true;
            }else{
                return isNumeric(newItem);
            }
        }
        
        //Check to see if the typed key is a symbol, since we don't allow the parenthesis as 
        //an alternate form of multiplication. Therefore it must be a symbol, another paren or 
        //some other symbolic equivalent. So 2+4+(4*4)+3 is valid, however 2+4+(4*4)3+2 is not
        //This method does NOT check parenthesis completeness. 
        if (lastChar.equals(")")){
            return !isNumeric(newItem);
        }
        
        
        return newItem.equals(")") || isNumeric(newItem);
    }
    
    
    public boolean parenMatcher(String input){
        int leftCount = 0;
        int rightCount = 0;
        
        
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '('){
                leftCount ++;
            }
            if (input.charAt(i) == ')'){
                rightCount++;
            }
            
        }
        return leftCount == rightCount;
    }
    
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
