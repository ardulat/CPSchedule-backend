package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import models.Student;
import play.Logger;

public class StudentsDao {
	
	String userName = "root";
	String password = "root";
	String dbms = "mysql";
	String serverName = "localhost";
	String portNumber = "8888";
	String dbname = "yegor_chsherbatykh";		
	
	Connection conn = null;
	
	public StudentsDao() {
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
	
	public List<Student> getAllStudents() {
		List<Student> result = new ArrayList<>();
		try {
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENTS;");
            while (rs.next()) {
            	Student student = new Student();
            	student.id = rs.getString("id_number");
            	student.name = rs.getString("Name");
            	result.add(student);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}	
	
	public Student getStudentById(String id) {
		try {
			List<Student> students = new ArrayList<>();
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENTS where id_number = '" + id + "';");
            while (rs.next()) {
            	Student student = new Student();
            	student.id = rs.getString("id_number");
            	student.name = rs.getString("Name");
            	students.add(student);
            }
            if (students.isEmpty()) {
            	return null;
            } else {
            	return students.get(0);
            }
        }
        catch (Exception e) {
    		e.printStackTrace();
    		return null;
        }
	}
	
	public List<Student> getStudentsByName(String name) {
		List<Student> result = new ArrayList<>();
		try {
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENTS where Name like '%" + name + "%';");
            while (rs.next()) {
            	Student student = new Student();
            	student.id = rs.getString("id_number");
            	student.name = rs.getString("Name");
            	result.add(student);
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
	
	public Boolean insertStudent(String name, String id) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into STUDENTS(id_number, Name) values('"+id+"', '"+name+"');");
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean deleteStudentById(String id) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("delete from STUDENTS where id_number = '" + id + "';");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}