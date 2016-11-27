package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import models.Activity;
import models.Student;

public class ActivityDao {
	String userName = "root";
	String password = "root";
	String dbms = "mysql";
	String serverName = "localhost";
	String portNumber = "8888";
	String dbname = "yegor_chsherbatykh";		
	
	Connection conn = null;
	
	public ActivityDao() {
		try {
			conn = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    if (this.dbms.equals("mysql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/" + this.dbname,
	                   connectionProps);
	    }
	    return conn;
	}
	
	public boolean insertActivity(String id, String info, String time, String day) {
		try {
        	Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into STUDENTS_has_ACTIVITIES(stud_id, act_info, act_time, act_day) values ('"+id+"', '"+info+"', '"+time+"', '"+day+"');");
            stmt.close();
            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public List<Activity> getActivitiesById(String id) {
		List<Activity> result = new ArrayList<>();
		try {
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENTS_has_ACTIVITIES where stud_id = '"+id+"';");
            while (rs.next()) {
            	Activity act = new Activity();
            	act.id = rs.getString("stud_id");
            	act.info = rs.getString("act_info");
            	act.time = rs.getString("act_time");
            	act.day = rs.getString("act_day");
            	result.add(act);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
	}
	
	public boolean deleteActivity(String id, String info, String time, String day) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("delete from STUDENTS_has_ACTIVITIES where stud_id='"+id+"' and act_info='"+info+"' and act_time='"+time+"' and act_day='"+day+"';");
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
