package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import Model.Album;
import Model.Photo;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class UserAlbumViewController {
	@FXML
	private Text userTitle;
	@FXML
	private Button makeNewAlbum;
	@FXML
	private Button deleteAlbum;
	@FXML
	private Button renameAlbum;
	@FXML
	private Button logOut;
	@FXML
	private Button safeQuit;
	@FXML
	private ComboBox tagDropDown;
	@FXML
	private DatePicker dateSelector;
	@FXML
	private Button searchTag;
	@FXML
	private Button searchDate;
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn tableAlbumName;
	@FXML
	private TableColumn tableNumOfPhotos;
	@FXML
	private TableColumn tableEarliestPhoto;
	@FXML
	private TableColumn tableDateRange;
	
	public Stage currentStage;
	
	private User currUser;
	
	public void start(Stage mainStage){
		currentStage = mainStage;
		currUser = User.getCurrentUser();
		userTitle.setText(currUser.getUserName()+"'s Albums"); 
		System.out.println("Current user in User album view" + currUser);
		this.update();
		
		tableView
        	.getSelectionModel()
        	.selectedItemProperty()
        	.addListener(
        			(obs , oldVal, newVal) -> System.out.println());
	}
	
	public void update(){
		ObservableList<Album> obslist = FXCollections.observableArrayList();
		for (Album album: User.getCurrentUser().getAlbumList()){
			obslist.add(album);
		}
		
		
		tableAlbumName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableNumOfPhotos.setCellValueFactory(new PropertyValueFactory<>("numOfPhotos"));
		tableEarliestPhoto.setCellValueFactory(new PropertyValueFactory<>("oldestDate"));
		tableDateRange.setCellValueFactory(new PropertyValueFactory<>("latestDate"));
		tableView.setItems(obslist);
		
		tableView.getColumns().setAll(tableAlbumName,tableNumOfPhotos,tableEarliestPhoto,tableDateRange);
		
	}
	

	
	public void handle(ActionEvent e) throws IOException {
		Button b= (Button)e.getSource();
		
		if(b == makeNewAlbum){
			
			System.out.println("make new album");
			//get the name of the album helper method below
			String newAlbumName = oneLineDialog("Name New Album","","Enter name for new album","");
			System.out.println("The name of the new album is: *"+ newAlbumName+"*");
			
			if(newAlbumName==null){
				//if cancel was pressed the string is null
				return;
			}
			
			if(newAlbumName.equals("")){
				makeAlertInfo("No Ablum Name","","You must give your new album a name");
			}
			
			else{	
				Album newAlbum = new Album(newAlbumName); 
				Album.setCurrentAlbum(newAlbum);
				User.getCurrentUser().addAlbum(newAlbum);
			
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
			
				AnchorPane root = (AnchorPane)loader.load();
			
				PhotoViewController PhotoView=loader.getController();
				PhotoView.start(currentStage);
				Scene scene = new Scene(root);
				currentStage.setScene(scene);
				currentStage.centerOnScreen();
			}
		}
		else if(b == deleteAlbum){
			Album album = (Album) tableView.getSelectionModel().getSelectedItem(); 
			User.getCurrentUser().removeAlbum(album);
			this.update();
		}
		else if(b == renameAlbum){
			if(tableView.getSelectionModel().getSelectedItem()==null){
				return;
			}
			Album album = (Album) tableView.getSelectionModel().getSelectedItem(); 
			String newAlbumName = oneLineDialog("Rename Album","","Enter new name for album", album.getName());
			album.setName(newAlbumName);
			this.update();
		}
		else if(b == logOut){
			
		}
		else if(b == safeQuit){
			
		}
		else if(b == searchTag){
			
		}
		else if(b == searchDate){
			
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
	
	private void makeAlertInfo(String errorTitle, String errorHeader, String errorContent) {    
		
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.initOwner(null);
		   alert.setTitle(errorTitle);
		   alert.setHeaderText(errorHeader);
		   alert.setContentText(errorContent);
		   alert.showAndWait();
		   
	}
}//end of class
	