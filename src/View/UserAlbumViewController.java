package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import Model.Album;
import Model.Photo;
import Model.Tag;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class UserAlbumViewController {
	@FXML
	private Text userTitle;
	 @FXML
	 private TextField tagValue;
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
	private ComboBox<String>  tagDropDown;
	@FXML
	private DatePicker dateStart;
	@FXML
	private TextField hourStart;
	@FXML
	private TextField minStart;
	@FXML
	private DatePicker dateEnd;
	@FXML
	private TextField hourEnd;
	@FXML
	private TextField minEnd;
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
	@FXML
	private Button openAlbum;
	
	public Stage currentStage;
	
	private User currUser;
	
	private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
	
	public void start(Stage mainStage) throws IOException{
		currentStage = mainStage;
		currUser = User.getCurrentUser();
		userTitle.setText(currUser.getUserName()+"'s Albums"); 
		System.out.println("Current user in User album view" + currUser);
		this.update();
		User.write();
		tableView
        	.getSelectionModel()
        	.selectedItemProperty()
        	.addListener(
        			(obs , oldVal, newVal) -> System.out.println());
	}
	
	public void update(){
		
		for(String str: User.getCurrentUser().getTagTypes()){
			myComboBoxData.add(str);
		}
		
		myComboBoxData.add(new String("All Tags"));
		
		tagDropDown.setItems(myComboBoxData);
		
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
				makeAlertInfo("No Album Name","","You must give your new album a name");
			}
			
			else{	
				Album newAlbum = new Album(newAlbumName); 
				Album.setCurrentAlbum(newAlbum);
				User.getCurrentUser().addAlbum(newAlbum);
				Album.write();
			
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
			Album.write();
			User.write();
			this.update();
		}
		else if(b == logOut){

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
		
			LoginPageController LoginPage=loader.getController();
			LoginPage.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			currentStage.centerOnScreen();
			
		}
		else if(b == safeQuit){
			
		}
		else if(b == searchTag){
			ArrayList<Photo> results= new ArrayList<Photo>();
			
			String tagVal = tagValue.getText();
			String tagTyp = tagDropDown.getSelectionModel().getSelectedItem();
			System.out.println("Value: " + tagVal);
			System.out.println("Type: " + tagTyp);
			
			Tag target= new Tag(tagTyp,tagVal);
			
			User u = User.getCurrentUser();
	    	ArrayList<Album> albums = u.getAlbumList();
	    	for(Album a: albums){
	    		ArrayList<Photo> photos = a.getPhotosInAlbum();
	    		for(Photo p: photos){
	    			for(Tag t: p.getTags()){
	    				System.out.println(p);
	    				System.out.println("T Type: " + t.getType());
	    				System.out.println("T value: " + t.getValue());
	    				System.out.println("Target Type: " + target.getType());
	    				System.out.println("Target value: " + target.getValue());
	    				if(target.equals(t)||(target.getType()=="All Tags"&& target.getValue().equals(t.getValue()))){
	    					/*
	    					 * This means if the tags are equals add to list, or if the tag type of target is "All Tags"
	    					 * and the values are the same also add it to the list
	    					 */
	    					System.out.println("In here");
	    					results.add(p);
	    					break;
	    				}
	    			}
	    		}
	    	}
	    	System.out.println("Search Results---------------------------");
	    	for(Photo p: results){
	    		System.out.println(p);
	    	}
	    	if(results.size()<=0){
	    		//alert
	    		return;
	    	}
	    	else{
	  		
	    		
	    		Album.setSearchResults(results);
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/SearchResults.fxml"));
		
	    		GridPane root = (GridPane)loader.load();
		
	    		SearchResultsController SearchResults=loader.getController();
	    		SearchResults.start(currentStage);
	    		Scene scene = new Scene(root);
	    		currentStage.setScene(scene);
	    		currentStage.centerOnScreen();
	    	}
			
		}
		else if(b == searchDate){
			ArrayList<Photo> result = new ArrayList<Photo>();
			result = getPhotosInRange();
			
			if(result==null){
				makeInfoAlert("No Results Found","","You have no photos in this range");
			}
			
			else{
				Album.setSearchResults(result);
				
				FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/SearchResults.fxml"));
		
	    		GridPane root = (GridPane)loader.load();
		
	    		SearchResultsController SearchResults=loader.getController();
	    		SearchResults.start(currentStage);
	    		Scene scene = new Scene(root);
	    		currentStage.setScene(scene);
	    		currentStage.centerOnScreen();
			}
		}
		else if(b == openAlbum){
			if(tableView.getSelectionModel().getSelectedItem()==null){
				return;
			}
			Album album = (Album) tableView.getSelectionModel().getSelectedItem(); 
			Album.setCurrentAlbum(album);
			//Album.existsToTrue();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
		
			AnchorPane root = (AnchorPane)loader.load();
		
			PhotoViewController PhotoView=loader.getController();
			PhotoView.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
			currentStage.centerOnScreen();
		}
		
	}//end of handle
	
	
	private void makeInfoAlert(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		
	}

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
	
	private ArrayList<Photo> getPhotosInRange(){
		ArrayList<Photo> results= new ArrayList<Photo>();
		if(dateStart.getValue()==null||dateEnd.getValue()==null){
			return null;
		}
		int startYear= dateStart.getValue().getYear();	
		int startMonth= (dateStart.getValue().getMonthValue()-1);
		int startDay= dateStart.getValue().getDayOfMonth();
		int startHour= convertHourInput(hourStart.getText().toString());
		int startMin= convertHourInput(minStart.getText().toString());
		
		System.out.println("Start date: " + startYear+", "+startMonth+", "+startDay+", "+startHour+", "+startMin);
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(startYear, startMonth, startDay, startHour, startMin, 0);
		Date dateBeginRange = cal.getTime(); // get back a Date object
		System.out.println(dateBeginRange);
		
		int endYear= dateEnd.getValue().getYear();	
		int endMonth= (dateEnd.getValue().getMonthValue()-1);
		int endDay= dateEnd.getValue().getDayOfMonth();
		int endHour= convertHourInput(hourEnd.getText().toString());
		int endMin= convertMinInput(minEnd.getText().toString());
		
		cal.set(endYear, endMonth, endDay, endHour, endMin, 0);
		Date dateEndRange = cal.getTime(); // get back a Date object
		System.out.println(dateEndRange);
		
		System.out.println("End date: " + endYear+", "+endMonth+", "+endDay+", "+endHour+", "+endMin);
		
		for(Album a :User.getCurrentUser().getAlbumList()){
			for(Photo p: a.getPhotosInAlbum()){
				System.out.println("photo dates being searched on: "+p.getCalDate().toString());
				if(p.getCalDate().compareTo(dateBeginRange)>=0 && p.getCalDate().compareTo(dateEndRange)<=0){
					results.add(p);
				}
				else{
					continue;
				}
			}
		}
		return results;
	}
	//parse the input of text of hour and return 0 for anything not in the 0-23 range
	private static int convertHourInput(String str){
		int i;
		try{
			i=Integer.parseInt(str);
		}catch(Exception e){
			return 0;//if there is no int in the string
		}
		if(i>=0 && i<=23){
			return i;
		}
		else{
			return 0;
		}
	}
	
	//parse the input of text of hour and return 0 for anything not in the 0-59 range
	private static int convertMinInput(String str){
		int i;
		try{
			i=Integer.parseInt(str);
		}catch(Exception e){
			return 0;//if there is no int in the string
		}
		if(i>=0 && i<=59){
			return i;
		}
		else{
			return 0;
		}
	}
	
	
}//end of class
	