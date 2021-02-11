package utils;

import interface_demo.Demo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {
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
            HttpResponse httpResponse = httpClient.execute(httpPost);

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

            httpResponseText = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseText;
    }

    public static String handlePostAndGet(String sendType, String url, Map<String, String> params) {
        String result = "";
        if (sendType.toLowerCase().contains("get")) {
            result = Demo.handleGet(url, params);
        } else if (sendType.toLowerCase().contains("post")) {
            result = Demo.handlePost(url, params);
        }
        return result;
    }

}
