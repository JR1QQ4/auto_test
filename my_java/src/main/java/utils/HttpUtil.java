package utils;

import interface_demo.Demo;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * 处理 Http 请求的工具类
 */
public class HttpUtil {
    // 用于存放 cookie 信息
    public static Map<String, String> cookieMap = new HashMap<String, String>();

    public static String handlePost(String url, Map<String, String> params) {
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
            addCookieInRequestHeaderBeforeRequest(httpPost);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            getAndStoreCookiesFromResponseHeader(httpResponse);

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
            addCookieInRequestHeaderBeforeRequest(httpGet);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            getAndStoreCookiesFromResponseHeader(httpResponse);

            httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseText;
    }

    public static String handlePostAndGet(String type, String url, Map<String, String> params) {
        String result = "";
        if (type.toLowerCase().contains("get")) {
            result = Demo.handleGet(url, params);
        } else if (type.toLowerCase().contains("post")) {
            result = Demo.handlePost(url, params);
        }
        return result;
    }


    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest httpRequest) {
        String jsessionIdCookie = cookieMap.get("JSESSIONID");
        if (jsessionIdCookie != null){
            httpRequest.addHeader("Cookie", jsessionIdCookie);
        }
    }

    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        if (setCookieHeader != null) {
            String cookiePairsString = setCookieHeader.getValue();
            if (cookiePairsString != null && cookiePairsString.trim().length() > 0){
                String [] cookiePairs = cookiePairsString.split(";");
                if (cookiePairs.length > 0) {
                    for (String cookiePair:
                            cookiePairs) {
                        if (cookiePair.contains("JSESSIONID")){
                            cookieMap.put("JSESSIONID", cookiePair);
                        }
                    }
                }
            }
        }
    }
}
