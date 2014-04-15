package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.DateUtil;
import model.DateUsed;
import model.Person;
import model.Room;
import model.Slot;

public class DatabaseInterface {
	private String url;
	private String user;
	private String password;
	
	private Connection connection;
	
	private DBType dbType = DBType.SQLITE;
	private boolean connected = false;
	protected static final String DEFAULT_URL = "jdbc:sqlite:/home/etudiant/db/jee.db";
	protected static final String DEFAULT_USER = "default";
	protected static final String DEFAULT_PASSWORD = "password";
	//"jdbc:mysql://localhost:3306/jeedb";
	
	private static DatabaseInterface instance;
	
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
	 * @return DatabaseInterface
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
		this.url = url;
	}
	
	public Connection getConnection() {
		return connection;
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
	
	public List<Slot> getSlots() {
		List<Slot> slots = new ArrayList<Slot>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Slot;";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Slot slot = new Slot();
				int id = Integer.parseInt(result.getString("id"));
				slot.setId(id);
				
				Date date = new Date(result.getLong("beginning"));
				DateUsed beginning = DateUtil.createDateUsed(date);
				slot.setBeginning(beginning);
				
				slot.setDuration(result.getInt("duration"));
				slot.setName(result.getString("name"));
				slot.setType(result.getString("class_type"));
				
				slots.add(slot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slots;
	}
	
	/**
	 * Retrieves the list of users that are teachers.
	 * @return A List of User.
	 */
	public List<Person> getTeachers() {
		List<Person> teachers = new ArrayList<Person>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT User.id, name FROM Teacher, User"
					+ " WHERE User.id = user_id;";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				Person teacher = new Person();
				int id = result.getInt("id");
				teacher.setId(id);
				
				teacher.setName(result.getString("name"));
				teacher.setPassword("");
				teacher.setType("prof");
				
				teachers.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teachers;
	}

}
