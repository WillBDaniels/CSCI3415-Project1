package edu.csci.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is for displaying the read-me text file. This class guarantees that there
 * will be multi-platform support since it's entirely in native javafx. which is cool.
 * 
 * @author Wdaniels
 */
public class ReadmeWindow {
    public void show_readme(Stage containerStage){
        final VBox pane = new VBox();
        final Stage stage = new Stage();
        final TextArea ta = new TextArea();
        String inputText = "";
        String temp;
        containerStage.setOnCloseRequest((CloseEvent)->{
            stage.close();
        });
        try{
            try (InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("static_files/readme.txt"));
                    BufferedReader br = new BufferedReader(isr)) {
                while ((temp = br.readLine()) != null){
                    inputText += temp;
                }
            }
        }catch(IOException e){
            e.printStackTrace(System.err);
        }
        ta.setText(inputText);
        ta.setWrapText(true);
        pane.getChildren().addAll(ta);
        Scene scene = new Scene(pane);
        ta.setPrefHeight(containerStage.getHeight());
        ta.setPrefWidth(containerStage.getWidth());
        stage.setWidth(containerStage.getWidth());
        stage.setHeight(containerStage.getHeight());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icon256.png")));
        stage.setScene(scene);
        stage.show();
    }
}
