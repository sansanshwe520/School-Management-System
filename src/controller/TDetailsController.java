package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import model.Student;
import model.Teacher;
import model.TeacherDAO;

public class TDetailsController {

    @FXML
    private Label lblAddress;

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
    private Label lblPhone;
    
    private final TeacherDAO teacherDAO = new TeacherDAO();
    
    public void initData(Long teacherID) {
    	
    	Teacher teacher = teacherDAO.getTeacherByID(teacherID);
    	lblAddress.setText(teacher.getAddress());
    	lblBirthdate.setText(teacher.getBirthDate().toString());
    	lblEmail.setText(teacher.getEmail());
    	lblGender.setText(teacher.getGender());
    	lblID.setText(teacher.getId().toString());
    	lblName.setText(teacher.getName());
    	lblPhone.setText(teacher.getPhone());
    	
		
	}

}
