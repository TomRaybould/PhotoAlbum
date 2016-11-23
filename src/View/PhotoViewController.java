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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
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

/**
* PhotoViewController is the class associated with the screen for displaying an Albums main 
* page. This page allows the user to view thumbnails of all photos that exist in the selected album.
* It also allows the user to add, edit and delete tags from photos, add new photos, delete photos,
* add/edit captions, move and copy photos, view photos from a selected thumbnail image in a larger screen,
* and view the photos in a slideshow
* @author  Tom Raybould & Mike Tomkowich
*/
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
    
    @FXML
    private Button safeQuit;
    
    
    private Stage currentStage;
    
    private GridPane selectedPane;
    
    
    private final String STYLE_PRESSED = "-fx-border-color: #039ED3; -fx-faint-border-color: #039ED322;";
    private final String STYLE_NORMAL = "-fx-border-color: transparent; -fx-faint-border-color: transparent;";
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
    public void start(Stage mainStage){
    		currentStage = mainStage;
    		Photo.setCurrentPhoto(null);
    		photoTitle.setText("Photos in Album: " +Album.getCurrentAlbum().getName());
    		update();    	
	}
    /**
     * Updates data associated with screen
     * 
     * @return void
     */
    public void update(){
    	if(Album.getCurrentAlbum()==null){
    		return;
    	}
    	ArrayList<GridPane> nestedPanes=new ArrayList<GridPane>();
    	for(Photo photo: Album.getCurrentAlbum().getPhotosInAlbum()){
    		ImageView pic= new ImageView();
    		Image img = new Image(photo.getSrc());
    		int Hgap=0;
    		int leftPadding;
    		pic.setImage(img);
    		if(img.getWidth()>img.getHeight()){
    			pic.setFitHeight(100);
    			pic.setFitWidth(200);
    			Hgap=15;
    			leftPadding=10;
    		}
    		else{
    			pic.setFitHeight(100);
    			pic.setFitWidth(75);
    			Hgap=80;
    			leftPadding=70;
    		}
    		
    		
    		
    		Text caption=new Text();
    		caption.setWrappingWidth(200);
    		String shorterDateStr= photo.getCalDate().toString().substring(0,20)+photo.getCalDate().toString().substring(24);
    		
    		if(photo.getCaption() == null || photo.getCaption().equals("")){
    			caption.setText("Date:"+shorterDateStr+"\n" +"Caption: "+"N/A");
    		}
    		else if(photo.getCaption().length()>=100){
    			caption.setText("Date:"+shorterDateStr+"\n"+"Caption: "+photo.getCaption().substring(0,96)+"...");
    		}
    		else{
    			caption.setText("Date:"+shorterDateStr+"\n"+"Caption: "+photo.getCaption());
    		}
    		
    		GridPane nestedPane= new GridPane();
    		
    		nestedPane.add(pic, 0, 0);
    		nestedPane.add(caption, 1, 0);
    		
    		nestedPane.setVgap(10);
    		nestedPane.setHgap(Hgap);
    		
    		Insets nestedPadding =  new Insets(10,10,10,leftPadding); //top,right,bottom,left
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
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    @FXML
    public void handle(ActionEvent e) throws IOException {
    	Button b= (Button)e.getSource();
    	
    	if(b == displayPicture){
    		if(Photo.getCurrentPhoto()==null){
				makeAlertInfo("No Photo Selected","","You must select a photo");
				return;
			}
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
			if(Photo.getCurrentPhoto()==null){
				makeAlertInfo("No Photo Selected","","You must select a photo");
				return;
			}
			System.out.println("slideShow");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SlideShow.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			SlideShowController SlideShow = loader.getController();
			SlideShow.start(currentStage,Album.getCurrentAlbum().getPhotosInAlbum());
			Scene scene = new Scene(root);
			Stage newStage =new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(scene);
			newStage.centerOnScreen();
			newStage.showAndWait();
			
		}
		else if(b == backToMain){
			System.out.println("User main");
			User.write();
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
    				(file.getName().contains(".jpg") || file.getName().contains(".png") || file.getName().contains(".gif")|| 
    						file.getName().contains(".hmp") ||
    						file.getName().contains(".JPG") ||
    						file.getName().contains(".PNG") ||
    						file.getName().contains(".GIF") ||
    						file.getName().contains(".HMP") ||
    						file.getName().contains(".jpeg") ||
    						file.getName().contains(".JPEG") ||
    						file.getName().contains(".ani") ||
    						file.getName().contains(".ANI") ||
    						file.getName().contains(".bmp") ||
    						file.getName().contains(".BMP") ||
    						file.getName().contains(".jpe") ||
    						file.getName().contains(".JPE") ||
    						file.getName().contains(".JBG") ||
    						file.getName().contains(".jbg") ||
    						file.getName().contains(".img") ||
    						file.getName().contains(".IMG") ||
    						file.getName().contains(".PSD") ||
    						file.getName().contains(".psd") ||
    						file.getName().contains(".gif"))){
    			System.out.println("In file success");
    			
    		
			String URL = file.toURI().toString();
			
			Photo searchRes= User.searchBySrc(URL);
			
			if(searchRes!=null){//the photo already exists
				for(Photo p: Album.getCurrentAlbum().getPhotosInAlbum()){
					if(URL.equals(p.getSrc())){
						makeAlertInfo("Photo Alrady Exists","","This photo is already in this album");
						return;
					}
				}
				Album.getCurrentAlbum().addPhotoToAlbum(searchRes);
				this.update();
				return;
			}
    		
			//sets date to last modified fixed
    		Date date = new Date();
    		date.setTime(file.lastModified());
    		
    		System.out.println("date is: " + date);
    		
    		Photo photo = new Photo(date, URL);
    		System.out.println("Right after creation");
    		System.out.println(photo);
    		Photo.setCurrentPhoto(photo);
    		Album a = Album.getCurrentAlbum();
    		/*
    		 * The count will be adjusted when the user actually presses add photo
    		 * in the next view, because add photo now adjusts the count
    		 */
    		System.out.println("Photo count for album is: " + a.getNumOfPhotos());
		
			System.out.println("User main");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/addPhoto.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
			
			AddPhotoController addPhoto =loader.getController();
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
			if(Photo.getCurrentPhoto()==null){
				makeAlertInfo("No Photo Selected","","You must select a photo");
				return;
			}
			if(!(makeAlertConfirm("Deleteing Photo", "", "Are you sure you want to delete"))){
				return;
			}
			if(Photo.getCurrentPhoto()!=null){
			Album.getCurrentAlbum().removePhotoFromAlbum(Photo.getCurrentPhoto());
			//remove method subtracts from count
			System.out.println("Current count is " + Album.getCurrentAlbum().getNumOfPhotos());
			Photo.setCurrentPhoto(null);
			this.update();
			}
			
			return;
		}
		else if(b == movePhoto){
			if(Photo.getCurrentPhoto()==null){
				makeAlertInfo("No Photo Selected","","You must select a photo");
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
				makeAlertInfo("No Photo Selected","","You must select a photo");
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
				makeAlertInfo("No Photo Selected","","You must select a photo");
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
				makeAlertInfo("No Photo Selected","","You must select a photo");
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
				makeAlertInfo("No Photo Selected","","You must select a photo");
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/EditTagType.fxml"));
			
			AnchorPane root = (AnchorPane)loader.load();
			
			EditTagTypeController EditTagType = loader.getController();
			
			Scene scene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			EditTagType.start(newStage);
			newStage.setScene(scene);
			newStage.centerOnScreen();
		}
		else if(b == safeQuit){
			User.write();
			currentStage.close();
		}

    }//end of handle
    
    /**
     * 
     * @param String title
     * @param String header
     * @param String content
     * @param String hint
     * 
     * Takes in four strings and produces a one line dialog that can be used to 
     * prompt the user for a response
     * 
     * @return String , this is the string the user enter in the box
     */
    
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
    
    /**
     * 
     * @param String errorTitle
     * @param String errorHeader
     * @param String errorContent
     * 
     * Takes in three strings and produces a simply alert for the user to see
     * 
     * @return void
     */
    private void makeAlertInfo(String errorTitle, String errorHeader, String errorContent) {    
		
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.initOwner(null);
		   alert.setTitle(errorTitle);
		   alert.setHeaderText(errorHeader);
		   alert.setContentText(errorContent);
		   alert.showAndWait();
		   
	}
    
    /**
     * 
     * @param String alertTitle
     * @param String alertHeader
     * @param String alertContent
     * 
     * Takes in three strings and produces a simply alert for the user to see,
     * return true only if the user presses ok, otherwise returns false
     * 
     * @return boolean true if the user pressed ok
     */
    
    private boolean makeAlertConfirm(String alertTitle, String alertHeader, String alertContent){
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(alertTitle);
    	alert.setHeaderText(alertHeader);
    	alert.setContentText(alertContent);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    		if(result.get()==ButtonType.OK){
    			return true;
    		}
    		if(result.get()==ButtonType.CANCEL){
    			return false;
    		}
    	return false;
    }
    
}//end of class

