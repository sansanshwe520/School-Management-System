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
import model.Batch;
import model.BatchDAO;

public class BatchController implements Initializable{

    @FXML
    private TableView<Batch> batchTable;

    @FXML
    private TableColumn<Batch, String> batchname;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<Batch, Integer> no;

    @FXML
    private TextField tfBatch;
    
    private final BatchDAO batchDAO = new BatchDAO();
    
    //order
    private void showItemsInTable(ObservableList<Batch> lst) {
    for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setId(i+1);
		}
		batchTable.setItems(lst);
    }
    
    
    private void clear() {
    	tfBatch.clear();
    	
    	
    }

    @FXML
    void processAdd(ActionEvent event) {
    	
String batchname = tfBatch.getText().trim();
    	
    	Batch batch = new Batch(batchname);
    	boolean created = batchDAO.createBatch(batch);
    	
    	if(created) {
    		clear();
			
			/*
			 * ObservableList<Academic> lst = academicDAO.getAllAcademic();
			 * showItemsInTable(lst);
			 */ 
    		ObservableList<Batch> lst =batchDAO.getAllBatchs();
    		showItemsInTable(lst);
			 
//    		batchTable.setItems(batchDAO.getAllBatchs());
    	}else {
    		System.out.println("fail to create batch");
    	}

    }

    @FXML
    void processDelete(ActionEvent event) {
    	
Batch selectedBatch = batchTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedBatch == null) {
    		return;
    	}
    	
    	boolean deleted = batchDAO.deleteBatchByName(selectedBatch.getBatchname());
    	if(deleted) {
    		
    		ObservableList<Batch> lst = batchDAO.getAllBatchs();
    		showItemsInTable(lst);
    	}else {
    		System.out.println("Fail to delete  batch");
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		no.setCellValueFactory(new PropertyValueFactory<>("id"));
		batchname.setCellValueFactory(new PropertyValueFactory<>("batchname"));
		
		ObservableList<Batch> lst=batchDAO.getAllBatchs();
		showItemsInTable(lst);//order
		
//		batchTable.setItems(batchDAO.getAllBatchs());
		
	}

}
