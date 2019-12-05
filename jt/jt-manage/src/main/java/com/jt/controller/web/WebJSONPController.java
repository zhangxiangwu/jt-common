package com.jt.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.json.ObjectJsonUtil;
import com.jt.pojo.ItemDesc;

@Controller
public class WebJSONPController {

	@RequestMapping("/web/testJSONP")
	@ResponseBody
     private String testJson(String callback) {
    	 
		ItemDesc item = new ItemDesc();
		    item.setItemId(8L)
		        .setItemDesc("testJSON");
		    String itemJSON = ObjectJsonUtil.toJSON(item);
		    return callback+"("+itemJSON+")";
     }	
}
