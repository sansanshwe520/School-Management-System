package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MarksDAO {
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	private final YearDAO yearDAO = new YearDAO();
	private final ExamTypeDAO examtypeDAO = new ExamTypeDAO();
	private final CourseDAO courseDAO = new CourseDAO();
	private final BatchDAO batchDAO = new BatchDAO();
	private final StudentDAO studentDAO = new StudentDAO();

	private void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<Marks> getAllMarksByYear(Long year_id, Long examtype_id, Integer course_id,
			Long batch_m_id,Long student_m_id) {
		ObservableList<Marks> marks = FXCollections.observableArrayList();
		Year year = yearDAO.getYearById(year_id);
		ExamType examtype = examtypeDAO.getExamTypeById(examtype_id);
		Course course = courseDAO.getCourseById(course_id);
		Batch batch = batchDAO.getBatchById(batch_m_id);
		Student student = studentDAO.getStudentByID(student_m_id);
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from marks where year_id='" + year_id + "' and examtype_id='" + examtype_id
					+ "' and course_id='" + course_id + "' and batch_m_id='" + batch_m_id + "' and student_m_id='" + student_m_id + "';");
			while (rs.next()) {
				marks.add(new Marks(
						rs.getInt("id"), 
						rs.getInt("marks"), 
						year, 
						examtype,
						course, 
						batch,
						student
						
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}

	// view
	public Marks getMarksByID(Integer id) {
		Marks marks = null;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from marks where id='" + id + "';");

			while (rs.next()) {
				Year year = yearDAO.getYearById(rs.getLong("year_id"));
				ExamType examtype = examtypeDAO.getExamTypeById(rs.getLong("examtype_id"));
				Course course = courseDAO.getCourseById(rs.getInt("course_id"));
				Batch batch = batchDAO.getBatchById(rs.getLong("batch_m_id"));
				Student student = studentDAO.getStudentByID(rs.getLong("student_m_id"));
				marks = new Marks(
						rs.getInt("id"), 
						
						rs.getInt("marks"), 
						year, 
						examtype,
						course, 
						batch,
						student
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return marks;
	}

	public boolean createMarks(Marks marks) {
		boolean created = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("insert into marks "
					+ "(marks,year_id,examtype_id,course_id,batch_m_id,student_m_id) " + "values(?,?,?,?,?,?);");

			
			pStmt.setInt(1, marks.getMarks());
			pStmt.setLong(2, marks.getYear().getId());
			pStmt.setLong(3, marks.getExamtype().getId());
			pStmt.setInt(4, marks.getCourse().getId());
			pStmt.setLong(5, marks.getBatch().getId());
			pStmt.setLong(6, marks.getStudent().getId());
			created = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return created;
	}

	public boolean updateMarks(Marks marks) {
		boolean updated = false;
		connection = DBConnection.getConnection();
		try {
			pStmt = connection.prepareStatement("update marks set marks=?, year_id=?,examtype_id=?,course=?,batch_m_id=?,student_m_id=? where id=?;");

			pStmt.setInt(1, marks.getMarks());
			pStmt.setLong(2, marks.getYear().getId());
			pStmt.setLong(3, marks.getExamtype().getId());
			pStmt.setInt(4, marks.getCourse().getId());
			pStmt.setLong(5, marks.getBatch().getId());
			pStmt.setLong(6, marks.getStudent().getId());
			pStmt.setInt(7, marks.getId());

			updated = pStmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}

	public boolean deleteMarks(Integer marks) {
		boolean deleted = false;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			deleted = stmt.executeUpdate("delete from marks where marks='" + marks + "';") > 0 ? true
					: false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return deleted;
	}

	// view name
//	public ObservableList<Marks> getAllMarksByName(String studentname) {
//		ObservableList<Marks> marks = FXCollections.observableArrayList();
//		connection = DBConnection.getConnection();
//		try {
//			stmt = connection.createStatement();
//			rs = stmt.executeQuery("select * from marks where studentname='" + studentname + "';");
//			while (rs.next()) {
//				Year year = yearDAO.getYearById(rs.getInt("year_id"));
//				ExamType examtype = examtypeDAO.getExamTypeById(rs.getInt("examtype_id"));
//				Course course = courseDAO.getCourseById(rs.getInt("course_id"));
//				Batch batch = batchDAO.getBatchById(rs.getInt("batch_m_id"));
////			Batch batch=batchDAO.getBatchById(rs.getLong("batch_id"));
////			Academic academic = academicDAO.getAcademicById(rs.getLong("aca_id"));
//				marks.add(new Marks(rs.getInt("id"), rs.getString("studentname"), rs.getInt("marks"), year, examtype,
//						course, batch));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return marks;
//	}

	public ObservableList<Marks> getAllMarks() {
		ObservableList<Marks> marks = FXCollections.observableArrayList();
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from marks;");
			while (rs.next()) {
				Year year = yearDAO.getYearById(rs.getLong("year_id"));
				ExamType examtype = examtypeDAO.getExamTypeById(rs.getLong("examtype_id"));
				Course course = courseDAO.getCourseById(rs.getInt("course_id"));
				Batch batch = batchDAO.getBatchById(rs.getLong("batch_m_id"));
				Student student = studentDAO.getStudentByID(rs.getLong("student_m_id"));
				marks.add(new Marks(
						rs.getInt("id"), 
				
						rs.getInt("marks"), 
						year, 
						examtype,
						course, 
						batch,
						student
				));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return marks;
	}
	
	
	public Marks getAllMarksByALL(Marks sellectedmarks) {
		Marks mark = null;
		connection = DBConnection.getConnection();
		try {
			stmt = connection.createStatement();
//			 rs = stmt.executeQuery("select * from marks where marks='" + sellectedmarks.getMarks()
//				      + "'and year_id='" + sellectedmarks.getYear().getId() + "'and examtype_id='"+sellectedmarks.getExamtype().getId()
//				      +"'and course_id='"+sellectedmarks.getCourse().getId()+"'and batch_m_id='"+sellectedmarks.getBatch().getId()+"'and student_m_id='"+sellectedmarks.getStudent().getId()+"';" );
			rs = stmt.executeQuery("SELECT * FROM marks WHERE marks='" + sellectedmarks.getMarks()
		    + "' AND year_id='" + sellectedmarks.getYear().getId() + "' AND examtype_id='" + sellectedmarks.getExamtype().getId()
		    + "' AND course_id='" + sellectedmarks.getCourse().getId() + "' AND batch_m_id='" + sellectedmarks.getBatch().getId()
		    + "' AND student_m_id='" + sellectedmarks.getStudent().getId() + "';");

			
			if (rs.next()) {
				Year year = yearDAO.getYearById(rs.getLong("year_id"));
				ExamType examtype = examtypeDAO.getExamTypeById(rs.getLong("examtype_id"));
				Course course = courseDAO.getCourseById(rs.getInt("course_id"));
				Batch batch = batchDAO.getBatchById(rs.getLong("batch_m_id"));
				Student student = studentDAO.getStudentByID(rs.getLong("student_m_id"));
				mark = new Marks(rs.getInt("id"),year,examtype,course,batch,student);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return mark;
	}
}
