package edu.csci.hw1;

import java.util.Stack;

/*
  Author: ZURAWSKJ, 9-1-2014
  This is the class that will handle converting inset to RPN.
*/

public class InsetToRPN {
    private String inset_string;
    private String rpn_string;

    // CONSTRUCTOR
    // Receives a string in Inset Notation and converts to RPN
    public void inset (String expr_in) {
        inset_string = expr_in;
    }
  
    // ANSWER
    // PRE: The rpn_string data field contains a valid Inset Notation.
    // POST: Converts inset_string to RPN and stores in rpn_string.
    public void convert() throws Exception {
        CalculatorScanner cs = new CalculatorScanner(inset_string);
        StringBuilder rpn = new StringBuilder();
        Stack<Operator> operators = new Stack<>();

        try {
            // read the entire expression
            while (cs.hasNextToken()) {
                String token = cs.readToken();

                // if its a number, it can go on the string
                if (isDouble(token)) {
                    rpn.append(token + " ");
                } else {
                    Operator curr = new Operator(token);

                    // pop existing operators if they have higher precedence 
                    if (!operators.empty() && !curr.toString().equals("(")) {
                        Operator top = operators.peek();

                        while ((top.getPrecedence() >= curr.getPrecedence())
                               && !operators.empty() && curr.getLeftAssoc()) {
                            top = operators.pop();
                            rpn.append(top.toString() + " ");

                            // look at next operator
                            top = operators.peek();
                        }
                    }

                    // see if there's a matching left parenthesis
                    if (curr.toString().equals(")")) {
                        for (Operator next = operators.pop();
                             !next.toString().equals("(");
                             next = operators.pop()) {
                            rpn.append(next.toString() + " ");
                        }
                    }
                    
                    if (!curr.toString().equals(")")) {
                        operators.push(curr);
                    }
                }
            }

            while (!operators.empty()) {
                rpn.append(operators.pop().toString() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        rpn_string = rpn.toString();
    }
    
    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);

            return true;
        } catch (NumberFormatException e) {
            //e.printStackTrace(System.err);
            return false;
        }
    }

    // GET_INSET_STRING
    // POST: Returns inset_string
    public String getInsetString()
    {
        return inset_string;
    }
  
    // GET_RPN_STRING
    // POST: Returns RPN_string
    public String getRPNString()
    {
        return rpn_string;
    }
}
