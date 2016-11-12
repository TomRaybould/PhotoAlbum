package View;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public void start(Stage mainStage) throws MalformedURLException{
    	//Image View
    	System.out.println("here");
    	int imgTotal;
    	int imgPosition = 0;
    	ImageView imgMain = new ImageView();
		ImageView imgThumb = new ImageView();
		imgThumb.setFitHeight(100);
		imgThumb.setFitWidth(200);
		// List of Images
		System.out.println("here");
		List<File> images = new ArrayList<File>();
		//File chooser
		final FileChooser fileChooser = new FileChooser();
    		File file = fileChooser.showOpenDialog(mainStage);
    		if(file.isFile() &&
    				(file.getName().contains(".jpg") || file.getName().contains(".png") || file.getName().contains(".hmp") ||
    						file.getName().contains(".gif"))){
    			System.out.println("In file success");
    			images.add(file);
    			imgTotal = images.size();
    			
    			if(imgTotal > 1){
    				imgPosition++;
    			}
    			String thumbURL = file.toURI().toURL().toString();
        		Image imgLoad = new Image(thumbURL);
        		//pass image to ImageView
        		image.setImage(imgLoad);
        		Photo photo = new Photo("dummy date",thumbURL);
        		mainStage.show();
    		}
    			
   }
   
    public void handle(ActionEvent e) throws IOException{ 
    	Button b = (Button)e.getSource();
 
    	
    }

}