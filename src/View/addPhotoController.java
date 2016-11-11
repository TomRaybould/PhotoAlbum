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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
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
    
    private Stage currentStage;
    
    private String URL;
    
    private User u;
    
    private Album a;
    
    private Photo p;
    
    private boolean setATag;
    
    public void start(Stage mainStage) throws MalformedURLException{
		currentStage = mainStage;
		setATag = false;
		u = User.getCurrentUser();
		System.out.println("Current user in Photo view " + u);
		a = Album.getCurrentAlbum();
		System.out.println("Current Album in Photo view is: " + a);
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

    @FXML
    void handle(ActionEvent e) throws IOException {
    	Button b= (Button)e.getSource();
    	if(b == addPhoto){
    		if(setATag = false){
    			System.out.println("Enter Photo ID for now");
        		String date = IO.readString();
        		Photo photo = new Photo(date, URL);
        		a.addPhotoToAlbum(photo);
        		Photo.setCurrentPhoto(photo);
        		a.iterate();
        		System.out.println("make new album");
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
    		
    			GridPane root = (GridPane)loader.load();
    			
    			PhotoViewController PhotoView=loader.getController();
    			PhotoView.start(currentStage);
    			Scene scene = new Scene(root);
    			currentStage.setScene(scene);
    		}
    		else{
    			System.out.println("make new album");
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
    		
    			GridPane root = (GridPane)loader.load();
    			
    			PhotoViewController PhotoView=loader.getController();
    			PhotoView.start(currentStage);
    			Scene scene = new Scene(root);
    			currentStage.setScene(scene);
    			
    		}
    		
			
		}
		else if(b == cancel){
			System.out.println("make new album");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
		
			GridPane root = (GridPane)loader.load();
			
			PhotoViewController PhotoView=loader.getController();
			PhotoView.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == tagThisPhoto){
			System.out.println("Enter Photo ID for now");
    		String date = IO.readString();
    		Photo photo = new Photo(date, URL);
    		a.addPhotoToAlbum(photo);
    		Photo.setCurrentPhoto(photo);
    		a.iterate();
    		setATag = true;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/addTag.fxml"));
			
			AnchorPane root = (AnchorPane)loader.load();
			
			AddTagController addTag=loader.getController();
			addTag.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
    }

}