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
import javafx.scene.input.MouseEvent;
import model.Batch;
import model.BatchDAO;
import model.Teacher;
import model.TeacherAttendence;
import model.TeacherAttendenceDAO;
import model.TeacherDAO;
import model.Time;
import model.TimeDAO;
import utility.AlertSupport;

public class TeacherAttendenceController implements Initializable {

	@FXML
	private TableColumn<TeacherAttendence, String> batch;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnRefresh;

	@FXML
	private ComboBox<String> comboBatch;

	@FXML
	private ComboBox<String> comboChooseName;

	@FXML
	private ComboBox<String> comboTime;

	@FXML
	private TableColumn<TeacherAttendence, LocalDate> date;

	@FXML
	private DatePicker dpDate;

	@FXML
	private TableView<TeacherAttendence> teacherAttendenceTable;

	@FXML
	private TableColumn<TeacherAttendence, String> teacher;

	@FXML
	private TextField tfSearch;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnEdit;

	@FXML
	private TableColumn<TeacherAttendence, String> time;

	private final TeacherAttendenceDAO teacherAttendenceDAO = new TeacherAttendenceDAO();
	private final BatchDAO batchDAO = new BatchDAO();
	private final TimeDAO timeDAO = new TimeDAO();
	private final TeacherDAO teacherDAO = new TeacherDAO();
	private boolean isNewButtonClicked = false;
	private Long id=0L;

	private void enable() {
		dpDate.setDisable(false);
		comboBatch.setDisable(false);
		comboChooseName.setDisable(false);
		comboTime.setDisable(false);
	}

	private void disable() {
		dpDate.setDisable(true);
		comboBatch.setDisable(true);
		comboChooseName.setDisable(true);
		comboTime.setDisable(true);
	}

	private void clear() {
		

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

		TeacherAttendence selectedAttendence = teacherAttendenceTable.getSelectionModel().getSelectedItem();
		if (selectedAttendence == null) {
			return;
		}

		boolean deleted = teacherAttendenceDAO.deleteTeacherAttendence(selectedAttendence.getId());
		if (deleted) {
			// studentTable.setItems(studentDAO.getAllStudent());
			teacherAttendenceTable.setItems(teacherAttendenceDAO.getAllTeacherAttendence());
		} else {
			System.out.println("Fail to delete teacher attendence");
		}
	}

	@FXML
	void processSave(ActionEvent event) {
		LocalDate date = dpDate.getValue();
		String batch = comboBatch.getValue();
		String teacherName = comboChooseName.getValue();
		String time = comboTime.getValue();

		Batch batchName = batchDAO.getBatchByBatchName(batch);
		Time timeName = timeDAO.getTimebyName(time);
		Teacher teacher = teacherDAO.getTeacherByName(teacherName);

		if (isNewButtonClicked) {
			TeacherAttendence teacherAttendence = new TeacherAttendence(date, teacher, timeName, batchName);
			System.out.println("in the process save" + teacherAttendence);
			boolean created = teacherAttendenceDAO.createTeacherAttendence(teacherAttendence);
			System.out.println("after inserted" + created);

			if (created) {
//	    		clear();
				disable();
				teacherAttendenceTable.setItems(teacherAttendenceDAO.getAllTeacherAttendence());
				comboChooseName.setValue("Choose Name");
				comboBatch.setValue("Batch Name");
				comboTime.setValue("Time");
				dpDate.setValue(LocalDate.now());
			} else {
				System.out.println("fail to create teacher attendence");
			}
		} else {
			TeacherAttendence teacherAttendence = new TeacherAttendence(id,date, teacher, timeName, batchName);
			System.out.println("in the process save" + teacherAttendence);
			boolean created = teacherAttendenceDAO.updateTeacherAttendence(teacherAttendence);
			System.out.println("after inserted" + created);
			if (created) {
				clear();
				disable();
				teacherAttendenceTable.setItems(teacherAttendenceDAO.getAllTeacherAttendence());
			} else {
				System.out.println("fail to update teacher attendence");
			}
		}
	}

	@FXML
	void searchImage(MouseEvent event) {
		String searchQuery = tfSearch.getText().trim();
		teacherAttendenceTable.setItems(teacherAttendenceDAO.getAllTeacherAttendenceByName(searchQuery));
	}

	@FXML
	void processEdit(ActionEvent event) {
		TeacherAttendence selectedAttendence = teacherAttendenceTable.getSelectionModel().getSelectedItem();
		if (selectedAttendence == null) {
			return;
		}
		enable();
		isNewButtonClicked = false;

		dpDate.setValue(selectedAttendence.getDate());
		comboChooseName.setValue(selectedAttendence.getTeacher().getName());
		comboBatch.setValue(selectedAttendence.getBatch().getBatchname());
		comboTime.setValue(selectedAttendence.getTime().getTimename());

		TeacherAttendence originalTAttendence = teacherAttendenceDAO.getTeacherAttendenceByAll(selectedAttendence);
		id=originalTAttendence.getId();
	}

	@FXML
	void processRefresh(ActionEvent event) {
		tfSearch.setDisable(true);
		tfSearch.clear();
		teacherAttendenceTable.setItems(teacherAttendenceDAO.getAllTeacherAttendence());
	}

	@FXML
	void processNew(ActionEvent event) {
		enable();
		isNewButtonClicked = true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
//		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		batch.setCellValueFactory(new PropertyValueFactory<>("batch"));

//		ObservableList<TeacherAttendence> teacherAttList = teacherAttendenceDAO.getAllTeacherAttendence();
//		for (int i = 0; i < teacherAttList.size(); i++) {
//			System.out.println(teacherAttList.get(i));
//		}
		teacherAttendenceTable.setItems(teacherAttendenceDAO.getAllTeacherAttendence());
		dpDate.setValue(LocalDate.now());
		disable();

		// teacher
		ObservableList<Teacher> teacherList = teacherDAO.getAllTeachers();
		ObservableList<String> teacher = FXCollections.observableArrayList();
		for (Teacher teacher1 : teacherList) {
			teacher.add(teacher1.getName());
		}
		comboChooseName.setItems(teacher);

		// batch
		ObservableList<Batch> batchList = batchDAO.getAllBatchs();
		ObservableList<String> batchName = FXCollections.observableArrayList();
		for (Batch batch : batchList) {
			batchName.add(batch.getBatchname());
		}
		comboBatch.setItems(batchName);

		// time
		ObservableList<Time> timeList = timeDAO.getAllTime();
		ObservableList<String> time = FXCollections.observableArrayList();
		for (Time time1 : timeList) {
			time.add(time1.getTimename());
		}
		comboTime.setItems(time);
	}
}
