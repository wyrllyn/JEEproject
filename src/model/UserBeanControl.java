package model;

import database.DatabaseInterface;

public class UserBeanControl {
	
	public String checkUser(String username,String password){
		if(DatabaseInterface.getInstance().authenticateUser(username, password)){
			return "prof";
		}
		else
			return "etudiant";		
	}

}
