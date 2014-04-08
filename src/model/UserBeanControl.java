package model;

import controller.DatabaseInterface;

public class UserBeanControl {
	
	public String checkUser(String username,String password){
		if(DatabaseInterface.dbInterface.authenticateUser(username, password)){
			return "prof";
		}
		else
			return "etudiant";		
	}

}
