package com.jt.redis;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.vo.EasyUITree;

public class JsonObjectTest {
	
	private final static ObjectMapper MAPPER = new ObjectMapper();
	@Test
	public void toJSON() throws Exception {
		
		EasyUITree ui = new EasyUITree();
		
		ui.setId(2L)
		  .setState("abc")
		  .setText("我是超人");
		
		String json = MAPPER.writeValueAsString(ui);
		
		System.out.println(json);
		
		EasyUITree easyUITree = MAPPER.readValue(json, EasyUITree.class);
		
		System.out.println(easyUITree);
		
	}
	


}
