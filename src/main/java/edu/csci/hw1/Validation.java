package edu.csci.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.input.KeyCode;

/**
 * This class handles any and all of the actual validation of input from the
 * user. namely, it validates input based off the past two terms, since they
 * determine completely what is allowed.
 *
 * 08-2014
 *
 * @author William
 */
public class Validation {

    /**
     * This is a list of all of our accepted keys the user can type at any time
     * in the program. If the user types something else, it will simply be
     * ignored.
     */
    private final List<KeyCode> validKeys = new ArrayList(Arrays.asList(KeyCode.LEFT_PARENTHESIS, KeyCode.RIGHT_PARENTHESIS, KeyCode.PLUS, KeyCode.MINUS, KeyCode.SLASH, KeyCode.DIGIT1, KeyCode.DIGIT2,
            KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5, KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8, KeyCode.DIGIT9,
            KeyCode.DIGIT0, KeyCode.ASTERISK, KeyCode.CIRCUMFLEX, KeyCode.PERIOD, KeyCode.NUMPAD0, KeyCode.NUMPAD1, KeyCode.NUMPAD2, KeyCode.NUMPAD3, KeyCode.NUMPAD4, KeyCode.NUMPAD5, KeyCode.NUMPAD6, KeyCode.NUMPAD7, KeyCode.NUMPAD8, KeyCode.NUMPAD9, KeyCode.ADD, KeyCode.SUBTRACT, KeyCode.MULTIPLY, KeyCode.DIVIDE, KeyCode.DECIMAL));

    private final List<String> validKeysStrings = new ArrayList(Arrays.asList("(", ")", "+", "-", "/", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "*", "^", "."));

    /**
     * This method validates an individual keystroke being input by the user. if
     * the keystroke is not contained inside of our list, then false is
     * returned, true otherwise.
     *
     * @param keyStroke the KeyCode object that corresponds to whatever the user
     * happens to have typed.
     * @return true if the keystroke is in our list, and therefore valid, false
     * otherwise.
     */
    public boolean validateKeyStroke(KeyCode keyStroke) {
        if (keyStroke == KeyCode.BACK_SPACE) {
            return true;
        }
        return validKeys.contains(keyStroke);
    }

