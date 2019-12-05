
package com.jt.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

public class JsonObjectListTest {
    
	   ObjectMapper MAPPER = new ObjectMapper();
	@Test
	public void testList() throws IOException {
		ItemDesc itemDesc1 = new ItemDesc();
		itemDesc1.setItemId(100L)
				.setItemDesc("商品详情")
				.setCreated(new Date())
				.setUpdated(itemDesc1.getCreated());
		ItemDesc itemDesc2 = new ItemDesc();
		itemDesc2.setItemId(100L)
				.setItemDesc("商品详情")
				.setCreated(new Date())
				.setUpdated(itemDesc2.getCreated());
		List list = new ArrayList();
		list.add(itemDesc1);
		list.add(itemDesc2);
		String json = MAPPER.writeValueAsString(list);
		System.out.println(json);
		List<ItemDesc> list2 = MAPPER.readValue(json, list.getClass());
		System.out.println(list2);
	}

	
}
