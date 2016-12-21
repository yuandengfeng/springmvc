package com.mqtt.curl;

import com.mqtt.curl.mqtt.api.MQTTClient;
import com.mqtt.curl.mqtt.api.MQTTListener;
import com.mqtt.curl.mqtt.exe.CMD_EXE_script;
import com.mqtt.curl.mqtt.util.Base64Util;
import com.mqtt.curl.mqtt.util.CMD;
import com.mqtt.curl.mqtt.util.ICMDCallback;
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
public class Main {

    public static void main(String[] args)
    {

        try {
            MQTTListener.getInstance().subscribe("YunAC/+/+/");
        } catch (MqttException e) {
            e.printStackTrace();
        }

        String url="http://192.168.0.81:8080/kttax/login/";
        String SID="shop";  //服务号
        String SIDParam= null;   //加密串，json格式
        try {
            SIDParam = URLEncoder.encode("account=admin&passwd=admin", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        try {
            sb.append("com.mqtt.curl -i -s -d \""+ URLDecoder.decode(SIDParam,"UTF-8")+"\" "+url+SID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        CMD cmd24 = new CMD_EXE_script(Base64Util.encode(sb.toString().getBytes()), "1");
        cmd24.setSID("YunAC");
        cmd24.setDID("D4EE07251D06");
        System.out.println("sctipt:" + CMD.toPString(cmd24));
        try {
            MQTTClient.getInstance().send(cmd24, new ICMDCallback() {
                @Override
                public void process(CMD cmd, JSONObject response) throws Throwable {
                    System.out.println(response);
                }
            });
        } catch (MqttException ex) {
            Logger.getLogger(Cmain.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

}
