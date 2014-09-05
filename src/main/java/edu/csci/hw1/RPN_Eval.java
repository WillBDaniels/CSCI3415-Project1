package edu.csci.hw1;

import java.util.Stack;
import static java.lang.Math.pow;

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
    
    //Stack of doubles to hold values before evaluations
    Stack<Double> s1 = new Stack<>();
    
    //Check each character in string temp
    for(int i = 0; i < rpn_string.length(); i++)
    {
     //If a number or decimal point
     //Negative sign is checked for in operator- block
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
      //Data to hold operands
      Double x = 0.0,y = 0.0,sum = 0.0;
      //Put operands into variables and pop two operands off stack
      y = s1.peek();
      s1.pop();
      x = s1.peek();
      s1.pop();
      
      //Evaluate
      sum = x + y;
      
      //Push value on stack s1
      s1.push(sum);
     }
     
     //Else if -
     else if(rpn_string.charAt(i) == '-')
     {
      //If next char is digit or decimal point, add on to temp
      //Next char not being a space means it can only be a number or decimal point (If proper format)
      if((i < rpn_string.length() - 1) && (rpn_string.charAt(i+1) != ' '))
      {
       temp += "-";
      }
      
      //Data to hold operands
      Double x = 0.0,y = 0.0,diff = 0.0;
      //Put operands into variables and pop two operands off stack
      y = s1.peek();
      s1.pop();
      x = s1.peek();
      s1.pop();
      
      //Evaluate
      diff = x - y;
      
      //Push value on stack s1
      s1.push(diff);
     }
     
     //Else if *
     else if(rpn_string.charAt(i) == '*')
     {
      //Data to hold operands
      Double x = 0.0,y = 0.0,prod = 0.0;
      //Put operands into variables and pop two operands off stack
      y = s1.peek();
      s1.pop();
      x = s1.peek();
      s1.pop();
      
      //Evaluate
      prod = x * y;
      
      //Push value on stack s1
      s1.push(prod);
     }
     
     //Else if /
     else if(rpn_string.charAt(i) == '/')
     {
      //Data to hold operands
      Double x = 0.0,y = 0.0,quo = 0.0;
      //Put operands into variables and pop two operands off stack
      y = s1.peek();
      s1.pop();
      x = s1.peek();
      s1.pop();
      
      //Evaluate
      quo = x / y;
      
      //Push value on stack s1
      s1.push(quo);
     }
     
     //Else if ^
     else if(rpn_string.charAt(i) == '^')
     {
      //Data to hold operands
      Double x = 0.0,y = 0.0,pow = 0.0;
      //Put operands into variables and pop two operands off stack
      y = s1.peek();
      s1.pop();
      x = s1.peek();
      s1.pop();
      
      //Evaluate
      pow = pow(x, y);
      
      //Push value on stack s1
      s1.push(pow);
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
