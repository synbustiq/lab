package other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.TableModel;

import database.DatabaseConnection;
import net.proteanit.sql.DbUtils;

public class Table {

	DatabaseConnection dataBase = new DatabaseConnection();

	public TableModel table(String name, String tableName) {
		System.out.println("i have reached here table 1");

		Connection connection = dataBase.getConnection();
		PreparedStatement statement;
		ResultSet set;
		try {
			String sql = "select " + name + " from " + tableName + "";
			statement = connection.prepareStatement(sql);
			set = statement.executeQuery();
			return DbUtils.resultSetToTableModel(set);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
