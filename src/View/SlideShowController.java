package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SlideShowController {

    @FXML
    private Button nextImage;

    @FXML
    private Button lastImage;
    
    private Stage currentStage;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
	}

    @FXML
    void handle(ActionEvent e) {

    }

}
