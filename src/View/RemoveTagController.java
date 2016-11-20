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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
* RemoveTagController is the class associated with the screen for removing tags from a selected
* photo.
* @author  Tom Raybould & Mike Tomkowich
*/
public class RemoveTagController {

	  @FXML
	  private Button removeTags;

	  @FXML
	  private ListView<String> list;
    
    private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedTag;
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
		list
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedTag = list.getSelectionModel().getSelectedItem();});
		
	}
    /**
     * Updates data associated with screen
     * 
     * @return void
     */
    public void update(){
		obslist = FXCollections.observableArrayList();
		Photo p = Photo.getCurrentPhoto();
		for(Tag t : p.getTags()){
			String type = t.getType();
			String val = t.getValue();
			String addThis = type + "-" + val;
			obslist.add(addThis);
		}
		
		list.setItems(obslist);
	}
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    @FXML
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == removeTags){
    		System.out.println("remove Tag");
    		String type = selectedTag.substring(0, selectedTag.indexOf("-"));
    		String value = selectedTag.substring(selectedTag.indexOf("-") + 1, selectedTag.length());
    		System.out.println("type -" + type + "- ");
    		System.out.println("value -" + value + "- ");
    		
    		Tag target = Photo.getCurrentPhoto().searchTags(type, value);
    
    		Photo.getCurrentPhoto().removeTag(target);
    
    		currentStage.close();
    		
    	}
    }

}