package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	
	@FXML
    private Circle circle;

    @FXML
    private Button academicyear;

    @FXML
    private Button batch;

    @FXML
    private BorderPane bp;

    @FXML
    private Button course;

    @FXML
    private Button courseenrollment;

    @FXML
    private Button exam;

    @FXML
    private Button home;

    @FXML
    private Button marks;

    @FXML
    private Button sattendance;

    @FXML
    private Button student;

    @FXML
    private Button tattendance;

    @FXML
    private Button teacher;

    @FXML
    private Button time;

    @FXML
    private Button year;

    @FXML
    void btnAcademicYear(ActionEvent event) throws IOException {
    
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/AcademicYear.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnBatch(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Batch.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnCourse(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Course.fxml"));
    	bp.setCenter(view);
    }

    
    @FXML
    void btnCourseEnrollment(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/CourseEnrollment.fxml"));
    	bp.setCenter(view);
    }

    @FXML
    void btnExam(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Exam.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnHome(ActionEvent event) throws IOException {
//    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Home2.fxml"));
//    	bp.setCenter(view);
    	

		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.hide();
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
		Scene mainScene = new Scene(mainRoot);
		stage.setScene(mainScene);
		
		stage.setTitle("Host Myanmar Institute Page");
		stage.show();

    }


    @FXML
    void btnMarks(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Marks.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnStudent(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Student.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnTAttendance(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/TeacherAttendance.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnTeacher(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Teacher.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnTime(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Time.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void btnYear(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/Year.fxml"));
    	bp.setCenter(view);

    }

    @FXML
    void sAttendance(ActionEvent event) throws IOException {
    	BorderPane view = FXMLLoader.load(getClass().getResource("../view/StudentAttendance.fxml"));
    	bp.setCenter(view);

    }
    
    @FXML
    void processlogout(ActionEvent event) throws IOException  {
    	
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.hide();
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("../view/LoginUI.fxml"));
		Scene mainScene = new Scene(mainRoot);
		stage.setScene(mainScene);
		stage.setMaximized(false);
//		stage.setResizable(false);
		stage.setTitle("Login Page");
		stage.show();
    	

    }
    
    
    
    
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image img = new Image("./image/hmilogo.jpg");
		circle.setFill(new ImagePattern(img));
	}

}


  

   


	


   
    
  
    
   
	
    
    
