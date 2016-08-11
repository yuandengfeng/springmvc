package com.rest.apache;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2016/4/12.
 */
public class ApacheHttpRestClient1 {

    public static void main(String[] args) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // specify the host, protocol, and port
//            HttpHost target = new HttpHost("weather.yahooapis.com", 80, "http");
            HttpHost target = new HttpHost("weather.yahooapis.com", 80, "http");

            // specify the get request
            HttpGet getRequest = new HttpGet("/forecastrss?p=80020&u=f");

            System.out.println("executing request to " + target);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity entity = httpResponse.getEntity();

            System.out.println("----------------------------------------");
            System.out.println("------------getStatusLine()--------------"+httpResponse.getStatusLine());
            Header[] headers = httpResponse.getAllHeaders();
            System.out.println("--------------- headers ---------------------");
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
            }

            System.out.println("--------------- entity ---------------------");
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }

}
