package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public Connection getConnection() {
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab", "root", "");
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
