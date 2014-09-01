package edu.csci.hw1;

/*
Author: ZURAWSKJ, 9-1-2014
 This is the class that will handle converting inset to RPN.
*/


public class InsetToRPN 
{
  //CONSTRUCTOR
  //Receives a string in Inset Notation and converts to RPN
  InsetToRPN(String expr_in)
  {
    inset_string = expr_in;
  }
  
  //ANSWER
  //  PRE: The rpn_string data field contains a valid Inset Notation.
  //  POST: Converts inset_string to RPN and stores in rpn_string.
  void convert()
  {
    
  }
  
  //GET_INSET_STRING
  //  POST: Returns inset_string
  String getInsetString()
  {
    return inset_string;
  }
  
  //GET_RPN_STRING
  //POST: Returns RPN_string
  String getRPNString()
  {
    return rpn_string;
  }
  
  private String inset_string;
  private String rpn_string;

}
