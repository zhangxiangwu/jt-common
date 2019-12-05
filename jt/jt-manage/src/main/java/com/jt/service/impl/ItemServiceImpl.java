package com.jt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jt.json.ObjectJsonUtil;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.web.ItemService;
import com.jt.vo.EasyUITable;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public EasyUITable findItemAll(Integer page, Integer rows) {
		
		//查询总行数
        Integer Count = itemMapper.selectCount(null);
        
        //2查询每页的数据
        //2.1计算起始数据
        Integer start = rows*(page-1);
        //2.2每页显示数据
        Integer pageCount = rows;
        
        List<Item> itemList = itemMapper.findByPage(start,pageCount);
        
		return new EasyUITable(Count,itemList);
	}

	@Override
	@Transactional
	public void itemSave(Item item,ItemDesc itemDesc) {
		// TODO Auto-generated method stub
		
		item.setStatus(1)
		    .setUpdated(new Date())
		    .setCreated(item.getUpdated());
		itemMapper.insert(item);
		
		itemDesc.setItemId(item.getId())
		         .setItemDesc(itemDesc.getItemDesc())
		         .setCreated(new Date())
		         .setUpdated(itemDesc.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	@Transactional
	public void itemUpdate(Item item,ItemDesc itemDesc) {
		// TODO Auto-generated method stub
		
		item.setCreated(new Date())
		    .setUpdated(item.getCreated());
		itemMapper.updateById(item);
		
		itemDesc.setItemId(item.getId())
		        .setCreated(item.getCreated())
	            .setUpdated(item.getCreated());
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	@Transactional
	public void itemDelete(Long[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			itemMapper.deleteById(ids[i]);
			itemDescMapper.deleteById(ids[i]);
		}
	}

	@Override
	public void itemInsockReshelf(Long[] ids,int status) {
		// TODO Auto-generated method stub
		
		for (Long id : ids) {
			
			Item item = new Item();
			item.setId(id)
			    .setStatus(status)
			    .setUpdated(new Date())
			    .setCreated(item.getUpdated());
			itemMapper.updateById(item);
		} 
		
		
		
//		List<Long> itemIds = Arrays.asList(ids);
//		
//		List<Item> itemList = itemMapper.selectBatchIds(itemIds);
//		
//		for (Item item : itemList) {
//			System.out.println("status="+status);
//			item.setStatus(status);
//			itemMapper.updateById(item);
//		}
		
//		System.out.println(itemList);
//		Iterator<Item> iterator = itemList.iterator();
//		
//		while(iterator.hasNext()) {
//			iterator.
//			iterator.next().setStatus(status);
//			System.out.println(iterator.next());
//			itemMapper.updateById(iterator.next());
//		}
		
		
	}

	@Override
	public String findItemById(Long itemId) {
		// TODO Auto-generated method stub
		Item item = itemMapper.selectById(itemId);
		System.out.println("service"+"========================");
		String itemJSON = ObjectJsonUtil.toJSON(item);
		return itemJSON;
	}
}

























