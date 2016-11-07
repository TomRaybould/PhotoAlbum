package application;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class DisplayPicture extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("poop");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/DisplayPicture.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		
		DisplayPictureController DisplayController=loader.getController();
	//	DisplayController.start(primaryStage);
		Scene scene = new Scene(root);
	//shows image to start	
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/pics/snow.jpg")));
		root.getChildren().add(image);
		
		
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		System.out.println("poop");
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}