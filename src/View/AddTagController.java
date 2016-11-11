package View;

import java.io.IOException;
import java.util.ArrayList;

import Model.Album;
import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddTagController {


    @FXML
    private ComboBox<String> tagDropDown;

    @FXML
    private TextField tagText;

    @FXML
    private Button addTag;

    @FXML
    private Button addTypeToList;

    @FXML
    private Text newTagType;

    @FXML
    private TextField newTagTypeText;

    @FXML
    private Text currentTagType;
    
    private Album a;
    
    private User u;
    
    private Photo p;
    
    private Tag t;
    
    private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
    
    private Stage currentStage;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
		u = User.getCurrentUser();
		System.out.println("Current user in Photo view " + u);
		a = Album.getCurrentAlbum();
		System.out.println("Current Album in Photo view is: " + a);
		p = Photo.getCurrentPhoto();
		System.out.println("Current Photo in add tag is: " + p);
		myComboBoxData.add(new String("Location"));
		myComboBoxData.add(new String("Weather"));
		myComboBoxData.add(new String("Food"));
		myComboBoxData.add(new String("Other"));
		tagDropDown.setItems(myComboBoxData);
			currentStage.show();
	}
    
    @FXML
    private void handleComboBoxAction() {
      String tag= tagDropDown.getSelectionModel().getSelectedItem();
      currentTagType.setText(tag);
    }
    
    public void handle(ActionEvent e) throws IOException{
    	Button b = (Button)e.getSource();
    	if(b == addTag){
    		System.out.println("add Tag");
    		String tagType = currentTagType.getText();
    		String tag = tagText.getText();
    		p = Photo.getCurrentPhoto();
    		t = new Tag(tagType, tag);
    		p.addTag(t);
    		ArrayList<Tag> photoTags = p.getTags();
    		for(Tag tag1: photoTags){
    			System.out.println(tag1);
    		}
    		FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
		
			GridPane root = (GridPane)loader.load();
			
			PhotoViewController PhotoView=loader.getController();
			PhotoView.start(currentStage);
			Scene scene = new Scene(root);
			currentStage.setScene(scene);
    	}
    	else if(b == addTypeToList){
    		System.out.println("Create new tag type");
    		
    	}
    }

}