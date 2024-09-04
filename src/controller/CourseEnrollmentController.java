package controller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Academic;
import model.AcademicDAO;
import model.Batch;
import model.BatchDAO;
import model.Course;
import model.CourseDAO;
import model.CourseEnrollment;
import model.CourseEnrollmentDAO;
import model.Teacher;
import model.TeacherDAO;
import model.Year;
import model.YearDAO;

public class CourseEnrollmentController implements Initializable {

	@FXML
	private TableColumn<CourseEnrollment, Integer> No;

	@FXML
	private TableColumn<CourseEnrollment, String> academicyear;

	@FXML
	private TableColumn<CourseEnrollment, String> batch;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnClear;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnRefresh;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnView;

	@FXML
	private ComboBox<String> comboAcademicYear;

	@FXML
	private ComboBox<String> comboBatch;

	@FXML
	private ComboBox<String> comboCourse;

	@FXML
	private ComboBox<String> comboTeacher;

	@FXML
	private ComboBox<String> comboYear;

	@FXML
	private TableView<CourseEnrollment> enrollmentTable;

	@FXML
	private TableColumn<CourseEnrollment, String> course;

	@FXML
	private TableColumn<CourseEnrollment, String> teacher;

	@FXML
	private TableColumn<CourseEnrollment, String> year;

	private final CourseEnrollmentDAO courseenrollmentDAO = new CourseEnrollmentDAO();
	private final AcademicDAO academicDAO = new AcademicDAO();
	private final TeacherDAO teacherDAO = new TeacherDAO();
	private final CourseDAO courseDAO = new CourseDAO();
	private final BatchDAO batchDAO = new BatchDAO();
	private final YearDAO yearDAO = new YearDAO();
	private boolean isNewButtonClicked = false;
	private Integer no = 0;
	
	private void showItemsInTable(ObservableList<CourseEnrollment> lst) {
        for (int i = 0; i < lst.size(); i++) {
    			lst.get(i).setId(i+1);
    		}
    		enrollmentTable.setItems(lst);
        }

	private void disable() {
		comboAcademicYear.setDisable(true);
		comboBatch.setDisable(true);
		comboCourse.setDisable(true);
		comboTeacher.setDisable(true);
		comboYear.setDisable(true);
		btnSave.setDisable(true);
		btnClear.setDisable(true);
	}

	private void enable() {
		comboAcademicYear.setDisable(false);
		comboBatch.setDisable(false);
		comboCourse.setDisable(false);
		comboTeacher.setDisable(false);
		comboYear.setDisable(false);
		btnSave.setDisable(false);
		btnClear.setDisable(false);
	}

	private void clear() {
		comboAcademicYear.setValue("Academic Year");
		comboBatch.setValue("Batch");
		comboCourse.setValue("Course");
		comboTeacher.setValue("Teacher");
		comboYear.setValue("Year");
	}

	@FXML
	void processAdd(ActionEvent event) {
		enable();
		isNewButtonClicked = true;
	}

	@FXML
	void processClear(ActionEvent event) {
		clear();
	}

	@FXML
	void processDelete(ActionEvent event) {
		CourseEnrollment selectedenrollment = enrollmentTable.getSelectionModel().getSelectedItem();
		if (selectedenrollment == null) {
			return;
		}
		boolean deleted = courseenrollmentDAO.deleteCourseEnrollment(selectedenrollment.getId());
		if (deleted) {
//			enrollmentTable.setItems(courseenrollmentDAO.getAllEnrollment());
			ObservableList<CourseEnrollment> lst = courseenrollmentDAO.getAllEnrollment();
    		showItemsInTable(lst);
		} else {
			System.out.println("fail to delete enrollment!");
		}
	}

	@FXML
	void processEdit(ActionEvent event) {
		CourseEnrollment selectedenrollment = enrollmentTable.getSelectionModel().getSelectedItem();
		if (selectedenrollment == null) {
			return;
		}
		enable();
		isNewButtonClicked =false;
		comboTeacher.setValue(selectedenrollment.getTeacher().toString());
		comboCourse.setValue(selectedenrollment.getCourse().toString());
		comboBatch.setValue(selectedenrollment.getBatch().toString());
		comboAcademicYear.setValue(selectedenrollment.getAcademicyear().toString());
		comboYear.setValue(selectedenrollment.getYear().toString());
		
		CourseEnrollment originalEnroll=courseenrollmentDAO.getEntrollbyTeacherCourseBatchAcademicYear(selectedenrollment);
		no = originalEnroll.getId();
//		no = originalEnroll.getId();
	}

