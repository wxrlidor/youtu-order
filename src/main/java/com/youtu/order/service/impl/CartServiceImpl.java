package com.youtu.order.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.youtu.common.pojo.YouTuResult;
import com.youtu.order.dao.JedisClient;
import com.youtu.order.service.CartService;

/**
 * 购物车相关类
 *@author:王贤锐
 *@date:2018年1月31日  下午2:36:22
**/
@Service
public class CartServiceImpl implements CartService {
	@Value("${REDIS_CART_KEY}")
	private String REDIS_CART_KEY;//购物车信息在reids中的存放方式为REDIS_CART_KEY:userId
	@Autowired
	private JedisClient jedisClient;
	/**
	 * 根据用户id从redis中查询出购物车信息
	 */
	@Override
	public YouTuResult getCartItemListByUser(Long userId) {
		//从redis中取出购物车信息
		String json = jedisClient.get(REDIS_CART_KEY + ":" +userId);
		//如果json数据不为空,则把json放入youtuResult中返回
		if(!StringUtils.isBlank(json)){
			return YouTuResult.ok(json);
		}
		
		return null;
	}

}
