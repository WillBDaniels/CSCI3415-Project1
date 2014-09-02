package edu.csci.hw1;

import java.util.Stack;

/*
Author: ZURAWSKJ, 9-1-2014
 This is the class that will handle solving an RPN expression.
*/


public class RPN_Eval 
{
   //Data
   private String rpn_string;
 
  //CONSTRUCTOR
  //Receives a string in RPN notation, A B * C * A,B,C = operands, * = operators
  RPN_Eval(String expr_in)
  {
    rpn_string = expr_in;
  }
  
  //ANSWER, Requires a stack of doubles
  //  PRE: The rpn_string data field contains a valid RPN expression.
  //  POST: Returns a single solution to the RPN expression.
  double answer()
  {
    //Temporary string to hold each number before pushing onto stack as a double
    String temp = "";
    
    //Stack of doubles to hold values before evaluation
    Stack<Double> s1 = new Stack<>();
    
    //Check each character in string temp
    for(int i = 0; i < rpn_string.length(); i++)
    {
     //If a number or decimal point
     if(Character.isDigit(rpn_string.charAt(i)) || rpn_string.charAt(i) == '.')
     {
      //Add on to temp
      temp += rpn_string.charAt(i);
     } 
     
     //Else if a space
     else if(rpn_string.charAt(i) == ' ')
     {
      //If temp is not empty
      if(temp != "")
      {
       //Convert temp to double and push onto stack
       s1.push(Double.parseDouble(temp));
        
       //empty temp
       temp = "";
      }
     }
      
     //Else if +
     else if(rpn_string.charAt(i) == '+')
     {
      //Pop two operands off stack
      //Evaluate
      //Push value on stack s1
     }
     
     //Else if -
     else if(rpn_string.charAt(i) == '-')
     {
      //Pop two operands off stack
      //Evaluate
      //Push value on stack s1
     }
     
     //Else if *
     else if(rpn_string.charAt(i) == '*')
     {
      //Pop two operands off stack
      //Evaluate
      //Push value on stack s1
     }
     
     //Else if /
     else if(rpn_string.charAt(i) == '/')
     {
      //Pop two operands off stack
      //Evaluate
      //Push value on stack s1
     }
     
     //Else if ^
     else if(rpn_string.charAt(i) == '^')
     {
      //Pop two operands off stack
      //Evaluate
      //Push value on stack s1
     }
     
     //Else
     else
     {
      //Error?
     }
    }
    
    //Return the answer
    //If stack has one value
    if(s1.size() == 1)
    {
     //Return s1.top()
     return s1.peek();
    }
    
    //Else
    else
    {
     //Error: Return Not a number or zero
     return 0;
    }
  }

}
