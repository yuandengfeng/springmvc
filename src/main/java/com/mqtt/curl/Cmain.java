package com.mqtt.curl;

import com.mqtt.curl.mqtt.api.MQTTClient;
import com.mqtt.curl.mqtt.api.MQTTListener;
import com.mqtt.curl.mqtt.exe.CMD_EXE_script;
import com.mqtt.curl.mqtt.util.Base64Util;
import com.mqtt.curl.mqtt.util.CMD;
import com.mqtt.curl.mqtt.util.ICMDCallback;
import com.mqtt.curl.mqtt.util.SynWait;
import net.sf.json.JSONObject;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yuandengfeng on 2016/11/9.
 */
public class Cmain {


    public static void main(String args[])
    {
        try {
            MQTTListener.getInstance().subscribe("chaohui/+/+/");
        } catch (MqttException ex) {
            Logger.getLogger(Cmain.class.getName()).log(Level.SEVERE, null, ex);
        }

//        String url="http://10.1.1.27:9999/InvSvr";
        String url="http://192.168.199.231:9999/InvSvr";
        String SID="2";      //服务号 0获取发票库存 1 发票开具 2 发票打印  3 发票清单打印 4 发票作废 12 启动开票服务 44 卷式发票开具
        String SIDParam= null;
        try {
            SIDParam= URLEncoder.encode(CurlParam.param3().toString(), "UTF-8");
            System.out.println(CurlParam.param3());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(SIDParam);

        StringBuilder sb = new StringBuilder();
//        sb.append("com.mqtt.curl -i -s -d \"account=admin&passwd=admin\" http://192.168.0.81:8080/kttax/login/shop");
        sb.append("com.mqtt.curl -s -d \"SID="+SID+"&SIDParam="+SIDParam+"\" "+url);


        CMD cmd24 = new CMD_EXE_script(Base64Util.encode(sb.toString().getBytes()), "1");
        cmd24.setSID("chaohui");
        cmd24.setDID("D4EE07251D06");  //指定接收命令的路由mac
//        cmd24.setDID("ShiYun");
//        cmd24.setDID("60ACC801185E");
        System.out.println("sctipt:" + CMD.toPString(cmd24));


        JSONObject result= synMethod(cmd24);   //将异步方法转换成同步方法

//        try {
//            MQTTClient.getInstance().send(cmd24, new ICMDCallback() {  //回调函数，异步执行
//                @Override
//                public void process(CMD cmd, JSONObject response) throws Throwable {
//                    System.out.println("返回结果！");
//                    System.out.println(response);
//                    System.out.println(response.containsKey("log_stdout"));
//                    String str = (String) response.get("log_stdout");
//                    System.out.println(str);
//                    System.out.println(URLDecoder.decode(str,"UTF-8"));
////                    System.out.println(URLDecoder.decode(js.getString("log_stdout"),"UTF-8"));
//                }
//            });
//        } catch (MqttException ex) {
//            Logger.getLogger(Cmain.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        System.out.println(result.get("status"));
//        System.out.println("ok done");
    }


    public static JSONObject synMethod(CMD cmd24){

        final SynWait<JSONObject> synWait = new SynWait();

        try {
            MQTTClient.getInstance().send(cmd24, new ICMDCallback() {
                @Override
                public void process(CMD cmd, JSONObject response) throws Throwable {
//                    System.out.println("返回结果！");
//                    System.out.println(response);
//                    System.out.println(response.containsKey("log_stdout"));
                    String str = (String) response.get("log_stdout");
                    System.out.println("----"+str);
                    System.out.println(URLDecoder.decode(str,"UTF-8"));


                    synWait.setContent(response);


                }
            });
        } catch (MqttException ex) {
            Logger.getLogger(Cmain.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONObject result = new JSONObject();
        JSONObject obj = synWait.startAndWait();
        if(obj !=null && obj.get("state").equals("0"))
        {
            result.put("status","0");
        }
        else {
            result.put("status","1");
        }
        System.out.println(obj.toString(4));
        return result;

    }





}
