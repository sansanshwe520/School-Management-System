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
import model.ExamType;
import model.ExamTypeDAO;

public class ExamTypeController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<ExamType, String> examtype;

    @FXML
    private TableView<ExamType> examtypeTable;

    @FXML
    private TableColumn<ExamType, Integer> no;

    @FXML
    private TextField tfExamType;
    
    private final ExamTypeDAO  examtypeDAO = new ExamTypeDAO();
//    private Integer examtypeid = 0;
    
    //order
    private void showItemsInTable(ObservableList<ExamType> lst) {
    for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setId(i+1);
		}
		examtypeTable.setItems(lst);
    }
    
    
    private void clear() {
    	tfExamType.clear();
    	
    	
    }
    
    @FXML
    void processAdd(ActionEvent event) {
    	
    	String examtypename = tfExamType.getText().trim();
    	    	
    	    	ExamType examtype = new ExamType(examtypename);
    	    	boolean created = examtypeDAO.createExamType(examtype);
    	    	
    	    	if(created) {
    	    		clear();
    				
    				/*
    				 * ObservableList<Academic> lst = academicDAO.getAllAcademic();
    				 * showItemsInTable(lst);
    				 */ 
    	    		ObservableList<ExamType> lst =examtypeDAO.getAllExamTypes();
    	    		showItemsInTable(lst);
    				 
//    	    		batchTable.setItems(batchDAO.getAllBatchs());
    	    	}else {
    	    		System.out.println("fail to create examtype");
    	    	}

    	    }
    @FXML
    void processDelete(ActionEvent event) {
    	
ExamType selectdExamType = examtypeTable.getSelectionModel().getSelectedItem();
    	
    	if(selectdExamType == null) {
    		return;
    	}
    	
    	boolean deleted = examtypeDAO.deleteExamType(selectdExamType.getExamtype());
    	if(deleted) {
    		
//    		examtypeTable.setItems(examtypeDAO.getAllExamTypes());
    		ObservableList<ExamType> lst =examtypeDAO.getAllExamTypes();
    		showItemsInTable(lst);
    	}else {
    		System.out.println("Fail to delete  exam");
    	}

    }
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		no.setCellValueFactory(new PropertyValueFactory<>("id"));
//		batchname.setCellValueFactory(new PropertyValueFactory<>("batchname"));
//		
//		ObservableList<Batch> lst=batchDAO.getAllBatchs();
//		showItemsInTable(lst);//order
//		
////		batchTable.setItems(batchDAO.getAllBatchs());
//		
//	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		no.setCellValueFactory(new PropertyValueFactory<>("id"));
		examtype.setCellValueFactory(new PropertyValueFactory<>("examtype"));
		
		ObservableList<ExamType> lst=examtypeDAO.getAllExamTypes();
		showItemsInTable(lst);//order
		

	
	
		
	}

}
