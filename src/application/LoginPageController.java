package application;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginPageController {
	
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
    
  
    
   // public void start(mainStage){
    	
  // }
    

    @FXML
    public void handle(ActionEvent e) { 
    	System.out.println("b");
    	  String name;
    	    String passwordInput;
    	    Users u;
		username.setText("User");
    	Button b = (Button)e.getSource();
    	if(b == user){
    		System.out.println("Log in button was pressed for user");
    		name = username.getText();
    		passwordInput = password.getText();
    		u = new Users(name, passwordInput);
    		boolean exists = Users.checkIfInSystem(u);
    		if(exists){
    			System.out.println("User is in system");
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
    		u = new Users(name, passwordInput);
    		boolean exists = Users.checkIfInSystem(u);
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
    		u = new Users(name, passwordInput);
    		u.setAdmin();
    		Users.addUser(u);
    		username.setText("new Admin");
    	}
    	else if(b == newUser){
    		System.out.println("Button was pressed for new User");
    		name = username.getText();
    		passwordInput = password.getText();
    		u = new Users(name, passwordInput);
    		Users.addUser(u);
    		username.setText("new User");
    	}
    }

}
