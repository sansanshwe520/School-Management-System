package database;

import java.sql.Connection;

public class testDB {

	public static void main(String[] args) {
		Connection connection = DBConnection.getConnection();
		if(connection != null) {
			System.out.println("Successfully Connected to DB.");
			}else {
				System.out.println("Fail to connect to DB");
			}
	}
}
