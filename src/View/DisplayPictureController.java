package View;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import Model.Album;
import Model.Photo;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DisplayPictureController {

    @FXML
    private Text caption;

    @FXML
    private Text date;

    @FXML
    private ImageView image;
    
    private Stage currentStage;
    
    public void start(Stage mainStage) throws MalformedURLException{
    	currentStage = mainStage;
		System.out.println("Current user in Photo view " + User.getCurrentUser());
		System.out.println("Current Album in Photo view is: " + Album.getCurrentAlbum());
		System.out.println("Current Photo in add tag is: " + Photo.getCurrentPhoto());
		Photo p = Photo.getCurrentPhoto();
		Image anImage = new Image(p.getSrc());
		image.setImage(anImage);
		caption.setText(p.getCaption());
		date.setText(p.getDate());
    			
   }
   
    public void handle(ActionEvent e) throws IOException{ 
    	Button b = (Button)e.getSource();
 
    	
    }

}