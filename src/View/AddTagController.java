package View;

import java.io.IOException;
import java.util.ArrayList;

import Model.Album;
import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
* addTagController is the class associated with the screen for adding tags to a
* selected photo. Its main purpose is to add a new tag to an existing photo
* @author  Tom Raybould & Mike Tomkowich
*/

public class AddTagController {


    @FXML
    private ComboBox<String> tagDropDown;

    @FXML
    private TextField tagText;

    @FXML
    private Button addTag;

    @FXML
    private Button addTypeToList;

    @FXML
    private Text newTagType;

    @FXML
    private TextField newTagTypeText;

    @FXML
    private Text currentTagType;
    
    private ObservableList<String> myComboBoxData;
    
    private Stage currentStage;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
		System.out.println("Current user in Photo view " + User.getCurrentUser());
		System.out.println("Current Album in Photo view is: " + Album.getCurrentAlbum());
		System.out.println("Current Photo in add tag is: " + Photo.getCurrentPhoto());
		
			currentStage.show();
			update();
			/*
			 * make the combo box update from back end like in the userAlbumViewController 
			 */
	}
    private void update (){
    	myComboBoxData = FXCollections.observableArrayList();
    	//populates list from back end users tag types
    	for(String tagType :User.getCurrentUser().getTagTypes()){
    		myComboBoxData.add(tagType);
    	}
    	
    	tagDropDown.setItems(myComboBoxData);
    }
    
    public void handle(ActionEvent e) throws IOException{
    	Button b = (Button)e.getSource();
    	if(b == addTag){
    		System.out.println("add Tag");
    		String tagType = tagDropDown.getSelectionModel().getSelectedItem();
    		String tag = tagText.getText();
    		Photo p = Photo.getCurrentPhoto();
    		if (p == null){
    			return;
    		}
    		Tag t = new Tag(tagType, tag);
    		p.addTag(t);
    		ArrayList<Tag> photoTags = p.getTags();
    		for(Tag tag1: photoTags){
    			System.out.println(tag1);
    		}
    		currentStage.close();
    	}
    	else if(b == addTypeToList){
    		System.out.println("Create new tag type");
    		
    	}
    }

}