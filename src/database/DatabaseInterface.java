package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Room;

public class DatabaseInterface {
	private String url;
	private String user;
	private String password;
	
	private Connection connection;
	
	private DBType dbType = DBType.SQLITE;
	private boolean connected = false;
	protected static final String DEFAULT_URL = "jdbc:sqlite:db/jee.db";
	protected static final String DEFAULT_USER = "default";
	protected static final String DEFAULT_PASSWORD = "password";
	//"jdbc:mysql://localhost:3306/jeedb";
	
	private static DatabaseInterface instance;
			;
	
	protected enum DBType {
		MY_SQL,
		SQLITE
	}
	
	/**
	 * 
	 * @param url
	 * @param user
	 * @param password
	 */
	public DatabaseInterface(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * Needs to be here for bean purposes.
	 */
	public DatabaseInterface() {
		this(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
	}
	
	/**
	 * Retrieves or creates the master DBI instance.
	 * @return 
	 */
	public static DatabaseInterface getInstance() {
		if (instance == null) {
			instance = new DatabaseInterface();
		}
		return instance;
	}
	
	/*
	 * Getters / setters
	 */
	
	public void setUrl(String url) {
		this.url = "jdbc:sqlite:" + url + "db/jee.db";
	}

	/*
	 * Connect / Disconnect / Driver
	 */
	
	/**
	 * Establishes a connection with the database if none exists.
	 */
	public void connect() {
		if (!connected) {
			try {
				Class.forName( getDriverName() );
				switch (dbType) {
				case MY_SQL:
					connection = DriverManager.getConnection( url, user, password );
					break;
				case SQLITE:
					connection = DriverManager.getConnection(url);
					break;
				}
				connected = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
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
	
	/*
	 * Requests
	 */
	
	/**
	 * 
	 * @return Names of the groups in table Group_
	 */
	public List<String> getGroupList() {
		connect();
		List<String> list = new ArrayList<String>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT name FROM Group_;";
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

	/**
	 * 
	 * @param username
	 * @param userpassword
	 * @return true if the user has been authenticated.
	 */
	public boolean authenticateUser(String username, String userpassword) {
		connect();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT count(*) FROM User "
					+ "WHERE name = \"" + username + "\" AND password = \"" + userpassword
					+ "\";";
			ResultSet result = statement.executeQuery(query);
			result.next();
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
	
	/**
	 * Retrieves the list of Room in table Room
	 * @return
	 */
	public List<Room> getRooms() {
		connect();
		List<Room> rooms = new ArrayList<Room>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Room;";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				int id = Integer.parseInt(result.getString("id"));
				Room room = new Room();
				room.setId(id);
				rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	
}
