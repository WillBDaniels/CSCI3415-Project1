package edu.csci.hw1;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * 08-2014
 *
 * @author William
 */
public class Primary_Controller {
    private boolean shiftDown = false;
    @FXML
    private VBox vb_main_window;
    
    @FXML
    private TextField tf_expression, tf_result;
    

    /**
     * This is the main entry point into the calculator program .
     */
    @FXML
    private void initialize() {
        vb_main_window.requestFocus();
        tf_expression.setEditable(false);
        tf_expression.setFocusTraversable(false);
        tf_result.setFocusTraversable(false);
        if (!tf_expression.focusedProperty().get()){
            vb_main_window.requestFocus();
        }
        tf_expression.focusedProperty().addListener((ChangeListener)->{
            if (tf_expression.focusedProperty().get()){
                vb_main_window.requestFocus();
            }
        });
        tf_result.focusedProperty().addListener((ChangeListener)->{
            if (tf_result.focusedProperty().get()){
                vb_main_window.requestFocus();
            }
        });
        /**
         * Adding some various listeners to the text boxes for valid user input and the
         * ability to simply start typing on the keyboard to enter valid input. 
         */
        vb_main_window.setOnKeyReleased((KeyEvent e)->{
           if (e.getCode().equals(KeyCode.SHIFT)){ 
                shiftDown = false;
           }
        });
        vb_main_window.setOnKeyPressed((KeyEvent e)->{
            if (e.getCode() == KeyCode.BACK_SPACE){
                if (!tf_expression.getText().isEmpty()){
                    tf_expression.setText(tf_expression.getText().substring(0, tf_expression.getText().length() -1));
                }
            }else{
                Validation valid = new Validation();
                KeyCode temp;
                String output;
                if (shiftDown){
                    if (e.getCode().equals(KeyCode.EQUALS)){
                        temp = KeyCode.PLUS;
                        output = "+";
                    }else if(e.getCode().equals(KeyCode.DIGIT9)){
                        temp = KeyCode.LEFT_PARENTHESIS;
                        output = "(";
                    }else if(e.getCode().equals(KeyCode.DIGIT0)){
                        temp = KeyCode.RIGHT_PARENTHESIS;
                        output = ")";
                    }else{
                        temp = e.getCode();
                        output = e.getText();
                    }
                }else{
                    temp = e.getCode();
                    output = e.getText();
                }
                shiftDown = e.isShiftDown();
                if (valid.validateKeyStroke(temp)){
                    tf_expression.setText(tf_expression.getText() + output);
                }
            }
        });
    }

    @FXML
    private void clear_all() {
        tf_expression.setText("");

    }

    @FXML
    private void clear_one() {
        tf_expression.setText("");
    }

    @FXML
    private void equal_pressed() {

    }

    @FXML
    private void left_paren_pressed() {
        tf_expression.setText(tf_expression.getText() + "(");

    }

    @FXML
    private void right_paren_pressed() {
        tf_expression.setText(tf_expression.getText() + ")");

    }

    @FXML
    private void plus_pressed() {
        tf_expression.setText(tf_expression.getText() + "+");

    }

    @FXML
    private void one_pressed() {
        tf_expression.setText(tf_expression.getText() + "1");

    }

    @FXML
    private void two_pressed() {
        tf_expression.setText(tf_expression.getText() + "2");

    }

    @FXML
    private void three_pressed() {
        tf_expression.setText(tf_expression.getText() + "3");

    }

    @FXML
    private void minus_pressed() {
        tf_expression.setText(tf_expression.getText() + "-");

    }

    @FXML
    private void four_pressed() {
        tf_expression.setText(tf_expression.getText() + "4");
    }

    @FXML
    private void five_pressed() {
        tf_expression.setText(tf_expression.getText() + "5");
    }

    @FXML
    private void six_pressed() {
        tf_expression.setText(tf_expression.getText() + "6");
    }

    @FXML
    private void slash_pressed() {
        tf_expression.setText(tf_expression.getText() + "/");

    }

    @FXML
    private void seven_pressed() {
        tf_expression.setText(tf_expression.getText() + "7");

    }

    @FXML
    private void eight_pressed() {
        tf_expression.setText(tf_expression.getText() + "8");

    }

    @FXML
    private void nine_pressed() {
        tf_expression.setText(tf_expression.getText() + "9");

    }

    @FXML
    private void star_pressed() {
        tf_expression.setText(tf_expression.getText() + "*");

    }

    @FXML
    private void zero_pressed() {
        tf_expression.setText(tf_expression.getText() + "0");

    }

    @FXML
    private void period_pressed() {
        tf_expression.setText(tf_expression.getText() + ".");

    }

    @FXML
    private void carat_pressed() {
        tf_expression.setText(tf_expression.getText() + "^");
    }
    
    @FXML
    private void load_help(){
        
    }
    @FXML
    private void load_expression(){
        File_Processor fp;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File temp = fileChooser.showOpenDialog(Calculator.get_stage());
        if (temp != null){
            fp = new File_Processor(temp);
        }
    }
    
    @FXML
    private void close_program(){
        Calculator.get_stage().close();
    }
    
    @FXML
    private void back_pressed(){
        tf_expression.setText(tf_expression.getText().substring(0,tf_expression.getText().length()-1));
    }

}
