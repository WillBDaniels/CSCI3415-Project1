package edu.csci.hw1;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * This is the main class that handles all of the interactions with the GUI. 
 * This sparks off the various methods such as validation, evaluating the expression, 
 * etc. etc. This class is strictly responsible for handling all of the interactions
 * with the keyboard that force the user to only type accepted characters, as well as 
 * only being able to type a valid expression. 
 * 08-2014
 *
 * @author William
 */
public class Primary_Controller {
    private boolean shiftDown = false;
    private boolean controlDown = false;
    
    private final Validation valid = new Validation();
    
    //This is the main window in the actual program
    @FXML
    private VBox vb_main_window;
    
    //These are the two text fields that we'll be using in the project to hold the
    //expression as well as the result. 
    @FXML
    private TextField tf_expression, tf_result;
    

    
    /**
     * This is the main entry point into the calculator program . it initializes
     * all of our listeners that grab things like keyboard input, etc. etc. It also
     * sets some of the tab order, and makes sure that the text fields themselves 
     * never come under focus, since there is a weird consume() bug where I was having
     * a hard time consuming events when the text fields had focus. 
     */
    @FXML
    private void initialize() {
        Tooltip tip = new Tooltip();
        final String tipText = "In this window you can either just start typing any "
                + "valid mathematical expression, it will dynamically check your input, "
                + "or you can use the buttons below to add input. At any time, if you "
                + "want to copy this input, simply press 'ctrl + c' to copy the expression"
                + " to the clipboard. ";
        
        tip.setText(tipText);
        tf_expression.setTooltip(tip);
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        
        
        //force the focus onto our main window. 
        vb_main_window.requestFocus();
        
        //make it so that both of the text fields are un-editable, and can't obtain
        //focus. 
        tf_expression.setEditable(false);
        tf_expression.setFocusTraversable(false);
        tf_result.setFocusTraversable(false);
        tf_result.setEditable(false);
        
        //These listeners make it so that if somehow the user manages to give the 
        //text fields focus, it automatically shifts it back to the main window. 
        if (!tf_expression.focusedProperty().get()){
            vb_main_window.requestFocus();
        }
        
        /*
        This listener makes it so the expression window can't obtain focus since
        it was giving me some trouble with the keystroke listeners ( I couldn't block
        them properly). This does make it so you can't copy paste directly, but if you
        use ctrl + c you can copy/paste the window without trouble. 
        */
        tf_expression.focusedProperty().addListener((ChangeListener)->{
            if (tf_expression.focusedProperty().get()){
                vb_main_window.requestFocus();
            }
        });
        
        /*
        This listener makes it so that the result can't have focus, really, we just
        don't ever want that window to have focus for any reason. This makes it so you 
        can't copy/paste, but that's not a huge deal. 
        */
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
           if (e.getCode().equals(KeyCode.CONTROL)){
               controlDown = false;
           }
        });
        
        vb_main_window.setOnKeyPressed((KeyEvent e)->{

        });
        
        //This is the primary listener that kicks off the keyboard-level validation. 
        //Everything inside of this method should force the user to input nothing
        //but valid mathematical expressions. 
        vb_main_window.setOnKeyPressed((KeyEvent e)->{
            if (e.getCode() == KeyCode.BACK_SPACE){
                if (!tf_expression.getText().isEmpty()){
                    tf_expression.setText(tf_expression.getText().substring(0, tf_expression.getText().length() -1));
                }
            }else if (e.getCode() == KeyCode.ENTER){
                equal_pressed();
            }else{
                if (controlDown){
                    if (e.getCode() == KeyCode.C){
                        content.putString(tf_expression.getText());
                        clipboard.setContent(content);
                    }
                }
                controlDown = e.isControlDown();
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
                    }else if (e.getCode().equals(KeyCode.DIGIT6)){
                        temp = KeyCode.CIRCUMFLEX;
                        output = "^";
                    }else if (e.getCode().equals(KeyCode.DIGIT8)){
                        temp = KeyCode.ASTERISK;
                        output = "*";
                    }else{
                        temp = e.getCode();
                        output = e.getText();
                    }
                }else{
                    temp = e.getCode();
                    output = e.getText();
                }
                shiftDown = e.isShiftDown();
                if (valid.validateKeyStroke(temp) && valid.validateOrder(tf_expression.getText(), output)){
                    tf_expression.setText(tf_expression.getText() + output);
                }

                if (!valid.parenMatcher(tf_expression.getText())){
                    tf_expression.getStyleClass().removeAll("good", "bad");
                    tf_expression.getStyleClass().addAll("bad");
                }else{
                    tf_expression.getStyleClass().removeAll("good", "bad");
                    tf_expression.getStyleClass().addAll("good");
                }
            }
        });
    }

    /**
     * 
     * This method simply clears out both of the text fields. 
     */
    @FXML
    private void clear_all() {
        tf_result.setText("");
        tf_expression.setText("");
    }

    
    /**
     * This method only clears out the expression window in case someone wants to 
     * remember the answer from last time. 
     */
    @FXML
    private void clear_one() {
        tf_expression.setText("");
    }

    /**
     * This method kicks off the evaluation of the actual expression. If the user 
     * has typed an invalid number of parenthesis, then the calculation will not proceed. 
     */
    @FXML
    private void equal_pressed() {
      if (!tf_expression.getStyleClass().contains("bad")){
          Process_Expression expression = new Process_Expression();
          double outputValue = expression.calculate_result(tf_expression.getText());
          if (outputValue == 9999999999.0){
              tf_result.setText("Not a Number (NaN)");
          }else if (outputValue == -9999999999.0){
              tf_result.setText("Infinity");
          }else{
            tf_result.setText(String.valueOf(outputValue));
          }
      }
    }

    /**
     * This method adds a left parenthesis to the screen when the corresponding button
     * is pressed. if the user has entered more left than right parenthesis, the window
     * turns red. 
     */
    @FXML
    private void left_paren_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "(")){
            tf_expression.setText(tf_expression.getText() + "(");
        }
        if (!valid.parenMatcher(tf_expression.getText())){
            tf_expression.getStyleClass().removeAll("good", "bad");
            tf_expression.getStyleClass().addAll("bad");
        }else{
            tf_expression.getStyleClass().removeAll("good", "bad");
            tf_expression.getStyleClass().addAll("good");
        }
    }

    @FXML
    private void right_paren_pressed() {
        if (valid.validateOrder(tf_expression.getText(), ")")){
            tf_expression.setText(tf_expression.getText() + ")");
        }
        if (!valid.parenMatcher(tf_expression.getText())){
            tf_expression.getStyleClass().removeAll("good", "bad");
            tf_expression.getStyleClass().addAll("bad");
        }else{
            tf_expression.getStyleClass().removeAll("good", "bad");
            tf_expression.getStyleClass().addAll("good");
        }

    }

    @FXML
    private void plus_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "+")){
            tf_expression.setText(tf_expression.getText() + "+");
        }

    }

    @FXML
    private void one_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "1")){
            tf_expression.setText(tf_expression.getText() + "1");
        }
    }

    @FXML
    private void two_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "2")){
            tf_expression.setText(tf_expression.getText() + "2");
        }
    }

    @FXML
    private void three_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "3")){
            tf_expression.setText(tf_expression.getText() + "3");
        }
    }

    @FXML
    private void minus_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "-")){
            tf_expression.setText(tf_expression.getText() + "-");
        }
    }

    @FXML
    private void four_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "4")){
            tf_expression.setText(tf_expression.getText() + "4");
        }
    }

    @FXML
    private void five_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "5")){
            tf_expression.setText(tf_expression.getText() + "5");
        }
    }

    @FXML
    private void six_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "6")){
            tf_expression.setText(tf_expression.getText() + "6");
        }
    }

    @FXML
    private void slash_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "/")){
            tf_expression.setText(tf_expression.getText() + "/");
        }
    }

    @FXML
    private void seven_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "7")){
            tf_expression.setText(tf_expression.getText() + "7");
        }
    }

    @FXML
    private void eight_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "8")){
            tf_expression.setText(tf_expression.getText() + "8");
        }
    }

    @FXML
    private void nine_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "9")){
            tf_expression.setText(tf_expression.getText() + "9");
        }
    }

    @FXML
    private void star_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "*")){
            tf_expression.setText(tf_expression.getText() + "*");
        }
    }

    @FXML
    private void zero_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "0")){
            tf_expression.setText(tf_expression.getText() + "0");
        }
    }

    @FXML
    private void period_pressed() {
        if (valid.validateOrder(tf_expression.getText(), ".")){
            tf_expression.setText(tf_expression.getText() + ".");
        }
    }

    @FXML
    private void carat_pressed() {
        if (valid.validateOrder(tf_expression.getText(), "^")){
            tf_expression.setText(tf_expression.getText() + "^");
        }
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
            tf_result.setText("Result of batch files can be found at: " + fp.processBatchFiles());
        }
    }
    
    @FXML
    private void close_program(){
        Calculator.get_stage().close();
    }
    
    @FXML
    private void back_pressed(){
        if (tf_expression.getText().length() > 0){
            tf_expression.setText(tf_expression.getText().substring(0,tf_expression.getText().length()-1));
        }
    }

}
