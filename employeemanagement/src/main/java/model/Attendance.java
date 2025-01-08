package model;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Attendance {
    private int attendance_id ;
    private int employee_id   ;
    private Date date;
    private String status;
    
   
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attendance(int attendance_id, int employee_id, Date date, String status) {
		super();
		this.attendance_id = attendance_id;
		this.employee_id = employee_id;
		this.date = date;
		this.status = status;
	}
	@Override
	public String toString() {
		return "\nAttendance\n------------------------>\nAttendance Id=" + attendance_id + "\nEmployee Id=" + employee_id + "\nDate=" + date
				+ "\nstatus=" + status + "\n";
	}
	public int getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(int attendance_id) {
		this.attendance_id = attendance_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
