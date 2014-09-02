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
        
        //double to hold answer
        double answer = 0;
        
        try {
            //result is a valid in-set notation expression
            String result = scanner.readToken();
            
            //Convert result to RPN
            InsetToRPN converter = new InsetToRPN(result);
            converter.convert();
            String rpn_out = converter.getRPNString();
        
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
