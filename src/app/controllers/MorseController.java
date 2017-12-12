package app.controllers;

import app.crypting.Morse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;

public class MorseController {

    @FXML private TextArea morseInputArea;
    @FXML private TextArea morseOutputArea;
    @FXML private Label morseMessageLabel;

    private File selectedFile;

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

    @FXML
    public void morseDecrypt(ActionEvent actionEvent) {
        morseMessageLabel.setText("");
        if ( !morseInputArea.getText().equals(null)) {
            morseOutputArea.setText(Morse.getInstance().morseDecryptString(morseInputArea.getText()));
        }
        if ( selectedFile != null ) {
            Morse.getInstance().morseDeCryptFile(selectedFile.getPath());
            morseMessageLabel.setText("Crypted file created at same location !");
        }
    }

    @FXML
    public void chooseAFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);
    }

}
