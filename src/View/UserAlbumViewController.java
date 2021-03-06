package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
/**
* UserAlbumViewController is the class associated with the User homepage for all albums.
* In this page the user can search for photos by tags or dates and see all existing albums.
* The albums show the date ranges, earliest photo, number of photos in album, and the name of the 
* album. The User can make a new album, open an existing album, and delete an album on this page
* @author  Tom Raybould & Mike Tomkowich
*/
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
	private TextField secStart;
	@FXML
	private DatePicker dateEnd;
	@FXML
	private TextField hourEnd;
	@FXML
	private TextField minEnd;
	@FXML
	private TextField secEnd;
	@FXML
	private Button searchTag;
	@FXML
	private Button searchDate;
	@FXML
	private TableView tableView;
	@FXML
	private ImageView firstPic;
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
	/**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
	public void start(Stage mainStage) throws IOException{
		Album.setCurrentAlbum(null);
		Album.setSearchResults(null);
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
        			(obs , oldVal, newVal) -> loadFirstPhoto());
	}
	/**
     * Updates data associated with screen
     * 
     * @return void
     */
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
		tableDateRange.setCellValueFactory(new PropertyValueFactory<>("dateRange"));
		tableView.setItems(obslist);
		
		tableView.getColumns().setAll(tableAlbumName,tableNumOfPhotos,tableEarliestPhoto,tableDateRange);
		
	}//end of update
	
	private void loadFirstPhoto(){
		if(tableView.getSelectionModel().getSelectedItem()==null){
			firstPic.setImage(null);
			return;
		}
		Album a =(Album)tableView.getSelectionModel().getSelectedItem();
		if(a.getNumOfPhotos()<=0){
			firstPic.setImage(null);
			return;
		}
		else if (a.getNumOfPhotos()>=1){
			Image img = new Image(a.getPhotosInAlbum().get(0).getSrc());
			firstPic.setImage(img);
			//firstPic.setFitHeight(110);
			firstPic.setFitWidth(150);
			return;
		}
		else{
			System.out.println("First image loader is not working");
			return;
		}
	}
	
	 /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
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
			for(Album a : User.getCurrentUser().getAlbumList()){
				if(newAlbumName.equals(a.getName())){
					makeAlertInfo("Invalid Album Name","","This album name is already taken");
					return;
				}
			}
			
			if(newAlbumName.equals("")){
				makeAlertInfo("No Album Name","","You must give your new album a name");
				return;
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
			if(tableView.getSelectionModel().getSelectedItem()==null){
				return;
			}
			if (!makeAlertConfirm("Deleting" , "", "Are you sure you want to delete this album?")){
				return;
			}
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
			
			if(newAlbumName==null){
				//if cancel was pressed the string is null
				return;
			}
			
			for(Album a : User.getCurrentUser().getAlbumList()){
				if(newAlbumName.equals(a.getName())){
					makeAlertInfo("Invalid Album Name","","This album name is already taken");
					return;
				}
			}
			
			if(newAlbumName.equals("")){
				makeAlertInfo("No Album Name","","You must give your album a name");
				return;
			}
			
			album.setName(newAlbumName);
			User.write();
			this.update();
		}
		else if(b == logOut){
			User.write();
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
			User.write();
			currentStage.close();
		}
		else if(b == searchTag){
			ArrayList<Photo> results= new ArrayList<Photo>();
			
			String tagVal = tagValue.getText();
			if (tagDropDown.getSelectionModel().getSelectedItem()==null){
				makeAlertInfo("Invalid Search","","You must select a tag type");
				return;
			}
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
	    				if(target.equals(t)||(target.getType().equals("All Tags")&& target.getValue().equals(t.getValue()))){
	    				
	    					System.out.println("In here");
	    					//will stop duplicates
	    					if(!results.contains(p)){
	    						results.add(p);
	    					}
	    					break;
	    				}
	    			}
	    		}
	    	}
	    	System.out.println("Search Results---------------------------");
	    	
	    	if(results==null||results.size()<=0){
				makeAlertInfo("No Results Found","","No photos contain that tag");
				return;
			}
	    	else{
	    		Comparator<Photo> c= (h,k)-> h.getCalDate().compareTo(k.getCalDate());
				results.sort(c);
		    	for(Photo p: results){
		    		System.out.println(p);
		    	}
	    		
	    		Album.setSearchResults(results);
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/SearchResults.fxml"));
		
	    		AnchorPane root = (AnchorPane)loader.load();
		
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
			
			if(result==null||result.size()<=0){
				makeAlertInfo("No Results Found","","You have no photos in this range");
				return;
			}
			
			
			else{
				Album.setSearchResults(result);
				
				FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/SearchResults.fxml"));
		
	    		AnchorPane root = (AnchorPane)loader.load();
		
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
	
	/**
     * Handles button and action events that occur on this screen
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
     * Handles button and action events that occur on this screen
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
     * Handles button and action events that occur on this screen
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
	
	
	/**
     *	Pulls the date range from the UI and searches for photos in that range 
     *	It then returns an array of all of the photos in that range	
     *
     * 
     * @return ArrayList<Photo> The photos in the date range
     */
	
	private ArrayList<Photo> getPhotosInRange(){
		ArrayList<Photo> results= new ArrayList<Photo>();
		if(dateStart.getValue()==null||dateEnd.getValue()==null){
			return null;
		}
		int startYear= dateStart.getValue().getYear();	
		int startMonth= (dateStart.getValue().getMonthValue()-1);
		int startDay= dateStart.getValue().getDayOfMonth();
		int startHour= convertHourInput(hourStart.getText().toString());
		int startMin= convertMinInput(minStart.getText().toString());
		int startSec= convertMinInput(secStart.getText().toString());
		
		System.out.println("Start date: " + startYear+", "+startMonth+", "+startDay+", "+startHour+", "+startMin+", "+startSec);
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(startYear, startMonth, startDay, startHour, startMin, startSec);
		Date dateBeginRange = cal.getTime(); // get back a Date object
		System.out.println(dateBeginRange);
		
		int endYear= dateEnd.getValue().getYear();	
		int endMonth= (dateEnd.getValue().getMonthValue()-1);
		int endDay= dateEnd.getValue().getDayOfMonth();
		int endHour= convertHourInput(hourEnd.getText().toString());
		int endMin= convertMinInput(minEnd.getText().toString());
		int endSec= convertMinInput(secEnd.getText().toString());
		
		cal.set(endYear, endMonth, endDay, endHour, endMin, endSec);
		Date dateEndRange = cal.getTime(); // get back a Date object
		System.out.println(dateEndRange);
		
		System.out.println("End date: " + endYear+", "+endMonth+", "+endDay+", "+endHour+", "+endMin+", "+endSec);
		
		for(Album a :User.getCurrentUser().getAlbumList()){
			for(Photo p: a.getPhotosInAlbum()){
				System.out.println("photo dates being searched on: "+p.getCalDate().toString());
				if(p.getCalDate().compareTo(dateBeginRange)>=0 && p.getCalDate().compareTo(dateEndRange)<=0){
					if(!results.contains(p)){
						results.add(p);
					}
				}
				else{
					continue;
				}
			}
		}
		return results;
	}
	/**
	 * parses the input of String for an int and returns it if it is in the range 
	 * of 0-23 , for the hours of search
     *
     * 
     * @return int returns the int parsed if it was in the range of 0-23 otherwise returns 0
     */
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
	
	/**
	 * parses the input of String for an int and returns it if it is in the range 
	 * of 0-59 , for the mins/secs of search
     *
     * 
     * @return int returns the int parsed if it was in the range of 0-59 otherwise returns 0
     */
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
	