	@FXML
	void processRefresh(ActionEvent event) {
		disable();
		clear();
		ObservableList<CourseEnrollment> lst = courseenrollmentDAO.getAllEnrollment();
		showItemsInTable(lst);
	}

	@FXML
	void processSave(ActionEvent event) {
		String academicyearname = comboAcademicYear.getValue();
		String batchname = comboBatch.getValue();
		String coursename = comboCourse.getValue();
		String teachername = comboTeacher.getValue();
		String yearname = comboYear.getValue();

		Academic academicyear = academicDAO.getAcademicByName(academicyearname);
		Batch batch = batchDAO.getBatchByBatchName(batchname);
		Course course = courseDAO.getCourseByCourseName(coursename);
		Teacher teacher = teacherDAO.getTeacherByName(teachername);
		Year year = yearDAO.getYearByYearName(yearname);

		if (isNewButtonClicked) {
			CourseEnrollment courseEnrollment = new CourseEnrollment(teacher, course, batch, academicyear, year);
			boolean saved = courseenrollmentDAO.addCourseEnrollment(courseEnrollment);
			if (saved) {
				disable();
				clear();
//				enrollmentTable.setItems(courseenrollmentDAO.getAllEnrollment());
				ObservableList<CourseEnrollment> lst = courseenrollmentDAO.getAllEnrollment();
	    		showItemsInTable(lst);
			} else {
				System.out.println("fail to add course enrollment!");
			}
		} else {
			CourseEnrollment courseEnrollment = new CourseEnrollment(no, teacher, course, batch, academicyear, year);
			boolean updated = courseenrollmentDAO.updateCourseEnrollment(courseEnrollment);
			if (updated) {
				disable();
				clear();
//				enrollmentTable.setItems(courseenrollmentDAO.getAllEnrollment());
				ObservableList<CourseEnrollment> lst = courseenrollmentDAO.getAllEnrollment();
	    		showItemsInTable(lst);
			} else {
				System.out.println("fail to update course enrollment!");
			}
		}
	}

//	@FXML
//	void processView(ActionEvent event) throws IOException {
//		CourseEnrollment selectedEnrollment = enrollmentTable.getSelectionModel().getSelectedItem();
//		if(selectedEnrollment==null) {
//			return;
//		}
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TDetails.fxml"));
//		Parent root = loader.load();
//		TDetailsController tDetailsController = loader.getController();
//		tDetailsController.initData(selectedEnrollment.getId());
//		
//		Stage stage = new Stage();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.setTitle("Course Enrollment Details");
//		stage.show();
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		No.setCellValueFactory(new PropertyValueFactory<>("id"));
		teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
		course.setCellValueFactory(new PropertyValueFactory<>("course"));
		batch.setCellValueFactory(new PropertyValueFactory<>("batch"));
		academicyear.setCellValueFactory(new PropertyValueFactory<>("academicyear"));
		year.setCellValueFactory(new PropertyValueFactory<>("year"));

		// academic
		ObservableList<Academic> academicList = academicDAO.getAllAcademic();
		ObservableList<String> academicYearName = FXCollections.observableArrayList();
		for (Academic academic : academicList) {
			academicYearName.add(academic.getAcademicyearname());
		}
		comboAcademicYear.setItems(academicYearName);

		// teacher
		ObservableList<Teacher> teacherList = teacherDAO.getAllTeachers();
		ObservableList<String> teachername = FXCollections.observableArrayList();
		for (Teacher teacher : teacherList) {
			teachername.add(teacher.getName());
		}
		comboTeacher.setItems(teachername);

		// course
		ObservableList<Course> courseList = courseDAO.getAllCourse();
		ObservableList<String> coursename = FXCollections.observableArrayList();
		for (Course course : courseList) {
			coursename.add(course.getCoursename());
		}
		comboCourse.setItems(coursename);

		// batch
		ObservableList<Batch> batchList = batchDAO.getAllBatchs();
		ObservableList<String> batchname = FXCollections.observableArrayList();
		for (Batch batch : batchList) {
			batchname.add(batch.getBatchname());
		}
		comboBatch.setItems(batchname);

		// year
		ObservableList<Year> yearList = yearDAO.getallyears();
		ObservableList<String> yearname = FXCollections.observableArrayList();
		for (Year year : yearList) {
			yearname.add(year.getYearname());
		}
		comboYear.setItems(yearname);

		disable();
		ObservableList<CourseEnrollment> list = courseenrollmentDAO.getAllEnrollment();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i) + "list");
		}

//		enrollmentTable.setItems(courseenrollmentDAO.getAllEnrollment());
		ObservableList<CourseEnrollment> lst = courseenrollmentDAO.getAllEnrollment();
		showItemsInTable(lst);
	}

}
