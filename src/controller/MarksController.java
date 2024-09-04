package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Batch;
import model.BatchDAO;
import model.Course;
import model.CourseDAO;
import model.ExamType;
import model.ExamTypeDAO;
import model.Marks;
import model.MarksDAO;
import model.Student;
import model.StudentDAO;
import model.Year;
import model.YearDAO;

public class MarksController implements Initializable{

    @FXML
    private Button btDelete;

    @FXML
    private Button btEdit;

    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnClear;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<String> comboCourseName;

    @FXML
    private ComboBox<String> comboExamType;

    @FXML
    private ComboBox<String> comboYear;
    
    @FXML
    private ComboBox<String> comboBatch;
    
    @FXML
    private ComboBox<String> comboStudent;

    @FXML
    private TableColumn<Marks, String> coursename;

    @FXML
    private TableColumn<Marks, String> examtype;

    @FXML
    private TableColumn<Marks, String> id;

    @FXML
    private TableColumn<Marks, String> marks;

    @FXML
    private TableView<Marks> marksTable;

    @FXML
    private TableColumn<Marks, String> studentname;

    @FXML
    private TextField tfMarks;

    

    @FXML
    private TextField tfSearch;



    @FXML
    private TableColumn<Marks, String> year;
    
    @FXML
    private TableColumn<Marks, String> batch;
    
    
    private final MarksDAO marksDAO = new MarksDAO();
    private final YearDAO yearDAO = new YearDAO();
    private final ExamTypeDAO examtypeDAO= new ExamTypeDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final BatchDAO batchDAO = new BatchDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private boolean isNewButtonClicked = false;
    private Integer no = 0;
    
    
    private void disable() {
		 tfMarks.setDisable(true);
//		 tfSearch.setDisable(true);
//		 tfStudentName.setDisable(true);
		 comboBatch.setDisable(true);
		 comboCourseName.setDisable(true);
		 comboStudent.setDisable(true);
		 comboExamType.setDisable(true);
		 comboYear.setDisable(true);
		 btnClear.setDisable(true);
		 btnSave.setDisable(true);
	   
	    }
    
    private void enable() {
		 tfMarks.setDisable(false);
//		 tfSearch.setDisable(false);
//		 tfStudentName.setDisable(false);
		 comboBatch.setDisable(false);
		 comboCourseName.setDisable(false);
		 comboStudent.setDisable(false);
		 comboExamType.setDisable(false);
		 comboYear.setDisable(false);
		 btnClear.setDisable(false);
		 btnSave.setDisable(false);
	   
	    }
    private void showItemsInTable(ObservableList<Marks> lst) {
        for (int i = 0; i < lst.size(); i++) {
    			lst.get(i).setId(i+1);
    		}
    		marksTable.setItems(lst);
        }
        

//    @FXML
//    void image(MouseEvent event) {
//    	String searchQuery = tfSearch.getText().trim();
//    	marksTable.setItems(marksDAO.getAllMarksByName(searchQuery));;
//    }

    private void clear() {
		 tfMarks.clear();
//		 tfSearch.clear();
//		 tfStudentName.clear();
		 comboBatch.setValue("Choose Batch");
		 comboCourseName.setValue("Choose Course");
		 comboStudent.setValue("Choose Student");
		 comboExamType.setValue("Choose ExamType");
		 comboYear.setValue("Choose Year");
		 
	   
	    }
    
    @FXML
    void processclear(ActionEvent event) {
    	clear();
    }
    @FXML
    void processnew(ActionEvent event) {
    	enable();
    	isNewButtonClicked = true;
    }

    @FXML
    void processrefresh(ActionEvent event) {
    	marksTable.setItems(marksDAO.getAllMarks());
    }
    @FXML
    void processdelete(ActionEvent event) {
    	Marks selectedMarks = marksTable.getSelectionModel().getSelectedItem();

		if (selectedMarks == null) {
			return;
		}

		boolean deleted = marksDAO.deleteMarks(selectedMarks.getMarks());
		if (deleted) {
			ObservableList<Marks> lst = marksDAO.getAllMarks();
    		showItemsInTable(lst);
		} else {
			System.out.println("Fail to delete marks");
		}
    }

    @FXML
    void processsEdit(ActionEvent event) {
    	
    	Marks selectedMarks = marksTable.getSelectionModel().getSelectedItem();

		if (selectedMarks == null) {
			return;
		}
		enable();
		isNewButtonClicked = false;
//		tfStudentName.setText(selectedMarks.getStudentname());
		comboBatch.setValue(selectedMarks.getBatch().toString());
		comboCourseName.setValue(selectedMarks.getCourse().toString());
		comboExamType.setValue(selectedMarks.getExamtype().toString());
		comboStudent.setValue(selectedMarks.getStudent().toString());
		comboYear.setValue(selectedMarks.getYear().toString());
		tfMarks.setText(String.valueOf(selectedMarks.getId()));

		Marks originalmarks = marksDAO.getAllMarksByALL(selectedMarks);
//		no = originalmarks.getId();
		no =originalmarks.getId();
	

    }

