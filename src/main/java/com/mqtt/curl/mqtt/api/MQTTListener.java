/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.curl.mqtt.api;

import com.mqtt.curl.mqtt.util.CMD;
import net.sf.json.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 */
public class MQTTListener implements MqttCallback {

    private static MQTTListener instance = null;
    String broker = "tcp://mqtt.kunteng.org:1883";
    String clientId = "YunACListener";
    MemoryPersistence persistence = null;
    MqttClient sampleClient = null;
    MqttConnectOptions connOpts = null;
    ExecutorService threadPool = Executors.newFixedThreadPool(100);

    public static MQTTListener getInstance() {
        if (instance == null) {
            try {
                instance = new MQTTListener();
            } catch (MqttException ex) {
                System.err.println("{MQTTServer for MQTTListener DOWN!!!RESTART}");
            }
        }
        return instance;
    }

    private MQTTListener() throws MqttException {
        persistence = new MemoryPersistence();
        clientId = clientId + UUID.randomUUID().toString();
        sampleClient = new MqttClient(broker, clientId, persistence);
        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setKeepAliveInterval(1000);
        connOpts.setConnectionTimeout(3000);
        sampleClient.setCallback(this);
        sampleClient.connect(connOpts);
    }

    public void subscribe(String topic) throws MqttException {
        sampleClient.subscribe(topic, 2);
    }

    public void unsubscribe(String topic) throws MqttException {
        sampleClient.unsubscribe(topic);
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        System.out.println("{isConnected:" + sampleClient.isConnected() + "}");
//      logger.warn("{lost connection." + sampleClient.isConnected() + "}", thrwbl);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mm) throws Exception {
        byte[] b = mm.getPayload();
//        String result = new String(mm.getPayload());
        //$SYS/broker/connection/D4EE0721D6A0/state
//        System.out.println("From WIFI TOPIC : " + topic + "\tgetPayload size:" + b.length + "\t AND content:" + result);
        if (topic.startsWith("chaohui")) {
            final JSONObject response = JSONObject.fromObject(new String(mm.getPayload()));
            if (response.containsKey("id")) {
                final String id = response.getString("id");
                final Map<String, CMD> tmpMap = MQTTClient.getInstance().getCmdIDMap();
                if (tmpMap.containsKey(id)) {
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                MQTTClient.getInstance().getCallbackMap().get(tmpMap.get(id)).process(tmpMap.get(id), response);
                            } catch (Throwable ex) {
//                                logger.warn("threadPool:", ex);
                            } finally {
                                MQTTClient.getInstance().getCallbackMap().remove(tmpMap.get(id));
                                tmpMap.remove(id);
                                System.out.println("finally size:" + tmpMap.size());
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        System.out.println("Complate!");
    }
}
