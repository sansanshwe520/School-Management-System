package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	private void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//view
	public Teacher getTeacherByID(Long id) {
		
		Teacher teacher = null;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacher where id='"+id+"';");
		
			while (rs.next()) {
				teacher = new Teacher(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("gender"), rs.getDate("dateofbirth").toLocalDate(), rs.getString("phone"),
						rs.getString("address"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return teacher;

	}

	// Read -R
	public ObservableList<Teacher> getAllTeachers() {
		ObservableList<Teacher> teachers = FXCollections.observableArrayList();

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacher;");
			while (rs.next()) {
				teachers.add(new Teacher(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("gender"), rs.getDate("dateofbirth").toLocalDate(), rs.getString("phone"),
						rs.getString("address")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return teachers;

	}

	public ObservableList<Teacher> getAllTeachersByName(String name) {
		ObservableList<Teacher> teachers = FXCollections.observableArrayList();

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacher where name='" + name + "';");
			while (rs.next()) {
				teachers.add(new Teacher(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("gender"), rs.getDate("dateofbirth").toLocalDate(), rs.getString("phone"),
						rs.getString("address")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return teachers;

	}
	
	public Teacher getTeacherByName(String name) {
		Teacher teachers = null;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from teacher where name='" + name + "';");
			if(rs.next()) {
				teachers=new Teacher(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("gender"), rs.getDate("dateofbirth").toLocalDate(), rs.getString("phone"),
						rs.getString("address"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return teachers;

	}

	public boolean createTeacher(Teacher teacher) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into teacher "
					+ "(id,name,email,gender,dateofbirth,phone,address) " + "values(?,?,?,?,?,?,?);");
			pStmt.setLong(1, teacher.getId());
			pStmt.setString(2, teacher.getName());
			pStmt.setString(3, teacher.getEmail());
			pStmt.setString(4, teacher.getGender());
			pStmt.setDate(5, Date.valueOf(teacher.getBirthDate()));
			pStmt.setString(6, teacher.getPhone());
			pStmt.setString(7, teacher.getAddress());

			created = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;

	}

	public boolean updateTeacher(Teacher teacher) {
		boolean created = false;
		connection = DBConnection.getConnection();
		
		try {
			pStmt = connection.prepareStatement("update teacher set "
			
					+"name=?,"
					+"email=?,"
					+"gender=?,"
					+"dateofbirth=?,"
					+"phone=?,"
					+"address=? where id=?;"
					);
			
			pStmt.setString(1, teacher.getName());
			pStmt.setString(2, teacher.getEmail());
			pStmt.setString(3, teacher.getGender());
			pStmt.setDate(4,Date.valueOf(teacher.getBirthDate()));
			pStmt.setString(5, teacher.getPhone());
			pStmt.setString(6, teacher.getAddress());
			pStmt.setLong(7, teacher.getId());
		
			created = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
	}
	public boolean deleteTeacher(long id) {
		boolean created = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			created = stmt.executeUpdate("delete from teacher where id='" + id + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return created;

	}
	
}