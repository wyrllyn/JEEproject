package model;

import controller.database.DatabaseInterface;

public class UserBeanControl {
	
	public String checkUser(String username,String password){
		int type = DatabaseInterface.getInstance().authenticateUser(username, password);
		//type = 0 => prof;
		//	   = 1 => etudiante;
		//     = 2 => illegal login
		if(type==0){
			return "prof";
		}
		else if(type==1){
			return "etudiant";	
		}
		else {
			return "illegal";
		}
	}
	
	public Person checkTeacher(String teachername){
		Person teacher = DatabaseInterface.getInstance().getTeacherByName(teachername);
		if(teacher!=null){
			return teacher;
		}
		else
			return null;		
	}
	

}
