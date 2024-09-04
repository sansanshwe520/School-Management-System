package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Time {

	private IntegerProperty id;
	private StringProperty timename;
	private StringProperty starttime;
	private StringProperty endtime;
	private StringProperty starthour;
	private StringProperty startminute;
	private StringProperty endhour;
	private StringProperty endminute;
	
	public Time() {
		// TODO Auto-generated constructor stub
	}

	public Time(String timename, String starttime, String endtime) {
		super();
		this.timename = new SimpleStringProperty(timename);
		this.starttime = new SimpleStringProperty(starttime);
		this.endtime = new SimpleStringProperty(endtime);
//		this.starthour= new SimpleStringProperty(starthour);
//		this.startminute = new SimpleStringProperty(startminute);
//		this.endhour = new SimpleStringProperty(endhour);
//		this.endminute = new SimpleStringProperty(endminute);
	}

	public Time(Integer id, String timename, String starttime, String endtime) {
			//String starthour, String startminute, String endhour, String endminute) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.timename = new SimpleStringProperty(timename);
		this.starttime = new SimpleStringProperty(starttime);
		this.endtime = new SimpleStringProperty(endtime);
//		this.starthour=new SimpleStringProperty(starthour);
//		this.startminute= new SimpleStringProperty(startminute);
//		this.endhour= new SimpleStringProperty(endhour);
//		this.endminute=new SimpleStringProperty(endminute);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getTimename() {
		return timename.get();
	}

	public void setTimename(String timename) {
		this.timename = new SimpleStringProperty(timename);
	}

	public String getStarttime() {
		return starttime.get();
	}

	public void setStarttime(String starttime) {
		this.starttime = new SimpleStringProperty(starttime);
	}

	public String getEndtime() {
		return endtime.get();
	}

	public void setEndtime(String endtime) {
		this.endtime = new SimpleStringProperty(endtime);
	}
	
	public String getStarthour() {
		return starthour.get();
	}
	
	public void setStarthour(String starthour) {
		this.starthour = new SimpleStringProperty(starthour);
	}
	
	public String getStartminute() {
		return startminute.get();
	}
	
	public void setStartminute(String startminute) {
	  this.startminute = new SimpleStringProperty(startminute);	
	}
	
	public String getEndhour() {
		return endhour.get();
	}
	
	public void setEndhour(String endhour) {
		this.endhour = new SimpleStringProperty(endhour);
	}
	
	public String getEndminute() {
		return endminute.get();
	}
	
	public void setEndminute(String endminute) {
		this.endminute = new SimpleStringProperty(endminute);
	}
	
	@Override
	public String toString() {
		return timename.get();
	}
	
	
	
	
}
