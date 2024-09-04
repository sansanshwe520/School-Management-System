package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import model.Batch;
import model.BatchDAO;
import model.Student;
import model.StudentAttendence;
import model.StudentAttendenceDAO;
import model.StudentDAO;
import model.TeacherAttendence;
import model.Time;
import model.TimeDAO;
import utility.AlertSupport;

public class StudentAttendenceController implements Initializable {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnEdit;

	@FXML
	private ComboBox<String> comboBatch;

	@FXML
	private ComboBox<String> comboStudentName;

	@FXML
	private ComboBox<String> comboTime;

	@FXML
	private TableColumn<StudentAttendence, LocalDate> date;

	@FXML
	private TableColumn<StudentAttendence, String> batch;

	@FXML
	private DatePicker dpChooseDate;

	@FXML
	private TableView<StudentAttendence> studentAttendenceTable;

	@FXML
	private TableColumn<StudentAttendence, String> studentName;

	@FXML
	private TextField tfSearch;

	@FXML
	private TableColumn<StudentAttendence, String> time;
	
	private Long id=0L;

	@FXML
	void processNew(ActionEvent event) {
		enable();
		isNewButtonClicked = true;
	}

	private void enable() {
		dpChooseDate.setDisable(false);
		comboBatch.setDisable(false);
		comboStudentName.setDisable(false);
		comboTime.setDisable(false);
	}

	private void disable() {
		dpChooseDate.setDisable(true);
		comboBatch.setDisable(true);
		comboStudentName.setDisable(true);
		comboTime.setDisable(true);
	}

	private void clear() {

	}

	private final StudentAttendenceDAO studentAttendenceDAO = new StudentAttendenceDAO();
	private final BatchDAO batchDAO = new BatchDAO();
	private final TimeDAO timeDAO = new TimeDAO();
	private final StudentDAO studentDAO = new StudentDAO();
	private boolean isNewButtonClicked = false;

	@FXML
	void processBatch(InputMethodEvent event) {
		System.out.println("select combo");
	}

	@FXML
	void processEdit(ActionEvent event) {

		StudentAttendence selectedAttendence = studentAttendenceTable.getSelectionModel().getSelectedItem();

		if (selectedAttendence == null) {
			return;
		}
		enable();
		isNewButtonClicked = false;

		dpChooseDate.setValue(selectedAttendence.getDate());
		comboStudentName.setValue(selectedAttendence.getStudent().getName());
		comboBatch.setValue(selectedAttendence.getBatch().getBatchname());
		comboTime.setValue(selectedAttendence.getTime().getTimename());
		
		StudentAttendence originalAttendence = studentAttendenceDAO.getStudentAttendanceByAll(selectedAttendence);
		id=originalAttendence.getId();
	}

	@FXML
	void processRefresh(ActionEvent event) {
		tfSearch.setDisable(false);
		studentAttendenceTable.setItems(studentAttendenceDAO.getAllStudentAttendence());
	}

	@FXML
	void processDelete(ActionEvent event) {
		Alert alert = AlertSupport.getAlert(AlertType.CONFIRMATION, "Confirm", "Delete Confirmation",
				"Are you sure you want to delete");
		alert.getButtonTypes().clear();
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		alert.getButtonTypes().addAll(buttonYes, buttonNo);

		Optional<ButtonType> resultButton = alert.showAndWait();
		if (resultButton.isPresent() && resultButton.get().equals(buttonNo)) {
			return;
		}
		if (resultButton.isEmpty()) {
			return;
		}

		StudentAttendence selectedAttendence = studentAttendenceTable.getSelectionModel().getSelectedItem();

		if (selectedAttendence == null) {
			return;
		}

		boolean deleted = studentAttendenceDAO.deleteStudentAttendence(selectedAttendence.getId());
		if (deleted) {
			// studentTable.setItems(studentDAO.getAllStudent());
			studentAttendenceTable.setItems(studentAttendenceDAO.getAllStudentAttendence());
		} else {
			System.out.println("Fail to delete student attendence");
		}
	}

	@FXML
	void processSave(ActionEvent event) {
		LocalDate date = dpChooseDate.getValue();
		String batch = comboBatch.getValue();
		String studentName = comboStudentName.getValue();
		String time = comboTime.getValue();

		Batch batchName = batchDAO.getBatchByBatchName(batch);
		Time timeName = timeDAO.getTimebyName(time);
		Student student = studentDAO.getStudentByName(studentName);

		if (isNewButtonClicked) {
			StudentAttendence studentAttendence = new StudentAttendence(date, student, batchName, timeName);
			System.out.println("in the process save" + studentAttendence);

			boolean created = studentAttendenceDAO.createStudentAttendence(studentAttendence);
			System.out.println("after inserted" + created);

			if (created) {
//	    		clear();
				disable();
				comboStudentName.setValue("Student Name");
				comboBatch.setValue("Batch");
				comboTime.setValue("Time");
				dpChooseDate.setValue(LocalDate.now());
				studentAttendenceTable.setItems(studentAttendenceDAO.getAllStudentAttendence());
			} else {
				System.out.println("fail to create student attendence");
			}
		} else {

			StudentAttendence studentAttendence = new StudentAttendence(id,date, student, batchName, timeName);
			System.out.println("in the process save" + studentAttendence);

			boolean created = studentAttendenceDAO.updateStudentAttendence(studentAttendence);
			System.out.println("after inserted" + created);

			if (created) {
				clear();
				disable();
				studentAttendenceTable.setItems(studentAttendenceDAO.getAllStudentAttendence());
			} else {
				System.out.println("fail to update student attendence");
			}
		}
	}

	@FXML
	void searchImage(MouseEvent event) {
		String searchQuery = tfSearch.getText().trim();
		studentAttendenceTable.setItems(studentAttendenceDAO.getAllStudentAttendenceByName(searchQuery));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

//		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		studentName.setCellValueFactory(new PropertyValueFactory<>("student"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		batch.setCellValueFactory(new PropertyValueFactory<>("batch"));

		studentAttendenceTable.setItems(studentAttendenceDAO.getAllStudentAttendence());
		dpChooseDate.setValue(LocalDate.now());
		disable();

		// batch
		ObservableList<Batch> batchList = batchDAO.getAllBatchs();

		ObservableList<String> batchName = FXCollections.observableArrayList();

		for (Batch batch : batchList) {
			batchName.add(batch.getBatchname());
		}

		comboBatch.setItems(batchName);

		// add action in batch combox, student list will display according to the
		// selected
		// batch name

		comboBatch.setOnAction(event -> {
			// Call a method to determine which item in the list the user has selected
			// doAction(dropDown.getValue().toString()); //Send the selected item to the
			// method
			// set student list for comboStudent

			ObservableList<Student> studentList = studentDAO.getAllStudentByBatchName(comboBatch.getValue());
			ObservableList<String> StudentNameLst = FXCollections.observableArrayList();

			for (Student student : studentList) {
				StudentNameLst.add(student.getName());
			}

			comboStudentName.setItems(StudentNameLst);
		});

		// time

		ObservableList<Time> timeList = timeDAO.getAllTime();

		ObservableList<String> time = FXCollections.observableArrayList();

		for (Time time1 : timeList) {
			time.add(time1.getTimename());
		}

		comboTime.setItems(time);

	}

}
