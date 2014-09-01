package edu.csci.hw1;

/**
 *
 * 08-2014 
 * @author William
 */
public class Process_Expression {
    
    
    /**
     * This method will be the primary entry point that the GUI uses to handle the 
     * actual calculation from the expression typed into the calculator. This method
     * should return a double value that corresponds to the value represented by 
     * the expression that was typed into the main field. A NaN should be represented
     * by the double value 9999999999 and infinity should be represented by -9999999999
     * 
     * @param inputExpression a String that corresponds to a valid mathematical expression
     * @return The double valued result of parsing and interpreting the expression. 999999999 will be
     * returned if the expression evaluates to NaN (not a number) and -9999999999 for infinity. 
     */
    public double calculate_result(String inputExpression){
        CalculatorScanner scanner = new CalculatorScanner(inputExpression);
        try {
            //Just a sample result of how to use it. 
            String result = scanner.readToken();
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        
        //Added by Joe, 8/30/2014 10:04 P.M.---------------
        //Evaluating input---------------------------------
        //Create Stack for rpn
        stack_class rpn_stack = new stack_class;
        
        //Create stack for operators
        stack_class ops_stack = new stack_class;
        
        //Integer to iterate over entire string
        int index = 0;
        String temp;
    
        //Convert String (in-set notation) to Reverse Polish Notation and pushes onto stack
        //Also uses a second stack for shunting-yard algorithm.
        //isn_to_rpn(result, s1);
        while(index < result.length());
        {
            
            //Handle each operator precedence
            if(result.charAt(index) == ' ')
            {
                //do nothing
            }
            else if(Character.isDigit(result.charAt(index)) || result.charAt(index) == '.')
            {
                //Add digit to temporary string
                temp += result.charAt(index);
                
                //Check next index for digit or .
                while(Character.isDigit(result.charAt(index + 1)) || result.charAt(index + 1) == '.')
                {
                    //Add digits and decimal point until a non digit is found
                    temp += result.charAt(index + 1);
                    
                    //Increment index
                    index++;
                }
                
                //temp is now an entire number (double or int)
                //push onto rpn stack
                rpn_stack.push(temp);
            }
            else if(result.charAt(index) == '+' || result.charAt(index) == '-')
            {
                //If no other operation on ops_stack, push
                if(ops_stack.used() != 0)
                {
                    if(result.charAt(index) == '+')
                        ops_stack.push("+");
                    else
                        ops_stack.push("-");
                }
                    
                //Otherwise, check for other ops
                //Since + is lowest precedence and left-associated, all other ops
                //  on stack go first except (
                else
                {
                    //While operators are on stack and the top is not left paren
                    while(ops_stack.used() != 0 && ops_stack.top() != "(")
                    {
                        //add it to rpn stack and remove from operator stack
                        rpn_stack.push(ops_stack.top();
                        ops_stack.pop();
                    }
                }
                
                if(result.charAt(index) == '+')
                    ops_stack.push("+");
                else
                    ops_stack.push("-");
            }
            else if(result.charAt(index) == '*' || result.charAt(index) == '/')
            {
                //Other operators will be similar to +-
            }
            else if(result.charAt(index) == '^')
            {
                
            }
            else if(result.charAt(index) == '(')
            {
                
            }
            
            index++;
        }
        
        //Pop remaining off operator stack
        
        //Evaluate
        evaluate(s1);
        
        //Solution remains on stack
        //-------------------------------------------------
        
        //This is simply to display something temporarily until all the calculations are hooked up. 
        double result = 9999999999.0;
        
        return result;
    }
}
