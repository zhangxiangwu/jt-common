package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.ItemDesc;
import com.jt.service.web.ItemDescService;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/item")
public class ItemDescController {

	@Autowired
	private ItemDescService itemDescService;
	
	
	//http://127.0.0.1:8091/item/param/item/query/1474391969
	//http://127.0.0.1:8091/item/query/item/desc/1474391969
	
	@RequestMapping("/query/item/desc/{id}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable("id") Long itemId) {
		
		ItemDesc itemDesc = itemDescService.findItemDescById(itemId);
		
		return SysResult.success(itemDesc);
	}
}

	
