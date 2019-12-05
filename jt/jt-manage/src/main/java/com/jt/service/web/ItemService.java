package com.jt.service.web;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {

	EasyUITable findItemAll(Integer page, Integer rows);

	void itemSave(Item item,ItemDesc itemDesc);

	void itemUpdate(Item item,ItemDesc itemDesc);

	void itemDelete(Long[] ids);

	void itemInsockReshelf(Long[] ids,int status);

	String findItemById(Long itemId);

	
}
