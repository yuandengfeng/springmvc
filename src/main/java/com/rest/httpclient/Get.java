package com.rest.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Created by Administrator on 2016/8/8.
 */
public class Get extends Thread {

    HttpClient client =HttpClients.createDefault();

    public void run(){
        HttpGet get = new HttpGet("http://www.baidu.com");
        try {


          HttpResponse response= client.execute(get);
//            response.getEntity();
            HttpEntity entity = response.getEntity();
           String result= EntityUtils.toString(entity,"UTF-8");
            System.out.println( result);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new Get().run();
    }

}
