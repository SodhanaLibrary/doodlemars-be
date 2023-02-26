package com.doodlemars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.doodlemars.db.DBConn;
import com.doodlemars.db.DBException;
import com.doodlemars.dto.VarientDTO;
import com.doodlemars.utils.Constants;

public class BodyModelDAO {
	public static ArrayList<VarientDTO> getBodyModels() throws DBException {
		Connection conn = null;
        PreparedStatement psInsert = null;
        ArrayList<VarientDTO> asrp = new ArrayList<VarientDTO>();
        try {
            conn = DBConn.getConnection();
            psInsert = conn.prepareStatement("select name, image, json from "+Constants.DB_TABLE_BODY_MODELS+"");
            ResultSet resp = psInsert.executeQuery();
            while(resp.next()){
            	VarientDTO srp = new VarientDTO();
                srp.setName(resp.getString(1));
                srp.setImage(resp.getString(2));
                srp.setJson(resp.getString(3));
                asrp.add(srp);
            }
            DBConn.close(conn, psInsert);
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
            DBConn.close(conn, psInsert);
            throw new DBException(e.getMessage());
        }
        return asrp;
	}
	
	public static void addBodyModels(VarientDTO vdto) throws DBException {
		 Connection conn = null;
	        PreparedStatement psInsert = null;
	        try {
	            conn = DBConn.getConnection();
	            psInsert = conn.prepareStatement("insert into "
	                    + Constants.DB_TABLE_BODY_MODELS + "(name, image, json) values (?,?,?)");
	            psInsert.setString(1, vdto.getName());
	            psInsert.setString(2, vdto.getImage());
	            psInsert.setString(3, vdto.getJson());
	            psInsert.executeUpdate();
	            DBConn.close(conn, psInsert);
	        } catch (ClassNotFoundException | SQLException e) {
	        	e.printStackTrace();
	            DBConn.close(conn, psInsert);
	            throw new DBException(e.getMessage());
	        }
	}
}
