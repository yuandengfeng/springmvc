package com.rsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class MainPost {

    public static void post() throws UnsupportedEncodingException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("sn","qwxcnf3681ewq2k");
//        jsonObject.put("type","wifi");
//        jsonObject.put("mac","ad:hk:jf:hf");
//        jsonObject.put("pmac","a1:h2:4f:6f");
//        jsonObject.put("ssid","sd234lj423");
//        String str =jsonObject.toString();

        String str="{\"sn\":\"ccc33206\",\"type\":\"WIFI\",\"mac\":\"d4:ee:07:48:02:a6\",\"pmac\":\"00:1d:fa:7d:2a:fd\",\"ssid\":\"东区树霖办公打卡Wi-Fi\"}";
        String base64_publickey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXYAxWt5h6AADUD986DCGALx+roZoW/wgJ176kr+6LB+x72HUo9HJSbtmhAe2c0dXexCmi/Ze8AjYpal/KJFrW9w0PqLpXjPgQ8/p3ztGOtq0rfUvrjWig2f+GQIOFr/4hPVHKoLWjl9033xNou1RAwRqaIHX53F0Q3MxcIcEq/QIDAQAB";// getPublicKey(pairkeys);
        byte[] encrypt = RsaUtils.encryptByRSA(Base64.decodeBase64(base64_publickey), str.getBytes("utf-8"));
        String posinfo =RsaUtils.encodeBase64(encrypt);
        HttpClient client = HttpClients.createDefault();
//        HttpPost post = new HttpPost("http://localhost:8080/springmvc/posinfo/pos");
        HttpPost post = new HttpPost("http://jyt.kunteng.org/posinfo/pos");
//        HttpPost post = new HttpPost("http://localhost:8080/yunac/posinfo/pos");
        try {
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            parameters.add(new BasicNameValuePair("content",posinfo));
            post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
            HttpResponse response= client.execute(post);
            response.getEntity();
            HttpEntity entity = response.getEntity();
            String result= EntityUtils.toString(entity, "UTF-8");
            System.out.println( result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void timeTest(){

         long unixTime=System.currentTimeMillis()/1000L;
         System.out.println(unixTime);
         System.out.println(unixTime-1490844602);


//        System.out.println(new Date());

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
      post();
//      timeTest();
//        POS pos = new POS();
//        pos.setMac("ccc");
//        pos.setSn("ccs");
//        System.out.println(pos.getMac());
//        System.out.println(pos.getChannelPath());
    }





}
