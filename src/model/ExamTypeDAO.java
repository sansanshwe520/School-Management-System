package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExamTypeDAO {
	
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
	
	public ObservableList<ExamType> getAllExamTypes(){
		ObservableList<ExamType> examtype = FXCollections.observableArrayList();
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from exam;");
			while (rs.next()) {
				examtype.add(new ExamType(
						rs.getInt("id"),
						rs.getString("examtype")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return examtype;
	}
	
	public boolean createExamType(ExamType examtype) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into exam "
					+ "(examtype) " + "values(?);");
			
			pStmt.setString(1, examtype.getExamtype());
			

			created = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;

	}
	
	public boolean deleteExamType(String examtype) {
		boolean created = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			created = stmt.executeUpdate("delete from exam where examtype='" + examtype + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return created;

	}

	public ExamType getExamTypeById(Long examtype_id) {
		ExamType examtype = null;
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from exam where id='"+examtype_id+"';");
			if (rs.next()) {
				examtype=new ExamType(rs.getInt("id"),rs.getString("examtype"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return examtype;
	}

	public ExamType getExamTypeByName(String examtypename) {
               ExamType examtype = null;
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from exam where examtype='"+examtypename+"';");
			if (rs.next()) {
				examtype=new ExamType(rs.getInt("id"),rs.getString("examtype"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return examtype;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
