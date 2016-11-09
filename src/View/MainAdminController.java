
package View;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import Model.Admin;
import Model.User;

public class MainAdminController {

    @FXML
    private Button addUser;

    @FXML
    private Button deleteUser;

    @FXML
    private Button logOut;

    @FXML
    private Button safeQuit;
    
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private Stage currentStage;
	
	public void start(Stage mainStage){
		currentStage = mainStage;
	}
    
    public void handle(ActionEvent e) throws IOException{
    	Button b = (Button)e.getSource();
    	if(b == addUser){
    		System.out.println("addUser");
    		String name = username.getText();
    		String pass = password.getText();
    		User u = new User(name, pass);
    		User.addUser(u);
    		for(User K: User.allUsers){
    			System.out.println(K);
    		}
    	}
    	else if(b == deleteUser){
    		System.out.println("deleteUser");
    		String name = username.getText();
    		String pass = password.getText();
    		User u = new User(name, pass);
    		User.deleteUser(u);
    		for(User K: User.allUsers){
    			System.out.println(K);
    		}	
    	}
		else if(b == logOut){
			System.out.println("log out");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			LoginPageController LoginPage=loader.getController();
			LoginPage.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
		}
		else if(b == safeQuit){
			System.out.println("safe quit");
		}
    }
}

