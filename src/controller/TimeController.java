package controller;

import java.net.URL;


import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Time;
import model.TimeDAO;

public class TimeController implements Initializable {

    @FXML
    private Button btnAdd;
//
//    @FXML
//    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;
    
    @FXML
    private TextField tftimename;

    @FXML
    private ComboBox<String> combostartHour;

    @FXML
    private ComboBox<String> comboEndMinute;

    @FXML
    private ComboBox<String> comboEndHour;
    

    @FXML
    private ComboBox<String> comboStartMinute;

    @FXML
    private TableColumn<Time, LocalDateTime> endtime;

    @FXML
    private TableColumn<Time, Integer> id;

    @FXML
    private Label lblEndTime;

    @FXML
    private Label lblStartTime;

    @FXML
    private TableColumn<Time, LocalDateTime> starttime;

    @FXML
    private TableView<Time> timeTable;

    @FXML
    private TableColumn<Time, String> timename;
    
    private final TimeDAO timeDAO = new TimeDAO();
    private boolean isNewButtonClicked = false;
    private Integer No = 0;
    
    private void showItemsInTable(ObservableList<Time> lst) {
        for (int i = 0; i < lst.size(); i++) {
    			lst.get(i).setId(i+1);
    		}
    		timeTable.setItems(lst);
        }
    
    private void disable() {
    	tftimename.setDisable(true);
    	combostartHour.setDisable(true);
    	comboStartMinute.setDisable(true);
    	comboEndHour.setDisable(true);
    	comboEndMinute.setDisable(true);
    	btnSave.setDisable(true);
//    	btnDelete.setDisable(true);
//    	btnClear.setDisable(true);

    }
    
    private void enable() {
    	tftimename.setDisable(false);
    	combostartHour.setDisable(false);
    	comboStartMinute.setDisable(false);
    	comboEndHour.setDisable(false);
    	comboEndMinute.setDisable(false);
    	btnSave.setDisable(false);
//    	btnDelete.setDisable(false);
    }
    
    private void clear() {
    	tftimename.clear();
//    	combostartHour.setValue("Hour");
//    	comboStartMinute.setValue("Minutes");
//    	comboEndHour.setValue("Hour");
//    	comboEndMinute.setValue("Minutes");;
    	btnSave.setDisable(false);
    	
    }

    @FXML
    void processAdd(ActionEvent event) {
    	enable();
    	clear();
    	isNewButtonClicked = true;
    }

//    @FXML
//    void processClear(ActionEvent event) {
//    	clear();
//    	
//    }
    


    @FXML
    void processDelete(ActionEvent event) {
    	Time selectedtime = timeTable.getSelectionModel().getSelectedItem();
    	if(selectedtime==null) {
    		
    		return;
    		
    	}
    	
    	
    	boolean deleted = timeDAO.deletetimename(selectedtime.getTimename());
    	if(deleted) {
    		ObservableList<Time> lst=timeDAO.getalltime();
    		showItemsInTable(lst);
    	}else {
    		System.out.println("fail to delete!!!");
    	}
    }

//    @FXML
//    void processRefresh(ActionEvent event) {
//    	ObservableList<Time> lst=timeDAO.getalltime();
//		showItemsInTable(lst);
//    }

    @FXML
    void processSave(ActionEvent event) {
    	String timename = tftimename.getText();
    	String starthour= combostartHour.getValue();
    	String startminute = comboStartMinute.getValue();
    	String endhour=comboEndHour.getValue();
    	String endminute=comboEndMinute.getValue();
    	
    	String starttime =starthour+":"+startminute;
    	String endtime = endhour+":"+endminute;
    	
    	System.out.println(starttime);
    	System.out.println(endtime);
    	
    	if(isNewButtonClicked) {
    		Time time = new Time(timename, starttime, endtime);
    		boolean created = timeDAO.createtime(time);
    		
    		if(created) {
    			disable();
    			clear();
    			combostartHour.setValue("Hour");
    	    	comboStartMinute.setValue("Minutes");
    	    	comboEndHour.setValue("Hour");
    	    	comboEndMinute.setValue("Minutes");;
    			ObservableList<Time> lst=timeDAO.getalltime();
    			showItemsInTable(lst);   			
    		}else {
    			System.out.println("fail to create!");
    		}
    	}
//    	else {
//    		Time time = new Time(No, timename, starttime, endtime);
//    		boolean updated = timeDAO.updatetime(time);
//    		
//    		if(updated) {
//    			disable();
//    			clear();
//    			ObservableList<Time> lst=timeDAO.getalltime();
//    			showItemsInTable(lst);
//    		}else {
//    			System.out.println("fail to update");
//    		}
    	}
    	
    			
//    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method 
		ObservableList<String> hourList = FXCollections.observableArrayList(
				"09","10","11","12","01","02","03","04"
				);
		combostartHour.setItems(hourList);
		
		comboStartMinute.setItems(FXCollections.observableArrayList(
				"00","01","02","03","04","05","06","07","08","09","10",
				"11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30",
				"31","32","33","34","35","36","37","38","39","40",
				"41","42","43","44","45","46","47","48","49","50",
				"51","52","53","54","55","56","57","58","59"
				));
		
		comboEndHour.setItems(FXCollections.observableArrayList(
				"09","10","11","12","01","02","03","04"
				));
		
		comboEndMinute.setItems(FXCollections.observableArrayList(
				"00","01","02","03","04","05","06","07","08","09","10",
				"11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30",
				"31","32","33","34","35","36","37","38","39","40",
				"41","42","43","44","45","46","47","48","49","50",
				"51","52","53","54","55","56","57","58","59"
				));
		
		//binding
	    id.setCellValueFactory(new PropertyValueFactory<>("id"));
	    timename.setCellValueFactory(new PropertyValueFactory<>("timename"));
		starttime.setCellValueFactory(new PropertyValueFactory<>("starttime"));
		endtime.setCellValueFactory(new PropertyValueFactory<>("endtime"));
		
		ObservableList<Time> lst=timeDAO.getalltime();
		showItemsInTable(lst);
		disable();
//		btnDelete.setDisable(true);
	}

}

