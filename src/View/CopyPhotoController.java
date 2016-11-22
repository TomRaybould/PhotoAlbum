package View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import Model.Album;
import Model.Photo;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
* CopyPhotoController is the class associated with the screen for copying photos from one 
* album to another. Its main purpose is to allow the user to select a photo from one destination
* and move that photo to another destination by making a copy of the selcted photo
* @author  Tom Raybould & Mike Tomkowich
*/
public class CopyPhotoController {
	@FXML
	private ListView<String> destList;
	@FXML
	private Button copyButton;
	
	private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedAlbum;
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
		
		destList
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedAlbum = destList.getSelectionModel().getSelectedItem();});
			//shouldnt be called selected user
	}
	/**
     * Updates data associated with screen
     * 
     * @return void
     */
	public void update(){
		obslist = FXCollections.observableArrayList();
		
		for(Album a : User.getCurrentUser().getAlbumList()){
			//the current album should not be an option
			if(!a.getName().equals(Album.getCurrentAlbum().getName())){	
				obslist.add(a.toString());
			}
		}
		
		destList.setItems(obslist);
	}
	
	 /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
	// Event Listener on Button[#copyButton].onAction
	@FXML
	public void handle(ActionEvent e) {
		Button b = (Button)e.getSource();
    	if(b == copyButton){
    		System.out.println(selectedAlbum);
    		System.out.println(Photo.getCurrentPhoto().toString());
    		for(Album a: User.getCurrentUser().getAlbumList()){
    			if(a.getName().equals(selectedAlbum)){
  
    				if(!a.getPhotosInAlbum().contains(Photo.getCurrentPhoto())){
    					// the photo isn't already there 
    					a.addPhotoToAlbum(Photo.getCurrentPhoto());
    					this.update();
    					currentStage.close();
    				}
    				else{
    					makeAlertInfo("Invalid Copy","","The photo is already in the destination album");
    				}
    			}
    		}
    	}
	}
	
	private void makeAlertInfo(String errorTitle, String errorHeader, String errorContent) {    
		
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.initOwner(null);
		   alert.setTitle(errorTitle);
		   alert.setHeaderText(errorHeader);
		   alert.setContentText(errorContent);
		   alert.showAndWait();   
	}
}
