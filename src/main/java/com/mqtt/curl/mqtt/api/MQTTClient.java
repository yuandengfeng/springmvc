/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mqtt.curl.mqtt.api;


import com.mqtt.curl.mqtt.util.CMD;
import com.mqtt.curl.mqtt.util.ICMDCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
public class MQTTClient {

    private static MQTTClient instance = null;

    private final Map<CMD, ICMDCallback> callbackMap = new HashMap<>();
    private final Map<String, CMD> cmdIDMap = new HashMap<>();

    String broker = "tcp://mqtt.kunteng.org:1883";
    String clientId = "YunACClient";

    MemoryPersistence persistence = null;

    MqttClient sampleClient = null;


    public static MQTTClient getInstance() {
        if (instance == null) {
            try {
                instance = new MQTTClient();
            } catch (MqttException ex) {
                System.err.println("{MQTTServer for MQTTClient DOWN!!!RESTART}");
            }
        }
        return instance;
    }

    private MQTTClient() throws MqttException {
        persistence = new MemoryPersistence();
        clientId = clientId+UUID.randomUUID().toString();
        sampleClient = new MqttClient(broker, clientId, persistence);

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setKeepAliveInterval(1000);
        connOpts.setConnectionTimeout(3000);
        sampleClient.connect(connOpts);
    }


    public void send(CMD cmd, ICMDCallback callback) throws MqttException {
        MqttMessage message = new MqttMessage(CMD.toPString(cmd).getBytes());
        message.setQos(2);
        callbackMap.put(cmd, callback);
        cmdIDMap.put(cmd.getId(), cmd);
        sampleClient.publish(cmd.getTopic(), message);
    }

    public void send(String topoc, String msg) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        sampleClient.publish(topoc, message);
    }

    public Map<CMD, ICMDCallback> getCallbackMap() {
        return callbackMap;
    }

    public Map<String, CMD> getCmdIDMap() {
        return cmdIDMap;
    }
}
