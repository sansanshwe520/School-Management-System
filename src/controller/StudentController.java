package controller;

import java.io.IOException;

import java.net.URL;

import java.time.LocalDate;
//import java.util.Iterator;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StudentDAO;
//import model.Teacher;
import utility.AlertSupport;
import model.Academic;
import model.AcademicDAO;
import model.Batch;
import model.BatchDAO;
import model.Student;

public class StudentController implements Initializable {

	@FXML
	private TableColumn<Student, String> academicyear;

	@FXML
	private TableColumn<Student, String> address;

	@FXML
	private TableColumn<Student, String> batch;

	@FXML
	private TableColumn<Student, LocalDate> birthDate;

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
	private ComboBox<String> comboAcademic;

	@FXML
	private ComboBox<String> comboBatch;

	@FXML
	private ComboBox<String> comboGender;

	@FXML
	private DatePicker dpBirthDate;

	@FXML
	private TableColumn<Student, String> email;

	@FXML
	private TableColumn<Student, String> gender;

	@FXML
	private TableColumn<Student, String> id;

	@FXML
	private TableColumn<Student, String> name;

	@FXML
	private TableColumn<Student, String> parentName;

	@FXML
	private TableColumn<Student, String> phone;

	@FXML
	private TextField tfAddress;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfParentName;

	@FXML
	private TextField tfPhone;

	@FXML
	private TextField tfSearch;

	@FXML
	private TableView<Student> studentTable;
	
	private final StudentDAO studentDAO = new StudentDAO();
	private final AcademicDAO academicDAO = new AcademicDAO();
	private final BatchDAO batchDAO = new BatchDAO();
	private boolean isNewButtonClicked = false;
//	private Long studentID = 0L;
	
	 private void disable() {
		 tfAddress.setDisable(true);
		 tfEmail.setDisable(true);
		 tfId.setDisable(true);
		 tfName.setDisable(true);
		 tfParentName.setDisable(true);
		 tfPhone.setDisable(true);
		 dpBirthDate.setDisable(true);
		 comboAcademic.setDisable(true);
		 comboBatch.setDisable(true);
		 comboGender.setDisable(true);
		 btclear.setDisable(true);
		 btsave.setDisable(true);

		 
	   
	    }
	    
	    private void enable() {
	    	 tfAddress.setDisable(false);
			 tfEmail.setDisable(false);
			 tfId.setDisable(false);
			 tfName.setDisable(false);
			 tfParentName.setDisable(false);
			 tfPhone.setDisable(false);
			 dpBirthDate.setDisable(false);
			 comboAcademic.setDisable(false);
			 comboBatch.setDisable(false);
			 comboGender.setDisable(false);
			 btclear.setDisable(false);
			 btsave.setDisable(false);
	    }
//	private static boolean updateStatus = false;

	@FXML
	void image(MouseEvent event) {
		String searchQuery = tfSearch.getText().trim();
    	studentTable.setItems(studentDAO.getAllStudentByNameSearch(searchQuery));;
    	studentTable.setItems(studentDAO.getAllStudentByBatchName(searchQuery));
	}

	private void clear() {
		tfId.clear();
		tfName.clear();
		tfParentName.clear();
		tfEmail.clear();
		
		tfPhone.clear();
		tfAddress.clear();
	}
	
	@FXML
    void processclear(ActionEvent event) {
    	clear();
    }
	
	 
    @FXML
    void processview(ActionEvent event) throws IOException {
    	Student selStudent = studentTable.getSelectionModel().getSelectedItem();
    	if(selStudent == null) {
    		return;
    	}
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SDetails.fxml"));
    	Parent root = loader.load();
    	SDetailsController sdetailsController = loader.getController();
    	sdetailsController.initData(selStudent.getId());
    	
    	Stage stage = new Stage();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.setTitle("Student  Details");
    	stage.show();
    	

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
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();

		if (selectedStudent == null) {
			return;
		}

		boolean deleted = studentDAO.deleteStudent(selectedStudent.getId());
		if (deleted) {
			studentTable.setItems(studentDAO.getAllStudent());
		} else {
			System.out.println("Fail to delete student");
		}
	}
	

