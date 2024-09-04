package model;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marks {
	
	private IntegerProperty id;
	private IntegerProperty marks;
	
	private Year year;
	private ExamType examtype;
	private Course course;
	private Batch batch;
	private Student student;

	
	
	public Marks() {
		// TODO Auto-generated constructor stub
	}

	public Marks(Integer marks, Year year, ExamType examtype,Course course,Batch batch,Student student) {
		super();
//		this.studentname = new SimpleStringProperty(studentname);
		this.marks = new SimpleIntegerProperty(marks);
		this.year = year;
		this.examtype = examtype;
		this.course = course;
		this.batch = batch;
		this.student = student;
	}

	public Marks(Integer id, Integer marks, Year year, ExamType examtype,Course course, Batch batch,Student student) {
		super();
		this.id =new SimpleIntegerProperty(id);
//		this.studentname = new SimpleStringProperty(studentname);
		this.marks = new SimpleIntegerProperty(marks);
		this.year = year;
		this.examtype = examtype;
		this.course = course;
		this.batch = batch;
		this.student = student;
	}

//	public Integer getId() {
//		return id.get();
//	}
//	
//	public void setId(Integer id) {
//		this.id = new SimpleIntegerProperty(id);
//	}
	
	

	

//	public String getStudentname() {
//		return studentname.get();
//	}
//
//	public void setStudentname(String studentname) {
//		this.studentname = new SimpleStringProperty(studentname);
//	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	public Integer getMarks() {
		return marks.get();
	}

	

	public void setMarks(Integer marks) {
		this.marks = new SimpleIntegerProperty(marks);
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public ExamType getExamtype() {
		return examtype;
	}

	public void setExamtype(ExamType examtype) {
		this.examtype = examtype;
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
	
	
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Marks [id=" + id + ", year=" + year + ", examtype=" + examtype + ", course=" + course + ", marks="+ marks + ", batch="+ batch + ", student="+ 
	student +"]";
	}
	
	
	

}
