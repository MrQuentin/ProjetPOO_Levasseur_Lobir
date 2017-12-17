package app.controllers;

import app.crypting.Morse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
/**
 * This class allow us to call in the fxml graphic user interface
 * all the Morse methods by using instances of the Morse class
 */
public class MorseController {

    @FXML private TextArea morseInputArea;
    @FXML private TextArea morseOutputArea;
    @FXML private Label morseMessageLabel;

    private File selectedFile;
    /**
     * Encrypt a string or a file or both with the morse code, using a graphic user interface.
     * this method is calling the morseCrypString and morseCryptFile methods
     * this method is called in the morseTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void morseEncrypt(ActionEvent actionEvent) {
        morseMessageLabel.setText("");
        if ( !morseInputArea.getText().equals(null)) {
            morseOutputArea.setText(Morse.getInstance().morseCrypString(morseInputArea.getText()));
        }
        if ( selectedFile != null ) {
            Morse.getInstance().morseCryptFile(selectedFile.getPath());
            morseMessageLabel.setText("Crypted file created at same location !");
        }
    }
    /**
     * Decrypt a string or a file or both with the morse code, using a graphic user interface.
     * this method is calling the morseDecryptString and morseDeCryptFile methods
     * this method is called in the morseTabPage.fxml and print the result in the GUI
     */

    @FXML
    public void morseDecrypt(ActionEvent actionEvent) {
        morseMessageLabel.setText("");
        if ( !morseInputArea.getText().equals(null)) {
            morseOutputArea.setText(Morse.getInstance().morseDecryptString(morseInputArea.getText()));
        }
        if ( selectedFile != null ) {
            Morse.getInstance().morseDeCryptFile(selectedFile.getPath());
            morseMessageLabel.setText("Decrypted file created at same location !");
        }
    }
    /**
     * Allow the user to choose a text file in his storage through the GUI
     */

    @FXML
    public void chooseAFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
    }

}
