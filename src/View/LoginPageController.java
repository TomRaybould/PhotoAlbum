package View;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
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
		
		User dummy= new User("Dummy","dummy");
		User.addUser(dummy);
		
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
				currentStage.centerOnScreen();
			}
			else{//normal user
				
				String userName = userNameField.getText().toString();
				String pass = passwordField.getText().toString();
				System.out.println("*"+userName+"*"+pass+"*");
				
				User user= null;
				
				user= User.searchUser(userName,pass);
				
				if(user!= null){
					User.setCurrentUser(user);
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/UserAlbumView.fxml"));
				
					AnchorPane root = (AnchorPane)loader.load();
					
					UserAlbumViewController albumView=loader.getController();
					albumView.start(currentStage);
					Scene scene = new Scene(root);
					currentStage.setScene(scene);
					currentStage.centerOnScreen();
					
				}
			
				else{
					makeAlertInfo("User Not Found","","This username/password combination is no found");
				}
			}
		}
	}//end of handle
	private void makeAlertInfo(String errorTitle, String errorHeader, String errorContent) {    
    	
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.initOwner(null);
		   alert.setTitle(errorTitle);
		   alert.setHeaderText(errorHeader);
		   alert.setContentText(errorContent);
		   alert.showAndWait();
	}
}//end of class
