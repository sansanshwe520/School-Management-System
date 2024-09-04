package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CourseEnrollment {
	private IntegerProperty id;
	private Teacher teacher;
	private Course course;
	private Batch batch;
	private Academic academicyear;
	private Year year;
	
	public CourseEnrollment() {
		// TODO Auto-generated constructor stub
	}

	public CourseEnrollment(Teacher teacher, Course course, Batch batch, Academic academicyear, Year year) {
		super();
		this.teacher = teacher;
		this.course = course;
		this.batch = batch;
		this.academicyear = academicyear;
		this.year = year;
	}

	public CourseEnrollment(Integer id, Teacher teacher, Course course, Batch batch, Academic academicyear,
			Year year) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.teacher = teacher;
		this.course = course;
		this.batch = batch;
		this.academicyear = academicyear;
		this.year = year;
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Academic getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(Academic academicyear) {
		this.academicyear = academicyear;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CourseEnrollment [id=" + id + ", teacher=" + teacher.getName() + ", course=" + course.getCoursename() + ", batch=" + batch.getBatchname()
				+ ", academicyear=" + academicyear.getAcademicyearname() + ", year=" + year.getYearname() + "]";
	}

	
	
	
}
