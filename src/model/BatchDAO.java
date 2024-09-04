package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BatchDAO {
	
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
	
	public ObservableList<Batch> getAllBatchs(){
		ObservableList<Batch> batch = FXCollections.observableArrayList();
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from batch;");
			while (rs.next()) {
				batch.add(new Batch(
						rs.getInt("id"),
						rs.getString("batchname")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return batch;
	}
	
	
	public Batch getBatchById(Long batch_id) {
		Batch batch = null;
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from batch where id='"+batch_id+"';");
			if (rs.next()) {
				batch=new Batch(rs.getInt("id"),rs.getString("batchname"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return batch;
	}
	
	
	public Batch getBatchByBatchName(String batchName) {
	    Batch batch = null;
			
			connection = DBConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from batch where batchname='"+batchName+"';");
				if (rs.next()) {
					batch=new Batch(rs.getInt("id"),rs.getString("batchname"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return batch;
		}
	
	public boolean createBatch(Batch batch) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into batch "
					+ "(batchname) " + "values(?);");
			
			pStmt.setString(1, batch.getBatchname());
			

			created = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;

	}
	
	public boolean deleteBatch(Integer id) {
		boolean created = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			created = stmt.executeUpdate("delete from batch where id='" + id + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return created;

	}
	
	public boolean deleteBatchByName(String batchname) {
		boolean deleted = false;

		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from batch where batchname='" + batchname + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return deleted;

	}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
