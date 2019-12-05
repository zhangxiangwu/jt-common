package com.jt.util;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

	 private final static ObjectMapper objectMapper = new ObjectMapper();
	
	 public static String toJson(Object obj) {
		 
		 try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		 
	 }
	 
	 public static <T>T  toObject(String json,Class<T> clz) {
		 
		 try {
			T object =  objectMapper.readValue(json, clz);
			return object;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		 
		 
	 }
}
