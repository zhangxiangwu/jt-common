package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("tb_cart")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3803459761676831666L;

	@TableId(type = IdType.AUTO)
	private Long id;
	private Long userId;
	private Long itemId;
	private String itemTitle;
	private String itemImage;
	private Long   itemPrice;
	private Integer num;
	
}
