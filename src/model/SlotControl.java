package model;

import java.sql.Connection;
import java.sql.Statement;

import controller.database.DatabaseInterface;

public class SlotControl {
	//prepare statement
	private Statement sm=null;
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
			di = DatabaseInterface.getInstance();
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

	public boolean addSlot(String name, DateUsed beginning, int duration, Person teacher,
			String type) {
		boolean b= false;
		try {
			di = DatabaseInterface.getInstance();
			di.connect();
			ct=di.getConnection();
			sm=ct.createStatement();			
			
			int a=sm.executeUpdate("insert into Slot (name, beginning, duration, teacher_id, class_type)"
					+ "values('"+name+"','\""+beginning+"\"','"+duration+"','\""+teacher.getId()+"\"','"+type+"')");
			
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

	public boolean modifierSlot(int id, String name, DateUsed beginning,
			int duration, Person teacher,String type) {
		boolean b= false;
		//il n'y pas de prof du nom ce que vous avez donné
		if(teacher==null)return false;
		else{
				
			try {
				di = DatabaseInterface.getInstance();
				int teacher_id = di.getTeacherId(id);
				di.disconnect();
				di.connect();
				ct=di.getConnection();
				sm=ct.createStatement();
				beginning.synchronizeDate();
				long time = beginning.getDate().getTime();
				
				String query = "update Slot set "
						+ "name = '" + name + "', "
						+ "beginning = " + time + ", "
						+ "duration = " + duration + ", "
						+ "teacher_id = " + teacher_id + ", "
						+ "class_type = '" + type + "' "
						+ " where id = " + id + ";";
				System.out.println(query);
				int a = sm.executeUpdate(query);
				
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
}
