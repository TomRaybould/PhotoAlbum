package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import Model.Album;
import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
* EditTagTypeController is the class associated with the screen for editing tag types that exist
* in a User's instance. This will allow the User to add and delete tag types to and from their
* own personal existing array of tag types
* @author  Tom Raybould & Mike Tomkowich
*/
public class EditTagTypeController {

    @FXML
    private Button removeType;

    @FXML
    private Button addType;
    
    @FXML
    private Button saveAndExit;
    
    @FXML
    private TextField addTypeField;

    @FXML
    private ListView<String> listView;
    
    private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedTagType;
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
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
    /**
     * Updates data associated with screen
     * 
     * @return void
     */
    public void update(){
		obslist = FXCollections.observableArrayList();
		User u = User.getCurrentUser();
		ArrayList<String> types = u.getTagTypes();
		for(String type : types){
			obslist.add(type);
		}
		
		listView.setItems(obslist);
	}
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     * @throws IOException 
     */
    @FXML
    void handle(ActionEvent e) throws IOException {
    	Button b = (Button)e.getSource();
    	if(b == removeType){
    		if(!makeAlertConfirm("Deleting Tag", "", "Are you sure you want to delete this tag type?")){
    			return;
    		}
    		String selected = selectedTagType;
    		User u = User.getCurrentUser();
    		u.getTagTypes().remove(selected);
    		
    		//take out all the tags of that type
    		Predicate<Tag> pre= (i) -> i.getType().equalsIgnoreCase(selectedTagType);
    		
    		for(Album a:u.getAlbumList()){
    			for(Photo p: a.getPhotosInAlbum()){
    				p.getTags().removeIf(pre);
    			}
    		}
    		
    		
    		
    		this.update();
    	}
    	else if(b == addType){
    		String addThis = addTypeField.getText();
    		if(addThis == null){
    			return;
    		}
    		if(addThis.equals("")){
    			return;
    		}
    		else{
    			for(String type : User.getCurrentUser().getTagTypes()){
    				if(addThis.equalsIgnoreCase(type)){
    					return;
    				}
    			}
    			User u = User.getCurrentUser();
        		ArrayList<String> types = u.getTagTypes();
        		types.add(addThis);
        		System.out.println(types);
        		User.getCurrentUser().setTagTypes(types);
        		this.update();
    		}
    	}
    	else if(b == saveAndExit){
    		this.update();
    		User.write();
    		currentStage.close();
    	}

    }
    /**
     * 
     * @param String alertTitle
     * @param String alertHeader
     * @param String alertContent
     * 
     * Takes in three strings and produces a simply alert for the user to see,
     * return true only if the user presses ok, otherwise returns false
     * 
     * @return boolean true if the user pressed ok
     */
    private boolean makeAlertConfirm(String alertTitle, String alertHeader, String alertContent){
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(alertTitle);
    	alert.setHeaderText(alertHeader);
    	alert.setContentText(alertContent);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    		if(result.get()==ButtonType.OK){
    			return true;
    		}
    		if(result.get()==ButtonType.CANCEL){
    			return false;
    		}
    	return false;
    }
}
