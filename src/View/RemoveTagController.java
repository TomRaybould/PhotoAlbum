package View;

import java.util.ArrayList;

import Model.Photo;
import Model.Tag;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RemoveTagController {

	  @FXML
	  private Button removeTags;

	  @FXML
	  private ListView<String> list;
    
    private Stage currentStage;
    
    private ObservableList<String> obslist;
    
    private String selectedTag;
    
    public void start(Stage mainStage){
    	this.update();
		currentStage = mainStage;
		System.out.println("poopfart");
		currentStage.show();
		list
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (obs , oldVal, newVal) -> 
        	{this.selectedTag = list.getSelectionModel().getSelectedItem();});
		
	}
    
    public void update(){
		obslist = FXCollections.observableArrayList();
		Photo p = Photo.getCurrentPhoto();
		for(Tag t : p.getTags()){
			String type = t.getType();
			String val = t.getValue();
			String addThis = type + "|" + val;
			obslist.add(addThis);
		}
		
		list.setItems(obslist);
	}

    @FXML
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == removeTags){
    		System.out.println("remove Tag");
    		String type = selectedTag.substring(0, selectedTag.indexOf("|"));
    		String value = selectedTag.substring(selectedTag.indexOf("|") + 1, selectedTag.length());
    		System.out.println("type -" + type + "- ");
    		System.out.println("value -" + value + "- ");
    		Tag t = new Tag(type, value);
    		
    		ArrayList<Tag> tags =Photo.getCurrentPhoto().getTags();
    		for(Tag tag: tags){
    			if(t.getType().equals(tag.getType()) && t.getValue().equals(tag.getValue())){
    				tags.remove(tag);
    			}
    		}
    		Photo.getCurrentPhoto().setTags(tags);
    		currentStage.close();
    		
    	}
    }

}