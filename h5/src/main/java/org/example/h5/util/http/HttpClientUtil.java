package org.example.h5.util.http;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * 我们在接下来的请求阿里云第三方产品的时候会用到
 */
@Slf4j
public class HttpClientUtil {
    Map<String, String> parameter = new HashMap<String, String>();


    /**
     * 发送get请求
     * @param url    请求地址
     * @param param  map入参
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {
        log.info("请求接口:{},参数:{}", url, param);
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            log.info("请求接口{},返回:{}", response.getStatusLine().getStatusCode() == 200 ? "成功" : "失败", resultString);
        } catch (Exception e) {
            log.error("请求接口报错,url:{},", url, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 无参get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 发送post请求
     * @param url    请求地址
     * @param param  map入参
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        return doPost(url, param, null);
    }

    /**
     * 发送post请求
     * @param url
     * @param param
     * @param header 携带请求头参数
     * @return
     */
    public static String doPost(String url, Map<String, String> param, Map<String, String> header) {
        log.info("请求接口:{},参数:{}", url, param);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null && param.size() > 0) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 创建header列表
            if (header != null && header.size() > 0) {
                for (String key : header.keySet()) {
                    httpPost.addHeader(key, header.get(key));
                }
            }
//            httpPost.addHeader("Content-Type", "application/json");
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            log.info("请求接口{},返回:{}", response.getStatusLine().getStatusCode() == 200 ? "成功" : "失败", resultString);
        } catch (Exception e) {
            log.error("请求接口报错,url:{},", url, e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 发送post请求  入参为JSONObject格式
     * @param url
     * @param jsonObject
     * @return
     */
    public static String doJsonPost(String url, JSONObject jsonObject) {
        return doJsonPost(url, jsonObject, null);
    }

    /**
     *
     * @param url
     * @param jsonObject
     * @param header
     * @return
     */
    public static String doJsonPost(String url, JSONObject jsonObject, Map<String, String> header) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (jsonObject != null && jsonObject.size() > 0) {
                StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
                httpPost.setEntity(stringEntity);
            }
            // 创建header列表
            if (header != null && header.size() > 0) {
                for (String key : header.keySet()) {
                    httpPost.addHeader(key, header.get(key));
                }
            }
//            httpPost.addHeader("Content-Type", "application/json");

            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static CloseableHttpResponse doJsonPostObj(String url, JSONObject jsonObject, Map<String, String> header) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (jsonObject != null && jsonObject.size() > 0) {
                StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
                httpPost.setEntity(stringEntity);
            }
            // 创建header列表
            if (header != null && header.size() > 0) {
                for (String key : header.keySet()) {
                    httpPost.addHeader(key, header.get(key));
                }
            }
//            httpPost.addHeader("Content-Type", "application/json");

            // 执行http请求

            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * @param url
     * @param json
     * @return json
     */
    public static String doPostJson(String url, String json) {
        return doPostJson(url, json, null);
    }



    public static String doPostJson(String url, String json, Map headerMap) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            //添加请求头
            if (!CollectionUtil.isEmpty(headerMap)) {
                Iterator iterator = headerMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    httpPost.addHeader(key, (String) headerMap.get(key));
                }
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            log.error("发送http请求失败:{}", e);
            return null;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("发送http关闭资源失败:{}", e);
            }
        }
    }
    public static void main(String[] args) {
        String result=doGet("http://127.0.0.1:8080/");
        System.out.println("result = " + result);
    }
}