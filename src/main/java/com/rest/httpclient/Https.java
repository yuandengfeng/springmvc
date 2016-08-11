package com.rest.httpclient;

import com.rest.WebClientDevWrapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2016/8/8.
 */
public class Https {

    /**
     * get请求
     * 请求大数据接口，获取上网日志信息
     * @param url
     * @param charset
     * @return
     */
    public static String getNetSecurityLogs(String url, String charset) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = null;

        String result = "";
        try {
            httpClient = WebClientDevWrapper.wrapClient(httpClient);
            httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            System.out.println("URI:" + httpGet.getURI().toString());
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    /**
     * post请求
     * 请求大数据接口，获取上网日志信息
     * @param url
     * @param charset
     * @return
     */
    public static String postNetSecurityLogs(String url, String charset) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post=null;
        String result = "";
        try {
            httpClient = WebClientDevWrapper.wrapClient(httpClient);
            post = new HttpPost(url);
            HttpResponse response = httpClient.execute(post);
            System.out.println("URI:" + post.getURI().toString());
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }








}
