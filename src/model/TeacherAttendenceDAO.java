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

public class TeacherAttendenceDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	private final BatchDAO batchDAO = new BatchDAO();
	private final TimeDAO timeDAO = new TimeDAO();
	private final TeacherDAO teacherDAO = new TeacherDAO();

	private void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<TeacherAttendence> getAllTeacherAttendenceByOther(Long time_id, Long teacher_id,
			Long batch_id) {
		ObservableList<TeacherAttendence> teacherAttendence = FXCollections.observableArrayList();
		Batch batch = batchDAO.getBatchById(batch_id);
		Time time = timeDAO.getTimeById(time_id);
		Teacher teacher = teacherDAO.getTeacherByID(teacher_id);

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacherattendance where batch='" + batch_id + "' and teacher_id='"
					+ teacher_id + "' and time_id='" + time_id + "';");
			while (rs.next()) {
				teacherAttendence.add(new TeacherAttendence(rs.getLong("id"), rs.getDate("date").toLocalDate(), teacher,
						time, batch));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherAttendence;
	}

	public ObservableList<TeacherAttendence> getAllTeacherAttendence() {
		ObservableList<TeacherAttendence> teacherAttendence = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacherattendance;");
			while (rs.next()) {
				Batch batch = batchDAO.getBatchById(rs.getLong("batch"));
				Time time = timeDAO.getTimeById(rs.getLong("time_id"));
				Teacher teacher = teacherDAO.getTeacherByID(rs.getLong("teacher_id"));
				teacherAttendence.add(new TeacherAttendence(rs.getLong("id"), rs.getDate("date").toLocalDate(), teacher,
						time, batch));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherAttendence;
	}

	public boolean createTeacherAttendence(TeacherAttendence teacherAttendence) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement(
					"insert into teacherattendance " + "(date,teacher_id,time_id,batch)" + "values(?,?,?,?);");

			pStmt.setDate(1, Date.valueOf(teacherAttendence.getDate()));
			pStmt.setLong(2, teacherAttendence.getTeacher().getId());
			pStmt.setLong(3, teacherAttendence.getTime().getId());
			pStmt.setLong(4, teacherAttendence.getBatch().getId());

			created = pStmt.executeUpdate() > 0 ? true : false;
			System.out.println("IN th DAO, after inseted one row" + created);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
	}

	public boolean updateTeacherAttendence(TeacherAttendence teacherAttendence) {
		boolean updated = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("UPDATE teacherattendance SET "
		            + "date=?, "
		            + "teacher_id=?, "
		            + "time_id=?, "
		            + "batch=? WHERE id=?");
			pStmt.setDate(1, Date.valueOf(teacherAttendence.getDate()));
			pStmt.setLong(2, teacherAttendence.getTeacher().getId());
			pStmt.setLong(3, teacherAttendence.getTime().getId());
			pStmt.setLong(4, teacherAttendence.getBatch().getId());
			pStmt.setLong(5, teacherAttendence.getId());

			
			   updated = pStmt.executeUpdate()>0?true:false;

			    if (updated) {
			        System.out.println("Update successful. Rows affected: " + updated);
			    } else {
			        System.out.println("No rows updated.");
			    }
			//updated = pStmt.executeUpdate() > 0 ? true : false;
			System.out.println("IN th DAO, after updated one row" + updated);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}

	public boolean deleteTeacherAttendence(long id) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			created = stmt.executeUpdate("delete from teacherattendance where id='" + id + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return created;
	}

	public ObservableList<TeacherAttendence> getAllTeacherAttendenceByName(String name) {
		Teacher teacher = teacherDAO.getTeacherByName(name);
		ObservableList<TeacherAttendence> teacherAttendence = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacherattendance where teacher_id='" + teacher.getId() + "';");
			while (rs.next()) {
				Batch batch = batchDAO.getBatchById(rs.getLong("batch"));
				Time time = timeDAO.getTimeById(rs.getLong("time_id"));
				// Teacher teacher = teacherDAO.getTeacherByID(rs.getLong("teacher_id"));
				teacherAttendence.add(new TeacherAttendence(rs.getLong("id"), rs.getDate("date").toLocalDate(), teacher,
						time, batch));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherAttendence;
	}

	public TeacherAttendence getTeacherAttendenceByAll(TeacherAttendence selectedAttendence) {
	    // TODO Auto-generated method stub
	    TeacherAttendence attendance = null;
	    connection = DBConnection.getConnection();	    
	    try {
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery("select * from teacherattendance where date='"+selectedAttendence.getDate()+"' and teacher_id='"+selectedAttendence.getTeacher().getId()
	    		  +"'and time_id='"+selectedAttendence.getTime().getId()
	      +"' and batch='"+selectedAttendence.getBatch().getId()+"';");
	      if(rs.next()) {
	    	  Teacher teachername = teacherDAO.getTeacherByID(rs.getLong("teacher_id"));
	    	  Batch batch = batchDAO.getBatchById(rs.getLong("batch"));
	    	  Time time = timeDAO.getTimeById(rs.getLong("time_id"));
	        attendance = new TeacherAttendence(
	        		rs.getLong("id"),
	        		rs.getDate("date").toLocalDate(),
	        		teachername,
	        		time,
	        		batch);
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