package edu.csci.hw1;

import java.io.File;

/**
 * This class is for the processing of multiple lines of expressions that each
 * have a mathematical expression on them. 
 * 
 * 08-2014 
 * @author William
 */
public class File_Processor {
    private File inputFile;
    
    /**
     * This constructor initializes our batch file class with a String, corresponding
     * to a fully qualified String path name. 
     * 
     * @param inputFilePath the fully qualified path of the batch file that contains the 
     * mathematical expressions. 
     */
    public File_Processor(String inputFilePath){
        this(new File(inputFilePath));
    }
    
    /**
     * This constructor initializes our class given an actual File object which should
     * correspond to a set of mathematical expressions. 
     * 
     * @param inputFile A java.io.File object that contains a valid set of mathematical 
     * expressions, separated by line. 
     */
    public File_Processor(File inputFile){
        this.inputFile = inputFile;
    }
    
    /**
     * This is simply a private default constructor so that noone can use this
     * since we need the ability to create our own files. 
     */
    private File_Processor(){}
    
}
