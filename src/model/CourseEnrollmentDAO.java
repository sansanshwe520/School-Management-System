package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseEnrollmentDAO {
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	private BatchDAO batchDAO = new BatchDAO();
	private TeacherDAO teacherDAO = new TeacherDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private AcademicDAO academicDAO = new AcademicDAO();
	private YearDAO yearDAO = new YearDAO();

	private void close() {
		try {
			if (connection == null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// read
	public ObservableList<CourseEnrollment> getAllEnrollment() {
		ObservableList<CourseEnrollment> enrollment = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from courseenrollment;");
			while (rs.next()) {
				Batch batch = batchDAO.getBatchById(rs.getLong("batch_c"));
				Academic academic = academicDAO.getAcademicById(rs.getLong("academicyear"));
				Teacher teacher = teacherDAO.getTeacherByID(rs.getLong("teacher"));
				Course course = courseDAO.getCourseById(rs.getInt("course"));
				Year year = yearDAO.getYearById(rs.getLong("year"));
				enrollment.add(new CourseEnrollment(rs.getInt("id"), teacher, course, batch, academic, year));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return enrollment;
	}

	// add
	public boolean addCourseEnrollment(CourseEnrollment enrollment) {
		boolean added = false;
		connection = DBConnection.getConnection();
		System.out.println("in the dao," + enrollment);
		try {
			pStmt = connection.prepareStatement(
					"insert into courseenrollment" + "(teacher,course,batch_c,academicyear,year)" + "values(?,?,?,?,?)");
//			pStmt.setInt(1, enrollment.getId());
			pStmt.setLong(1, enrollment.getTeacher().getId());
			pStmt.setInt(2, enrollment.getCourse().getId());
			pStmt.setInt(3, enrollment.getBatch().getId());
			pStmt.setInt(4, enrollment.getAcademicyear().getId());
			pStmt.setInt(5, enrollment.getYear().getId());
			added = pStmt.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return added;
	}

	// update
	public boolean updateCourseEnrollment(CourseEnrollment enrollment) {
		boolean updated = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("update courseenrollment set course=?, teacher=?,batch_c=?,academicyear=?,year=? where id=?;");
			pStmt.setInt(1, enrollment.getCourse().getId());
			pStmt.setLong(2, enrollment.getTeacher().getId());
			pStmt.setInt(3, enrollment.getBatch().getId());
			pStmt.setInt(4, enrollment.getAcademicyear().getId());
			pStmt.setInt(5, enrollment.getYear().getId());
			pStmt.setInt(6, enrollment.getId());

			updated = pStmt.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return updated;
	}

	// delete
	public boolean deleteCourseEnrollment(Integer id) {
		boolean deleted = false;
		connection = DBConnection.getConnection();

		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from courseenrollment where  id='" + id + "';") > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return deleted;
	}

	public CourseEnrollment getEntrollbyTeacherCourseBatchAcademicYear(CourseEnrollment selectedenrollment) {
	    // TODO Auto-generated method stub
	    CourseEnrollment enrollment = null;
	    connection = DBConnection.getConnection();
	    try {
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery("select * from courseenrollment where course='" + selectedenrollment.getCourse().getId()
	      + "'and teacher='" + selectedenrollment.getTeacher().getId() + "'and batch_c='"+selectedenrollment.getBatch().getId()
	      +"'and academicyear='"+selectedenrollment.getAcademicyear().getId()+"'and year='"+selectedenrollment.getYear().getId()+"';" );
	      System.out.println(rs+"rs");
	      if (rs.next()) {
	        Batch batch = batchDAO.getBatchById(rs.getLong("batch_c"));
	        Academic academic = academicDAO.getAcademicById(rs.getLong("academicyear"));
	        Teacher teacher = teacherDAO.getTeacherByID(rs.getLong("teacher"));
	        Course course = courseDAO.getCourseById(rs.getInt("course"));
	        Year year = yearDAO.getYearById(rs.getLong("year"));
	        enrollment=new CourseEnrollment(rs.getInt("id"), teacher, course, batch, academic, year);
	      }
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } finally {
	      close();
	    }
	    return enrollment;
	  
	  }
		
	
}
