package app.controllers;

import app.crypting.Vigenere;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
/**
 * This class allow us to call in the fxml graphic user interface
 * all the Vigenere methods by using instances of the Vigenere class
 */
public class VigenereController {

    @FXML private TextArea vigenereInputArea;
    @FXML private TextArea vigenereOutputArea;
    @FXML private TextField vigenereKey;
    @FXML private Label vigenereMessageLabel;

    private File selectedFile;


    /**
     * Encrypt a string or a file or both with the algorithm of vigenere, using a graphic user interface.
     * this method is calling the vigenereCryptString and vigenereCryptFile methods
     * this method is called in the veigenereTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void vigenereEncrypt() {
        vigenereMessageLabel.setText("");
        if ( !vigenereKey.getText().equals("") ){
            if ( !vigenereInputArea.getText().equals(null)) {
                vigenereOutputArea.setText(Vigenere.getInstance().vigenereCryptString(vigenereInputArea.getText(), vigenereKey.getText()));
            }
            if ( selectedFile != null ) {
                Vigenere.getInstance().vigenereCryptFile(selectedFile.getPath(), vigenereKey.getText());
                vigenereMessageLabel.setText("Crypted file created at same location !");
            }
        } else {
            vigenereMessageLabel.setText("You must insert a Key!");
        }
    }
    /**
     * Decrypt a string or a file or both with the algorithm of vigenere, using a graphic user interface.
     * this method is calling the vigenereDecryptString and vigenereDeCryptFile methods
     * this method is called in the veigenereTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void vigenereDecrypt() {

        if ( !vigenereKey.getText().equals("") ){
            if ( !vigenereInputArea.getText().equals(null)) {
                vigenereOutputArea.setText(Vigenere.getInstance().vigenereDecryptString(vigenereInputArea.getText(), vigenereKey.getText()));
            }
            if ( selectedFile != null ) {
                Vigenere.getInstance().vigenereDeCryptFile(selectedFile.getPath(), vigenereKey.getText());
                vigenereMessageLabel.setText("Derypted file created at same location !");
            }
        } else {
            vigenereMessageLabel.setText("You must insert a Key!");
        }
    }

    /**
     * Allow the user to choose a text file in his storage through the GUI
     */

    @FXML
    public void chooseAFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
    }

}
