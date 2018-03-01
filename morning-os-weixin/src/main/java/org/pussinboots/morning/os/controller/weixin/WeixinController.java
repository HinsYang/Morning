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
    public void doPost(String signature, String timestamp, String nonce, String echostr, HttpServletRequest req, HttpServletResponse resp){
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
                    TextMsg reMsg=new TextMsg();
                    reMsg.setContent("<a href=\""+req.getRequestURL().append("index").toString()+"\">叮咚商城</a>");
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
