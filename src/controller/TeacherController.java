package controller;

import java.io.IOException;

import java.net.URL;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Teacher;
import model.TeacherDAO;
import utility.AlertSupport;

public class TeacherController implements Initializable {
//	private static boolean  updateStatus = false;

    @FXML
    private TableColumn<Teacher, String> address;

    @FXML
    private TableColumn<Teacher,LocalDate> birthDate;

    @FXML
    private Button btsave;

    @FXML
    private Button btdelete;

    @FXML
    private Button btedit;
    
    @FXML
    private Button btclear;
    
    @FXML
    private Button btnew;
    
    @FXML
    private Button btrefresh;
    
    @FXML
    private Button btview;
    
    @FXML
    private ComboBox<String> comboGender;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private TableColumn<Teacher, String> email;

    @FXML
    private TableColumn<Teacher, String> gender;

    @FXML
    private TableColumn<Teacher, Long> id;

    @FXML
    private TableColumn<Teacher, String> name;

    @FXML
    private TableColumn<Teacher, String> phone;

    @FXML
    private TableView<Teacher> teacherTable;
    
    @FXML
    private TextField tfaddress;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfphone;

    @FXML
    private TextField tfsearch;
    
    
    private final TeacherDAO teacherDAO = new TeacherDAO();
    
    private boolean isNewButtonClicked = false;
//    private Long teacherId =0L;
    
    private void disable() {
    	tfaddress.setDisable(true);
    	tfemail.setDisable(true);
    	tfid.setDisable(true);
    	tfname.setDisable(true);
    	tfphone.setDisable(true);
    	comboGender.setDisable(true);
    	dpBirthDate.setDisable(true);
    	btclear.setDisable(true);
    	btsave.setDisable(true);
    }
    
    private void enable() {
    	tfaddress.setDisable(false);
    	tfemail.setDisable(false);
    	tfid.setDisable(false);
    	tfname.setDisable(false);
    	tfphone.setDisable(false);
    	comboGender.setDisable(false);
    	dpBirthDate.setDisable(false);
    	btclear.setDisable(false);
    	btsave.setDisable(false);
    }
    
    @FXML
    void image(MouseEvent event) {
    	String searchQuery = tfsearch.getText().trim();
    	teacherTable.setItems(teacherDAO.getAllTeachersByName(searchQuery));
    }
    
   
    
   
    private void clear() {
    	tfid.clear();
    	tfname.clear();
    	tfemail.clear(); 	
//    	comboGender.setValue("Gender");
//    	dpBirthDate.setValue(LocalDate.now());
    	tfphone.clear();
    	tfaddress.clear();
    }
    
    @FXML
    void processclear(ActionEvent event) {
    	clear();
    }
    
    @FXML
    void processdelete(ActionEvent event) {
    	
    	Alert alert = AlertSupport.getAlert(AlertType.CONFIRMATION,"Confirm","Delete Confirmation", "Are you sure you want to delete");
		alert.getButtonTypes().clear();
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		alert.getButtonTypes().addAll(buttonYes,buttonNo);
		
		Optional<ButtonType> resultButton = alert.showAndWait();
		if (resultButton.isPresent() && resultButton.get().equals(buttonNo)) {
			return;
		}
		if (resultButton.isEmpty()) {
			return;
		}
    	//clear();
    	Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTeacher == null) {
    		return;
    	}
    	
    	boolean deleted = teacherDAO.deleteTeacher(selectedTeacher.getId());
    	if(deleted) {
    		teacherTable.setItems(teacherDAO.getAllTeachers());
    	}else {
    		System.out.println("Fail to delete student");
    	}
    }
    
    @FXML
    void processedit(ActionEvent event) {
    	
    	Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTeacher == null) {
    		return;
    	}
    	enable();
    	isNewButtonClicked= false;
    	
//    	teacherId = selectedTeacher.getId();
    	tfid.setText(String.valueOf(selectedTeacher.getId()));
    	tfname.setText(selectedTeacher.getName());
    	tfemail.setText(selectedTeacher.getEmail());
    	comboGender.setValue(selectedTeacher.getGender());
    	dpBirthDate.setValue(selectedTeacher.getBirthDate());
    	tfphone.setText(selectedTeacher.getPhone());
    	tfaddress.setText(selectedTeacher.getAddress());
    }
    
    @FXML
    void processnew(ActionEvent event) {
    	
    	enable();
    	isNewButtonClicked = true;

    }
    
    @FXML
    void processrefresh(ActionEvent event) {
    	teacherTable.setItems(teacherDAO.getAllTeachers());
    	tfsearch.clear();

    }
    
    @FXML
    void processview(ActionEvent event) throws IOException {
    	Teacher sellectTeacher = teacherTable.getSelectionModel().getSelectedItem();
    	if(sellectTeacher == null) {
    		return;
    	}
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TDetails.fxml"));
    	
    	Parent root = loader.load();
    	TDetailsController tdetailsController = loader.getController();
    	tdetailsController.initData(sellectTeacher.getId());
    	
    	Stage stage = new Stage();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.setTitle("Teacher Details");
    	stage.show();
    	

    }
    



    
    @FXML
    void processsave(ActionEvent event) {
    	
    	Long id = Long.parseLong(tfid.getText().trim());
    	String name = tfname.getText().trim();
    	String email = tfemail.getText().trim();
    	String gender = comboGender.getValue();
    	LocalDate dateofbirth = dpBirthDate.getValue();
    	String phone =tfphone.getText().trim();
    	String  address = tfaddress.getText().trim();
    	
    	if(isNewButtonClicked) {
    	
    	Teacher teacher = new Teacher(id, name, email, gender, dateofbirth, phone, address);
    	
    	boolean created = teacherDAO.createTeacher(teacher);
    	if(created) {
    		clear();
    		disable();
    		comboGender.setValue("Gender");
        	dpBirthDate.setValue(LocalDate.now());
    		teacherTable.setItems(teacherDAO.getAllTeachers());
    	}else {
    		System.out.println("fail to create teacher");
    	}
   
    } 
    	else {
    		Teacher teacher = new Teacher(id, name, email, gender, dateofbirth, phone, address);
        	
        	boolean updated = teacherDAO.updateTeacher(teacher);
        	
        	if(updated) {
        		clear();
        		disable();
        		
        		teacherTable.setItems(teacherDAO.getAllTeachers());
        	}else {
        		System.out.println("fail to update teacher");
        	}
    	}
			
		}
  
   
   

   

   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> genderList = FXCollections.observableArrayList(
				"Male","Female"
				);
		
		comboGender.setItems(genderList);
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		
		teacherTable.setItems(teacherDAO.getAllTeachers());
		dpBirthDate.setValue(LocalDate.now());
		disable();
		
		
		
	}

	

}
