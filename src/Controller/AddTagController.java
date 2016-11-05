package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddTagController {

    @FXML
    private ComboBox<?> tagDropDown;

    @FXML
    private TextField tagText;

    @FXML
    private Button addTag;

    @FXML
    private Button createNewTagType;
    
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == addTag){
    		System.out.println("add Tag");
    	}
    	else if(b == createNewTagType){
    		System.out.println("Create new tag type");
    		
    	}
    }

}