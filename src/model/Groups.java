package model;

import java.util.ArrayList;
import java.util.List;

public class Groups {
	private List<String> groups;
	
	public Groups() {
		groups = new ArrayList<String>();
		groups.add("TEST");
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
}
