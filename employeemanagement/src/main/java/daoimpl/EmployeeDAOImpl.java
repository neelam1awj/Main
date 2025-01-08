package daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDAO;
import model.Employee;
import utitliy.ConnectionProvider;

public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection connection = ConnectionProvider.getConnection();
	PreparedStatement pstmt = null;

	@Override
	public boolean addEmployee(Employee employee) throws SQLException {
	    pstmt = connection.prepareStatement("INSERT INTO employee (id,department_id, salary, join_date, name) VALUES (?,?, ?, ?, ?)");
	// System.out.println("Parameters: " + employee.getDepartmentId() + ", " + employee.getSalary() + ", " + employee.getJoinDate() + ", " + employee.getName());
        pstmt.setInt(1, employee.getId()); 
        pstmt.setInt(2, employee.getDepartmentId()); // department_id
	    pstmt.setBigDecimal(3, employee.getSalary());
	    pstmt.setDate(4, new java.sql.Date(employee.getJoinDate().getTime()));
	    pstmt.setString(5, employee.getName());
	  //  System.out.println("Executing Query: INSERT INTO employee (department_id, salary, join_date, name) VALUES (?, ?, ?, ?)");
	    int i = pstmt.executeUpdate();
	    System.out.println(i);
	    return i > 0;
	}

	@Override
	public Employee getEmployeeById(int id) throws SQLException{
		pstmt = connection.prepareStatement("select * from employee where id=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Employee employee = new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));
			employee.setDepartmentId(rs.getInt(3));
			employee.setSalary(rs.getBigDecimal(4));
			employee.setJoinDate(rs.getDate(5));
			
			return employee;
		}

		return null;
	}

	@Override
	public List<Employee> getAllEmployees()throws SQLException  {
		pstmt=connection.prepareStatement("select * from employee ");
		ResultSet rs=pstmt.executeQuery();
		List<Employee> empList=new ArrayList<Employee>();
		
		while(rs.next())
		{
		   Employee employee=new Employee();
		   employee.setId(rs.getInt(1));
		   employee.setName(rs.getString(2));
		   employee.setDepartmentId(rs.getInt(3));
		   employee.setSalary(rs.getBigDecimal(4));
		   employee.setJoinDate(rs.getDate(5));
		  
		   empList.add(employee);
		}
		
		if(!empList.isEmpty())
		    return empList;
		
		return null;
	}

	@Override
	public boolean updateEmployee(Employee employee) throws SQLException{
		pstmt=connection.prepareStatement("update employee set name=?,department_id=? , salary=? , join_date=? where id=? ");
		
//		System.out.println("Parameters: " + employee.getDepartmentId() + ", " + employee.getSalary() + ", " + employee.getJoinDate() + ", " + employee.getName());
//		System.out.println("Executing Query: UPDATE employee SET name='" 
//			    + employee.getName() + "', department_id=" + employee.getDepartmentId() 
//			    + ", salary=" + employee.getSalary() + ", join_date='" + employee.getJoinDate() 
//			    + "' WHERE id=" + employee.getId());
//		
	    pstmt.setString(1, employee.getName());
		pstmt.setInt(2,employee.getDepartmentId());
		pstmt.setBigDecimal(3, employee.getSalary());
		pstmt.setDate(4, employee.getJoinDate());
		pstmt.setInt(5, employee.getId());
		int i=pstmt.executeUpdate();
		if(i>0)
			return true;
		return false;

	}

	@Override
	public boolean deleteEmployee(int id)throws SQLException  {
		pstmt=connection.prepareStatement("delete from employee where id=? ");
		pstmt.setInt(1, id);
		int i=pstmt.executeUpdate();
		if(i>0)
			return true;
		return false;
	}


	@Override
	public List<Employee> getEmployeesByDepartmentId(int departmentId) {
		 List<Employee> empList = new ArrayList<>();
		    try {
		        pstmt = connection.prepareStatement("SELECT * FROM employee WHERE department_id=?");
		        pstmt.setInt(1, departmentId);
		        ResultSet rs = pstmt.executeQuery();
		        while (rs.next()) {
		            Employee employee = new Employee();
		            employee.setId(rs.getInt("id"));
		            employee.setName(rs.getString("name"));
		            employee.setDepartmentId(rs.getInt("department_id"));
		            employee.setSalary(rs.getBigDecimal("salary"));
		            employee.setJoinDate(rs.getDate("join_date"));
		            empList.add(employee);
		        }
		    } catch (SQLException e) {
		        System.err.println("Error retrieving employees by department id: " + e.getMessage());
		       
		    } 
		    return empList.isEmpty() ? null : empList;
	}

	@Override
	public List<Employee> getEmployeesByJoinDate(String joinDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
		  List<Employee> empList = new ArrayList<>();
		    try {
		        pstmt = connection.prepareStatement("SELECT * FROM employee WHERE salary BETWEEN ? AND ?");
		        pstmt.setBigDecimal(1, BigDecimal.valueOf(minSalary)); // Set minimum salary
		        pstmt.setBigDecimal(2, BigDecimal.valueOf(maxSalary)); // Set maximum salary
		        ResultSet rs = pstmt.executeQuery();
		        while (rs.next()) {
		            Employee employee = new Employee();
		            employee.setId(rs.getInt("id"));
		            employee.setName(rs.getString("name"));
		            employee.setDepartmentId(rs.getInt("department_id"));
		            employee.setSalary(rs.getBigDecimal("salary"));
		            employee.setJoinDate(rs.getDate("join_date"));
		            empList.add(employee);
		        }
		    } catch (SQLException e) {
		        System.err.println("Error retrieving employees by salary range: " + e.getMessage());
		    }
		    return empList.isEmpty() ? null : empList;
	}



}
