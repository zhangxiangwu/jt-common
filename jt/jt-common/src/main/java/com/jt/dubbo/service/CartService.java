package com.jt.dubbo.service;

import java.util.List;

import com.jt.pojo.Cart;

public interface CartService {

	List<Cart> findCartByUserId(Long userId);

	Cart updateCartNumByItemId(Cart cart);

	void deleteCart(Cart cart);

	void addCart(Cart cart);

}
