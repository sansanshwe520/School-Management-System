package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Academic {

	private IntegerProperty id;
	private StringProperty academicyearname;
	
	
public Academic() {
	// TODO Auto-generated constructor stub
}


public Academic(String academicyearname) {
	super();
	this.academicyearname = new SimpleStringProperty(academicyearname);
}


public Academic(Integer id, String academicyearname) {
	super();
	this.id = new SimpleIntegerProperty(id);
	this.academicyearname = new SimpleStringProperty(academicyearname);
}


public Integer getId() {
	return id.get();
}


public void setId(Integer id) {
	this.id = new SimpleIntegerProperty(id);
}





public String getAcademicyearname() {
	return academicyearname.get();
}


public void setAcademicyearname(String academicyearname) {
	this.academicyearname =new SimpleStringProperty(academicyearname);
}


@Override
public String toString() {
	return academicyearname.get();
}








	
}
