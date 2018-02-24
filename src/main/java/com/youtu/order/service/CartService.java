package com.youtu.order.service;
/**
 *@author:王贤锐
 *@date:2018年1月31日  下午2:35:09
**/

import java.util.List;

import com.youtu.common.pojo.YouTuResult;

public interface CartService {
	YouTuResult getCartItemListByUser(Long userId);
}
