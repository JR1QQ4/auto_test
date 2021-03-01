package utils;

import com.alibaba.fastjson.JSONObject;
import interface_demo.Demo;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 处理 Http 请求的工具类
 */
public class HttpUtil {
    private static Logger logger = Logger.getLogger(HttpUtil.class);

    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json;charset=utf-8";
    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";

    // 用于存放 cookie 信息
    public static Map<String, String> cookieMap = new HashMap<String, String>();

    public static String handlePost(String url, Map<String, String> params, String contentType) {
        String httpResponseText = "";
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();

//        Set<String> keys = params.keySet();
//        for (String key :
//                keys) {
//            String value = params.get(key);
//            pairList.add(new BasicNameValuePair(key, value));
//        }

        try {
            // 处理不同的数据提交类型
            if (CONTENT_TYPE_APPLICATION_JSON.equalsIgnoreCase(contentType)){
                httpPost.addHeader("Content-Type", CONTENT_TYPE_APPLICATION_JSON);
                if (params != null && params.size() > 0){
                    String jsonStr = JSONObject.toJSONString(params);
                    httpPost.setEntity(new StringEntity(jsonStr));
                }
            }else if(CONTENT_TYPE_FORM.equalsIgnoreCase(contentType)){
                httpPost.addHeader("Content-Type", CONTENT_TYPE_FORM);
                List<BasicNameValuePair> parameters = prepareNameValuesPairs(params);
                httpPost.setEntity(new UrlEncodedFormEntity(parameters));
            }


//            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

            HttpClient httpClient = HttpClients.createDefault();
            addCookieInRequestHeaderBeforeRequest(httpPost);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            getAndStoreCookiesFromResponseHeader(httpResponse);

            // 响应状态码
            int code = httpResponse.getStatusLine().getStatusCode();
            // 响应报文
            httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

            logger.info("响应状态码 Code=【" + code + "】, 响应结果 result=【" + httpResponseText + "】");
        } catch (Exception e) {
            logger.error("HttpUtil.handlePost Error: ");
            e.printStackTrace();
        }
        return httpResponseText;
    }

    public static String handleGet(String url, Map<String, String> params, String contentType) {
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

            // 指定接口提交的方式
            if (CONTENT_TYPE_APPLICATION_JSON.equalsIgnoreCase(contentType)){
                httpGet.addHeader("Content-Type", CONTENT_TYPE_APPLICATION_JSON);
            }else if(CONTENT_TYPE_FORM.equalsIgnoreCase(contentType)){
                httpGet.addHeader("Content-Type", CONTENT_TYPE_FORM);
            }

            HttpClient httpClient = HttpClients.createDefault();
            addCookieInRequestHeaderBeforeRequest(httpGet);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            getAndStoreCookiesFromResponseHeader(httpResponse);

            // 响应状态码
            int code = httpResponse.getStatusLine().getStatusCode();
            // 响应报文
            httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

            logger.info("响应状态码 Code=【" + code + "】, 响应结果 result=【" + httpResponseText + "】");
        } catch (Exception e) {
            logger.error("HttpUtil.handleGet Error: ");
            e.printStackTrace();
        }
        return httpResponseText;
    }

    public static String handlePostAndGet(String type, String url, Map<String, String> params) {
        String result = "";
        String contentType = PropertiesUtil.getApiContentType();
        if (type.toLowerCase().contains("get")) {
            result = handleGet(url, params, contentType);
        } else if (type.toLowerCase().contains("post")) {
            result = handlePost(url, params, contentType);
        }
        return result;
    }

    /**
     * 把 Cookies 添加到请求头上
     *
     * @param httpRequest 请求
     */
    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest httpRequest) {
        String jsessionIdCookie = cookieMap.get("JSESSIONID");
        if (jsessionIdCookie != null) {
            httpRequest.addHeader("Cookie", jsessionIdCookie);
        }
    }

    /**
     * 保存 Cookies
     *
     * @param httpResponse 响应结果
     */
    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        if (setCookieHeader != null) {
            String cookiePairsString = setCookieHeader.getValue();
            if (cookiePairsString != null && cookiePairsString.trim().length() > 0) {
                String[] cookiePairs = cookiePairsString.split(";");
                if (cookiePairs.length > 0) {
                    for (String cookiePair :
                            cookiePairs) {
                        if (cookiePair.contains("JSESSIONID")) {
                            cookieMap.put("JSESSIONID", cookiePair);
                        }
                    }
                }
            }
        }
    }

    private static List<BasicNameValuePair> prepareNameValuesPairs(Map<String, String> params){
        List<BasicNameValuePair> parameters = new ArrayList<>();
        Set<String> keys = params.keySet();
        for (String key :
                keys) {
            String value = params.get(key);
            parameters.add(new BasicNameValuePair(key, value));
        }
        return parameters;
    }
}
