package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RemoveTagController {

    @FXML
    private ComboBox<?> tagTypeDropDown;

    @FXML
    private Button removeSelected;

    @FXML
    private TextField listViewOfTags;
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == removeSelected){
    		System.out.println("Remove Selected");
    	}
    }
}
