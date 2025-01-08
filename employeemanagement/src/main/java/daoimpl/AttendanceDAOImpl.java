package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AttendanceDAO;
import model.Attendance;
import utitliy.ConnectionProvider;

public class AttendanceDAOImpl implements AttendanceDAO {

	private Connection connection = ConnectionProvider.getConnection();
	PreparedStatement pstmt = null;

	@Override
	public boolean addAttendance(Attendance attendance) throws SQLException {
		pstmt = connection.prepareStatement("INSERT INTO attendance (attendance_id,employee_id,date,status) VALUES (?,?,?,?)");
		pstmt.setInt(1, attendance.getAttendance_id());
		pstmt.setInt(2, attendance.getEmployee_id());
		pstmt.setDate(3, attendance.getDate());
		pstmt.setString(4, attendance.getStatus());
		int i = pstmt.executeUpdate();
		return i > 0;

	}

	@Override
	public Attendance getAttendanceById(int id) throws SQLException {
		pstmt = connection.prepareStatement("select * from attendance where attendance_id=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Attendance attendance = new Attendance();
			attendance.setAttendance_id(rs.getInt(1));
			attendance.setEmployee_id(rs.getInt(2));
			attendance.setDate(rs.getDate(3));
			attendance.setStatus(rs.getString(4));

			return attendance;
		}

		return null;
	}



	@Override
	public List<Attendance> getAllAttendance() throws SQLException{
		pstmt = connection.prepareStatement("select * from attendance ");
		ResultSet rs = pstmt.executeQuery();
		List<Attendance> attList = new ArrayList<Attendance>();

		while (rs.next()) {
			Attendance attendance = new Attendance();
			attendance.setAttendance_id(rs.getInt(1));
			attendance.setEmployee_id(rs.getInt(2));
			attendance.setDate(rs.getDate(3));
			attendance.setStatus(rs.getString(4));
			attList.add(attendance);
		}

		if (!attList.isEmpty())
			return attList;

		return null;
	}
	
	@Override
	public boolean updateAttendance(Attendance attendance) throws SQLException{
		pstmt = connection.prepareStatement("update attendance set employee_id=?,date=?,status=? where attendance_id=? ");
		pstmt.setInt(1, attendance.getEmployee_id());
		pstmt.setDate(2, attendance.getDate());
		pstmt.setString(3, attendance.getStatus());
	    pstmt.setInt(4, attendance.getAttendance_id());
		int i = pstmt.executeUpdate();
		if (i > 0)
			return true;
		return false;

	}

	@Override
	public boolean deleteAttendance(int id) throws SQLException {
		pstmt=connection.prepareStatement("delete from attendance where attendance_id=? ");
		pstmt.setInt(1, id);
		int i=pstmt.executeUpdate();
		if(i>0)
			return true;
		return false;

	}
	
	@Override
	public List<Attendance> getAttendanceByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public List<Attendance> getAttendanceByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attendance> getAttendanceByEmployeeAndDateRange(int employeeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Attendance> getAttendanceByEmployeeId(int employeeId)  {
		 List<Attendance> attendanceList = new ArrayList<>();
		    try {
		        // Assuming you have a prepared statement to query the attendance table
		        pstmt = connection.prepareStatement("SELECT * FROM attendance WHERE employee_id = ?");
		        pstmt.setInt(1, employeeId);
		        
		        ResultSet rs = pstmt.executeQuery();
		        
		        // Loop through the result set and create Attendance objects
		        while (rs.next()) {
		            Attendance attendance = new Attendance();
		            attendance.setAttendance_id(rs.getInt("attendance_id"));  
		            attendance.setEmployee_id(rs.getInt("employee_id"));
		            attendance.setDate(rs.getDate("date"));  
		            attendance.setStatus(rs.getString("status"));  
		            
		            attendanceList.add(attendance);
		        }
		        
		    } catch (SQLException e) {
		        System.err.println("Error retrieving attendance for employee ID " + employeeId + ": " + e.getMessage());		       
		    }
		    return attendanceList.isEmpty() ? null : attendanceList;
	}


}