    @FXML
    void procseesave(ActionEvent event) {
    	
//    	String studentname = tfStudentName.getText().trim();
    	Integer mark = Integer.parseInt(tfMarks.getText().trim());
    	String yearName = comboYear.getValue();
    	String examtypeName = comboExamType.getValue();
    	String courseName = comboCourseName.getValue();
    	String batchName = comboBatch.getValue();
    	String studentName = comboStudent.getValue();
    	
    	Year year = yearDAO.getYearByYearName(yearName);
    	ExamType examtype = examtypeDAO.getExamTypeByName(examtypeName);
    	Course course = courseDAO.getCourseByCourseName(courseName);
    	Batch batch = batchDAO.getBatchByBatchName(batchName);
    	Student student = studentDAO.getStudentByName(studentName);
    	
    	if(isNewButtonClicked) {
    	Marks marks = new Marks(mark,year,examtype,course,batch,student);
//    	System.out.println("in the process save"+mark);
    	
    	boolean created = marksDAO.createMarks(marks);
    	
    	if(created) {
    		
    		clear();
    		disable();
    		ObservableList<Marks> lst = marksDAO.getAllMarks();
    		showItemsInTable(lst);
     		
    	}else {
    		System.out.println("fail to create marks");
    	}
    	}
    	else {
    		Marks marks = new Marks(mark,year,examtype,course,batch,student);
    		boolean updated = marksDAO.updateMarks(marks);
    		
    		if(updated) {
        		
        		clear();
        		disable();
        		ObservableList<Marks> lst = marksDAO.getAllMarks();
        		showItemsInTable(lst);
         		
        	}else {
        		System.out.println("fail to update marks");
        	}
    	}
    	
    	
    	
    	

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		year.setCellValueFactory(new PropertyValueFactory<>("year"));
		examtype.setCellValueFactory(new PropertyValueFactory<>("examtype"));
		coursename.setCellValueFactory(new PropertyValueFactory<>("course"));
		studentname.setCellValueFactory(new PropertyValueFactory<>("student"));;
		marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
		batch.setCellValueFactory(new PropertyValueFactory<>("batch"));
		
		
	
		
		
	
		
		disable();
	    
	    //retrieve data from year database 
	    ObservableList<Year> yearList = yearDAO.getallyears();
	    
	    ObservableList<String> yearname = FXCollections.observableArrayList();
	    
	    for (Year  year: yearList) {
	      yearname.add(year.getYearname());
	    }
	    
	    comboYear.setItems(yearname);
	    
	    //retrieve data from examtype database 
	    ObservableList<ExamType> examtypeList = examtypeDAO.getAllExamTypes();
	    
	    ObservableList<String> examtype = FXCollections.observableArrayList();
	    
	    for (ExamType  examType : examtypeList) {
	      examtype.add(examType.getExamtype());
	    }
	    
	    comboExamType.setItems(examtype);
	    
	  //retrieve data from course database 
//	    ObservableList<Course> courseList = courseDAO.getAllCourse();
//	    
//	    ObservableList<String> courseName = FXCollections.observableArrayList();
//	    
//	    for (Course  coursename : courseList) {
//	      courseName.add(coursename.getCoursename());
//	    }
//	    
//	    comboCourseName.setItems(courseName);
//	    
	    
	    //retrieve data from student database 
	 
	    
	    
	  //batch
		 ObservableList<Batch> batchList = batchDAO.getAllBatchs();
		    
		    ObservableList<String> batchName = FXCollections.observableArrayList();
		    
		    for (Batch  batch: batchList) {
		      batchName.add(batch.getBatchname());
		    }
		    
		    comboBatch.setItems(batchName);
		    
		    comboBatch.setOnAction(event -> {

		    	ObservableList<Student> studentList = studentDAO.getAllStudentByBatchName(comboBatch.getValue());
		    	ObservableList<String> StudentNameLst = FXCollections.observableArrayList();
			    
			    for (Student  student: studentList) {
			    	StudentNameLst.add(student.getName());
			    }
			    
			    comboStudent.setItems(StudentNameLst);
		    });
	
		    
		    //retrieve data from course database 
		    ObservableList<Course> courseList = courseDAO.getAllCourse();
		    
		    ObservableList<String> courseName = FXCollections.observableArrayList();
		    
		    for (Course  coursename : courseList) {
		      courseName.add(coursename.getCoursename());
		    }
		    
		    comboCourseName.setItems(courseName);
		    
			ObservableList<Marks> lst = marksDAO.getAllMarks();
			showItemsInTable(lst);
	}
		    	
		 
}
		
	
	


