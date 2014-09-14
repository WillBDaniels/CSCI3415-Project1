package edu.csci.hw1;

/**
* This is the class that will handle operator symbols.
*
* 08-2014
*
*/

public class Operator {
    private int precedence;
    private String symbol;
    private boolean leftAssoc; 

    public Operator(String op) {
        setPrecedence(op);
        symbol = op;
        leftAssoc = op.equals("^") ? false : true;
    }

    private void setPrecedence(String op) {
        switch (op) {
        case "+":
        case "-":
            precedence = 1;
            break;
        case "/":
        case "*":
            precedence = 2;
            break;
        case "^":
            precedence = 3;
            break;
        case "(":
            precedence = 0;
            break;
        case ")":
            precedence = 4;
            break;
        default:
            break;
        }
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean getLeftAssoc() {
        return leftAssoc;
    }

    public String toString() {
        return symbol;
    }
}
