
package View;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import Model.Admin;
import Model.User;
/**
* MainAdminController is the class associated with the screen for the Main administrator page.
* This screen allows the administrator to add and delete users.
* @author  Tom Raybould & Mike Tomkowich
*/
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
    
    @FXML
    private ListView<String> userList;

    private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedUser;
	
    /**
     * Loads current screen and data associated with the screen
     * 
     * @param mainStage stage that is passed to be loaded
     * 
     * @return void
     */
	public void start(Stage mainStage){
		this.update();
		currentStage = mainStage;
		
		userList
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedUser = userList.getSelectionModel().getSelectedItem();});
	}
	/**
     * Updates data associated with screen
     * 
     * @return void
     */
	public void update(){
		obslist = FXCollections.observableArrayList();
		
		for(User u : User.getAllUsers()){
			String s = new String(u.getUserName());
			String password = new String(u.getPassword());
			if(s==u.getUserName()){
				System.out.println("not working");
			}
			String namePasswordCombo = s + "-" + password;
			obslist.add(namePasswordCombo);
		}
		
		userList.setItems(obslist);
	}
	
	 /**
     * Handles button and action events that occur on this screen
     * 
     * @param e an Action event
     * 
     * @return void
     */
    public void handle(ActionEvent e) throws IOException{
    	Button b = (Button)e.getSource();
    	if(b == addUser){
    		System.out.println("addUser");
    		String name = username.getText().toString();
    		String pass = password.getText().toString();
    		
    		if(User.isInSystem(name)){
    			makeAlertInfo("Invalid User Name","","This user name is already taken");
    		}
    		else{
    			User u = new User(name, pass);
    			User.addUser(u);
    			User.write();
    			for(User K: User.getAllUsers()){
    			System.out.println(K);
    			}
    		}
    		this.update();

    	}
    	else if(b == deleteUser){
    		System.out.println(selectedUser);
    		String deleteThis = selectedUser.substring(0, selectedUser.indexOf("-"));
    		User.deleteUser(deleteThis);
    		this.update();
    	}
		else if(b == logOut){
			System.out.println("log out");
			User.write();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			LoginPageController LoginPage=loader.getController();
			LoginPage.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
		}
		else if(b == safeQuit){
			User.write();
			currentStage.close();
		}
    }

	public TextField getUsername() {
		return username;
	}
	
	private void makeAlertInfo(String errorTitle, String errorHeader, String errorContent) {    
    	
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.initOwner(null);
		   alert.setTitle(errorTitle);
		   alert.setHeaderText(errorHeader);
		   alert.setContentText(errorContent);
		   alert.showAndWait();
	}

}

