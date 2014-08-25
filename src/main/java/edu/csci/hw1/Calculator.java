package edu.csci.hw1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * 08-2014 
 * @author William
 */
public class Calculator extends Application {
    private static Stage stage;
    
    
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
        Calculator.stage = stage;
        stage.show();

      
    }
    
    
    public static Stage get_stage(){
        return stage;
    }
}

