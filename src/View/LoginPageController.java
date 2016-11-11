package View;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import Model.User;

public class LoginPageController {
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;

	private Stage currentStage;
	
	public void start(Stage mainStage){
		currentStage = mainStage;
	}
	
	public void handle(ActionEvent e) throws Exception{
		Button b = (Button)e.getSource();
		if(b == loginButton){
			if(userNameField.getText().toString().equalsIgnoreCase("admin") && passwordField.getText().toString().equalsIgnoreCase("admin")){
				// admin screen
				System.out.println("admin");
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/MainAdmin.fxml"));
			
				AnchorPane root = (AnchorPane)loader.load();
				
				MainAdminController MainAdmin=loader.getController();
				MainAdmin.start(currentStage);
				Scene scene = new Scene(root);
				currentStage.setScene(scene);
			}
			else{//normal user
				
				String userName = userNameField.getText().toString();
				String pass = passwordField.getText().toString();
				System.out.println("*"+userName+"*"+pass+"*");
				User u= new User(userName,pass);
				
				if(User.isInSystem(u)==true){
					User.setCurrentUser(User.searchUser(userName, pass));
				}
				//load user main page
			
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/UserAlbumView.fxml"));
			
				BorderPane root = (BorderPane)loader.load();
				
				UserAlbumViewController albumView=loader.getController();
				albumView.start(currentStage);
				Scene scene = new Scene(root);
				currentStage.setScene(scene);
			}
		}
	}
}
