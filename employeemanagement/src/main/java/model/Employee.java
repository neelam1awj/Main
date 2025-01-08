package model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class Employee {
	private int id; // Primary key
	private int departmentId; // Foreign key to the department table (nullable)
	private BigDecimal salary; // Employee's salary
	private Date joinDate; // Date of joining (non-null)
	private String name; // Employee's name
	

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, int departmentId, BigDecimal salary, Date joinDate, String name) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.salary = salary;
		this.joinDate = joinDate;
		this.name = name;
	}
	@Override
	public String toString() {
		return "\nEmployee\n------------------------>\nEmployee Id=" + id + "\ndepartment Id=" + departmentId + "\nsalary=" + salary + "\njoinDate=" + joinDate
				+ "\nname=" + name + "\n";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
