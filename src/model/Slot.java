package model;

import java.util.Date;

public class Slot {
	private int id;
	private String name;
	private DateUsed beginning;
	private int duration;
	private Person teacher;
	private String type; //CM, TD etc.
	
	public Slot() {
		this.name = "";
		this.setBeginning(null);
		this.setDuration(0);
		this.setTeacher(new Person());
		this.getTeacher().setName("");
		this.setType("");
	}
	


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public DateUsed getBeginning() {
		return beginning;
	}
	public void setBeginning(DateUsed beginning) {
		this.beginning = beginning;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Person getTeacher() {
		return teacher;
	}


	public void setTeacher(Person teacher) {
		this.teacher = teacher;
	}
	
/*	public Date getEndDate() {
		Date end = new Date(beginning.getTime() + (duration * 60000));
		return end;
	}*/
}
