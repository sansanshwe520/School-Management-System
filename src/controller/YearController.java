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
import model.Year;
import model.YearDAO;


public class YearController implements Initializable{

    @FXML
    private Button btnAdd;

    

    @FXML
    private Button btnDelete;
    


   

    @FXML
    private TableColumn<Year, Integer> id;

    @FXML
    private TextField tfyearname;

    @FXML
    private TableView<Year> yearTable;

    @FXML
    private TableColumn<Year, String> yearname;

    private final YearDAO yearDAO = new YearDAO();

    
    private void showItemsInTable(ObservableList<Year> lst) {
        for (int i = 0; i < lst.size(); i++) {
    			lst.get(i).setId(i+1);
    		}
    		yearTable.setItems(lst);
        }
    
    private void clear() {
    	tfyearname.clear();
    	
    	
    }
    
  
    
  

   
    @FXML
    void processDelete(ActionEvent event) {

    	Year selectedyear = yearTable.getSelectionModel().getSelectedItem();
    	if(selectedyear==null) {
    		return;
    	}
    	boolean deleted = yearDAO.deleteYearByName(selectedyear.getYearname());
    	if(deleted) {
    		ObservableList<Year> lst=yearDAO.getallyears();
    		showItemsInTable(lst);
    	}else {
    		System.out.println("fail to delete!!!");
    	}
    	
    }
    



    @FXML
    void processAdd(ActionEvent event) {
    	String yearname = tfyearname.getText();
    	 
    	
    		Year year = new Year(yearname);
    		boolean created = yearDAO.createyear(year);
		    		if(created) {
		    			clear();
		    			ObservableList<Year> lst=yearDAO.getallyears();
		    			showItemsInTable(lst);
		    		}else {
		    			System.out.println("fail to create!");
		    		}
    		}
    
    	
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		yearname.setCellValueFactory(new PropertyValueFactory<>("yearname"));
		
		ObservableList<Year> lst=yearDAO.getallyears();
		showItemsInTable(lst);
	
	}

}
