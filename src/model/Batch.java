package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Batch {
	
	private IntegerProperty id;
	private StringProperty batchname;
	
	public Batch() {
		// TODO Auto-generated constructor stub
	}

	public Batch(String batchname) {
		super();
		this.batchname = new SimpleStringProperty(batchname);
	}

	public Batch(Integer id, String batchname) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.batchname = new SimpleStringProperty(batchname);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getBatchname() {
		return batchname.get();
	}

	public void setBatchname(String batchname) {
		this.batchname =new SimpleStringProperty(batchname);
	}

	@Override
	public String toString() {
		return batchname.get() ;
	}
	
	
	
	

}
