package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RemoveTagController {

    @FXML
    private ComboBox<?> tagTypeDD;

    @FXML
    private Button removeTags;

    @FXML
    private TextField lVOfTags;

    @FXML
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == removeTags){
    		System.out.println("remove Tag");
    	}
    }

}