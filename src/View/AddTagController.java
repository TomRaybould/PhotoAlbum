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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
      
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
    public void start(Stage mainStage){
		currentStage = mainStage;
		System.out.println("Current user in Photo view " + User.getCurrentUser());
		System.out.println("Current Album in Photo view is: " + Album.getCurrentAlbum());
		System.out.println("Current Photo in add tag is: " + Photo.getCurrentPhoto());
			
		tagDropDown.getSelectionModel().select(0);
			tagDropDown.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
    			public void handle(MouseEvent m){
    				update();
    			}
    		});
			
			currentStage.show();
			update();
			/*
			 * make the combo box update from back end like in the userAlbumViewController 
			 */
	}
    /**
     * Updates data associated with screen
     * 
     * @return void
     */
    private void update (){
    	myComboBoxData = FXCollections.observableArrayList();
    	//populates list from back end users tag types
    	for(String tagType :User.getCurrentUser().getTagTypes()){
    		myComboBoxData.add(tagType);
    	}
    	
    	tagDropDown.setItems(myComboBoxData);
    }
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    public void handle(ActionEvent e) throws IOException{
    	Button b = (Button)e.getSource();
    	if(b == addTag){
    		System.out.println("add Tag");
    		String tagType = tagDropDown.getSelectionModel().getSelectedItem();
    		if(tagType==null){
    			makeAlertInfo("Invalid Tag", "","Select a tag type");
				return;
    		}
    		String tag = tagText.getText();
    		Photo p = Photo.getCurrentPhoto();
    		if (p == null|| tag.equals("")){
    			return;
    		}
    		Tag t = new Tag(tagType, tag);
    		System.out.println(t);
    		for(Tag tagInList :p.getTags()){
    			if(t.equals(tagInList)){
    				makeAlertInfo("Invalid Tag", "","The photo already contains this tag");
    				return;
    			}
    		}
    		
    		p.addTag(t);
    		
    		ArrayList<Tag> photoTags = p.getTags();
    		for(Tag tag1: photoTags){
    			System.out.println(tag1);
    		}
    		currentStage.close();
    	}
    	else if(b == addTypeToList){
    		System.out.println("Create new tag type");
    		FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/EditTagType.fxml"));
			
			AnchorPane root = (AnchorPane)loader.load();
			
			EditTagTypeController EditTagType = loader.getController();
			
			Scene scene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			EditTagType.start(newStage);
			newStage.setScene(scene);
			newStage.centerOnScreen();
			
			///how to reload list??

			update();
    	}
    }
    /**
     * 
     * @param String errorTitle
     * @param String errorHeader
     * @param String errorContent
     * 
     * Takes in three strings and produces a simply alert for the user to see
     * 
     * @return void
     */
    private void makeAlertInfo(String errorTitle, String errorHeader, String errorContent) {    
		
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.initOwner(null);
		   alert.setTitle(errorTitle);
		   alert.setHeaderText(errorHeader);
		   alert.setContentText(errorContent);
		   alert.showAndWait();
		   
	}
}