package edu.csci.hw1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    
    
    /**
     * This method processes mathematical expressions in a 'batch file' mode, where
     * multiple lines of math expressions are read all at once and the output is saved
     * to a specific file. 
     * 
     * @return the full path location of the file that was written. 
     */
    public String processBatchFiles(){
        Validation valid = new Validation();
        Process_Expression pex = new Process_Expression();
        String outputFileLocation;
        File outputFile = null;
        FileReader fis = null; 
        FileWriter fos = null; 
        try {
            fis = new FileReader(inputFile);
            if (inputFile.exists()){
                outputFileLocation = inputFile.getAbsolutePath();
                outputFile = new File(outputFileLocation.substring(0, outputFileLocation.lastIndexOf(".")) + "outputFile.txt");
            }
            try (BufferedReader br = new BufferedReader(fis)) {
                fos = new FileWriter(outputFile);
                try (BufferedWriter bw = new BufferedWriter(fos)) {
                    String item, thingToWrite;
                    double output;
                    int i = 1;
                    while((item = br.readLine()) != null){
                        if (valid.validateEntireString(item)){
                            output = pex.calculate_result(item);
                            if (output == 9999999999.0){
                                thingToWrite = "Not a Number(NaN)";
                            }else if (output == -9999999999.0){
                                thingToWrite = "Infinity";
                            }else{
                                thingToWrite = String.valueOf(output);
                            }
                            bw.write("Result of line " + i + " :" +  thingToWrite + "\n");
                        }else{
                            bw.write("Invalid mathematical expression on line " + i + "\n");
                        }
                        i++;
                    }
                }
            }
            
            
        } catch (FileNotFoundException ex) {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException ex1) {
                    ex1.printStackTrace(System.err);
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException ex1) {
                    ex1.printStackTrace(System.err);
                }
            }
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        
        if (outputFile != null){
            return outputFile.getAbsolutePath();
        }
        return "";
    }
}
