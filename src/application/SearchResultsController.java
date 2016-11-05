package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
