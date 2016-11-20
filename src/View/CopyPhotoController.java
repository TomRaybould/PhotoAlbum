package View;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import Model.Album;
import Model.Photo;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
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
	
	public void update(){
		obslist = FXCollections.observableArrayList();
		
		for(Album a : User.getCurrentUser().getAlbumList()){
			String s = new String(a.getName());
			if(s==a.getName()){
				System.out.println("not working");
			}
			obslist.add(s);
		}
		
		destList.setItems(obslist);
	}
	

	// Event Listener on Button[#copyButton].onAction
	@FXML
	public void handle(ActionEvent e) {
		Button b = (Button)e.getSource();
    	if(b == copyButton){
    		System.out.println(selectedAlbum);
    		System.out.println(Photo.getCurrentPhoto().toString());
    		for(Album a: User.getCurrentUser().getAlbumList()){
    			if(a.getName().equals(selectedAlbum)){
    				
    				a.addPhotoToAlbum(Photo.getCurrentPhoto());
    				//no need to mess with the current album of Album Class
    				//add Photo method adjusts the count
    				this.update();
    				currentStage.close();
    			}
    		}
    		
    	}
	}
}
