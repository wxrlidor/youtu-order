package com.youtu.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtu.common.pojo.YouTuResult;
import com.youtu.common.utils.IDUtils;
import com.youtu.mapper.TbOrderItemMapper;
import com.youtu.mapper.TbOrderMapper;
import com.youtu.mapper.TbOrderShippingMapper;
import com.youtu.order.service.OrderService;
import com.youtu.pojo.TbOrder;
import com.youtu.pojo.TbOrderItem;
import com.youtu.pojo.TbOrderShipping;

/**
 * 订单Service
 * 
 * @author:王贤锐
 * @date:2018年1月26日 下午7:10:33
 **/
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;

	/**
	 * 1、对应订单表的pojo。 2、订单明细表对应的商品列表。每个元素是订单明细表对应的pojo 3、物流表对应的pojo
	 */
	@Override
	public YouTuResult createOrder(TbOrder order, List<TbOrderItem> orderItemList, TbOrderShipping orderShipping,
			HttpServletRequest request,HttpServletResponse response) {
		Date date = new Date();
		// 向订单表中插入记录
		// 获得订单号
		long orderId = IDUtils.genItemId();
		// 补全pojo的属性
		order.setOrderId(orderId + "");
		// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// 0：未评价 1：已评价
		order.setBuyerRate(0);
		// 向订单表插入数据
		orderMapper.insert(order);
		
		// 插入订单明细
		for (TbOrderItem tbOrderItem : orderItemList) {
			// 补全订单明细
			// 取订单明细id
			long orderDetailId = IDUtils.genItemId();
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderId + "");
			// 向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);
		}
		
		// 插入物流表
		// 补全物流表的属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		
		
		return YouTuResult.ok(orderId);
	}
}
