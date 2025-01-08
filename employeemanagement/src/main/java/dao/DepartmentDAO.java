package dao;

import java.sql.SQLException;
import java.util.List;

import model.Department;

public interface DepartmentDAO {
	
	boolean addDepartment(Department department) throws SQLException;
	Department getDepartmentById(int deptId) throws SQLException;
	 List<Department> getAllDepartments()throws SQLException;
	 boolean updateDepartment(Department department)throws SQLException;
	 boolean deleteDepartment(int id)throws SQLException ;
	 Department getDepartmentByName(String deptName);
}
