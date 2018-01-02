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
			String sql = "create database IF NOT EXISTS lab";
			Statement statement = connection.createStatement();
			int set = statement.executeUpdate(sql);
			if (set == 1) {
				System.out.println("database created");
			} else {
				System.out.println("database already exits");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
