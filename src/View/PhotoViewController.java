package View;

import java.io.IOException;
import java.util.ArrayList;

import Model.Album;
import Model.Photo;
import Model.User;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PhotoViewController {
	@FXML
	private Text photoTitle;
	
	@FXML
	private ScrollPane scrollPane;
	
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
    
    private User currUser;
    
    private Album currAlbum;
    
    private GridPane selectedPane;
    
    private Photo selectedPhoto;
    
    private final String STYLE_PRESSED = "-fx-border-color: #039ED3; -fx-faint-border-color: #039ED322;";
    private final String STYLE_NORMAL = "-fx-border-color: transparent; -fx-faint-border-color: transparent;";
    
    public void start(Stage mainStage){
    	
		currentStage = mainStage;
		currUser = User.getCurrentUser();
		System.out.println("Current user in Photo view " + currUser);
		currAlbum = Album.getCurrentAlbum();
		System.out.println("Current Album in Photo view is: " + currAlbum);
		
		photoTitle.setText("Photos in Ablum: "+ currAlbum.getName());
		
		
		
	
	}
    
    public void update(){
    	ArrayList<GridPane> nestedPanes=new ArrayList<GridPane>();
    	for(Photo photo: currAlbum.getPhotosInAlbum()){
    		ImageView pic= new ImageView();
    		Image img = new Image(photo.getSrc());
    		pic.setImage(img);
    		pic.setFitHeight(100);
    		pic.setFitWidth(200);
    		Text caption=new Text();
    		caption.setWrappingWidth(200);
    		caption.setText(photo.getCaption());
    		
    		GridPane nestedPane= new GridPane();
    		
    		nestedPane.add(pic, 0, 0);
    		nestedPane.add(caption, 1, 0);
    		
    		nestedPane.setVgap(25);
    		nestedPane.setHgap(15);
    		nestedPane.setStyle(STYLE_NORMAL);
    		
    		nestedPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
    			public void handle(MouseEvent m){
    				if(selectedPane!=null){
    					selectedPane.setStyle(STYLE_NORMAL);   
    				}
    				selectedPhoto = photo;
    				nestedPane.setStyle(STYLE_PRESSED);
    				selectedPane = nestedPane;
    				System.out.println("selected");
    			}
    		});
    		
    		nestedPanes.add(nestedPane);
    	}
    	
    	int row = 0;
    	int col= 0;
    	GridPane containerPane= new GridPane();
    	for(GridPane pane: nestedPanes ){
    		if(col== 0){
    			containerPane.add(pane, col, row);
    			col++;
    			continue;
    		}
    		if(col== 1){
    			containerPane.add(pane, col, row);
    			row++;
    			col=0;
    		}
    	}
    	containerPane.setVgap(30);
    	containerPane.setHgap(30);
    	Insets paddingVals =  new Insets(20,1,1,30); //top,right,bottom,left
		scrollPane.setPadding(paddingVals);
		scrollPane.setContent(containerPane);
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
			Stage newStage =new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(scene);
			newStage.centerOnScreen();
			newStage.showAndWait();
			
		}
		else if(b == slideShow){
			System.out.println("slideShow");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SlideShow.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			SlideShowController SlideShow = loader.getController();
			SlideShow.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			
		}
		else if(b == backToMain){
			System.out.println("User main");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UserAlbumView.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
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
			Scene scene = new Scene(root);
			Stage newStage =new Stage();
			addPhoto.start(newStage);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(scene);
			newStage.showAndWait();
			this.update();
			
		}
		else if(b == removePhoto){
			//need an alert
			currAlbum.removePhotoFromAlbum(selectedPhoto);
			this.update();
			
		}
		else if(b == movePhoto){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MovePhoto.fxml"));
		
			BorderPane root = (BorderPane)loader.load();
			
			MovePhotoController movePhoto =loader.getController();
			Scene scene = new Scene(root);
			Stage newStage =new Stage();
			movePhoto.start(newStage);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(scene);
			newStage.showAndWait();
		}
		else if(b == copyPhoto){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/CopyPhoto.fxml"));
		
			BorderPane root = (BorderPane)loader.load();
			
			CopyPhotoController copyPhoto =loader.getController();
			Scene scene = new Scene(root);
			Stage newStage =new Stage();
			copyPhoto.start(newStage);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(scene);
			newStage.showAndWait();
			
		}
		else if(b == addEditCaption){
			//should bring up a dialog box
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

    }//end of handle
    
    
}//end of class

