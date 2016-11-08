package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class UserAlbumViewController {
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
	
	public Stage currentStage;
	
	public void start(Stage mainStage){
		currentStage=mainStage;
	}
	
	public void handle(ActionEvent e) throws IOException {
		Button b= (Button)e.getSource();
		
		if(b == makeNewAlbum){
			System.out.println("make new album");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
		
			GridPane root = (GridPane)loader.load();
			
			PhotoViewController PhotoView=loader.getController();
			PhotoView.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
		}
		else if(b == deleteAlbum){
			
		}
		else if(b == renameAlbum){
		
		}
		else if(b == logOut){
			
		}
		else if(b == safeQuit){
			
		}
		else if(b == searchTag){
			
		}
		else if(b == searchDate){
			
		}
		
		
	}
}
	