package edu.csci.hw1;

import java.util.Stack;

/**
* This is the class that will handle converting inset to RPN.
*
* 08-2014
* @author Michael Hunsinger
*/

public class InsetToRPN {
    private String inset_string;
    private String rpn_string;

    public void inset (String expr_in) {
        inset_string = expr_in;
        this.cleanInfixStr();
    }
    
    private void cleanInfixStr() {
        inset_string = inset_string.replaceAll("--", "+")
                                   .replaceAll("\\+-", "-")
                                   .replaceAll("-\\+", "-")
                                   .replaceAll("\\+\\+", "+")
                                   .replaceAll("\\*\\-", "*!")
                                   .replaceAll("\\/\\-", "/!")
                                   .replaceAll("\\^\\-", "^!")
                                   .replaceAll("\\(\\-", "\\(\\!");

        if (inset_string.charAt(0) == '-')
            inset_string = inset_string.replaceFirst("\\-", "!");
    }
  
    public void convert() throws Exception {
        CalculatorScanner cs = new CalculatorScanner(inset_string);
        StringBuilder rpn = new StringBuilder();
        Stack<Operator> operators = new Stack<>();

        try {
            // read the entire expression
            while (cs.hasNextToken()) {
                String token = cs.readToken();

                // if its a number, it can go on the string
                if (isDouble(token) || token.equals("!")) {
                    if (token.equals("!"))
                        rpn.append("-");
                    else
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
                            if (!operators.empty()){
                                top = operators.peek();
                            }
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

    public String getInsetString()
    {
        return inset_string;
    }
  
    public String getRPNString()
    {
        return rpn_string;
    }
}
