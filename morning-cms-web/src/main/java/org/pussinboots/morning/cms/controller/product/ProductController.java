package org.pussinboots.morning.cms.controller.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.morning.cms.common.result.CmsPageResult;
import org.pussinboots.morning.cms.common.result.CmsResult;
import org.pussinboots.morning.cms.common.security.AuthorizingUser;
import org.pussinboots.morning.cms.common.util.SingletonLoginUtils;
import org.pussinboots.morning.common.base.BaseController;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.constant.CommonReturnCode;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.Category;
import org.pussinboots.morning.product.entity.Product;
import org.pussinboots.morning.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 
* 项目名称：morning-cms-web Maven Webapp   
* 类名称：ProductCategoryController   
* 类描述：分类管理表示层控制器          
* 创建人：yeungchihang
* 创建时间：2017年5月20日 下午3:08:25   
*
 */
@Controller
@RequestMapping(value = "/product/list")
@Api(value = "产品管理", description = "产品管理")
public class ProductController extends BaseController {
	
	@Autowired
	private IProductService productService;
	
	/**
	 * GET 分类管理页面
	 * @return
	 */
	@ApiOperation(value = "产品管理页面", notes = "产品管理页面")
	@RequiresPermissions("product:list:view")
	@GetMapping(value = "/view")
	public String getAdvertPage(Model model) {
		return "/modules/product/product_list";
	}

	@ApiOperation(value = "获取订单列表", notes = "根据分页信息/搜索内容获取订单列表")
	@RequiresPermissions("product:list:view")
	@GetMapping(value = "/")
	public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search){
		BasePageDTO<Product> basePageDTO = productService.listByPage(pageInfo, search);
		return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
	}
}
