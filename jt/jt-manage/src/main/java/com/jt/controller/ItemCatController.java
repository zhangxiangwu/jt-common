package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.anno.Cache_ItemCat;
import com.jt.pojo.ItemCat;
import com.jt.service.web.ItemCatService;
import com.jt.vo.EasyUITree;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	//查询商品类目名字
	//http://127.0.0.1:8091/item/cat/queryItemName
	
	@ResponseBody
	@RequestMapping("/queryItemName")
	public String findItemCatNamea(Long itemCatId) {
	
		return itemCatService.findItemCatName(itemCatId).getName();
		
	}
	
	
//	@ResponseBody
//	@RequestMapping("/queryItemName")
//	private String findItemCatName(Long itemCatId) {
//		
//		System.out.println("=====================================");
//		System.out.println("itemCatService="+itemCatService);
//		ItemCat itemCat = itemCatService.findItemCatName(itemCatId);
//		
//		System.out.println("itemCat.getName()="+itemCat.getName());
//		
//		return itemCat.getName();
//	}
	
	//查询商品类目列表
	//127.0.0.1:8091/item/cat/list
	
	@RequestMapping("/list")
	@ResponseBody
	@Cache_ItemCat
	public List<EasyUITree> findItemCatAll(@RequestParam(value = "id", defaultValue = "0")Long parentId) {
		
		List<EasyUITree> easyUITreeList = itemCatService.findItemCatAll(parentId);
		
		//List<EasyUITree> easyUITreeList = itemCatService.findItemCatCacheAll(parentId);
		return easyUITreeList;
	}
	
}
























