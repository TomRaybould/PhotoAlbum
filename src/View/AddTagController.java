package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
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
    
    private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
    
    private Stage currentStage;
    
    public void start(Stage mainStage){
		currentStage = mainStage;
		myComboBoxData.add(new String("option1"));
		myComboBoxData.add(new String("option2"));
		myComboBoxData.add(new String("option3"));
		myComboBoxData.add(new String("option4"));
		tagDropDown.setItems(myComboBoxData);
			currentStage.show();
	}
    
    @FXML
    private void handleComboBoxAction() {
      String tag= tagDropDown.getSelectionModel().getSelectedItem();
      currentTagType.setText(tag);
    }
    
    public void handle(ActionEvent e){
    	Button b = (Button)e.getSource();
    	if(b == addTag){
    		System.out.println("add Tag");
    	}
    	else if(b == addTypeToList){
    		System.out.println("Create new tag type");
    		
    	}
    }

}