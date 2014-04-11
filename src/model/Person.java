package model;

public class Person {
	
	private int id;
	private String username;
	/*type = {prof,etudiant}*/
	private String type;
	private String password;
	
	public Person() {
		
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [username=" + username + ", type=" + type
				+ ", password=" + password + "]";
	}
}
