package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class PrimaryController {

    @FXML
    private Button primaryButton;
    @FXML
    private ImageView imagenTlf;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("patronContrasenia");
    }
}