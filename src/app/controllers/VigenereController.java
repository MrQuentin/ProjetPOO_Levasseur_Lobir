package app.controllers;

import app.crypting.Vigenere;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class VigenereController {

    @FXML private TextArea vigenereInputArea;
    @FXML private TextArea vigenereOutputArea;
    @FXML private TextField vigenereKey;
    @FXML private TextField vigenereSourceFile;
    @FXML private Label vigenereMessageLabel;

    private File selectedFile;

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

    @FXML
    public void chooseAFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
    }

}
