package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	public void database() {
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			String databaseName = "lab";
			ResultSet set = connection.getMetaData().getCatalogs();
			while (set.next()) {
				String catlog = set.getString(1);
				if (databaseName.equals(catlog)) {
					System.out.println("database already created");
				} else {
					String sql = "create database lab";
					Statement statement = connection.createStatement();
					int set1 = statement.executeUpdate(sql);
					if (set1 == 1) {
						System.out.println("Database created successfully");
					} else {
						System.out.println("Database not created successfully");

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
