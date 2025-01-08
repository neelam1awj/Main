package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utitliy.ConnectionProvider;

import dao.DepartmentDAO;
import model.Department;

public class DepartmentDAOImpl implements DepartmentDAO {

	private Connection connection = ConnectionProvider.getConnection();
	PreparedStatement pstmt = null;

	@Override
	public boolean addDepartment(Department department) throws SQLException {

		pstmt = connection.prepareStatement("INSERT INTO departments (dept_id,dept_name) VALUES (?,?)");
		pstmt.setInt(1, department.getDept_id());
		pstmt.setString(2, department.getDept_name()); // department_id
		int i = pstmt.executeUpdate();
		return i > 0;

	}

	@Override
	public Department getDepartmentById(int id) throws SQLException {
		pstmt = connection.prepareStatement("select * from departments where dept_id=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Department department = new Department();
			department.setDept_id(rs.getInt(1));
			department.setDept_name(rs.getString(2));
			return department;
		}

		return null;
	}

	@Override
	public List<Department> getAllDepartments() throws SQLException {
		pstmt = connection.prepareStatement("select * from departments ");
		ResultSet rs = pstmt.executeQuery();
		List<Department> deptList = new ArrayList<Department>();

		while (rs.next()) {
			Department department = new Department();
			department.setDept_id(rs.getInt(1));
			department.setDept_name(rs.getString(2));
			deptList.add(department);
		}

		if (!deptList.isEmpty())
			return deptList;

		return null;
	}

	@Override
	public boolean updateDepartment(Department department) throws SQLException {
		pstmt = connection.prepareStatement("update departments set dept_name=? where dept_id=? ");
		pstmt.setString(1, department.getDept_name());
		pstmt.setInt(2, department.getDept_id());
		int i = pstmt.executeUpdate();
		if (i > 0)
			return true;
		return false;

	}

	@Override
	public boolean deleteDepartment(int id)throws SQLException  {
		pstmt=connection.prepareStatement("delete from departments where dept_id=? ");
		pstmt.setInt(1, id);
		int i=pstmt.executeUpdate();
		if(i>0)
			return true;
		return false;

	}

	@Override
	public Department getDepartmentByName(String deptName) {
		 Department department = null;
		    try {
		        pstmt = connection.prepareStatement("SELECT * FROM departments where dept_name = ?");
		        pstmt.setString(1, deptName);
		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            department = new Department();
		            department.setDept_id(rs.getInt("dept_id"));
		            department.setDept_name(rs.getString("dept_name"));
		        }
		    } catch (SQLException e) {
		        System.err.println("Error retrieving department by name: " + e.getMessage());
		    }
		    return department;
	}

}
