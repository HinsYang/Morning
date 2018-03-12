package org.pussinboots.morning.os.controller.weixin;

import io.swagger.annotations.Api;
import org.dom4j.DocumentException;
import org.pussinboots.morning.common.base.BaseController;
import org.pussinboots.morning.os.common.util.SignUtil;
import org.pussinboots.morning.os.common.util.XmlUtil;
import org.pussinboots.morning.os.entity.TextMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hins on 2018/3/1.
 */
@Controller
@Api(value = "微信公众号消息处理", description = "微信公众号消息处理")
@RequestMapping(value = "/")
public class WeixinController extends BaseController{

    @GetMapping(value = "")
    public void doGet(String signature, String timestamp, String nonce, String echostr, HttpServletResponse resp){
        if (SignUtil.checkSignature(signature,timestamp,nonce)){
            PrintWriter writer= null;
            try {
                writer = resp.getWriter();
                writer.print(echostr);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "")
    public void doPost(String signature, String timestamp, String nonce, HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            if (SignUtil.checkSignature(signature,timestamp,nonce)){
                try {
                    Map<String,String> map= XmlUtil.parseFromXml(req);
                    Set<String> keySet=map.keySet();
                    for (String key:keySet){
                        System.out.println(key+"--->"+map.get(key));
                    }
                    String reMsgContent="";
                    String type=map.get("MsgType");
                    if (type.equals("event")){
                        if (map.get("EventKey").equals("customService")){
                            reMsgContent="【自助客户服务菜单】\n" +
                                    "请回复序号选择客户服务：\n" +
                                    "11.商城打不开怎么回事\n" +
                                    "12.为何无法正常登陆\n" +
                                    "13.下一次具体的活动时间\n" +
                                    "14.物流及配送\n" +
                                    "15.发票修改咨询\n";
                        }
                    }else {
                        String msg=map.get("Content");
                        if (msg.equals("1")||msg.contains("商城首页")){
                            reMsgContent="挑选最适合你的数码产品，戳这里<a href=\""+req.getRequestURL().append("index").toString()+"\">【叮咚商城DIDOBUY】</a>";
                        }else if (msg.equals("2")||msg.contains("推荐")){
                            reMsgContent="快来看这个月的推荐商品，传送门<a href=\""+req.getRequestURL().append("list?categoryId=1").toString()+"\">【推荐商品】</a>";
                        }else if (msg.equals("3")||msg.contains("热销")||msg.contains("手机")){
                            reMsgContent="「3.7~3.9女神节：4GB+64GB版本直降200元，女神享专属福利」<a href=\""+req.getRequestURL().append("list?categoryId=2").toString()+"\">【热销手机】</a>";
                        }else if (msg.equals("4")||msg.contains("智能")||msg.contains("硬件")){
                            reMsgContent="「近距离对话，由此刻开始」<a href=\""+req.getRequestURL().append("list?categoryId=3").toString()+"\">【智能硬件】</a>";
                        }else if (msg.equals("11")){
                            reMsgContent="请您确定一下网络连接稳定后，再次访问商城。";
                        }else if (msg.equals("12")){
                            reMsgContent="无法正常登录，原因有以下几种可能：\n" +
                                    "(1).您曾经在登录帐号后，执行了一些违规操作，如发广告贴等。帐号被暂时冻结。您可以稍后重试\n" +
                                    "(2).您在登录帐号时，多次输入错误密码，导致帐号被暂时冻结。";
                        }else if (msg.equals("13")){
                            reMsgContent="(1).什么时候开始下次的活动？\n" +
                                    "本次活动结束后，官网稍后会在产品界面显示下次购买时间，敬请关注。\n" +
                                    "(2).什么时候能购买官网活动的产品？\n" +
                                    "登陆商城，选择您所喜爱的产品，点击“加入购物车”之后付款，预祝您买到自己心仪的产品。";
                        }else if (msg.equals("14")){
                            reMsgContent="叮咚网购物采用第三方快递配送。\n" +
                                    "用户可以在我的订单里查询分配的订单的快递，从而及时查阅物流信息。\n" +
                                    "用户可以登录第三方快递公司相应网站";
                        }else if (msg.equals("15")){
                            reMsgContent="您好，目前在官网购买的商品默认是开具电子发票，若是您需要纸质发票，请在下单时手动选择普通发票，选择后不能更改，请谨慎操作。";
                        }else {
                            reMsgContent="您好，我是【叮咚商城机器人】\n" +
                                    "可以回复以下关键字获取传送门：\n" +
                                    "1.商城首页\n" +
                                    "2.推荐商品\n" +
                                    "3.热销手机\n" +
                                    "4.智能硬件\n";
                        }
                    }
                    //reMsgContent+="挑选最适合你的数码产品，戳这里<a href=\""+req.getRequestURL().append("index").toString()+"\">【叮咚商城DIDOBUY】</a>";
                    TextMsg reMsg=new TextMsg();
                    reMsg.setContent(reMsgContent);
                    reMsg.setToUserName(map.get("FromUserName"));
                    reMsg.setFromUserName(map.get("ToUserName"));
                    reMsg.setMsgType("text");
                    reMsg.setCreateTime(Calendar.getInstance().getTime().toString());
                    String reXml=XmlUtil.parseToXml(reMsg);
                    PrintWriter writer=resp.getWriter();
                    writer.print(reXml);
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
