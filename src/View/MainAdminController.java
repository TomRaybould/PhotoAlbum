
package View;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainAdminController {

    @FXML
    private Button addUser;

    @FXML
    private Button deleteUser;

    @FXML
    private Button logOut;

    @FXML
    private Button safeQuit;
    
    
    public void start(Stage mainStage){
    	
    }
    
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == addUser){
    		System.out.println("addUser");
    	}
    	else if(b == deleteUser){
    		System.out.println("deleteUser");
    		
    	}
		else if(b == logOut){
			System.out.println("log out");
		    		
		}
		else if(b == safeQuit){
			System.out.println("safe quit");
			
		}
    }

}

