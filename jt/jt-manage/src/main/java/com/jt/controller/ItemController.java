package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.web.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	//Http://127.0.0.1:8091/item/query?page=1&rows=20
	/*
	 * 
	 * 
	 * 
	 */
	
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUITable findItemAll(@Param("page") Integer page,@Param("rows") Integer rows) {
		
		return itemService.findItemAll(page,rows);
		
	} 
	
	//http://127.0.0.1:8091/item/save
	
	@RequestMapping("/save")
	@ResponseBody
	public SysResult itemSave(Item item,ItemDesc itemDesc) {
		  //controller层异常写入SystemException类中
			itemService.itemSave(item,itemDesc);
			
		    return SysResult.success();
	
	}
	
	//http://127.0.0.1:8091/item/update
	
	@RequestMapping("/update")
	@ResponseBody
	public SysResult itemUpdate(Item item,ItemDesc itemDesc) {
		System.out.println("itemDesc="+itemDesc);
		System.out.println("item="+item);
		itemService.itemUpdate(item,itemDesc);
		
		return SysResult.success();
	}
	
	//http://127.0.0.1:8091/item/delete
	
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult itemDelete(Long[] ids) {
		
		itemService.itemDelete(ids);
		return SysResult.success();
	}
	
	//http://127.0.0.1:8091/item/instock  下架
	//http://127.0.0.1:8091/item/reshelf  上架
	
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult itemInsock(Long[] ids) {
		int status = 2;
		System.out.println("========"+ids);
		itemService.itemInsockReshelf(ids,status);
		return SysResult.success();
	}
	
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult itemReshelf(Long[] ids) {
		
		int status = 1;
		itemService.itemInsockReshelf(ids, status);
		return SysResult.success();
				
	}
	
	
	
}





















