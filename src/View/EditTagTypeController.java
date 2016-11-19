package View;

import java.util.ArrayList;

import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTagTypeController {

    @FXML
    private Button removeType;

    @FXML
    private Button addType;
    
    @FXML
    private TextField addTypeField;

    @FXML
    private ListView<String> listView;
    
    private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedTagType;
    
    public void start(Stage mainStage){
    	this.update();
		currentStage = mainStage;
		System.out.println("poopfart");
		currentStage.show();
		listView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedTagType = listView.getSelectionModel().getSelectedItem();});
		}
    
    public void update(){
		obslist = FXCollections.observableArrayList();
		User u = User.getCurrentUser();
		ArrayList<String> types = u.getTagTypes();
		for(String type : types){
			obslist.add(type);
		}
		
		listView.setItems(obslist);
	}
    @FXML
    void handle(ActionEvent e) {
    	Button b = (Button)e.getSource();
    	if(b == removeType){
    		String selected = selectedTagType;
    		User u = User.getCurrentUser();
    		ArrayList<String> types = u.getTagTypes();
    		for(String s: types){
    			if(s.equals(selected)){
    				types.remove(s);
    				break;
    			}
    		}
    		User.getCurrentUser().setTagTypes(types);
    		this.update();
    	}
    	else if(b == addType){
    		String addThis = addTypeField.getText();
    		if(addThis == null){
    			return;
    		}
    		else{
    			User u = User.getCurrentUser();
        		ArrayList<String> types = u.getTagTypes();
        		types.add(addThis);
        		User.getCurrentUser().setTagTypes(types);
        		this.update();
        		
    		}
    	}

    }

}
