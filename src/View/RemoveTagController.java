package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RemoveTagController {

    @FXML
    private ComboBox<?> tagTypeDD;

    @FXML
    private Button removeTags;

    @FXML
    private TextField lVOfTags;
    
    private Stage currentStage;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
	}

    @FXML
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == removeTags){
    		System.out.println("remove Tag");
    	}
    }

}