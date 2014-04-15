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
	
	public Person checkTeacher(String teachername){
		Person teacher = DatabaseInterface.getTeacherByName(teachername);
		if(teacher!=null){
			return teacher;
		}
		else
			return null;		
	}
	

}
