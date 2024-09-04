package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExamType {
	private IntegerProperty id;
	private StringProperty examtype;
	
	public ExamType() {
		// TODO Auto-generated constructor stub
	}

	public ExamType(String examtype) {
		super();
		this.examtype = new SimpleStringProperty(examtype);
	}

	public ExamType(Integer id, String examtype) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.examtype = new SimpleStringProperty(examtype);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getExamtype() {
		return examtype.get();
	}

	public void setExamtype(String examtype) {
		this.examtype = new SimpleStringProperty(examtype);
	}
	
	@Override
	public String toString() {
		return examtype.get() ;
	}
	
	
	

}
