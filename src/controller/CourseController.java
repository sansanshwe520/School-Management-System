package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Course;
import model.CourseDAO;

public class CourseController implements Initializable{

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> coursename;

    @FXML
    private TableColumn<Course, String> description;

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private TextField tfcoursename;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfsearch;
    
    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnEdit;
    
    private final CourseDAO courseDAO = new CourseDAO();
    private boolean isNewButtonClicked = false;
    private Integer courseId = 0;
    //order
    private void showItemsInTable(ObservableList<Course> lst) {
    for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setId(i+1);
		}
		courseTable.setItems(lst);
    }
    
    private void disable() {
    	tfcoursename.setDisable(true);
        tfdescription.setDisable(true);
        btnSave.setDisable(true);
        btnClear.setDisable(true);       
    }
    
    private void enable() {
    	tfcoursename.setDisable(false);
        tfdescription.setDisable(false);
        btnSave.setDisable(false);
        btnClear.setDisable(false);       
    }
    
    private void clear() {
    	tfcoursename.clear();
    	tfdescription.clear();
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
    	Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
    	if(selectedCourse == null) {
    		return;
    	}
    	boolean deleted = courseDAO.deleteCourseByName(selectedCourse.getCoursename());
    	if(deleted) {
    		ObservableList<Course> lst=courseDAO.getAllCourse();
    		showItemsInTable(lst);
    	}else {
    		System.out.println("fail to delete course");
    	}
    }
    
    @FXML
    void processEdit(ActionEvent event) {
    	Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
    	if(selectedCourse == null) {
    		return;
    	}   	
    	enable();
    	isNewButtonClicked = false;
    	   	
    	
    	tfcoursename.setText(selectedCourse.getCoursename().toString());
    	tfdescription.setText(selectedCourse.getDescription().toString());
    	
    	Course originalCourse = courseDAO.getCourseByCoursenameAndDescription(selectedCourse);
    	System.out.println("selected course"+selectedCourse.getId());
    	courseId = originalCourse.getId();
    	
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	ObservableList<Course> lst=courseDAO.getAllCourse();
    	showItemsInTable(lst);
    }

    @FXML
    void processSave(ActionEvent event) {
    	String coursename = tfcoursename.getText();
    	String description= tfdescription.getText(); 
    	System.out.println("courseid"+courseId);
    	if(isNewButtonClicked) {  	
    	Course course = new Course(coursename, description);   	
    	boolean added = courseDAO.addCourse(course);   	
    	if(added) {
    		clear();
    		disable();    		
    		ObservableList<Course> lst=courseDAO.getAllCourse();
    		showItemsInTable(lst);
    	}else {
    		System.out.println("courseid"+courseId);
    		System.out.println("fail to add course");
    	}
    	
    }
    	else {    		
    		Course course = new Course(courseId, coursename, description);       	
        	boolean updated = courseDAO.updateCourse(course);
        	System.out.println("course id in update"+courseId);
        	if(updated) {
        		clear();
        		disable();       		
        		ObservableList<Course> lst=courseDAO.getAllCourse();
        		showItemsInTable(lst);
        	}else {
        		System.out.println("fail to update course");
        	}
    		
    	}
    }
    	

    @FXML
    void processSearch(ActionEvent event) {
     String searchQuery = tfsearch.getText().trim();
//     ObservableList<Course> lst=courseDAO.getCourseByCourseName(searchQuery);
//     showItemsInTable(lst);//order
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		//binding
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		coursename.setCellValueFactory(new PropertyValueFactory<>("coursename"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		ObservableList<Course> lst=courseDAO.getAllCourse();
		showItemsInTable(lst);//order
		disable();
	}

}
