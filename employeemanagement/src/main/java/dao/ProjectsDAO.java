package dao;

import java.sql.SQLException;
import java.util.List;

import model.Project;

public interface ProjectsDAO {

	boolean addProject(Project project) throws SQLException;

	Project getProjectById(int id) throws SQLException ;

	List<Project> getAllProjects() throws SQLException ;

	boolean updateProject(Project project) throws SQLException ;

	boolean deleteProject(int id) throws SQLException ;

	List<Project> getProjectsByManagerId(int managerId);

	List<Project> getProjectsByStartDate(String startDate);

	List<Project> getProjectsByEndDate(String endDate);

	List<Project> getActiveProjects();

}
