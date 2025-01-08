package com.anudip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import model.Attendance;
import model.Department;
import model.Employee;
import model.Project;
import daoimpl.AttendanceDAOImpl;
import daoimpl.DepartmentDAOImpl;
import daoimpl.EmployeeDAOImpl;
import daoimpl.ProjectDAOImpl;

public class Menu {

	public void displayMenu() throws NumberFormatException, IOException {

		Scanner scan = new Scanner(System.in);
		System.out.println(".....Main Menu..... ");
		System.out.println("1. Employee ");
		System.out.println("2. Department ");
		System.out.println("3. Attendance ");
		System.out.println("4. Project ");
		System.out.println("----------------------------------------");
		System.out.println("Please Enter your Choice :");
		int ch = scan.nextInt();

		switch (ch) {
		case 1:
			displayEmployeeMenu();
			break;
		case 2:
			displayDepartmentMenu();
			break;
		case 3:
			displayAttendenceMenu();
			break;
		case 4:
			displayProjectMenu();
			break;
		default: {
			System.out.println("Please enter a valid choice :");
			ch = scan.nextInt();
		}
		}

	}

	private void displayEmployeeMenu() throws NumberFormatException, IOException {
		EmployeeDAOImpl employeedao = new EmployeeDAOImpl();
		char choice;
		do {
			System.out.println(" Menu :");
			System.out.println("1.New Employee :");
			System.out.println("2.Search Employee :");
			System.out.println("3.View All Employees :");
			System.out.println("4.Update Employee :");
			System.out.println("5.Delete Employee :");
			System.out.println("6.Search Employees by Department");
			System.out.println("7.Search Employees within salary range");
			System.out.println("8.Return to Main Menu :");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1: {
				System.out.println("Enter Employee Id :");
				int id = Integer.parseInt(br.readLine());

				System.out.println("Enter Employee Department Id :");
				int departmentId = Integer.parseInt(br.readLine());

				System.out.println("Enter Employee Salary :");
				double salary = Double.parseDouble(br.readLine());
				BigDecimal salaryBigDecimal = BigDecimal.valueOf(salary);

				System.out.println("Enter Employee Join Date (yyyy-mm-dd) :");
				String joinDate = br.readLine();
				Date joinDateSql = Date.valueOf(joinDate);

				System.out.println("Enter Employee Name :");
				String name = br.readLine();

				Employee employee = new Employee(id, departmentId, salaryBigDecimal, joinDateSql, name);
				// Employee employee = new Employee(101, BigDecimal.valueOf(30000),
				// Date.valueOf("2024-09-14"), "Sita");

				try {
					boolean res = employeedao.addEmployee(employee);
					if (res == false)
						System.out.println("Something went wrong while adding employee.");
					else
						System.out.println("Employee added successfully.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
			case 2: {
				System.out.println("Enter Employee Id :");
				int id = Integer.parseInt(br.readLine());
				try {
					Employee employee = employeedao.getEmployeeById(id);
					if (employee != null)
						System.out.println(employee);
					else
						System.out.println("Employee with this id does not exist.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}

			case 3: {
				try {
					List<Employee> empList = employeedao.getAllEmployees();
					if (empList != null) {
						for (Employee e : empList)
							System.out.println(e);
					} else
						System.out.println("No Employee in the record.");
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			}
			case 4: {
				System.out.println("Please Enter Employee Id for Update :");
				int id = Integer.parseInt(br.readLine());
				try {
					Employee employee = employeedao.getEmployeeById(id);
					if (employee != null) {

						System.out.println("Enter Employee Name :");
						String name = br.readLine();

						System.out.println("Enter Employee Department Id :");
						int departmentId = Integer.parseInt(br.readLine());

						System.out.println("Enter Employee Salary :");
						double salary = Double.parseDouble(br.readLine());
						BigDecimal salaryBigDecimal = BigDecimal.valueOf(salary);

						System.out.println("Enter Employee Join Date (yyyy-mm-dd) :");
						String joinDate = br.readLine();
						Date joinDateSql = Date.valueOf(joinDate);

						employee = new Employee(id, departmentId, salaryBigDecimal, joinDateSql, name);
						boolean res = employeedao.updateEmployee(employee);
						if (res)
							System.out.println("Employee updated Successfully.");
						else
							System.out.println("Something went wrong while updating employee.");
					} else
						System.out.println("Employee with this Id does not exist");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}

			case 5: {
				System.out.println("Please Enter Employee Id for Delete :");
				int id = Integer.parseInt(br.readLine());
				Employee employee;
				try {
					employee = employeedao.getEmployeeById(id);
					if (employee == null)
						System.out.println("Plese enter valid id:");
					else {
						boolean res = employeedao.deleteEmployee(id);
						if (res)
							System.out.println("employee deleted successfully.");
						else
							System.out.println("Something went wrong while deleting the employee.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			case 6: {
				System.out.println("Enter Department ID to search employees:");
				int departmentId = Integer.parseInt(br.readLine());

				// EmployeeDAOImpl employeedao = new EmployeeDAOImpl();
				List<Employee> employees = employeedao.getEmployeesByDepartmentId(departmentId);
				if (employees != null && !employees.isEmpty()) {
					System.out.println("Employees in Department ID " + departmentId + ":");
					for (Employee employee : employees) {
						System.out.println(employee); // Assuming Employee's toString method is formatted well
					}
				} else {
					System.out.println("No employees found in this department.");
				}
				break;
			}

			case 7: {
				System.out.println("Enter Minimum Salary:");
				double minSalary = Double.parseDouble(br.readLine());
				System.out.println("Enter Maximum Salary:");
				double maxSalary = Double.parseDouble(br.readLine());

				try {
					List<Employee> employees = employeedao.getEmployeesBySalaryRange(minSalary, maxSalary);
					if (employees != null && !employees.isEmpty()) {
						System.out.println("Employees with Salary Range " + minSalary + " - " + maxSalary + ":");
						for (Employee employee : employees) {
							System.out.println(employee); // Assuming Employee's toString method is formatted well
						}
					} else {
						System.out.println("No employees found in the specified salary range.");
					}
				} catch (Exception e) {
					System.err.println("Error retrieving employees by salary range: " + e.getMessage());
				}
				break;
			}
			case 8: {
				displayMenu();
				break;
			}
			default: {
				System.out.println("Invalid choice :");
			}
			}
			System.out.println("Do you want to continue (y/n):");
			choice = br.readLine().toLowerCase().charAt(0);

		} while (choice == 'y');

	}

	private void displayDepartmentMenu() throws NumberFormatException, IOException {
		DepartmentDAOImpl departmentdao = new DepartmentDAOImpl();
		char choice;
		do {
			System.out.println("Department Menu :");
			System.out.println("1.New Department :");
			System.out.println("2.Search Department :");
			System.out.println("3.View All  Departments :");
			System.out.println("4.Update Department :");
			System.out.println("5.Delete Department :");
			System.out.println("6.Search Department By Department Name");
			System.out.println("7.Return to Main Menu :");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1: {
				System.out.println("Enter Department Id :");
				int id = Integer.parseInt(br.readLine());

				System.out.println("Enter Department Name :");
				String name = br.readLine();
				Department department = new Department(id, name);
				try {
					boolean res = departmentdao.addDepartment(department);
					if (res == false)
						System.out.println("Something went wrong while adding department.");
					else
						System.out.println("Department added successfully.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
			case 2: {
				System.out.println("Enter Departmemnt Id :");
				int id = Integer.parseInt(br.readLine());
				try {
					Department department = departmentdao.getDepartmentById(id);
					if (department != null)
						System.out.println(department);
					else
						System.out.println("Department with this id does not exist.");
				} catch (SQLException e) {

					e.printStackTrace();
				}

				break;
			}
			case 3: {
				try {
					List<Department> deptList = departmentdao.getAllDepartments();
					if (deptList != null) {
						for (Department d : deptList)
							System.out.println(d);
					} else
						System.out.println("No department in the record.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				System.out.println("Please Enter Department Id for Update :");
				int id = Integer.parseInt(br.readLine());
				try {
					Department department = departmentdao.getDepartmentById(id);
					if (department != null) {
						System.out.println("Enter department Name :");
						String name = br.readLine();

						department = new Department(id, name);
						boolean res = departmentdao.updateDepartment(department);
						if (res)
							System.out.println("Department updated Successfully.");
						else
							System.out.println("Something went wrong while updating department.");
					} else
						System.out.println("Department with this Id does not exist");
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			}
			case 5: {
				System.out.println("Please Enter Department Id for Delete :");
				int id = Integer.parseInt(br.readLine());
				Department department;
				try {
					department = departmentdao.getDepartmentById(id);
					if (department == null)
						System.out.println("Plese enter valid id:");
					else {
						boolean res = departmentdao.deleteDepartment(id);
						if (res)
							System.out.println("department deleted successfully.");
						else
							System.out.println("Something went wrong while deleting the department.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case 6: {
				System.out.println("Enter Department Name to search:");
				String deptName = br.readLine();

				Department department = departmentdao.getDepartmentByName(deptName); // Assuming employeedao is properly
																						// initialized
				if (department != null) {
					System.out.println("Department Details:");
					System.out.println("Department ID: " + department.getDept_id());
					System.out.println("Department Name: " + department.getDept_name());
				} else {
					System.out.println("No department found with the name: " + deptName);
				}
				break;
			}
			case 7: {
				displayMenu();
				break;
			}
			default: {
				System.out.println("Invalid choice :");
			}
			}
			System.out.println("Do you want to continue (y/n):");
			choice = br.readLine().toLowerCase().charAt(0);

		} while (choice == 'y');

	}

	private void displayAttendenceMenu() throws NumberFormatException, IOException {

		AttendanceDAOImpl attendancedao = new AttendanceDAOImpl();
		char choice;
		do {
			System.out.println("Attendance Menu :");
			System.out.println("1.New Attendance :");
			System.out.println("2.View attendance :");
			System.out.println("3.Attendance Report:");
			System.out.println("4.Update Attendance :");
			System.out.println("5.Delete Attendance :");
			System.out.println("6.View Attencdance By Employee Id");
			System.out.println("7.Return to Main Menu :");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1: {
				System.out.println("Enter Attendance Id :");
				int id = Integer.parseInt(br.readLine());

				System.out.println("Enter Employee Id :");
				int eid = Integer.parseInt(br.readLine());

				System.out.println("Enter date :");
				Date dateSql = Date.valueOf(br.readLine());

				System.out.println("Enter status :");
				String status = br.readLine();

				Attendance attendance = new Attendance(id, eid, dateSql, status);
				try {
					boolean res = attendancedao.addAttendance(attendance);
					if (res == false)
						System.out.println("Something went wrong while inserting attendance.");
					else
						System.out.println("attendance inserted successfully.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
			case 2: {
				System.out.println("Enter Attendance Id :");
				int id = Integer.parseInt(br.readLine());
				try {
					Attendance attendance = attendancedao.getAttendanceById(id);
					if (attendance != null)
						System.out.println(attendance);
					else
						System.out.println("Attendance with this id does not exist.");
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			}
			case 3: {
				try {
					List<Attendance> attList = attendancedao.getAllAttendance();
					if (attList != null) {
						for (Attendance a : attList)
							System.out.println(a);
					} else
						System.out.println("No attendance record found");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				System.out.println("Please Enter Attendance Id for Update :");
				int id = Integer.parseInt(br.readLine());
				try {
					Attendance attendance = attendancedao.getAttendanceById(id);
					if (attendance != null) {
						System.out.println("Enter Employee  Id :");
						int eid = Integer.parseInt(br.readLine());

						System.out.println("Enter Date  :");
						Date dateSql = Date.valueOf(br.readLine());

						System.out.println("Enter status:");
						String status = br.readLine();

						attendance = new Attendance(id, eid, dateSql, status);
						boolean res = attendancedao.updateAttendance(attendance);
						if (res)
							System.out.println("Attendance updated Successfully.");
						else
							System.out.println("Something went wrong while updating attendance.");
					} else
						System.out.println("attendance with this Id does not exist");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case 5: {
				System.out.println("Please Enter attendance Id for Delete :");
				int id = Integer.parseInt(br.readLine());
				Attendance attendance;
				try {
					attendance = attendancedao.getAttendanceById(id);
					if (attendance == null)
						System.out.println("Plese enter valid id:");
					else {
						boolean res = attendancedao.deleteAttendance(id);
						if (res)
							System.out.println("attendance deleted successfully.");
						else
							System.out.println("Something went wrong while deleting the attendance.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}

			case 6: {
			    System.out.println("Enter Employee ID to view attendance:");
			    int employeeId = Integer.parseInt(br.readLine());

			    
			       
			        List<Attendance> attendanceList = attendancedao.getAttendanceByEmployeeId(employeeId);
			        
			        if (attendanceList != null && !attendanceList.isEmpty()) {
			            System.out.println("Attendance Records for Employee ID: " + employeeId);
			            for (Attendance attendance : attendanceList) {
			                System.out.println("Attendance ID: " + attendance.getAttendance_id());
			                System.out.println("Date: " + attendance.getDate());
			                System.out.println("Status: " + attendance.getStatus());
			                System.out.println("-----------------------------");
			            }
			        } else {
			            System.out.println("No attendance records found for Employee ID: " + employeeId);
			        }
			    
			    break;
			}
			case 7: {
				displayMenu();
				break;
			}
			default: {
				System.out.println("Invalid choice :");
			}

			}
			System.out.println("Do you want to continue (y/n):");
			choice = br.readLine().toLowerCase().charAt(0);

		} while (choice == 'y');

	}

	private void displayProjectMenu() throws NumberFormatException, IOException {

		ProjectDAOImpl projectdao = new ProjectDAOImpl();
		char choice;
		do {
			System.out.println("Project Menu :");
			System.out.println("1.New project :");
			System.out.println("2.Search project :");
			System.out.println("3.View All Projects :");
			System.out.println("4.Update project :");
			System.out.println("5.Delete project :");
			System.out.println("6.Search Project By Manager Id");
			System.out.println("7.Return to Main Menu :");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1: {
				System.out.println("Enter Project Id :");
				int id = Integer.parseInt(br.readLine());

				System.out.println("Enter project Name :");
				String name = br.readLine();

				System.out.println("Enter Project Start Date :");
				Date start_date = Date.valueOf(br.readLine());

				System.out.println("Enter Project End Date :");
				Date end_date = Date.valueOf(br.readLine());

				System.out.println("Enter Manager Id :");
				int manager_id = Integer.parseInt(br.readLine());

				Project project = new Project(id, name, start_date, end_date, manager_id);
				try {
					boolean res = projectdao.addProject(project);
					if (res == false)
						System.out.println("Something went wrong while adding project.");
					else
						System.out.println("Project inseerted successfully.");
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			}
			case 2: {
				System.out.println("Enter Project Id :");
				int id = Integer.parseInt(br.readLine());
				try {
					Project project = projectdao.getProjectById(id);
					if (project != null)
						System.out.println(project);
					else
						System.out.println("project with this id does not exist.");
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			}
			case 3: {
				try {
					List<Project> proList = projectdao.getAllProjects();
					if (proList != null) {
						for (Project p : proList)
							System.out.println(p);
					} else
						System.out.println("No Project in the record.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				System.out.println("Please Enter Project Id for Update :");
				int id = Integer.parseInt(br.readLine());
				try {
					Project project = projectdao.getProjectById(id);
					if (project != null) {
						System.out.println("Enter project Name :");
						String name = br.readLine();

						System.out.println("Enter Project Start Date :");
						Date start_date = Date.valueOf(br.readLine());

						System.out.println("Enter Project End Date :");
						Date end_date = Date.valueOf(br.readLine());

						System.out.println("Enter Manager Id :");
						int manager_id = Integer.parseInt(br.readLine());

						project = new Project(id, name, start_date, end_date, manager_id);
						boolean res = projectdao.updateProject(project);
						if (res)
							System.out.println("Project updated Successfully.");
						else
							System.out.println("Something went wrong while updating Project.");
					} else
						System.out.println("Project with this Id does not exist");
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			}
			case 5: {
				System.out.println("Please Enter Project Id for Delete :");
				int id = Integer.parseInt(br.readLine());
				Project project;
				try {
					project = projectdao.getProjectById(id);
					if (project == null)
						System.out.println("Plese enter valid id:");
					else {
						boolean res = projectdao.deleteProject(id);
						if (res)
							System.out.println("Project deleted successfully.");
						else
							System.out.println("Something went wrong while deleting the project.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case 6: {
			    System.out.println("Enter Manager ID to view projects:");
			    int managerId = Integer.parseInt(br.readLine());

			    try {
			        List<Project> projectList = projectdao.getProjectsByManagerId(managerId);
			        
			        if (projectList != null && !projectList.isEmpty()) {
			            System.out.println("Projects managed by Manager ID: " + managerId);
			            for (Project project : projectList) {
			                System.out.println("Project ID: " + project.getProject_id());
			                System.out.println("Project Name: " + project.getProject_name());
			                System.out.println("Manager ID: " + project.getManager_id());
			                System.out.println("Start Date: " + project.getStart_date());
			                System.out.println("End Date: " + project.getEnd_date());
			                System.out.println("-----------------------------");
			            }
			        } else {
			            System.out.println("No projects found for Manager ID: " + managerId);
			        }
			    } catch (Exception e) {
			        System.err.println("Error while retrieving projects: " + e.getMessage());
			    }
			    break;
			}
			case 7: {
				displayMenu();
				break;
			}
			default: {
				System.out.println("Invalid choice :");
			}
			}
			System.out.println("Do you want to continue (y/n):");
			choice = br.readLine().toLowerCase().charAt(0);

		} while (choice == 'y');

	}

}