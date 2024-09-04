package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import model.Student;
import model.StudentDAO;
import model.Teacher;
import model.TeacherDAO;

public class SDetailsController {

	 @FXML
	    private Label lblAcademicYear;

	    @FXML
	    private Label lblAddress;

	    @FXML
	    private Label lblBatchName;

	    @FXML
	    private Label lblBirthdate;

	    @FXML
	    private Label lblEmail;

	    @FXML
	    private Label lblGender;

	    @FXML
	    private Label lblID;

	    @FXML
	    private Label lblName;

	    @FXML
	    private Label lblParentName;

	    @FXML
	    private Label lblPhone;
    
    private final StudentDAO studentDAO = new StudentDAO();
    
    public void initData(Long studentID) {

    	Student student = studentDAO.getStudentByID(studentID);
    	lblAcademicYear.setText(student.getAcademicyear().getAcademicyearname());
    	lblAddress.setText(student.getAddress());
    	lblBatchName.setText(student.getBatch().getBatchname());
    	lblBirthdate.setText(student.getBirthDate().toString());
    	lblEmail.setText(student.getEmail());
    	lblGender.setText(student.getGender());
    	lblID.setText(student.getId().toString());
    	lblName.setText(student.getName());
    	lblParentName.setText(student.getParentName());
    	lblPhone.setText(student.getPhone());	
	}
}
