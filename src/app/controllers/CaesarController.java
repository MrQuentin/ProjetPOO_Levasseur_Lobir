package app.controllers;

import app.crypting.Caesar;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;
/**
 * This class allow us to call in the fxml graphic user interface
 * all the Caesar methods by using instances of the Caesar class
 */

public class CaesarController {
    @FXML private TextArea caesarInputArea;
    @FXML private TextArea caesarOutputArea;
    @FXML private TextField caesarKey;

    @FXML private Label caesarMessageLabel;
    private File selectedFile;

    /**
     * Encrypt a string or a file or both with the caesar algorithm, using a graphic user interface.
     * this method is calling the caesarCryptString and caesarCryptFile methods
     * this method is called in the cesarTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void caesarEncrypt() {
        caesarMessageLabel.setText("");

        if (!caesarKey.getText().equals("")){
            if (!caesarInputArea.getText().equals(null)){
                caesarOutputArea.setText(Caesar.getInstance().caesarCryptString(caesarInputArea.getText(),Integer.parseInt(caesarKey.getText())));
            }
            if (selectedFile != null){
                Caesar.getInstance().caesarCryptFile(selectedFile.getPath(),Integer.parseInt(caesarKey.getText()));
                caesarMessageLabel.setText("Crypted file created at same location !");
            }
        } else {
            caesarMessageLabel.setText("You must insert a key !!");
        }
    }
    /**
     * Decrypt a string or a file or both with the caesar algorithm, using a graphic user interface.
     * this method is calling the caesarDecryptString and caesarDecryptFile methods
     * this method is called in the cesarTabPage.fxml and print the result in the GUI
     */
    @FXML
    public void caesarDecrypt(){
        if ( !caesarKey.getText().equals("")){
            if (!caesarInputArea.getText().equals(null)){
                caesarOutputArea.setText(Caesar.getInstance().caesarDecryptString(caesarInputArea.getText(),Integer.parseInt(caesarKey.getText())));
            }
            if (selectedFile != null){
                Caesar.getInstance().caesarDecryptFile(selectedFile.getPath(),Integer.parseInt(caesarKey.getText()));
                caesarMessageLabel.setText("Decrypted file created at same location !");

            }

        } else {
            caesarMessageLabel.setText("You must insert a key !!");
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
