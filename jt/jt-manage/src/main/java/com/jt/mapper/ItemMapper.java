package com.jt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;


public interface ItemMapper extends BaseMapper<Item>{

	List<Item> findByPage(Integer start, Integer pageCount);
	
}
