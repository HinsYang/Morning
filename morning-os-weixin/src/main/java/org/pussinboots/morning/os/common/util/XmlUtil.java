package org.pussinboots.morning.os.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.pussinboots.morning.os.entity.BaseMsg;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hins on 2017/12/19.
 */
public class XmlUtil {
    public static Map<String,String> parseFromXml(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map=new HashMap<String, String>();
        InputStream is=request.getInputStream();
        SAXReader reader=new SAXReader();
        Document document=reader.read(is);
        Element root=document.getRootElement();
        List<Element> elementList=root.elements();

        for (Element e:elementList){
            map.put(e.getName(),e.getText());
        }

        is.close();

        return map;
    }

    public static String parseToXml(BaseMsg msg){
        XStream xStream=new XStream(new XppDriver());
        xStream.alias("xml",msg.getClass());
        return xStream.toXML(msg);
    }
    /*public static Map<String,String> parseEncryptXml(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map=new HashMap<String, String>();
        InputStream is=request.getInputStream();
        SAXReader reader=new SAXReader();
        Document document=reader.read(is);
        Element root=document.getRootElement();
        List<Element> elementList=root.elements();

        for (Element e:elementList){
            map.put(e.getName(),e.getText());
        }

        is.close();

        return map;
    }*/
}
