<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>订单管理 - 叮咚Morning</title>
<link rel="stylesheet" href="${ctxsta}/common/bootstrap-table/bootstrap-table.min.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>产品管理</h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <div class="row row-lg">
            <div class="col-sm-12">
              <div class="example-wrap">
                <div class="example">
                  <div id="toolbar" class="btn-group m-t-sm">
                    <shiro:hasPermission name="product:list:add">
                      <button type="button" class="btn btn-default"  title="创建产品" onclick="layer_show('创建广告位','${ctx}/online/advert/create','800','700')"> <i class="glyphicon glyphicon-plus"></i> </button>
                    </shiro:hasPermission>
                  </div>
                  <table id="table"
                         data-toggle="table"
                         data-height="600"
                         data-search="true"
                         data-show-refresh="true"
                         data-show-toggle="true"
                         data-show-export="true"
                         data-show-pagination-switch="true"
                         data-show-columns="true"
                         data-url="${ctx}/product/list/"
                         data-pagination="true"
                         data-page-size="20"
                         data-page-list="[20, 50, 100, 200]"
                         data-side-pagination="server"
                         data-striped="true"
                         data-sort-order="desc"
                         data-toolbar="#toolbar">
                    <thead>
                      <tr>
                        <th data-field="productId" data-halign="center" data-align="center" data-sortable="true">产品id</th>
                        <th data-field="productNumber" data-halign="center" data-align="center" data-sortable="true">产品号码</th>
                        <th data-field="labelId" data-formatter="labelIdFormatter" data-halign="center" data-align="center" data-sortable="true">标签</th>
                        <th data-field="name" data-halign="center" data-align="center" data-sortable="true">产品名称</th>
                        <th data-field="showPrice" data-halign="center" data-align="center" data-sortable="true">显示价钱</th>
                        <th data-field="introduce" data-halign="center" data-align="center" data-sortable="true">描述</th>
                        <th data-field="picImg" data-halign="center" data-align="center" data-sortable="true">图片路径</th>
                        <th data-field="createTime" data-formatter="timeFormatter" data-halign="center" data-align="center" data-sortable="true">生成时间</th>
                        <th data-field="createBy" data-halign="center" data-align="center" data-sortable="true">生成人</th>
                        <th data-field="updateTime" data-formatter="timeFormatter" data-halign="center" data-align="center" data-sortable="true">更新时间</th>
                        <th data-field="updateBy" data-halign="center" data-align="center" data-sortable="true">更新人</th>
                        <th data-formatter="actionFormatter" data-events="actionEvents" data-halign="center" data-align="center" data-sortable="true">操作</th>
                      </tr>
                    </thead>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<myfooter> 
  <!-- Bootstrap table --> 
  <script src="${ctxsta}/common/bootstrap-table/bootstrap-table.min.js"></script> 
  <script src="${ctxsta}/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script> 
  <script src="${ctxsta}/common/bootstrap-table/tableExport.js"></script> 
  <script src="${ctxsta}/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script> 
  <!-- 自定义js --> 
  <script src="${ctxsta}/cms/js/product.js"></script>
</myfooter>
</body>
</html>