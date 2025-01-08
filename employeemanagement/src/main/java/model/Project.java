package model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Project {
       private int project_id;
       private String project_name;
       private Date start_date;
       private Date end_date;
       private int manager_id;
       
       
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Project(int project_id, String project_name, Date start_date, Date end_date, int manager_id) {
		super();
		this.project_id = project_id;
		this.project_name = project_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "\nProject\n------------------------>\nproject_id=" + project_id + "\nproject_name=" + project_name + "\nstart_date=" + start_date
				+ "\nend_date=" + end_date + "\nmanager_id=" + manager_id + "\n";
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
          
}
