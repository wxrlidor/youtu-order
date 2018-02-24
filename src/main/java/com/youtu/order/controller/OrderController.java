package com.youtu.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youtu.common.pojo.YouTuResult;
import com.youtu.common.utils.ExceptionUtil;
import com.youtu.order.pojo.Order;
import com.youtu.order.service.OrderService;

/**
 * 订单controller
 *@author:王贤锐
 *@date:2018年1月26日  下午7:56:07
**/
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public YouTuResult createOrder(@RequestBody Order order,HttpServletRequest request,HttpServletResponse response) {
		try {
			YouTuResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping(),request,response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return YouTuResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
