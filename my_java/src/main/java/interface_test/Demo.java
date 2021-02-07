package interface_test;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class Demo {
    public static void baiduWeatherWithPost() throws IOException {
        // Api 调用步骤:
        // 1.填写接口地址
        String url = "http://api.map.baidu.com/weather/v1/";

        // 2.指定接口请求方式
        HttpPost httpPost = new HttpPost(url);

        // 3.准备数据
        String data_type = "all";  // 请求数据类型。数据类型有：now/fc/index/alert/fc_hour/all，控制返回内容
        String ak = "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7";
        String district_id = "222405";  // 区县的行政区划编码，查询的地点

        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("data_type", data_type));
        pairList.add(new BasicNameValuePair("ak", ak));
        pairList.add(new BasicNameValuePair("district_id", district_id));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

        // 4.准备请求头数据（不需要就不设置）

        // 5.发起请求，获取接口响应数据（状态吗、响应报文或某些特殊的响应数据）
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = httpClient.execute(httpPost);

        // 6.处理数据
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

        System.out.println(statusCode);
        System.out.println(httpResponseText);
    }

    public static void baiduWeatherWithGet() throws IOException {
        // Api 调用步骤:
        // 1.填写接口地址
        String url = "http://api.map.baidu.com/weather/v1/";

        // 2.准备数据
        String data_type = "all";  // 请求数据类型。数据类型有：now/fc/index/alert/fc_hour/all，控制返回内容
        String ak = "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7";
        String district_id = "222405";  // 区县的行政区划编码，查询的地点
        String urlPath = url + "?district_id=" + district_id + "&data_type=" + data_type + "&ak=" + ak;
        System.out.println(urlPath);

        // 3.指定接口请求方式
        HttpGet httpGet = new HttpGet(urlPath);

        // 4.准备请求头数据（不需要就不设置）

        // 5.发起请求，获取接口响应数据（状态吗、响应报文或某些特殊的响应数据）
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        // 6.处理数据
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

        System.out.println(statusCode);
        System.out.println(httpResponseText);
    }

    public static String handlePost(String url, Map<String, String> params) throws IOException {
        String httpResponseText = "";

        HttpPost httpPost = new HttpPost(url);

        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        Set<String> keys = params.keySet();
        for (String key :
                keys) {
            String value = params.get(key);
            pairList.add(new BasicNameValuePair(key, value));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // int statusCode = httpResponse.getStatusLine().getStatusCode();
            httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseText;
    }

    public static String handleGet(String url, Map<String, String> params) {
        Set<String> keys = params.keySet();
        int flag = 1;
        StringBuilder urlBuilder = new StringBuilder(url);
        for (String key :
                keys) {
            String value = params.get(key);
            if (flag == 1) {
                urlBuilder.append("?").append(key).append("=").append(value);
            } else {
                urlBuilder.append("&").append(key).append("=").append(value);
            }
            flag++;
        }
        url = urlBuilder.toString();
        String httpResponseText = "";

        try {
            HttpGet httpGet = new HttpGet(url);

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpGet);

            // int statusCode = httpResponse.getStatusLine().getStatusCode();
            httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseText;
    }

    public static void main(String[] args) throws IOException {
        // Demo.baiduWeatherWithPost();

        // Demo.baiduWeatherWithGet();

        // String area = URLEncoder.encode("北京", "utf-8");
        // System.out.println(area);
        // area = URLDecoder.decode(area, "utf-8");
        // System.out.println(area);

        // String url = "http://api.map.baidu.com/weather/v1/";
        // String data_type = "all";
        // String ak = "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7";
        // String district_id = "222405";
        // Map<String, String> postParams = new HashMap<String, String>();
        // postParams.put("data_type", data_type);
        // postParams.put("district_id", district_id);
        // postParams.put("ak", ak);
        // Map<String, Object> responseInfo = Demo.handlePost(url, postParams);
        // System.out.println(responseInfo.toString());

        String url = "http://api.map.baidu.com/weather/v1/";
        String data_type = "all";
        String ak = "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7";
        String district_id = "222405";
        Map<String, String> getParams = new HashMap<String, String>();
        getParams.put("data_type", data_type);
        getParams.put("district_id", district_id);
        getParams.put("ak", ak);
        String responseText = handleGet(url, getParams);
        System.out.println(responseText);
    }
}
