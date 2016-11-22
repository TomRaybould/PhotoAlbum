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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
* DisplayPictureController is the class associated with the screen for displaying a selected picture.
* The screen will show the date, caption and tags associated with the selected photo.
* @author  Tom Raybould & Mike Tomkowich
*/
public class DisplayPictureController {

	@FXML
    private Text caption;

    @FXML
    private Text date;

    @FXML
    private ImageView image;

    @FXML
    private ListView<String> listOfTags;
    
    private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedTag;
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
    public void start(Stage mainStage) throws MalformedURLException{
    	currentStage = mainStage;
		System.out.println("Current user in Photo view " + User.getCurrentUser());
		System.out.println("Current Album in Photo view is: " + Album.getCurrentAlbum());
		System.out.println("Current Photo in add tag is: " + Photo.getCurrentPhoto());
		Photo p = Photo.getCurrentPhoto();
		Image anImage = new Image(p.getSrc());
		image.setImage(anImage);
		caption.setWrappingWidth(300);
		caption.setText(p.getCaption());
		date.setText(p.getCalDate().toString());
    	//listview of tags
		this.update();
		currentStage.show();
		listOfTags
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedTag = listOfTags.getSelectionModel().getSelectedItem();});
	}
    /**
     * Updates data associated with screen
     * 
     * @return void
     */
    public void update(){
		obslist = FXCollections.observableArrayList();
		Photo p = Photo.getCurrentPhoto();
		for(Tag t : p.getTags()){
			String type = t.getType();
			String val = t.getValue();
			String addThis = type + "-" + val;
			obslist.add(addThis);
		}
		
		listOfTags.setItems(obslist);
	}
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    public void handle(ActionEvent e) throws IOException{ 
    	Button b = (Button)e.getSource();
 
    	
    }

}