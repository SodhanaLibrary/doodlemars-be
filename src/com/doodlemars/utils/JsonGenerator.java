package com.doodlemars.utils;

import com.doodlemars.dto.ResponseDTO;
import com.google.gson.Gson;

public final class JsonGenerator {
/*
 * this class is for generating JSON	
 */
	
	/*
	 * initialization of Gson class 
	 */
	private static Gson gson = new Gson();
	
	/*
	 * This class is for generating JSON from MODEL class
	 * @param Object
	 * @return String 
	 */
	public static String generateJson(Object to) {
		return null == to ? "" : gson.toJson(to);
	}
	
	public static String generateSuccessJson(String message) {
		ResponseDTO rd = new ResponseDTO();
		rd.setStatus("SUCCESS");
		rd.setMessage(message);
		return gson.toJson(rd);
	}
	
	public static String generateFailureJson(String message) {
		ResponseDTO rd = new ResponseDTO();
		rd.setStatus("ERROR");
		rd.setMessage(message);
		return gson.toJson(rd);
	}

	/*
	 * Generates the transfer object from the given JSON using Google Gson.
	 * 
	 * @param String
	 * @return transfer object
	 */
	public static Object generateTOfromJson(String json, Class<?> class1) {
		return gson.fromJson(json, class1);
	}
	
}
