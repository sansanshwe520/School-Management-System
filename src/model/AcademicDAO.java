package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AcademicDAO {

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
	
	
	//read
	public ObservableList<Academic> getAllAcademic() {
		ObservableList<Academic> academic = FXCollections.observableArrayList();

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from academicyear;");
			while (rs.next()) {
				academic.add(new Academic(
						rs.getInt("id"),
						rs.getString("academicyearname")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return academic;

	}
	
	
	public Academic getAcademicById(Long aca_id) {
		Academic academic = null;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from academicyear where id='"+aca_id+"';");
			if (rs.next()) {
				academic=new Academic(
						rs.getInt("id"),
						rs.getString("academicyearname"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return academic;
	}
	
	public Academic getAcademicByName(String academicyearname) {
		Academic academic = null;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from academicyear where academicyearname='"+academicyearname+"';");
			if (rs.next()) {
				academic=new Academic(
						rs.getInt("id"),
						rs.getString("academicyearname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return academic;
	}
	
	
	//create
	public boolean createAcademicYear(Academic academicyearname) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into academicyear "
					+ "(academicyearname) " + "values(?);");
			
			pStmt.setString(1, academicyearname.getAcademicyearname());
			

			created = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;

	}
	
	//delete
	public boolean deleteAcademic(Integer id) {
		boolean deleted = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from academicyear where id='" + id + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return deleted;
	}

	
	public boolean deleteAcademicByName(String academicyearname) {
		boolean created = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			created = stmt.executeUpdate("delete from academicyear where academicyearname='" + academicyearname + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return created;
	}


	



	
	}
	
	

