package com.mqtt.curl.mqtt;


import com.mqtt.curl.Cmain;
import com.mqtt.curl.CurlParam;
import com.mqtt.curl.mqtt.api.MQTTListener;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yuandengfeng on 2016/11/9.
 */
public class Test {

    public static void main(String[] args)
    {
       try {
            MQTTListener.getInstance().subscribe("chaohui/+/+/");
        } catch (MqttException ex) {
            Logger.getLogger(Cmain.class.getName()).log(Level.SEVERE, null, ex);
        }

        String url="http://127.0.0.1:9999/InvSvr";
        String SID="1";
        String SIDParam= null;   //加密串，json格式

        try {
//            SIDParam = URLEncoder.encode("account=admin&passwd=admin", "UTF-8");
            SIDParam = URLEncoder.encode(CurlParam.param2().toString(), "UTF-8");
            System.out.println(url+"?SID="+SID+"&SIDParam="+SIDParam);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(url+"?SID="+SID+"&SIDParam="+URLDecoder.decode(SIDParam,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            sb.append("curl -i -s -d \"SID="+SID+"&SIDParam="+SIDParam+"\" "+url);

            System.out.println("curl="+sb.toString()
            );


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
