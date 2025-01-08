package dao;

import java.sql.SQLException;
import java.util.List;

import model.Employee;

public interface EmployeeDAO {
	
	 boolean addEmployee(Employee employee) throws SQLException;
	 Employee getEmployeeById(int id) throws SQLException;
	 List<Employee> getAllEmployees() throws SQLException;
	 boolean updateEmployee(Employee employee) throws SQLException;
	 boolean deleteEmployee(int id) throws SQLException;
	 List<Employee> getEmployeesByDepartmentId(int departmentId);
	 List<Employee> getEmployeesByJoinDate(String joinDate);
	 List<Employee> getEmployeesBySalaryRange(double minSalary, double maxSalary);
	

}
