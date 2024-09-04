package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class YearDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private void close() {	
			try {
				if(connection != null) 
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	//read 
	public ObservableList<Year> getallyears(){
		ObservableList<Year> year = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from year;");
			
			while(rs.next()) {
				year.add(new Year(
						rs.getInt("id"),
						rs.getString("yearname")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return year;
	}
	
	
	
	public Year getYearById(Long year_id) {
		Year year = null;
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from year where id='"+year_id+"';");
			if (rs.next()) {
				year=new Year(rs.getInt("id"),rs.getString("yearname"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return year;
	}
	
	public Year getYearByYearName(String yearname) {
		Year year = null;
				
				connection = DBConnection.getConnection();
				try {
					stmt = connection.createStatement();
					rs = stmt.executeQuery("select * from year where yearname='"+yearname+"';");
					if (rs.next()) {
						year=new Year(rs.getInt("id"),rs.getString("yearname"));
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					close();
				}
				return year;
			}
	
	//create
	public boolean createyear(Year year) {
		System.out.println(year);
		boolean created = false;
		connection = DBConnection.getConnection();
		
		try {
			//INSERT INTO `schoolmanagementsystemdb`.`year` (`yearname`) VALUES ('2022');

			pStmt = connection.prepareStatement("insert into year "
					+ "(yearname)"
					+ "value(?);");
			pStmt.setString(1, year.getYearname());
			
			created = pStmt.executeUpdate() >0 ? true : false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return created;		
	}
	
	//delete
	public boolean deleteyear(Integer id) {
		boolean deleted = false;
		
		connection = DBConnection.getConnection();
		
		try {
			stmt = connection.createStatement();			
			deleted = stmt.executeUpdate("delete from year where id ='"+id+"';") > 0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return deleted;
	}
	
	//update
	public boolean updateyear(Year year) {
		System.out.println(year);
		boolean updated = false;
		connection = DBConnection.getConnection();
		
		try {
			pStmt = connection.prepareStatement("update year set"
					+ "yearname =? where id=?;");			
			pStmt.setString(1, year.getYearname());
			pStmt.setInt(2, year.getId());
			updated = pStmt.executeUpdate() > 0? true:false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return updated;
	}
	
	
	public boolean deleteYearByName(String yearname) {
		boolean deleted = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from year where yearname='" + yearname + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return deleted;

	}
	


	
	
	
	
}
