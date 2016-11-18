package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Model.Album;
import Model.User;
import View.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginPage extends Application {
	private static final String storeDirUser = "dat";
	private static final String storeFileUser= "Users.dat";
	private static final String storeDirAlbum = "dat";
	private static final String storeFileAlbum = "Albums.dat";
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		//////user load
		  ObjectInputStream oisUser; 
			oisUser = new ObjectInputStream(
		            new FileInputStream(storeDirUser + File.separator + storeFileUser));
			User u;
		    try {
		        while ((u = (User) oisUser.readObject()) != null) {
		            User.addUser(u);
		        }
		    } catch (EOFException e) {
		
		    } finally {
		        oisUser.close();
		    }
		
		    
			for(User k: User.getAllUsers()){
				System.out.println(k+"test");
		}
		/////album load
			ObjectInputStream oisAlbum; 
			oisAlbum = new ObjectInputStream(
		            new FileInputStream(storeDirAlbum + File.separator + storeFileAlbum));
			Album a;
		    try {
		        while ((a = (Album) oisAlbum.readObject()) != null) {
		            for(User k: User.getAllUsers()){
		            	ArrayList<Album> temp = new ArrayList<Album>();
		            	temp =k.getAlbumList();
		            	for(Album album: temp){
		            		if(a.getName().equals(album.getName())){
		            			k.addAlbum(album);
		            		}
		            	}
		            }
		        }
		    } catch (EOFException e) {
		
		    } finally {
		        oisUser.close();
		    }
		
			
		LoginPageController loginController=loader.getController();
		loginController.start(primaryStage);
		Scene scene = new Scene(root);
	
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}