package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProjectsDAO;
import model.Project;
import utitliy.ConnectionProvider;

public class ProjectDAOImpl implements ProjectsDAO {

	private Connection connection = ConnectionProvider.getConnection();
	PreparedStatement pstmt = null;

	@Override
	public boolean addProject(Project project) throws SQLException {
		pstmt = connection.prepareStatement("INSERT INTO projects (project_id,project_name,start_date,end_date,manager_id) VALUES (?,?,?,?,?)");
		pstmt.setInt(1, project.getProject_id());
		pstmt.setString(2, project.getProject_name());
		pstmt.setDate(3, project.getStart_date());
		pstmt.setDate(4, project.getEnd_date());
		pstmt.setInt(5, project.getManager_id());
		int i = pstmt.executeUpdate();
		return i > 0;

	}

	@Override
	public Project getProjectById(int id) throws SQLException {
		pstmt = connection.prepareStatement("select * from projects where project_id=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Project project = new Project();
			project.setProject_id(rs.getInt(1));
			project.setProject_name(rs.getString(2));
			project.setStart_date(rs.getDate(3));
			project.setEnd_date(rs.getDate(4));
			project.setManager_id(rs.getInt(5));
			
			return project;
		}

		return null;
	}

	@Override
	public List<Project> getAllProjects() throws SQLException {
		pstmt = connection.prepareStatement("select * from projects ");
		ResultSet rs = pstmt.executeQuery();
		List<Project> proList = new ArrayList<Project>();

		while (rs.next()) {
			Project project = new Project();
			project.setProject_id(rs.getInt(1));
			project.setProject_name(rs.getString(2));
			project.setStart_date(rs.getDate(3));
			project.setEnd_date(rs.getDate(4));
			project.setManager_id(rs.getInt(5));
			proList.add(project);
		}

		if (!proList.isEmpty())
			return proList;

		return null;
	}

	@Override
	public boolean updateProject(Project project) throws SQLException {
		pstmt = connection.prepareStatement("update projects set project_name=?,start_date=?,end_date=? ,manager_id=? where project_id=? ");
	    
		pstmt.setString(1, project.getProject_name());
		pstmt.setDate(2, project.getStart_date());
		pstmt.setDate(3, project.getEnd_date());
	    pstmt.setInt(4, project.getManager_id());
	    pstmt.setInt(5,project.getProject_id());
	
		int i = pstmt.executeUpdate();
		if (i > 0)
			return true;
		return false;

	}

	@Override
	public boolean deleteProject(int id) throws SQLException {
		pstmt=connection.prepareStatement("delete from projects where project_id=? ");
		pstmt.setInt(1, id);
		int i=pstmt.executeUpdate();
		if(i>0)
			return true;
		return false;


	}

	@Override
	public List<Project> getProjectsByManagerId(int managerId) {
		  List<Project> projectList = new ArrayList<>();
		    try {
		        // Preparing the SQL query to fetch projects managed by a specific manager
		        pstmt = connection.prepareStatement("SELECT * FROM projects WHERE manager_id = ?");
		        pstmt.setInt(1, managerId);
		        
		        ResultSet rs = pstmt.executeQuery();
		        
		        // Loop through the result set and create Project objects
		        while (rs.next()) {
		            Project project = new Project();
		            project.setProject_id(rs.getInt("project_id"));
		            project.setProject_name(rs.getString("project_name"));
		            project.setManager_id(rs.getInt("manager_id"));
		            project.setStart_date(rs.getDate("start_date"));
		            project.setEnd_date(rs.getDate("end_date"));
		            
		            projectList.add(project);
		        }
		    } catch (SQLException e) {
		        System.err.println("Error retrieving projects for manager ID " + managerId + ": " + e.getMessage());
		    }
		    return projectList.isEmpty() ? null : projectList;
	}

	@Override
	public List<Project> getProjectsByStartDate(String startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getProjectsByEndDate(String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getActiveProjects() {
		// TODO Auto-generated method stub
		return null;
	}

}
