package com.youtu.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youtu.common.pojo.YouTuResult;
import com.youtu.pojo.TbOrder;
import com.youtu.pojo.TbOrderItem;
import com.youtu.pojo.TbOrderShipping;

/**
 *@author:王贤锐
 *@date:2018年1月26日  下午7:09:10
**/
public interface OrderService {
	YouTuResult createOrder(TbOrder order,List<TbOrderItem> orderItemList,TbOrderShipping shipping,HttpServletRequest request,HttpServletResponse response);
}
