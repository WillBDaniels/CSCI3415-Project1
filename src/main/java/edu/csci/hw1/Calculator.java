package edu.csci.hw1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This is the main class that starts the application through the javafx framework
 * The main method is used by gradle to launch the applicaiton properly, whereas 
 * the actual 'launch' method is used to spark off all of the pertinent items. 
 * 
 * 08-2014 
 * @author William
 */
public class Calculator extends Application {
    private static Stage stage;
    
    
    /**
     * This is simply a wrapper around the launch method that allows us to properly 
     * start the application in gradle. 
     * 
     * @param args any commnad-line arguments being passed to the program. 
     */
    public static void main(String[] args) {
        Application.launch(Calculator.class, args);
    }
    
    /**
     * Sets the stage for GUI(JavaFX). Style sheet is added in this method. This kicks
     * off the primary FXML link and allows us to have the css added properly as well. 
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(final Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("fxml/primary_window.fxml"));
        root.getStylesheets().add(getClass().getResource("css/primary.css").toExternalForm());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Java Calculator");
        stage.resizableProperty().not();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icon256.png")));
        Calculator.stage = stage;
        stage.show();

      
    }
    
    /**
     * This method returns the static stage object that is created and persisted 
     * throughout the project for as long as the application is open.  
     * 
     * @return our static Stage object that will be used throughout the project persistently. 
     */
    public static Stage get_stage(){
        return stage;
    }
}

