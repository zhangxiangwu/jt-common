package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain  = true)
@TableName("tb_item_desc")
@AllArgsConstructor
@NoArgsConstructor
public class ItemDesc extends BasePojo{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5755786477543278577L;
	@TableId
	private Long itemId;
	private String itemDesc;
}
