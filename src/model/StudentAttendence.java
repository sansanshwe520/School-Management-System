package model;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;


public class StudentAttendence {
	private LongProperty id;
	private ObjectProperty<LocalDate> date;
	private Student student;
	private Batch batch;
	private Time time;
	
	public  StudentAttendence() {
		
	}

	public StudentAttendence(LocalDate date, Student student,Batch batch, Time time) {
		super();
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.student = student;
		this.batch = batch;
		this.time = time;
	}

	public StudentAttendence(Long id, LocalDate date, Student student,Batch batch, Time time) {
		super();
		this.id = new SimpleLongProperty(id);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.student = student;
		this.batch = batch;
		this.time = time;
	}

	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id = new SimpleLongProperty(id);
	}

	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(LocalDate date) {
		this.date = new SimpleObjectProperty<LocalDate>(date);
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	


	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	@Override
	public String toString() {
		return "StudentAttendence [id=" + id + ", date=" + date + ", student=" + student + ", time=" + time + 
				 "]";
	}

	
	
	
}
