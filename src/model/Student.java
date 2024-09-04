package model;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Student {

	private LongProperty id;
	private StringProperty name;
	private StringProperty parentName;
	private StringProperty email;
	private StringProperty gender;
	private ObjectProperty<LocalDate> birthDate;
	private StringProperty phone;
	private StringProperty address;
	private Batch batch;
	private Academic academicyear;

	
	
	public Student() {
		
	}

	public Student(String name, String parentName, String email, String gender, 
			LocalDate birthDate, String phone, String address,Batch batch,Academic academicyear) {
		super();
		this.name = new SimpleStringProperty(name);
		this.parentName = new SimpleStringProperty(parentName);
		this.email = new SimpleStringProperty(email);
		this.gender = new SimpleStringProperty(gender);
		this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
		this.phone = new SimpleStringProperty(phone);
		this.address = new SimpleStringProperty(address);
		this.batch = batch;
		this.academicyear = academicyear;
	}

	public Student(Long id, String name, String parentName,  String email,String gender,
			LocalDate birthDate, String phone, String address,Batch batch,Academic academicyear) {
		super();
		this.id = new SimpleLongProperty(id);
		this.name = new SimpleStringProperty(name);
		this.parentName = new SimpleStringProperty(parentName);
		this.email = new SimpleStringProperty(email);
		this.gender = new SimpleStringProperty(gender);
		this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
		this.phone = new SimpleStringProperty(phone);
		this.address = new SimpleStringProperty(address);
		this.batch = batch;
		this.academicyear = academicyear;
	}
	
	

	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id = new SimpleLongProperty(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getParentName() {
		return parentName.get();
	}

	public void setParentName(String parentName) {
		this.parentName = new SimpleStringProperty(parentName);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}

	
	
	
	 

	public LocalDate getBirthDate() {
		return birthDate.get();
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email =  new SimpleStringProperty(email);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone =  new SimpleStringProperty(phone);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public Academic getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(Academic academicyear) {
		this.academicyear = academicyear;
	}

	@Override
	public String toString() {
		return  name.get();
	}
	

	
	
	
	
}
