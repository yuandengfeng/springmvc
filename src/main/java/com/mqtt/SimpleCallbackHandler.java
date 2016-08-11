/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt;

import net.sf.json.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Administrator
 */
public class SimpleCallbackHandler implements MqttCallback {

    @Override
    public void connectionLost(Throwable thrwbl) {
        thrwbl.printStackTrace();
    }

    @Override
    public void messageArrived(String topicName, MqttMessage mm) throws Exception {
        System.out.println("From YunAC TOPIC:"+topicName);
        System.out.println(JSONObject.fromObject(new String(mm.getPayload())).toString(4));

        JSONObject cmdObject = JSONObject.fromObject(new String(mm.getPayload()));
        String id = cmdObject.getString("id");
        JSONObject cmdResponse = new JSONObject();
        cmdResponse.put("id",id);
        cmdResponse.put("item", cmdObject.getString("item"));
        cmdResponse.put("state","0");
        cmdResponse.put("log", "");
        String cmd = topicName.split("/")[2];
        
//        MQTTClient.getInstance().send("YunAC/1232123000/"+cmd, cmdResponse.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {

        System.out.println("Complete!");
    }
}
