package controller;

import java.io.IOException;

import java.io.ObjectInputFilter.Status;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import utility.AlertSupport;
import javafx.scene.shape.Circle;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;









public class LoginController implements Initializable{

	@FXML
    private Circle circle;

	  @FXML
	    private Label lblStatus;

	    @FXML
	    private TextField tfPassword;

	    @FXML
	    private TextField tfUsername;
	    
	    
	  
    private List<User> userList;

   

    @FXML
    void processLogin(ActionEvent event) throws IOException {
    	String username = tfUsername.getText();
    	String password = tfPassword.getText();
    	
    	
    	lblStatus.setVisible(true);
    	if(username.isEmpty() || password.isEmpty() ) {
    		lblStatus.setTextFill(Paint.valueOf("red"));
    		lblStatus.setText("!!!  All fields are required");
    		
    		return;
    	}
    	
    	boolean valid = userList.stream().anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    	
    	if(valid) {

    		lblStatus.setVisible(false);
    		Alert successAlert = AlertSupport.getAlert(AlertType.INFORMATION, "Success", "Login Granted", "Welcome Admin, You can now manage school mangagement system");


    		successAlert.showAndWait();
    		
    		
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		stage.hide();
    		
    		Parent mainRoot = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
    		Scene mainScene = new Scene(mainRoot);
    		stage.setScene(mainScene);
//    		successAlert.showAndWait();
    		stage.setMaximized(true);
    		stage.setTitle("Host Myanmar Institute Page");
    		stage.show();
    	}else {

    		
    		Alert failAlert = AlertSupport.getAlert(AlertType.INFORMATION, "Fail", "Login Failed", "Username or Password is incorrect");
    		failAlert.show();
    		lblStatus.setVisible(false);
    		
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Image img = new Image("./image/hmilogo.jpg");
		circle.setFill(new ImagePattern(img));
		
		userList = Arrays.asList(
			new User("admin","1234")
				);
		
		

	}

}
