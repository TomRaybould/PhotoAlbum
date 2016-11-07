package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginPageController {
	int imgTotal;
	int imgPosition;
    @FXML
    private Button user;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button admin;
    
    @FXML
    private Button newAdmin;

    @FXML
    private Button newUser;
    
  
   
    public void start(Stage mainStage) throws MalformedURLException{
    	//Image View
    	ImageView imgMain = new ImageView();
		ImageView imgThumb = new ImageView();
		imgThumb.setFitHeight(100);
		imgThumb.setFitWidth(200);
		// List of Images
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
        		imgThumb.setImage(imgLoad);
        		mainStage.show();
    		}
    						
    		
    		
    		
    		
    		
    		String imgURL = images.get(imgPosition).toURI().toURL().toString();
    		Image imgLoad2 = new Image(imgURL, 500, 300, true, true, true);
    		imgMain.setImage(imgLoad2);
    			
   }
    

    @FXML
    public void handle(ActionEvent e) throws IOException{ 
    	System.out.println("b");
    	String name;
    	String passwordInput;
    	User u;
    	Button b = (Button)e.getSource();
    	
    
    	if(b == user){
    		System.out.println("Log in button was pressed for user");
    		name = username.getText();
    		passwordInput = password.getText();
    		u = new User(name, passwordInput);
    		boolean exists = User.checkIfInSystem(u);
    		if(exists){
    			System.out.println("User is in system " + name +passwordInput);
    		}
    		else{
    			System.out.println("User is not in system");
    		}
    		username.setText("User");
    	}
    	else if(b == admin){
    		System.out.println("Button was pressed for admin");
    		name = username.getText();
    		passwordInput = password.getText();
    		u = new User(name, passwordInput);
    		boolean exists = User.checkIfInSystem(u);
    		if(exists){
    			System.out.println("User is in system");
    		}
    		else{
    			System.out.println("User is not in system");
    		}
    		username.setText("Admin");
    	}
    	else if(b == newAdmin){
    		System.out.println("Button was pressed for new Admin");
    		name = username.getText();
    		passwordInput = password.getText();
    		u = new User(name, passwordInput);
    		User.addUser(u);
    		username.setText("new Admin");
    	}
    	else if(b == newUser){
    		System.out.println("Button was pressed for new User");
    		name = username.getText();
    		passwordInput = password.getText();
    		u = new User(name, passwordInput);
    		User.addUser(u);
    		User.write(u);
    		username.setText("new User");
    	}
    }

}
