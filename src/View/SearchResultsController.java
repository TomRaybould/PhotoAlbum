package View;

import java.util.ArrayList;

import Model.Album;
import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SearchResultsController {

    @FXML
    private Button displayPicture;

    @FXML
    private Button slideShow;

    @FXML
    private Button backToAlbum;

    @FXML
    private Button removePhoto;

    @FXML
    private Button movePhoto;

    @FXML
    private Button copyPhoto;

    @FXML
    private Button addEditCaption;

    @FXML
    private Button addTag;

    @FXML
    private Button removeTag;

    @FXML
    private Button createAlbum;
    
    private Stage currentStage;
    
    private ArrayList<Photo> searchResult = new ArrayList<Photo>();
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
    public void start(Stage mainStage){
    	
    	currentStage = mainStage;
    	
    	
	}
    
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == addTag){
    		System.out.println("add Tag");
    	}
    	else if(b == slideShow){
    		System.out.println("slideShow");
    		
    	}
    	else if(b == backToAlbum){
    		System.out.println("back to album");
    		
    	}
    	else if(b == removePhoto){
    		System.out.println("remove photo");
    		
    	}
    	else if(b == movePhoto){
    		System.out.println("move photo");
    		
    	}
    	else if(b == copyPhoto){
    		System.out.println("copy photo");
    		
    	}
    	else if(b == addEditCaption){
    		System.out.println("add edit caption");
    		
    	}
    	else if(b == displayPicture){
    		System.out.println("display Picture");
    		
    	}
    	else if(b == removeTag){
    		System.out.println("remove tag");
    		
    	}
    	else if(b == createAlbum){
    		System.out.println("Create Album");
    		
    	}
    }


}
