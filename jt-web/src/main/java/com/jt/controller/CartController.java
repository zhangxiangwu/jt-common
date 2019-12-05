package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.service.CartService;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.util.ThreadLocalUtil;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Reference(check = false)
	private CartService cartService;

	//跳转至购物车页面
	//http://www.jt.com/cart/show.html

	@RequestMapping("/show")
	public String cart(Model model) {

		Long userId = ThreadLocalUtil.get().getId();

		List<Cart> listCart = cartService.findCartByUserId(userId);

		model.addAttribute("cartList", listCart);

		return "cart";
	}

	//更新购物车
	//http://www.jt.com/cart/update/num/1079507380/2

	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public Cart updateCart(Cart cart){

		Long userId = ThreadLocalUtil.get().getId();

		List<Cart> listCart =cartService.findCartByUserId(userId);

		Cart cart1 = null;

		for (Cart carts : listCart) {

			if(carts.getItemId().equals(cart.getItemId())) {
				cart.setUserId(userId);
				cart1 = cartService.updateCartNumByItemId(cart);
				break;
			}

		}
		return cart1;
	}

	//删除购物车商品
	//http://www.jt.com/cart/delete/1079507380.html

	@RequestMapping("/delete/{itemId}")
	public String deleteCart(Cart cart) {


		Long userId = ThreadLocalUtil.get().getId();

		cart.setUserId(userId);

		cartService.deleteCart(cart);

		System.out.println("CartController.deleteCart()");

		return "redirect:/cart/show.html";
	}

	//把商品添加致购物车	
	//http://www.jt.com/cart/add/1474391976.html

	@RequestMapping("/add/{itemId}")
	public String addCart(Cart cart,@PathVariable Long itemId) {

		Long userId = ThreadLocalUtil.get().getId();

		cart.setUserId(userId);
		cartService.addCart(cart);

		return "redirect:/items/"+itemId+".html";
	}

}






















