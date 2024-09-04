package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	private final BatchDAO batchDAO=new BatchDAO();
	private final AcademicDAO academicDAO=new AcademicDAO();
	
	private void close() {
		try {
			if(connection != null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// view
		public Student getStudentByID(Long id) {
			
			Student student = null;
			
			
			connection = DBConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs= stmt.executeQuery("select * from student where id='"+id+"';");
				
				while(rs.next()) {
					Batch batch=batchDAO.getBatchById(rs.getLong("batch_id"));
					Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
					student = new Student(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getString("parentname"),
							rs.getString("email"),
							rs.getString("gender"),
							rs.getDate("dateofbirth").toLocalDate(),
							rs.getString("phone"),
							rs.getString("address"),
							batch,
							academic
							
							);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return student;
		}
		
		public ObservableList<Student> getAllStudent() {
			ObservableList<Student> students = FXCollections.observableArrayList();
			connection = DBConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from student;");
				while (rs.next()) {
					Batch batch=batchDAO.getBatchById(rs.getLong("batch_id"));
					Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
					System.out.println("academic in DAo"+academic);
					students.add(new Student(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getString("parentname"),
							rs.getString("email"),
							rs.getString("gender"),
							rs.getDate("dateofbirth").toLocalDate(),
							rs.getString("phone"),
							rs.getString("address"),
							batch,
							academic
							));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return students;
		}
		
		public Student getAllStudentByName(String name){
			ObservableList<Student> student = FXCollections.observableArrayList();
			
			connection = DBConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from student where name='"+name+"';");
				while (rs.next()) {
					Batch batch=batchDAO.getBatchById(rs.getLong("batch_id"));
					Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
					student.add(new Student(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getString("parentname"),
							rs.getString("email"),
							rs.getString("gender"),
							rs.getDate("dateofbirth").toLocalDate(),
							rs.getString("phone"),
							rs.getString("address"),
							batch,
							academic
							));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return (Student) student;
			
		}
		
		
		public Student getStudentByName(String studentName) {
		    
		    Student student = null;
		    connection = DBConnection.getConnection();
		    try {
		      stmt = connection.createStatement();
		      rs = stmt.executeQuery("select * from student where name='"+studentName+"';");
		      if (rs.next()) {
		        Batch batch=batchDAO.getBatchById(rs.getLong("batch_id"));
		        Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
		        student =new Student(
		        		rs.getLong("id"),
						rs.getString("name"),
						rs.getString("parentname"),
						rs.getString("email"),
						rs.getString("gender"),
						rs.getDate("dateofbirth").toLocalDate(),
						rs.getString("phone"),
						rs.getString("address"),
						batch,
						academic
		            );
		      }
		    } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }    
		    return student;
		  }

		
	public  ObservableList<Student> getAllStudentByBatch(Long batch_id, Long aca_id) {
		ObservableList<Student> students = FXCollections.observableArrayList();
		Batch batch=batchDAO.getBatchById(batch_id);
		Academic academic = academicDAO.getAcademicById(aca_id);
		connection = DBConnection.getConnection();
		
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from student where batch_id='"+batch_id+"' and aca_id='"+aca_id+"';");
				while (rs.next()) {
					students.add(new Student(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getString("parentname"),
							rs.getString("email"),
							rs.getString("gender"),
							rs.getDate("dateofbirth").toLocalDate(),
							rs.getString("phone"),
							rs.getString("address"),
							batch,
							academic
							
							
					));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			
				
			return students;			
	}
	
	
	public boolean createStudent(Student student) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			//INSERT INTO `shoolmanagementsystemdb`.`student` (`id`, `name`, `parentname`, `email`, `gender`, `dateofbirth`, `phone`, `address`, `batch_id`) 
			//VALUES ('1002', 'ggg', 'gg', 'gg@Gmail.com', 'Female', '2024-02-03', '95685768', 'mdy', '1');
			pStmt = connection.prepareStatement("insert into student "
					+ "(id,name,parentname,email,gender,dateofbirth,phone,address,batch_id,aca_id) "
					+ "values(?,?,?,?,?,?,?,?,?,?);");
			pStmt.setLong(1, student.getId());
			pStmt.setString(2, student.getName());
			pStmt.setString(3, student.getParentName());
			pStmt.setString(4, student.getEmail());
			pStmt.setString(5, student.getGender());
			pStmt.setDate(6, Date.valueOf(student.getBirthDate()));
			pStmt.setString(7, student.getPhone());
			pStmt.setString(8, student.getAddress());
			pStmt.setInt(9, student.getBatch().getId());
			pStmt.setInt(10, student.getAcademicyear().getId());
						
			 created = pStmt.executeUpdate() > 0 ? true : false;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;		
	}
	
	public boolean updateStudent(Student student) {
		boolean created = false;
		connection = DBConnection.getConnection();
		
		try {
//			Batch batch = batchDAO.getBatchById(rs.getLong("batch_id"));
//			Academic academicyear=academicDAO.getAcademicById(rs.getLong("aca_id"));
			pStmt = connection.prepareStatement("update student set "
			
					+"name=?,"
					+"parentName=?,"
					+"email=?,"
					+"gender=?,"
					+"dateofbirth=?,"
					+"phone=?,"
					+"address=?,"
					+ "batch_id=?,"
					+ "aca_id=?  where id=?;"
					);
			
			pStmt.setString(1, student.getName());
			pStmt.setString(2, student.getParentName());
			pStmt.setString(3, student.getEmail());
			pStmt.setString(4, student.getGender());
			pStmt.setDate(5,Date.valueOf(student.getBirthDate()));
			pStmt.setString(6, student.getPhone());
			pStmt.setString(7, student.getAddress());
			pStmt.setInt(8, student.getBatch().getId());
			pStmt.setInt(9, student.getAcademicyear().getId());
			pStmt.setLong(10, student.getId());
		
			created = pStmt.executeUpdate() > 0 ? true : false;
			System.out.println("in update dao !!! "+created);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
	}
	
	public boolean deleteStudent(long id) {
		boolean created = false;
		
		connection = DBConnection.getConnection();
		try {
			stmt  = connection.createStatement();
			created = stmt.executeUpdate("delete from student where id='"+id+"';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close();
		}
		return created;
		
	}



	public ObservableList<Student> getAllStudentByBatchName(String batchName){
	    Batch batch=batchDAO.getBatchByBatchName(batchName);
	    ObservableList<Student> students = FXCollections.observableArrayList();
	    connection = DBConnection.getConnection();
	    try {
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery("select * from student where batch_id='"+batch.getId()+"';");
	      while (rs.next()) {
	        
	        Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
	        students.add(new Student(
	        		rs.getLong("id"),
					rs.getString("name"),
					rs.getString("parentname"),
					rs.getString("email"),
					rs.getString("gender"),
					rs.getDate("dateofbirth").toLocalDate(),
					rs.getString("phone"),
					rs.getString("address"),
					batch,
					academic
	            ));
	      }
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    
	    return students;
	    
	  }
	
	public ObservableList<Student> getAllStudentByNameSearch(String name){
		ObservableList<Student> student = FXCollections.observableArrayList();
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from student where name='"+name+"';");
			while (rs.next()) {
				Batch batch=batchDAO.getBatchById(rs.getLong("batch_id"));
				Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
				student.add(new Student(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("parentname"),
						rs.getString("email"),
						rs.getString("gender"),
						rs.getDate("dateofbirth").toLocalDate(),
						rs.getString("phone"),
						rs.getString("address"),
						batch,
						academic
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return student;
		
	}
	

	
}
