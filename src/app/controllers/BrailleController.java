package app.controllers;

import app.crypting.Braille;
import javafx.fxml.FXML;

import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;
import javafx.event.ActionEvent;
import java.io.File;
/**
 * This class allow us to call in the fxml graphic user interface
 * all the Braille methods by using instances of the Braille class
 */
public class BrailleController {
    @FXML private TextArea brailleInputArea;
    @FXML private TextArea brailleOutputArea;
    @FXML private Label brailleMessageLabel;

    private File selectedFile;

    /**
     * Encrypt a string or a file or both with the braille code, using a graphic user interface.
     * this method is calling the brailleCryptingString and brailleCryptFile methods
     * this method is called in the brailleTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void brailleEncrypt(ActionEvent actionEvent){
        brailleMessageLabel.setText("");
        if (!brailleInputArea.getText().equals(null)){
            brailleOutputArea.setText(Braille.getInstance().brailleCryptingString(brailleInputArea.getText()));
        }
        if (selectedFile != null){
            Braille.getInstance().brailleCryptFile(selectedFile.getPath());
            brailleMessageLabel.setText("Crypted file created at same location !");
        }
    }
    /**
     * Encrypt a string or a file or both with the braille code, using a graphic user interface.
     * this method is calling the brailleCryptingString and brailleCryptFile methods
     * this method is called in the brailleTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void brailleDecrypt(ActionEvent actionEvent){
        brailleMessageLabel.setText("");
        if (!brailleInputArea.getText().equals(null)){
            brailleOutputArea.setText(Braille.getInstance().brailleDecryptString(brailleInputArea.getText()));
        }
        if (selectedFile != null){
            Braille.brailleDecryptFile(selectedFile.getPath());
            brailleMessageLabel.setText("Decrypted file created at same location !");

        }
    }
    /**
     * Allow the user to choose a text file in his storage through the GUI
     */
    @FXML
    public void chooseAFile(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files","*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
    }

}
