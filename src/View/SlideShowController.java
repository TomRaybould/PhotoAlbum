package View;

import java.util.ArrayList;

import Model.Album;
import Model.Photo;
import Model.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
* SlideShowController is the class associated with the screen for displaying photos
* in a slideshow. The photos are shown in order and the user moves along the slideshow
* by clicking the next or last button
* @author  Tom Raybould & Mike Tomkowich
*/
public class SlideShowController {

	@FXML
    private Button lastImage1;

    @FXML
    private Button nextImage;

    @FXML
    private Text caption;

    @FXML
    private Text date;

    @FXML
    private ImageView image;
    
    @FXML
    private ListView<String> listView;

    private ObservableList<String> obslist;
    
    private String selectedTag;
    
    private Stage currentStage;
    
    private int count;
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
    public void start(Stage mainStage){
		currentStage = mainStage;
		Album a = Album.getCurrentAlbum();
		ArrayList<Photo> photos = a.getPhotosInAlbum();
		Photo start = photos.get(0); ///need to have indexes or dates for slideshow
		System.out.println(start);
		Image anImage = new Image(start.getSrc());
		image.setImage(anImage);
		caption.setText(start.getCaption());
		date.setText(start.getCalDate().toString());
		count = 0; //will equal index
		//listview of tags
		this.update(start);
		currentStage.show();
		listView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedTag = listView.getSelectionModel().getSelectedItem();});
	}
    /**
     * Updates data associated with screen
     * 
     * @return void
     */
    public void update(Photo p){
		obslist = FXCollections.observableArrayList();
		for(Tag t : p.getTags()){
			String type = t.getType();
			String val = t.getValue();
			String addThis = type + "-" + val;
			obslist.add(addThis);
		}
		
		listView.setItems(obslist);
	}
	
    /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    @FXML
    void handle(ActionEvent e){
    	Button b= (Button)e.getSource();
    	if(b == nextImage){
    		count++;
    		if(count > Album.getCurrentAlbum().getPhotosInAlbum().size() - 1){
    			//count too large, so block action
    			count--;
    		}
    		else{
    			Photo curr = Album.getCurrentAlbum().getPhotosInAlbum().get(count);
    			Image anImage = new Image(curr.getSrc());
    			image.setImage(anImage);
    			caption.setText(curr.getCaption());
    			date.setText(curr.getCalDate().toString());
    			this.update(curr);
    		}
    		
    		
    	}
    	else if(b == lastImage1){
    		count--;
    		if(count < 0){
    			//count too small, so block action
    			count++;
    		}
    		else{
    			Photo curr = Album.getCurrentAlbum().getPhotosInAlbum().get(count);
    			Image anImage = new Image(curr.getSrc());
    			image.setImage(anImage);
    			caption.setText(curr.getCaption());
    			date.setText(curr.getCalDate().toString());
    			this.update(curr);
    		}
    		
    	}
    }

}
