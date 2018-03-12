<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的订单 - 叮咚商城</title>
</head>
<body>
<%--<div class="span16">

</div>--%>
<div class="uc-box uc-main-box">
  <div class="uc-content-box order-list-box">
    <div class="box-hd">
      <h2 class="title">商品评价<%--<small>请谨防钓鱼链接或诈骗电话，<a href="" target="_blank">了解更多&gt;</a></small>--%></h2>
      <div class="more clearfix">
        <%--<ul class="filter-list J_orderType">
          <li class="first ${type eq 0 ? 'active':''}"><a href="${ctx}/uc/order/list?type=0" data-type="0">全部有效订单</a></li>
          <li class="${type eq 1 ? 'active':''}"><a id="J_unpaidTab" href="${ctx}/uc/order/list?type=1" data-type="1">待支付</a></li>
          <li class="${type eq 2 ? 'active':''}"><a id="J_sendTab" href="${ctx}/uc/order/list?type=2" data-type="2">待收货</a></li>
          &lt;%&ndash;<li class="${type eq 3 ? 'active':''}"><a href="${ctx}/uc/order/list?type=3" data-type="3">已关闭</a></li>&ndash;%&gt;
        </ul>--%>
        <%--<form id="J_orderSearchForm" class="search-form clearfix" action="" method="get">
          <label for="search" class="hide">站内搜索</label>
          <input class="search-text" type="search" id="J_orderSearchKeywords" name="search" value="${search}" autocomplete="off" placeholder="输入商品名称、商品编号、订单号" />
          <input type="hidden" name="type" value="4" />
          <input type="submit" class="search-btn iconfont" value="搜索" />
        </form>--%>
      </div>
    </div>
    <div class="box-bd">
      <div id="J_orderList" >
        <div class="loading hide">
          <div class="loader"></div>
        </div>
      </div>
      <div id="J_orderList">
        <ul class="order-list">
          <%--<c:forEach items="${orderVO}" var="orderVO">

          </c:forEach>--%>
            <li class="uc-order-item uc-order-item-finish">
              <div class="order-detail">
                <div class="order-summary">
                  <div class="order-status">待评价</div>
                </div>
                <table class="order-detail-table">
                  <form id="commentForm" method="post" action="">
                    <thead>
                    <tr>
                      <th class="col-main"> <p class="caption-info">
                        <input type="hidden" name="orderId" value="${orderVO.orderId}">
                        <fmt:formatDate value="${orderVO.createTime}" pattern="yyyy年MM月dd日 hh:mm" />
                        <span class="sep">|</span>yeungchihang<span class="sep">|</span>订单号： <a href="${ctx}/uc/order/${orderVO.orderNumber}">${orderVO.orderNumber}</a><span class="sep">|</span>在线支付</p>
                        <p class="caption-price">订单金额：<span class="num">${orderVO.payAmount}</span>元</p>
                        <%--<a class="btn btn-small btn-line-gray" href="${ctx}/uc/order/${orderVO.orderNumber}">订单详情</a>--%>
                      </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td class="order-items"><ul class="goods-list">
                        <c:forEach items="${orderVO.orderProducts}" var="orderProduct">
                          <li>
                            <input type="hidden" name="productNumbers" value="${orderProduct.productNumber}">
                            <div class="figure figure-thumb"> <a href="${ctx}/detail/${orderProduct.productNumber}" target="_blank"> <img src="${ctximg}/${orderProduct.picImg}" width="80" height="80" alt="${orderProduct.name}&nbsp;&nbsp;${orderProduct.productSpecName}" title="${orderProduct.name}&nbsp;&nbsp;${orderProduct.productSpecName}"> </a> </div>
                            <p class="name"> <a target="_blank" href="${ctx}/detail/${orderProduct.productNumber}">${orderProduct.name}&nbsp;&nbsp;${orderProduct.productSpecName}</a> </p>
                            <p class="price">${orderProduct.price}元 × ${orderProduct.buyNumber}</p>
                          </li>
                          <li style="padding: 0;">
                            <textarea name="comments" placeholder="请输入你的评价"></textarea>
                          </li>
                        </c:forEach>
                      </ul></td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                      <th><button style="width:100%;height:42px;line-height:42px;color: white;"
                                  type="button" class="btn btn-small btn-warning" onclick="addComment()">评  价</button></th>
                    </tr>
                    </tfoot>
                  </form>
                </table>
              </div>
            </li>
        </ul>
      </div>
      <%--<div id="J_orderListPages">--%>
        <%--<c:if test="${pageInfo.total gt pageInfo.limit and not empty orderVOs}">--%>
          <%--<div id="pager" data-pager-href="${ctx}/uc/order/list?type=${type}&search=${search}&page=" data-pager-totalPage="${pageInfo.totalPage}" data-pager-nowpage="${pageInfo.current}" data-pager-total="${pageInfo.total}"></div>--%>
        <%--</c:if>--%>
      <%--</div>--%>
    </div>
  </div>
</div>
<myfooter> 
  <!-- layer javascript --> 
  <script src="${ctxsta}/common/layer/layer.js"></script>
  <script src="${ctxsta}/os/js/comment.js"></script>
  <!-- 分页js -->
  <%--<script src="${ctxsta}/common/pager/jquery.pager.js"></script>
  <script type="text/javascript">
		var pagecount = $('#pager').attr('data-pager-totalPage'); // 总页面数
		var nowpage = $('#pager').attr('data-pager-nowpage'); // 当前页数
		var href = $('#pager').attr('data-pager-href'); // 链接地址
		$(document).ready(function() {
			$("#pager").pager({
				pagenumber : nowpage,
				pagecount : pagecount,
				buttonClickCallback : PageClick
			});
		});
		PageClick = function(number) {
			$("#pager").pager({
				pagenumber : number,
				pagecount : pagecount,
				buttonClickCallback : PageClick
			});
			window.location.href = href + number;
  	}
  </script> --%>
</myfooter>
</body>
</html>