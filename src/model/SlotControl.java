package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.jsp.tagext.TryCatchFinally;

import controller.DatabaseInterface;

public class SlotControl {
	//prepare statement
	private Statement sm=null;
	private ResultSet rs=null;
	private Connection ct=null;
	private DatabaseInterface di=null;
	/**
	 * @param name:String
	 * @param id:int
	 * @param beginning:Date
	 * @param duration:int
	 * @param type:String
	 * @return boolean:succes=>true;fail=>false
	 */
	
	public boolean delSlotById(int id) {
		boolean b= false;
		try {
			di =new DatabaseInterface("jdbc:sqlite:jee.db","manager","manager");
			di.connect();
			ct=di.getConnection();
			sm=ct.createStatement();
			
			int a=sm.executeUpdate("delete from Slot where id='"+id+"'");
			
			if(a==1){				
				b=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			di.disconnect();
		}
		return b;
	}

	public boolean addSlot(String name, Date beginning, int duration, Person teacher,
			String type) {
		boolean b= false;
		try {
			di =new DatabaseInterface("jdbc:sqlite:jee.db","manager","manager");
			di.connect();
			ct=di.getConnection();
			sm=ct.createStatement();
			
			
			int a=sm.executeUpdate("insert into Slot values('"+name+"','"+beginning+"','"+duration+"','"+teacher+"','"+type+"')");
			
			if(a==1){				
				b=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			di.disconnect();
		}
		return b;
	}

	public boolean modifierSlot(int id, String name, Date beginning, int duration, Person teacher,
			String type) {
		boolean b= false;
		try {
			di =new DatabaseInterface("jdbc:sqlite:jee.db","manager","manager");
			di.connect();
			ct=di.getConnection();
			sm=ct.createStatement();
			
			int a=sm.executeUpdate("update Slot set name='"+name+"',beginning='"+beginning+"',duration='"+duration+"',teacher='"+teacher+"',type='"+type+"' where id='"+id+"'");
			
			if(a==1){				
				b=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			di.disconnect();
		}
		return b;
	}
	
}
