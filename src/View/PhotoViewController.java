package View;

import java.io.IOException;

import Model.Album;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PhotoViewController {

    @FXML
    private Button displayPicture;

    @FXML
    private Button slideShow;

    @FXML
    private Button backToMain;

    @FXML
    private Button addPhoto;

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
    private Button editTagTypes;
    
    private Stage currentStage;
    
    private User u;
    
    private Album a;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
		u = User.getCurrentUser();
		System.out.println("Current user in Photo view " + u);
		a = Album.getCurrentAlbum();
		System.out.println("Current Album in Photo view is: " + a);
	}

    @FXML
    public void handle(ActionEvent e) throws IOException {
    	Button b= (Button)e.getSource();
    	if(b == displayPicture){
    		System.out.println("DisplayPicture");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/DisplayPicture.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			DisplayPictureController DisplayPicture=loader.getController();
			DisplayPicture.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == slideShow){
			System.out.println("slideShow");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SlideShow.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			SlideShowController SlideShow =loader.getController();
			SlideShow.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == backToMain){
			System.out.println("User main");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UserAlbumView.fxml"));
		
			BorderPane root = (BorderPane)loader.load();
			
			UserAlbumViewController UserAlbumView =loader.getController();
			UserAlbumView.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
		
		}
		else if(b == addPhoto){
			System.out.println("User main");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/addPhoto.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			addPhotoController addPhoto =loader.getController();
			addPhoto.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == removePhoto){
			
		}
		else if(b == movePhoto){
			
		}
		else if(b == copyPhoto){
			
		}
		else if(b == addEditCaption){
			
		}
		else if(b == addTag){
			System.out.println("add Tag");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/AddTag.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			AddTagController AddTag =loader.getController();
			AddTag.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == removeTag){
			System.out.println("Remove Tag");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RemoveTag.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			RemoveTagController RemoveTag =loader.getController();
			RemoveTag.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == editTagTypes){
			
		}

    }

}