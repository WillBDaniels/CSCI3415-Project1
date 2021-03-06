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
        
        
        //double to hold answer
        double answer = 0;
        
        try {
            //Convert result to RPN
            InsetToRPN converter = new InsetToRPN();
            converter.inset(inputExpression);
            converter.convert();
            String rpn_out = converter.getRPNString();
            
            //For Debugging
            System.out.println("Inset: " + converter.getInsetString());
            System.out.println("RPN: " + converter.getRPNString());
        
            //Create new Reverse Polish Notation Evaluator
            RPN_Eval eval = new RPN_Eval(rpn_out);
            
            //Evaluate RPN Function
            answer = eval.answer();
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return answer;
    }
}
