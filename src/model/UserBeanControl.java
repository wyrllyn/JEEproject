package model;

public class UserBeanControl {
	
	public String checkUser(String username,String password){
		if(username.equals("goeffon")){
			return "prof";
		}
		else
			return "etudiant";		
	}

}
