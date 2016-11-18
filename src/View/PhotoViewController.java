package View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
    
    private GridPane selectedPane;
    
    
    private final String STYLE_PRESSED = "-fx-border-color: #039ED3; -fx-faint-border-color: #039ED322;";
    private final String STYLE_NORMAL = "-fx-border-color: transparent; -fx-faint-border-color: transparent;";
    
    public void start(Stage mainStage){
    		currentStage = mainStage;
    		Photo.setCurrentPhoto(null);
    		update();    	
	}
    
    public void update(){
    	ArrayList<GridPane> nestedPanes=new ArrayList<GridPane>();
    	for(Photo photo: Album.getCurrentAlbum().getPhotosInAlbum()){
    		ImageView pic= new ImageView();
    		Image img = new Image(photo.getSrc());
    		pic.setImage(img);
    		pic.setFitHeight(100);
    		pic.setFitWidth(200);
    		Text caption=new Text();
    		caption.setWrappingWidth(200);
    		
    		if(photo.getCaption() == null || photo.getCaption().equals("")){
    			caption.setText("Caption:\n"+"N/A");
    		}
    		else{
    			caption.setText("Caption:\n"+photo.getCaption());
    		}
    		
    		GridPane nestedPane= new GridPane();
    		
    		nestedPane.add(pic, 0, 0);
    		nestedPane.add(caption, 1, 0);
    		
    		nestedPane.setVgap(10);
    		nestedPane.setHgap(15);
    		
    		Insets nestedPadding =  new Insets(10,10,10,10); //top,right,bottom,left
    		nestedPane.setPadding(nestedPadding);
    		
    		nestedPane.setStyle(STYLE_NORMAL);
    		
    		nestedPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
    			public void handle(MouseEvent m){
    				if(selectedPane!=null){
    					selectedPane.setStyle(STYLE_NORMAL);   
    				}
    				
    				Photo.setCurrentPhoto(photo);
    				nestedPane.setStyle(STYLE_PRESSED);
    				selectedPane = nestedPane;
    				System.out.println("selected");
    				System.out.println(photo);
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
    	containerPane.setVgap(20);
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
			final FileChooser fileChooser = new FileChooser();
    		File file = fileChooser.showOpenDialog(currentStage);
    		if(file == null){
				System.out.println("Bad");
			}
    		else if(file.isFile() &&
    				(file.getName().contains(".jpg") || file.getName().contains(".png") || file.getName().contains(".hmp") ||
    						file.getName().contains(".gif"))){
    			System.out.println("In file success");
    			
			String URL = file.toURI().toURL().toString();
    		
    		Calendar cal = Calendar.getInstance();
    		Date date1 = new Date(0L);
    		cal.set(Calendar.MILLISECOND, 0);
    		date1 = cal.getTime();
    		String date = date1.toString();
    		Photo photo = new Photo(date, URL);
    		Photo.setCurrentPhoto(photo);
    		Album a = Album.getCurrentAlbum();
    		a.addOnePhotoToCount();
    		System.out.println("Photo count for album is: " + a.getNumOfPhotos());
		
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
			
		}
		else if(b == removePhoto){
			//need an alert
			if(Photo.getCurrentPhoto()!=null){
			Album.getCurrentAlbum().removePhotoFromAlbum(Photo.getCurrentPhoto());
			Album.getCurrentAlbum().subtractOnePhotoToCount();
			System.out.println("Current count is " + Album.getCurrentAlbum().getNumOfPhotos());
			Photo.setCurrentPhoto(null);
			this.update();
			}
			
			return;
		}
		else if(b == movePhoto){
			if(Photo.getCurrentPhoto()==null){
				return;
			}
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
			this.update();
		}
		else if(b == copyPhoto){
			if(Photo.getCurrentPhoto()==null){
				return;
			}
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
			this.update();
			
		}
		else if(b == addEditCaption){
			if(Photo.getCurrentPhoto()==null){
				return;
			}
			if(Photo.getCurrentPhoto()!=null){
				String caption = this.oneLineDialog("Add/Edit Caption", "", "Enter new caption", Photo.getCurrentPhoto().getCaption());
				if(caption==null){
					return;
				}
				else{
					Photo.getCurrentPhoto().setCaption(caption);
					this.update();
				}
			}
		}
		else if(b == addTag){
			if(Photo.getCurrentPhoto()==null){
				return;
			}
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
		else if(b == removeTag){
			if(Photo.getCurrentPhoto()==null){
				return;
			}
			System.out.println("Remove Tag");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RemoveTag.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			RemoveTagController RemoveTag =loader.getController();
			
			Scene scene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			RemoveTag.start(newStage);
			newStage.setScene(scene);
			newStage.centerOnScreen();
			
		}
		else if(b == editTagTypes){
			
		}

    }//end of handle
    
    private String oneLineDialog(String title, String header, String content, String hint){
		
		TextInputDialog dialog = new TextInputDialog(hint);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		Optional<String> result = dialog.showAndWait();
		String str = null;
		try{
			str = result.get();
			System.out.println(str);
		}
		catch(Exception e ){
			
		}
		return str;
	}
    
}//end of class

