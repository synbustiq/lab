package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
	public void database() {
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			String sql = "create database lab";
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("database created");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
