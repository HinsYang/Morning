package org.pussinboots.morning.cms.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.morning.cms.common.result.CmsPageResult;
import org.pussinboots.morning.cms.common.result.CmsResult;
import org.pussinboots.morning.cms.common.security.AuthorizingUser;
import org.pussinboots.morning.cms.common.util.SingletonLoginUtils;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.constant.CommonReturnCode;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.order.entity.Order;
import org.pussinboots.morning.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

/**
 *
 * 项目名称：morning-cms-web Maven Webapp
 * 类名称：OrderController
 * 类描述：订单管理表示层控制器
 * 创建人：yeungchihang
 * 创建时间：2017年4月10日 下午2:05:50
 *
 */
@Controller
@RequestMapping(value = "/system/order/list")
@Api(value = "订单管理", description = "订单管理")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "订单列表页面", notes = "订单列表页面")
    @RequiresPermissions("order:list:view")
    @GetMapping(value = "/view")
    public String getOrderPage(Model model){
        return "/modules/order/order_list";
    }

    @ApiOperation(value = "获取订单列表", notes = "根据分页信息/搜索内容获取订单列表")
    @RequiresPermissions("order:list:view")
    @GetMapping(value = "/")
    @ResponseBody
    public Object listOrder(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search){
        BasePageDTO<Order> basePageDTO = orderService.listByPage(pageInfo, search);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

    @ApiOperation(value = "手动取消订单", notes = "根据url订单ID取消订单")
    @RequiresPermissions("order:list:delete")
    @DeleteMapping(value = "/{advertId}")
    @ResponseBody
    public Object delete(@PathVariable("orderId") Long orderId) {
        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            Integer count = orderService.updateCancelOrder(orderId,authorizingUser.getUserId().toString());
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}
