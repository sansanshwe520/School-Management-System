package model;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {

	private IntegerProperty id;
	private StringProperty coursename;
	private StringProperty description;
	
	public Course() {
		
	}

	public Course(String coursename, String description) {
		super();
		this.coursename = new SimpleStringProperty(coursename);
		this.description = new SimpleStringProperty(description);
	}

	public Course(Integer id, String coursename, String description) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.coursename = new SimpleStringProperty(coursename);
		this.description = new SimpleStringProperty(description);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getCoursename() {
		return coursename.get();
	}

	public void setCoursename(String coursename) {
		this.coursename = new SimpleStringProperty(coursename);
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}

	@Override
	public String toString() {
		return coursename.get() ;
	}
	
}
