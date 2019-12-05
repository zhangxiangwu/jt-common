package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.json.ObjectJsonUtil;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemDescService;
import com.jt.util.HttpClientService;

@Service
public class ItemDescServcieImpl implements ItemDescService {

	@Autowired
	private HttpClientService httpClientService;
	
	@Override
	public ItemDesc findItemDescById(Long id) {
		// TODO Auto-generated method stub
		String url = "Http://manage.jt.com/web/item/findItemDescById";
		Map<String,String> map = new HashMap<>();
		
		map.put("itemId", String.valueOf(id));
		
		String itemDescJSON = httpClientService.doGet(url, map);
		
		ItemDesc itemDesc = ObjectJsonUtil.toObject(itemDescJSON, ItemDesc.class);
		
		return itemDesc;
	}

}
