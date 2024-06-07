package org.example.h5.util;

import cn.hutool.core.util.XmlUtil;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.SneakyThrows;
import java.util.Map;

/**
 * xml和map互相转换工具类
 *      1、微信交易的异步通知处理的时候会用到
 *      2、其他
 */
public class XmlUtilTest{

    @SneakyThrows
    public static void main(String[] args) {
        String xml="<returnsms> \n" +
                "  <returnstatus>Success（成功）</returnstatus>  \n" +
                "  <message>ok</message>  \n" +
                "  <remainpoint>1490</remainpoint>  \n" +
                "  <taskID>885</taskID>  \n" +
                "  <successCounts>1</successCounts> \n" +
                "</returnsms>";

        String xmlFormat= XmlUtil.format(xml);
        System.out.println("xmlFormat = " + xmlFormat);

        //XML格式字符串转换为Map>>>>>>方案一
        Map<String,Object> map= XmlUtil.xmlToMap(xml);
        System.out.println("map = " + map);

        //XML格式字符串转换为Map>>>>>>方案二
        Map<String,String> map1= WXPayUtil.xmlToMap(xml);

        //将Map转换为XML格式的字符串
        String xmlTemp=WXPayUtil.mapToXml(map1);
        System.out.println("xmlTemp = " + xmlTemp);

        //XML转Java Bean>>>>>>cn.hutool.core.util.XmlUtil.xmlToBean 后续有用到的同学自行研究

    }
}