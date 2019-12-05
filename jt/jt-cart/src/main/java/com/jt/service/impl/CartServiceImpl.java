package com.jt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.dubbo.service.CartService;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> findCartByUserId(Long userId) {
		// TODO Auto-generated method stub
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
		
		queryWrapper.eq("user_id", userId);
		
		return cartMapper.selectList(queryWrapper);
	}

	@Override
	public Cart updateCartNumByItemId(Cart cart) {
		// TODO Auto-generated method stub
		
//		QueryWrapper queryWrapper = new QueryWrapper();
//		queryWrapper.eq("item_id", itemId);
//		
//		Cart cart = cartMapper.selectOne(queryWrapper);
//		cart.setNum(num)
//		    
		Cart cartTemp  = new Cart();
		
		cart.setNum(cart.getNum())
		    .setCreated(new Date())
		    .setUpdated(cart.getCreated());
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper();
		
		updateWrapper.eq("item_id", cart.getItemId())
		             .eq("user_id", cart.getUserId());
		
		cartMapper.update(cart, updateWrapper);
		
		return cart;
	}

	@Override
	public void deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>(cart);
		
		cartMapper.delete(queryWrapper);
		
	}

	@Override
	public void addCart(Cart cart) {
		// TODO Auto-generated method stub
		
		QueryWrapper<Cart> queryWrapper = new QueryWrapper();
		
		queryWrapper.eq("user_id", cart.getUserId())
		            .eq("item_id",cart.getItemId());
		Cart cartDB = cartMapper.selectOne(queryWrapper);
		
		if(cartDB == null) {
			
			cart.setCreated(new Date())
		        .setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			Cart cartTemp = new Cart();
			cartTemp.setId(cartDB.getId())
			        .setNum(cart.getNum()+cartDB.getNum())
		            .setUpdated(cart.getCreated());
			
			cartMapper.updateById(cartTemp);
		}
		
		
		
	}

	
}

























