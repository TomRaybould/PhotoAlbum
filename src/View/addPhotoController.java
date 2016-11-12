package View;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
    
    private User currUser;
    
    private static Album currAlbum;
    
    private static Photo currPhoto;
    
    private boolean setATag = false;
    
    public void start(Stage mainStage) throws MalformedURLException{
		currentStage = mainStage;
		if(setATag == false){
			currUser = User.getCurrentUser();
			System.out.println("Current user in Photo view " + currUser);
			currAlbum = Album.getCurrentAlbum();
			System.out.println("Current Album in Photo view is: " + currAlbum);
	    	int imgTotal;
	    	int imgPosition = 0;
	    	ImageView imgMain = new ImageView();
			ImageView imgThumb = new ImageView();
			imgThumb.setFitHeight(100);
			imgThumb.setFitWidth(200);
			// List of Images
			System.out.println("here");
			List<File> images = new ArrayList<File>();
			//File chooser
			final FileChooser fileChooser = new FileChooser();
	    		File file = fileChooser.showOpenDialog(mainStage);
	    		if(file.isFile() &&
	    				(file.getName().contains(".jpg") || file.getName().contains(".png") || file.getName().contains(".hmp") ||
	    						file.getName().contains(".gif"))){
	    			System.out.println("In file success");
	    			images.add(file);
	    			imgTotal = images.size();
	    			
	    			if(imgTotal > 1){
	    				imgPosition++;
	    			}
	    			URL = file.toURI().toURL().toString();
	        		Image imgLoad = new Image(URL);
	        		//pass image to ImageView
	        		image.setImage(imgLoad);
	        		
	    		}
		}
		
    			
	}

    @FXML
    void handle(ActionEvent e) throws IOException {
    	Button b= (Button)e.getSource();
    	if(b == addPhoto){
   
        		String date = "dummy date";
        		Photo photo = new Photo(date, URL);
        		currAlbum.addPhotoToAlbum(photo);
        		Photo.setCurrentPhoto(photo);
        		currAlbum.iterate();
        		photo.setCaption(captionText.getText());
        		photo.setImg(new Image(URL));
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
			Stage newStage =new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(scene);
			newStage.showAndWait();
			
		}
    }

}