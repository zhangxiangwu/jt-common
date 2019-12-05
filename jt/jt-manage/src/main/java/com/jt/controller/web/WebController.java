package com.jt.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.json.ObjectJsonUtil;
import com.jt.pojo.ItemDesc;
import com.jt.service.web.ItemCatService;
import com.jt.service.web.ItemDescService;
import com.jt.service.web.ItemService;

@Controller
@RequestMapping("/web/item")
public class WebController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/findItemById")
	@ResponseBody
	public String findItemById(Long itemId) {
		System.out.println("+=========================");
		String itemJSON = itemService.findItemById(itemId);
		
		return itemJSON;
	}
	
	@RequestMapping("/findItemDescById")
	@ResponseBody
	public String findItemDescById(Long itemId) {
		
		ItemDesc itemDesc = itemDescService.findItemDescById(itemId);
		
		String itemDescJSON = ObjectJsonUtil.toJSON(itemDesc);
		
		return itemDescJSON;
		
	}
}