	@FXML
	void processedit(ActionEvent event) {
    	Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedStudent == null) {
    		return;
    	}
    	enable();
    	isNewButtonClicked= false;
    	tfId.setText(String.valueOf(selectedStudent.getId()));
    	tfName.setText(selectedStudent.getName());
    	tfParentName.setText(selectedStudent.getParentName());
    	tfEmail.setText(selectedStudent.getEmail());
    	comboGender.setValue(selectedStudent.getGender());
    	dpBirthDate.setValue(selectedStudent.getBirthDate());
    	tfPhone.setText(selectedStudent.getPhone());
    	tfAddress.setText(selectedStudent.getAddress());
    	comboAcademic.setValue(selectedStudent.getAcademicyear().toString());
    	comboBatch.setValue(selectedStudent.getBatch().toString());
	}
	
	@FXML
    void processnew(ActionEvent event) {
    	
    	enable();
    	isNewButtonClicked = true;

    }
    
    @FXML
    void processrefresh(ActionEvent event) {
    	studentTable.setItems(studentDAO.getAllStudent());
    	tfSearch.clear();

    }
    

	@FXML
	void processsave(ActionEvent event) {
		Long id = Long.parseLong(tfId.getText().trim());
		String name = tfName.getText().trim();
		String parentName = tfParentName.getText().trim();
		String email = tfEmail.getText().trim();
		String gender = comboGender.getValue();
		LocalDate dateofbirth = dpBirthDate.getValue();
		String phone = tfPhone.getText().trim();
		String address = tfAddress.getText().trim();
		String batchName = comboBatch.getValue();
		String academicName = comboAcademic.getValue();
		
		
		Batch batch=batchDAO.getBatchByBatchName(batchName);
		Academic academic = academicDAO.getAcademicByName(academicName);
	
		
		if(isNewButtonClicked) {
			Student student =new Student(id, name, parentName, email, gender, dateofbirth, phone, address, batch ,academic);
			System.out.println("in the process save"+student);

			boolean created = studentDAO.createStudent(student);    	
	    	if(created) {
	    		clear();
	    		disable();
	    		comboGender.setValue("Gender");
	    		comboAcademic.setValue("Academic Year");
	    		comboBatch.setValue("Batch");
	    		dpBirthDate.setValue(LocalDate.now());
	    		studentTable.setItems(studentDAO.getAllStudent());
	    	}else {
	    		System.out.println("fail to create student");
	    	}			
		}		
		else {
			Student student = new Student(id,name, parentName, email, gender, dateofbirth, phone, address, batch,academic);			
			boolean updated = studentDAO.updateStudent(student);	    	
	    	if(updated) {
	    		clear();
	    		disable();
	    		studentTable.setItems(studentDAO.getAllStudent());
	    	}else {
	    		System.out.println("fail to update student");
	    	}
		}
	}

	

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ObservableList<String> genderList = FXCollections.observableArrayList(
				"Male", "Female"
				);
		
		 comboGender.setItems(genderList);
		

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		parentName.setCellValueFactory(new PropertyValueFactory<>("parentName"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
//		TableColumn<Student, String> academicyearname;
		academicyear.setCellValueFactory(new PropertyValueFactory<>("academicyear"));
		batch.setCellValueFactory(new PropertyValueFactory<>("batch"));
		birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		System.out.println(studentDAO.getAllStudent());
		studentTable.setItems(studentDAO.getAllStudent());
				
		dpBirthDate.setValue(LocalDate.now());
		disable();
		
		//retrieve data from academic database 
	    ObservableList<Academic> academicList = academicDAO.getAllAcademic();
	    
	    ObservableList<String> academicYearName = FXCollections.observableArrayList();
	    
	    for (Academic academic : academicList) {
	      
	      academicYearName.add(academic.getAcademicyearname());
	    }
	    
	    comboAcademic.setItems(academicYearName);
	    
	    //retrieve data from batch database 
	    ObservableList<Batch> batchList = batchDAO.getAllBatchs();
	    
	    ObservableList<String> batchName = FXCollections.observableArrayList();
	    
	    for (Batch  batch: batchList) {
	      batchName.add(batch.getBatchname());
	    }
	    
	    comboBatch.setItems(batchName);

	}

}
