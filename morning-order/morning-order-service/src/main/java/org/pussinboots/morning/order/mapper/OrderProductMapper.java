package org.pussinboots.morning.order.mapper;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.order.entity.OrderProduct;
import org.pussinboots.morning.order.pojo.vo.OrderShoppingCartVO;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
* 项目名称：morning-order-service   
* 类名称：OrderProductMapper   
* 类描述：OrderProduct / 订单明细表 数据访问层接口      
* 创建人：yeungchihang
* 创建时间：2017年5月10日 上午10:33:40   
*
 */
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

	/**
	 * 创建订单明细表
	 * @param shoppingCartVOs 购物车选中商品
	 * @param orderId 订单ID
	 * @return
	 */
	Integer insertProducts(@Param("shoppingCartVOs") List<OrderShoppingCartVO> shoppingCartVOs,
			@Param("orderId") Long orderId);

    List<OrderProduct> listByPage(@Param("orderId") Long orderId, @Param("pageInfo") PageInfo pageInfo,
								  @Param("search") String search, RowBounds rowBounds);

	List<OrderProduct> listByOrderId(@Param("orderId") Long orderId);
}