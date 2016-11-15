package View;

import java.util.ArrayList;

import Model.Album;
import Model.Photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    
    private Stage currentStage;
    
    private int count;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
		Album a = Album.getCurrentAlbum();
		ArrayList<Photo> photos = a.getPhotosInAlbum();
		Photo start = photos.get(0); ///need to have indexes or dates for slideshow
		Image anImage = new Image(start.getSrc());
		image.setImage(anImage);
		caption.setText(start.getCaption());
		date.setText(start.getDate());
		count = 0; //will equal index
	}

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
    			date.setText(curr.getDate());
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
    			date.setText(curr.getDate());
    		}
    	}
    }

}
