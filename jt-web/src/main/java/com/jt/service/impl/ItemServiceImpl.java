package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.json.ObjectJsonUtil;
import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.util.HttpClientService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
    private HttpClientService httpClientService;
	
	@Override
	public Item findItemById(Long id) {
		// TODO Auto-generated method stub

		String url = "http://manage.jt.com/web/item/findItemById";
        
	    Map<String,String> params = new HashMap<>();
	    
	    params.put("itemId", String.valueOf(id));
				
        String itemJSON = httpClientService.doGet(url, params);		
        
		Item item = ObjectJsonUtil.toObject(itemJSON, Item.class);
		
		return item;
	}

}
