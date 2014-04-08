package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInterface {
	private String url;
	private String user;
	private String password;
	
	private Connection connection;
	
	private DBType dbType = DBType.MY_SQL;
	private boolean connected = false;
	protected static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/jeedb";
	
	public static DatabaseInterface dbInterface =
			new DatabaseInterface(DEFAULT_URL, "default", "password");
	
	protected enum DBType {
		MY_SQL,
		SQLITE
	}
	
	public DatabaseInterface(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	protected void connect() {
		if (!connected) {
			try {
				Class.forName( getDriverName() );
				connection = DriverManager.getConnection( url, user, password );
				connected = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void disconnect() {
		try {
			connection.close();
			connected  = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected String getDriverName() {
		switch (dbType) {
		case MY_SQL:
			return "com.mysql.jdbc.Driver";
		case SQLITE:
			return "org.sqlite.JDBC";
		}
		return null;
	}
	
	public List<String> getGroupList() {
		connect();
		List<String> list = new ArrayList<String>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT name FROM Group;";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				String name = result.getString("name");
				list.add(name);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean authenticateUser(String username, String userpassword) {
		connect();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT count(*) FROM User "
					+ "WHERE name = " + username + " AND password = " + userpassword;
			ResultSet result = statement.executeQuery(query);
			result.first();
			if (result.getInt(1) == 1) { //hackish crap
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
