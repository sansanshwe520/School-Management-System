package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Year {
 
	private IntegerProperty id;
	private StringProperty yearname;
	
	public Year() {
		// TODO Auto-generated constructor stub
	}

	public Year( String yearname) {
		super();		
		this.yearname = new SimpleStringProperty(yearname);
	}

	public Year(Integer id, String yearname) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.yearname = new SimpleStringProperty(yearname);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getYearname() {
		return yearname.get();
	}

	public void setYearname(String yearname) {
		this.yearname = new SimpleStringProperty(yearname);
	}

	
	
	@Override
	public String toString() {
		return yearname.get() ;
	}
	
	
	
}
