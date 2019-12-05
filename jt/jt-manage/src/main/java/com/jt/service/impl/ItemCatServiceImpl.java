package com.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.json.ObjectJsonUtil;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.web.ItemCatService;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
//	@Autowired
//	private Jedis jedis;

	@Override
	public ItemCat findItemCatName(Long itemCatId) {
		// TODO Auto-generated method stub
		
		return itemCatMapper.selectById(itemCatId);
	}

//	@Override
//	public List<EasyUITree> findItemCatCacheAll(Long parentId) {
//		// TODO Auto-generated method stub
//		
//		String key = "ITEM_CAT_" + parentId;
//		String result = jedis.get(key);
//		
//		List<EasyUITree> easyUITree = new ArrayList<EasyUITree>();
//		
//		if(StringUtils.isEmpty(result)) {
//			
//			easyUITree = findItemCatAll(parentId);
//			System.out.println("用户第一次查询数据库");
//			jedis.setex(key, 7*24*3600, ObjectJsonUtil.toJSON(easyUITree));
//			return easyUITree;
//		}else {
//			
//			easyUITree = ObjectJsonUtil.toObject(result, easyUITree.getClass());
//			System.out.println("redis缓存查询!!!");
//		}
//		
//		return easyUITree;
//	}
	

	public List<EasyUITree> findItemCatAll(Long id) {
		// TODO Auto-generated method stub
		
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		
		queryWrapper.eq("parent_id", id);
		
		List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
		
		List<EasyUITree> list = new ArrayList<>();
		
		for (ItemCat itemCat : itemCatList) {
			
			String name = itemCat.getName();
			
			Long paramId = itemCat.getId();
			
			String state = null;
			if(itemCat.getIsParent()) {
				state = "closed";
			}
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setId(paramId)
			          .setState(state)
			          .setText(name);
			list.add(easyUITree);
				
		}
		
		return list;
	}

	
}
