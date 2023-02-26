package com.doodlemars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.doodlemars.db.DBConn;
import com.doodlemars.db.DBException;
import com.doodlemars.utils.Constants;
import com.doodlemars.utils.LoggerDTO;
import com.doodlemars.utils.UtilFunc;


public class LoggerDAO {
	public static void insertRow(String className, String message, String type) throws DBException{
        Connection conn = null;
        PreparedStatement psInsert = null;
        try {
            conn = DBConn.getConnection();
            psInsert = conn.prepareStatement("insert into "
                    + Constants.DB_TABLE_LOGGER + "(time, className, type, message) values (?,?,?,?)");
            psInsert.setString(1, UtilFunc.getCurrentTimeStamp());
            psInsert.setString(2, className);
            psInsert.setString(3, type);
            psInsert.setString(4, message);
            psInsert.executeUpdate();
            DBConn.close(conn, psInsert);
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
            DBConn.close(conn, psInsert);
            throw new DBException(e.getMessage());
        }
    }
	
	public static ArrayList<LoggerDTO> getLoggerData() {
        Connection conn = null;
        PreparedStatement psInsert = null;
        ArrayList<LoggerDTO> all = new ArrayList<LoggerDTO>();
        try {
            conn = DBConn.getConnection();
            psInsert = conn.prepareStatement("select time, className, type, message from "+ Constants.DB_TABLE_LOGGER+" order by time desc limit 500");
            ResultSet res = psInsert.executeQuery();
            while(res.next()) {
            	LoggerDTO ld = new LoggerDTO();
            	ld.setTime(res.getString(1));
            	ld.setClassName(res.getString(2));
            	ld.setType(res.getString(3));
            	ld.setMessage(res.getString(4));
            	all.add(ld);
            }
            DBConn.close(conn, psInsert);
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
            DBConn.close(conn, psInsert);
        }
        return all;
    }

	public static void clear() throws DBException {
		Connection conn = null;
        PreparedStatement psInsert = null;
        try {
            conn = DBConn.getConnection();
            psInsert = conn.prepareStatement("TRUNCATE "+ Constants.DB_TABLE_LOGGER + "");
            psInsert.executeUpdate();
            DBConn.close(conn, psInsert);
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
            DBConn.close(conn, psInsert);
            throw new DBException(e.getMessage());
        }
	}
}
