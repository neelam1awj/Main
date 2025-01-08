package dao;

import java.sql.SQLException;
import java.util.List;

import model.Attendance;

public interface AttendanceDAO {

	boolean addAttendance(Attendance attendance) throws SQLException;

	Attendance getAttendanceById(int id) throws SQLException;

	List<Attendance> getAllAttendance() throws SQLException;

	List<Attendance> getAttendanceByEmployeeId(int employeeId) throws SQLException;

	List<Attendance> getAttendanceByDate(String date);

	boolean updateAttendance(Attendance attendance) throws SQLException;

	boolean deleteAttendance(int id) throws SQLException;

	List<Attendance> getAttendanceByStatus(String status);

	List<Attendance> getAttendanceByEmployeeAndDateRange(int employeeId, String startDate, String endDate);

}
