package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentAttendenceDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private final TimeDAO timeDAO = new TimeDAO();
	private final StudentDAO studentDAO = new StudentDAO();
	private final BatchDAO batchDAO = new BatchDAO();
	private void close() {
		try {
			if(connection != null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObservableList<StudentAttendence> getAllStudentAttendenceByOther(Long time_id,Long student_id ,Long batch_id){
		ObservableList<StudentAttendence> studentAttendence = FXCollections.observableArrayList();
		
		Student student = studentDAO.getStudentByID(student_id);
		Time time = timeDAO.getTimeById(time_id);
		Batch batch = batchDAO.getBatchById(batch_id);
		
		
		connection =	DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from studentattendence where student_idx='"+student_id+"' and timeid_idx='"+time_id+"' and batch_idx='"+batch_id+"';");
			while(rs.next()) {
				studentAttendence.add(new StudentAttendence(
						rs.getLong("id"),
						rs.getDate("date").toLocalDate(),
						student,
						batch,
						time
						
						));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentAttendence;
		
	}
	
	public ObservableList<StudentAttendence> getAllStudentAttendence(){
		ObservableList<StudentAttendence> studentAttendence = FXCollections.observableArrayList();
		
		
		connection =	DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from studentattendence;");
			
			while(rs.next()) {
				
				Time time = timeDAO.getTimeById(rs.getLong("timeid_idx"));
				Student student = studentDAO.getStudentByID(rs.getLong("student_idx"));
				Batch batch = batchDAO.getBatchById(rs.getLong("batch_idx"));
				studentAttendence.add(new StudentAttendence(
						rs.getLong("id"),
						rs.getDate("date").toLocalDate(),
						student,
						batch,
						time
						
						));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentAttendence;
		
	}
	
	public boolean createStudentAttendence(StudentAttendence studentAttendence) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into studentattendence "
												+"(date,student_idx,batch_idx,timeid_idx)"
												+"values(?,?,?,?);");
			
			pStmt.setDate(1, Date.valueOf(studentAttendence.getDate()));
			pStmt.setLong(2, studentAttendence.getStudent().getId());
			pStmt.setLong(3, studentAttendence.getBatch().getId());
			pStmt.setLong(4, studentAttendence.getTime().getId());
			
			created = pStmt.executeUpdate() > 0 ? true : false ;
			//created = pStmt.execute();
			System.out.println("IN th DAO, after inseted one row"+created);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
		
	}
	public boolean updateStudentAttendence(StudentAttendence studentAttendence) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("update studentattendence set"
					+"date=?,"
					+"student_idx=?,"
					+"batch_idx=?,"
					+"timeid_idx=? where id=?;"					
					);
			
			pStmt.setDate(1, Date.valueOf(studentAttendence.getDate()));
			pStmt.setLong(2, studentAttendence.getStudent().getId());
			pStmt.setLong(3, studentAttendence.getBatch().getId());
			pStmt.setLong(4, studentAttendence.getTime().getId());
			created = pStmt.executeUpdate() > 0 ? true : false ;
			//created = pStmt.execute();
			System.out.println("IN th DAO, after inseted one row"+created);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
		
	}
	
	public boolean deleteStudentAttendence(long id) {
		boolean created = false;
		
		connection = DBConnection.getConnection();
		try {
			stmt  = connection.createStatement();
			created = stmt.executeUpdate("delete from studentattendence where id='"+id+"';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close();
		}
		return created;
		
	}
	
	public ObservableList<StudentAttendence> getAllStudentAttendenceByName(String name){
		ObservableList<StudentAttendence> studentAttendence = FXCollections.observableArrayList();
		Student student=studentDAO.getStudentByName(name);
		
		connection =	DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from studentattendence where student_idx='"+student.getId()+"';");
			
			while(rs.next()) {
				
				Time time = timeDAO.getTimeById(rs.getLong("timeid_idx"));
				Batch batch = batchDAO.getBatchById(rs.getLong("batch_idx"));
				studentAttendence.add(new StudentAttendence(
						rs.getLong("id"),
						rs.getDate("date").toLocalDate(),
						student,
						batch,
						time
						
						));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentAttendence;		
	}
	
	public StudentAttendence getStudentAttendanceByAll(StudentAttendence selectedAttendence) {
	    // TODO Auto-generated method stub
	    StudentAttendence attendance = null;
	    connection = DBConnection.getConnection();    
	    try {
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery("select * from studentattendence where date='"+selectedAttendence.getDate()+"'student_idx='"+selectedAttendence.getStudent()+"' and timeid_idx='"+selectedAttendence.getTime()
	      +"' and batch_idx='"+selectedAttendence.getBatch()+"';");
	      if(rs.next()) {
	        Batch batch = batchDAO.getBatchById(rs.getLong("batch_idx"));
	        Student student = studentDAO.getStudentByID(rs.getLong("student_idx"));
	        Time time = timeDAO.getTimeById(rs.getLong("timeid_idx"));
	        attendance = new StudentAttendence(
	            rs.getLong("id"),
	            rs.getDate("date").toLocalDate(),
	            student,
	            batch,
	            time
	            );
	      }
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }finally {
	      close();
	    }
	    return attendance;
	  }
}
