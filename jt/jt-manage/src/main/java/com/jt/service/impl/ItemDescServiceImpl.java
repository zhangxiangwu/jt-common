package com.jt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.ItemDesc;
import com.jt.service.web.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		// TODO Auto-generated method stub
		
		ItemDesc itemDesc = itemDescMapper.selectById(itemId);
		
		return itemDesc;
	}

}
