package org.example.h5;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.example.h5.model.TestModel;

public class Fastjson2Test {
    public static void main(String[] args) {
        String text = "{\"code\":\"200\",\"msg\":\"返回成功\",\"data\":\"Hello OldA>>>>>>32\"}";

        //1、将JSON解析为JSONObject
        JSONObject jsonObject = JSON.parseObject(text);
        System.out.println("jsonObject = " + jsonObject);

        //2、 将JSON解析为Java对象【同时测试使用下GsonFormatPlus插件】
        TestModel testModel = JSON.parseObject(text, TestModel.class);
        System.out.println("testModel = " + testModel);

        //3、将Java对象序列化为JSON
        String text1 = JSON.toJSONString(testModel);
        System.out.println("text1 = " + text1);

        //4、从JSONObject中获取简单属性
        String msg = jsonObject.getString("msg");
        System.out.println("msg = " + msg);

        //5、转为JavaBean
        TestModel testModel1 = jsonObject.toJavaObject(TestModel.class);
        System.out.println("testModel1 = " + testModel1);
    }
}
