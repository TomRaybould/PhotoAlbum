package View;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

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
	
	public void handle(ActionEvent e) {
		Button b= (Button)e.getSource();
		
		if(b == makeNewAlbum){
			
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
	