package com.jt.service.web;

import java.util.List;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

public interface ItemCatService {

	ItemCat findItemCatName(Long itemCatId);

	List<EasyUITree> findItemCatAll(Long id);

	//List<EasyUITree> findItemCatCacheAll(Long parentId);
	
}
