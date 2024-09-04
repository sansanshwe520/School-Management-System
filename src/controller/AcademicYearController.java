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
import model.Academic;
import model.AcademicDAO;

public class AcademicYearController implements Initializable {

	@FXML
	private TableView<Academic> academicTable;

	@FXML
	private TableColumn<Academic, String> academicyearname;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btedit;

	@FXML
	private TableColumn<Academic, Integer> no;

	@FXML
	private TextField tfAcademicYear;

	private final AcademicDAO academicDAO = new AcademicDAO();

	// order
	private void showItemsInTable(ObservableList<Academic> lst) {
		for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setId(i + 1);
		}
		academicTable.setItems(lst);
	}

	private void clear() {
		tfAcademicYear.clear();

	}

	@FXML
	void processAdd(ActionEvent event) {

		String academicYear = tfAcademicYear.getText().trim();

		Academic academic = new Academic(academicYear);
		boolean created = academicDAO.createAcademicYear(academic);
		if (created) {

			clear();
			ObservableList<Academic> lst = academicDAO.getAllAcademic();
			showItemsInTable(lst);
		} else {
			System.out.println("fail to create academicYear");
		}

	}

	@FXML
	void processDelete(ActionEvent event) {
		Academic selectedAcademic = academicTable.getSelectionModel().getSelectedItem();

		if (selectedAcademic == null) {
			return;
		}

		boolean deleted = academicDAO.deleteAcademicByName(selectedAcademic.getAcademicyearname());
		if (deleted) {

			ObservableList<Academic> lst = academicDAO.getAllAcademic();
			showItemsInTable(lst);
		} else {
			System.out.println("Fail to delete academic year");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		no.setCellValueFactory(new PropertyValueFactory<>("id"));
		academicyearname.setCellValueFactory(new PropertyValueFactory<>("academicyearname"));
		ObservableList<Academic> lst = academicDAO.getAllAcademic();
		showItemsInTable(lst);
	}

}
