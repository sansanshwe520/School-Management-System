package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimeDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private void close() {
		try {
			if(connection!=null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//read
		public ObservableList<Time> getAllTime(){
			ObservableList<Time> time = FXCollections.observableArrayList();
			connection = DBConnection.getConnection();
			
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from time;");
				while(rs.next()) {
					time.add(new Time(
							rs.getInt("id"),
							rs.getString("timename"),
							rs.getString("starttime"),
							rs.getString("endtime")
							));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return time;
		}
	
	//read
	public ObservableList<Time> getalltime(){
		ObservableList<Time> time = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from time;");
			while(rs.next()) {
				time.add(new Time(
						rs.getInt("id"),
						rs.getString("timename"),
						rs.getString("starttime"),
						rs.getString("endtime")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return time;
	}
	
	public ObservableList<Time> getallbytimename(String timename){
		ObservableList<Time> time=FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from time where timename ='"+timename+"';");
			while(rs.next()) {
				time.add(new Time(
						rs.getInt("id"),
						rs.getString("timename"),
						rs.getString("starttime"),
						rs.getString("endtime")
//						rs.getString("starthour"),
//						rs.getString("startminute"),
//						rs.getString("endhour"),
//						rs.getString("endminute")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return time;		
	}
	
	//create
	public boolean createtime(Time time) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into time"
					+ "(timename,starttime,endtime)"
					+ "value(?,?,?) ;"					
					);
			pStmt.setString(1, time.getTimename());
			pStmt.setString(2, time.getStarttime());
			pStmt.setString(3, time.getEndtime());
			
			created = pStmt.executeUpdate() > 0 ? true : false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return created;
	}
	
	//update
	public boolean updatetime(Time time) {
		boolean updated = false;
		connection = DBConnection.getConnection();
		
		try {
			pStmt = connection.prepareStatement("update time set"
					+ "timename=?"
					+ "starttime=?"
					+ "endtime=? where id=? ;"					
					);
			pStmt.setString(1, time.getTimename());
			pStmt.setString(2, time.getStarttime());
			pStmt.setString(3, time.getEndtime());
			pStmt.setInt(4, time.getId());
			
			updated = pStmt.executeUpdate() > 0 ? true:false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return updated;
	}
	
	//delete
	public boolean deletetime(Integer id) {
		boolean deleted = false;
		connection = DBConnection.getConnection();
		
		try {
			stmt=connection.createStatement();
			deleted = stmt.executeUpdate("delete from time where id='"+id+"';") > 0 ? true:false ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return deleted;
	}
	
	//delete
	public boolean deletetimename(String timename) {
		boolean deleted = false;
		connection = DBConnection.getConnection();
		
		try {
			stmt=connection.createStatement();
			deleted = stmt.executeUpdate("delete from time where timename='"+timename+"';") > 0 ? true:false ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return deleted;
	}
	
	public Time getTimeById(Long time_id) {
		Time time = null;
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from time where id='"+time_id+"';");
			if (rs.next()) {
				time = new Time(
						rs.getInt("id"),
						rs.getString("timename"),
						rs.getString("starttime"),
						rs.getString("endtime")
						
						);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return time;
	}
	
	public Time getTimebyName(String timename){
		Time time = null;
		connection = DBConnection.getConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from time where timename ='"+timename+"';");
			if(rs.next()) {
				time = new Time(
						rs.getInt("id"),
						rs.getString("timename"),
						rs.getString("starttime"),
						rs.getString("endtime")
//						rs.getString("starthour"),
//						rs.getString("startminute"),
//						rs.getString("endhour"),
//						rs.getString("endminute")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return time;		
	}
	
}
