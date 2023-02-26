package com.doodlemars.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import com.doodlemars.dao.LoggerDAO;
import com.doodlemars.db.DBException;


public class Logger {
	private String className = "";
	
	public Logger(String className) {
		this.className = className;
	}
	
	public void info(String message) {
		try {
			if(message.length() > 499) {
				message = message.substring(0, 499);
			}
			System.out.println("INFO "+message);
			LoggerDAO.insertRow(className, message, "INFO");
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	public void error(String message) {
		try {
			LoggerDAO.insertRow(className, message, "ERROR");
			System.err.println("ERROR "+message);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	public void error(Exception e) {
		try {
			String message = e.getMessage();
			String totalMessage = e.getMessage();
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            if(exceptionAsString.length() > 500) {
            	totalMessage = exceptionAsString.substring(0, 498);
            } else {
            	totalMessage = exceptionAsString;
            }
			System.out.println("ERROR "+message);
			if(message != null && message.length() > 0) {
				LoggerDAO.insertRow(className, message, "ERROR");
			}
			LoggerDAO.insertRow(className, totalMessage, "ERROR");
		} catch (DBException dbe) {
			dbe.printStackTrace();
		}
	}
	
	public void error(Throwable e) {
		try {
			String message = e.getMessage();
			String totalMessage = e.getMessage();
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            if(exceptionAsString.length() > 500) {
            	totalMessage = exceptionAsString.substring(0, 498);
            } else {
            	totalMessage = exceptionAsString;
            }
			System.out.println("ERROR "+message);
			if(message != null && message.length() > 0) {
				LoggerDAO.insertRow(className, message, "ERROR");
			}
			LoggerDAO.insertRow(className, totalMessage, "ERROR");
		} catch (DBException dbe) {
			dbe.printStackTrace();
		}
	}
	
	
	public static String getArrayString(Set<String> inp) {
		String out = "";
		for(String str : inp) {
			out += str+", ";
		}
		return out;
	}
	
}
