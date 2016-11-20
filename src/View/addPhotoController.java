package View;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Model.Album;
import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
* addPhotoController is the class associated with the screen for adding photos to a
* Users album. It is capable of adding a photo, cancel add photo, tagging a photo,
* and putting a caption on to the photo
* @author  Tom Raybould & Mike Tomkowich
*/
public class addPhotoController {

	@FXML
    private ImageView image;

    @FXML
    private Button addPhoto;

    @FXML
    private Button cancel;

    @FXML
    private Button tagThisPhoto;
    
    @FXML
    private TextField captionText;
    
    private Stage currentStage;
    
    private String URL;
    
    private boolean setATag = false;
    
    private boolean nullPhoto = false;
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
    public void start(Stage mainStage) throws IOException{
		currentStage = mainStage;
		if(setATag == false){
			System.out.println("Current user in Photo view " + User.getCurrentUser());
			System.out.println("Current Album in Photo view is: " + Album.getCurrentAlbum());
			Image imgLoad = new Image(Photo.getCurrentPhoto().getSrc());
    		image.setImage(imgLoad);
		}
		
    			
	}
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    @FXML
    void handle(ActionEvent e) throws IOException {
    	Button b= (Button)e.getSource();
    	if (nullPhoto == true){
    		b = cancel;
    		nullPhoto = 	false;
    	}
    	if(b == addPhoto){

        		Album.getCurrentAlbum().addPhotoToAlbum(Photo.getCurrentPhoto());
        		Album.getCurrentAlbum().iterate();
        		Photo.getCurrentPhoto().setCaption(captionText.getText());
        		System.out.println("make new album");
    			currentStage.close();
			
		}
		else if(b == cancel){
			
			//just close the window because now addPhoto is loaded on top of photoview
			currentStage.close();
			
		}
		else if(b == tagThisPhoto){
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/addTag.fxml"));
			
			AnchorPane root = (AnchorPane)loader.load();
			
			AddTagController addTag = loader.getController();
			
			Scene scene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			addTag.start(newStage);
			newStage.setScene(scene);
			newStage.centerOnScreen();
			
		}
    }

}