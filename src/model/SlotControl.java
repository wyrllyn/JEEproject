package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.jsp.tagext.TryCatchFinally;

public class SlotControl {
	//prepare statement
	private Statement sm=null;
	private ResultSet rs=null;
	private Connection ct=null;
	
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
			//�õ�����
		/*	ct=new ConnDB().getConn();
			sm=ct.createStatement();
			//ִ��
			int a=sm.executeUpdate("delete from Slot where id='"+id+"'");
			
			if(a==1){
				
				b=true;
			} */
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		//	this.close();
		} 
		return b;
	}

	public boolean addSlot(String name, Date beginning, int duration,
			String type) {
		boolean b= false;
		return b;
	}

	public boolean modifierSlot(String name, Date beginning, int duration,
			String type) {
		
		return false;
	}
	
}