    /**
     * This method validates the current string in the window against the new
     * (valid) key that is being typed. This method contains a number of rules
     * that follow basic mathematical expression rules. returns true if the key
     * is valid in the current sequence of the expression, false otherwise.
     *
     * @param initialOrder the string before the new key gets added
     * @param newItem the next item that is desired to be added, should have
     * already been determined to be valid.
     * @return true if the key is valid within the existing order of the string,
     * false otherwise.
     */
    public boolean validateOrder(String initialOrder, String newItem) {

        /*
         for the first item, there are only so many things that are valid, namely, you can 
         have a number, a unary operator, or an open parens. That's it! nothing else. 
         */
        if (initialOrder.length() < 1) {
            return newItem.equals("(") || isNumeric(newItem) || newItem.equals("+") || newItem.equals("-");
        }

        String lastChar = initialOrder.substring(initialOrder.length() - 1, initialOrder.length());
        String secondToLast = "0";

        /*
         We can only have a 'second to last' element if there are more than two elements. otherwise 
         it's nonsense. 
         */
        if (initialOrder.length() > 1) {
            secondToLast = initialOrder.substring(initialOrder.length() - 2, initialOrder.length() - 1);
        }
        //If the last thing typed was a number, we don't really care what comes next as 
        //long as it's a subset of what we allow, which has already been checked for. 
        if (isNumeric(lastChar)) {
            return !newItem.equals("(");
        }
        //check to see if there is a period, if there is, and the character being typed
        //is a number, no problem! that's what we want, anything else is not valid.
        if (lastChar.equals(".")) {
            return isNumeric(newItem);
        }

        /*
         This method allows the expression to look like: --3+4 that initial '--' 
         was providing some problems. 
         */
        if ((lastChar.equals("-") || lastChar.equals("+")) && initialOrder.length() == 1) {
            return (isNumeric(newItem));
        }

        //Check the case where the second to last item types and the last items typed were both 
        //a + or -, such as something like ++ or -- This is allowed, since one can be the binary
        //operator, and one can be the unary operator. but the number being typed, MUST be a number. 
        if ((lastChar.equals("+") || lastChar.equals("-")) && ((secondToLast.equals("+") || (secondToLast.equals("-"))))) {
            if (!isNumeric(newItem) && !newItem.equals("(")) {
                return false;
            }
            //Handle the case where the last character typed was a + or -, but it was in front of a number, 
            //so the form of something like 2+
        } else if ((lastChar.equals("+") || lastChar.equals("-")) && !((secondToLast.equals("+") || (secondToLast.equals("-"))))) {
            return newItem.equals("+") || newItem.equals("-") || isNumeric(newItem) || newItem.equals("(");
        }

        //Check to see if the character immediately after an opening paren is either
        //another opening paren, or a number, the only two types of valid input. 
        //so 2+4+(4 would be valid up to this point, or 2+4+((((4 would be valid, but 
        //2+4+(+4 would not be valid. 
        if (lastChar.equals("(")) {
            if (newItem.equals("(") || newItem.equals("-") || newItem.equals("+")) {
                return true;
            } else {
                return isNumeric(newItem);
            }
        }

        //Check to see if the typed key is a symbol, since we don't allow the parenthesis as 
        //an alternate form of multiplication. Therefore it must be a symbol, another paren or 
        //some other symbolic equivalent. So 2+4+(4*4)+3 is valid, however 2+4+(4*4)3+2 is not
        //This method does NOT check parenthesis completeness. 
        if (lastChar.equals(")")) {
            return !isNumeric(newItem);
        }
        if (lastChar.equals("^") || lastChar.equals("*") || lastChar.equals("/")) {
            return newItem.equals("(") || isNumeric(newItem) || newItem.equals("+") || newItem.equals("-");
        }
        return newItem.equals(")") || isNumeric(newItem);
    }

    
    /**
     * This method counts the number of parenthesis in the program, and if the
     * number of left parenthesis is greater than the number of right
     * parenthesis, or vice versa, then the method returns false, true
     * otherwise.
     *
     * @param input the string that we are counting parenthesis in.
     * @return true if the number of left and right parenthesis are equal, false
     * otherwise.
     */
    public boolean parenMatcher(String input) {
        int leftCount = 0;
        int rightCount = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                leftCount++;
            }
            if (input.charAt(i) == ')') {
                rightCount++;
            }
        }
        return leftCount == rightCount;
    }

    /**
     * This method is designed to be able to verify that a string passes our
     * requirements post-facto, and is able to evaluate a mathematical
     * expression after it's been completely constructed. Essentially it
     * virtually creates the expression one character at a time, just like would
     * happen if the user typed it in by hand.
     *
     * @param input The input string being checked after it's already been
     * constructed.
     * @return true if the entire expression is valid, false otherwise.
     */
    public boolean validateEntireString(String input) {
        String temp, temp2;
        if (input.length() == 0) {
            return true;
        }
        if (input.length() == 1) {
            if (validKeysStrings.contains(input)) {
                return true;
            }
        }
        for (int i = 1; i < input.length() - 1; i++) {
            temp = input.substring(0, i);
            temp2 = input.substring(i, i + 1);
            if (!validKeysStrings.contains(temp2)) {
                return false;
            }
            if (!validateOrder(temp, input.substring(i, i + 1))) {
                return false;
            }
        }

        return parenMatcher(input);
    }

    /**
     * This is simply some regex to check to see if a string is purely a number.
     * Please note, this regex is VERY simplistic, no scientific notation, or anything
     * like that. This is NOT IEEE 754 standard regex since we only need a subset due to
     * input constraints in the actual program. 
     *
     * @param str the String being input by the program.
     * @return true if the string is numeric entirely, false otherwise.
     */
    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
