package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import dao.ActivityDao;
import dao.StudentsDao;
import dao.SubjectsDao;
import models.*;

public class Application extends Controller {

	private static StudentsDao studentDao = new StudentsDao();
	private static SubjectsDao subjectDao = new SubjectsDao();
	private static ActivityDao activityDao = new ActivityDao();
	
	public static void getAllStudents() {
		renderJSON(studentDao.getAllStudents());
	}
	
	public static void getAllSubjects() {
		renderJSON(subjectDao.getAllSubjects());
	}
	
	public static void getStudentById(String id) {
		renderJSON(studentDao.getStudentById(id));
	}
	
	public static void getSubjectsById(String id) {
		renderJSON(subjectDao.getSubjectsById(id));
	}
	
	public static void getStudentsByName(String name) {
		renderJSON(studentDao.getStudentsByName(name));
	}
	
	public static void insertStudent(String name, String id) {
		if (studentDao.insertStudent(name, id)) {
			renderText("Succesfully inserted.");
		} else {
			renderText("Failed");
		}
	}
	
	public static void deleteStudentById(String id) {
		if (studentDao.deleteStudentById(id)) {
			renderText("Succesfully deleted.");
		} else {
			renderText("Failed to delete.");
		}
	}
	
	public static void insertActivity(String id, String info, String time, String day) {
		if (activityDao.insertActivity(id, info, time, day)) {
			renderText("Activity successfully inserted.");
		} else {
			renderText("Failed to insert activity.");
		}
	}
	
	public static void getActivitiesById(String id) {
		renderJSON(activityDao.getActivitiesById(id));
	}
	
	public static void deleteActivity(String id, String info, String time, String day) {
		if (activityDao.deleteActivity(id, info, time, day)) {
			renderText("Activity successfully deleted.");
		} else {
			renderText("Failed to delete activity.");
		}
	}
}
