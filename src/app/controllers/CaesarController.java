package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class CaesarController {

    @FXML private Label lblText;

    @FXML
    public void vigenereEncrypt() {
        lblText.setText("Vous avez clické !");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            lblText.setText("File selected: " + selectedFile.getPath());
        }else {
            lblText.setText("File selection cancelled.");
        }
    }

}
