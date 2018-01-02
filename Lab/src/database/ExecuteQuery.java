package database;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ExecuteQuery {

	DatabaseConnection dataBase = new DatabaseConnection();

	public void executeQuery(String query, String message) {
		Connection connection = dataBase.getConnection();
		try {
			Statement statement = connection.createStatement();
			int set = statement.executeUpdate(query);
			if (set == 1) {
				JOptionPane.showMessageDialog(null, "Data " + message + "Successfully");

			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message + "Successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
