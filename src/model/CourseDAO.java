package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDAO {
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private void close() {
		try {
			if(connection != null) {
			connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//read - r
	public ObservableList<Course> getAllCourse(){
		ObservableList<Course> courses = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from course;");
			while (rs.next()) {
				courses.add(new Course(
						rs.getInt("id"),
						rs.getString("coursename"),
						rs.getString("description")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courses;  
	}
	

	public Course getCourseById(Integer course_id) {
		Course course = null;
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from course where id='"+course_id+"';");
			if (rs.next()) {
				course=new Course(rs.getInt("id"),rs.getString("coursename"),rs.getString("description"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return course;
	}
	public Course getCourseByCourseName(String coursename){
		Course course = null;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from course where coursename='"+coursename+"';");
			if (rs.next()) {
				course=new Course(
						rs.getInt("id"),
						rs.getString("coursename"),
						rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return course;  
	}
	
	
	public boolean addCourse(Course course) {
		boolean added = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into course "
					+ "(coursename,description) "
					+ "values(?,?);");
			pStmt.setString(1, course.getCoursename());
			pStmt.setString(2, course.getDescription());
			
			added = pStmt.executeUpdate() > 0 ? true : false ;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return added;		
	}
	
		
	//delete	
	public boolean deleteCourse(Integer id) {
		boolean deleted = false;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from course where id="+id+";") > 0 ? true : false ;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return deleted;		
	}
	
	
	
	//update	
		public boolean updateCourse(Course course) {
			boolean updated = false;
			System.out.println("course id in dao"+course);
			connection = DBConnection.getConnection();
			try {
				
				pStmt = connection.prepareStatement("update course set "
								+"coursename=?,"
								+"description=? where id=?;"									
										);
				
				pStmt.setString(1, course.getCoursename());
				pStmt.setString(2, course.getDescription());
				pStmt.setInt(3, course.getId());
				
				updated = pStmt.executeUpdate()>0?true:false;
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return updated;		
		}

	public boolean deleteCourseByName(String coursename) {
		boolean deleted = false;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from course where coursename='"+coursename+"';") > 0 ? true : false ;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return deleted;
	}
	
	// get course by coursename and description
		public Course getCourseByCoursenameAndDescription(Course course) {
			Course cou = null;
			connection = DBConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from course where coursename='" + course.getCoursename()
						+ "' and description='" + course.getDescription() + "';");
				if (rs.next()) {
					cou = (new Course(rs.getInt("id"), rs.getString("coursename"), rs.getString("description")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return cou;
		}

	public ObservableList<Course> getAllCourse(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	
