package com.jt.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectJsonUtil {

	private final static ObjectMapper MAPPER = new ObjectMapper();
	
	//对象转JSON
	public static String toJSON(Object obj) {
		
		String JSON = null;
		
		try {
			JSON = MAPPER.writeValueAsString(obj);
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return JSON;
	}
	
	//JSON转对象
	public static <T> T  toObject(String JSON,Class<T> cls) {
		
		T obj = null;
		
		try {
			obj = MAPPER.readValue(JSON, cls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		return obj;
	}
	
}
