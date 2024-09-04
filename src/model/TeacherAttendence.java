package model;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TeacherAttendence {

	private LongProperty id;
	private ObjectProperty<LocalDate> date;
	private Teacher teacher;
	private Time time;
	private Batch batch;

	public TeacherAttendence() {

	}

	public TeacherAttendence(LocalDate date, Teacher teacher, Time time, Batch batch) {
		super();
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.teacher = teacher;
		this.time = time;
		this.batch = batch;
	}

	public TeacherAttendence(Long id, LocalDate date, Teacher teacher, Time time, Batch batch) {
		super();
		this.id = new SimpleLongProperty(id);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.teacher = teacher;
		this.time = time;
		this.batch = batch;
	}

	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(LocalDate date) {
		this.date = new SimpleObjectProperty<LocalDate>(date);
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id = new SimpleLongProperty(id);
	}

	@Override
	public String toString() {
		return "TeacherAttendence [id=" + id + ", date=" + date + ", teacher=" + teacher + ", time=" + time + ", batch="
				+ batch + "]";
	}
}
