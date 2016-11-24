/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.curl.mqtt.api;

import com.mqtt.curl.mqtt.util.CMD;
import com.mqtt.curl.mqtt.util.ICMDCallback;
import net.sf.json.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author Administrator
 *
 * 增加了重连机制
 */
public class MQTTCli implements MqttCallback {

    private static MQTTCli instance = null;
//  String broker = "tcp://192.168.20.83:1883";
    String broker = "tcp://mqtt.kunteng.org:1883";
    String clientId = "YunACClient";
    MemoryPersistence persistence = null;
    MqttClient sampleClient = null;
    ExecutorService threadPool = Executors.newFixedThreadPool(1000);
    private final Map<CMD, ICMDCallback> callbackMap = new HashMap<>();
    private final Map<String, CMD> cmdIDMap = new HashMap<>();

    public static MQTTCli getInstance() {
        if (instance == null) {
            try {
                instance = new MQTTCli();
            } catch (MqttException ex) {

                System.out.println(ex.toString());
            }
        }
        return instance;
    }

    public MQTTCli() throws MqttException {
        persistence = new MemoryPersistence();
        clientId = clientId + UUID.randomUUID().toString();
        sampleClient = new MqttClient(broker, clientId, persistence);
        connect();
    }

    private void connect() throws MqttException {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setKeepAliveInterval(1000);
        connOpts.setConnectionTimeout(3000);
        sampleClient.setCallback(this);
        sampleClient.connect(connOpts);

        subscribe("chaohui/+/+/");
    }

    public void subscribe(String topic) throws MqttException {
        sampleClient.subscribe(topic, 0);
    }

    public void send(CMD cmd, ICMDCallback callback) throws MqttException {
        MqttMessage message = new MqttMessage(CMD.toPString(cmd).getBytes());
        message.setQos(2);
        callbackMap.put(cmd, callback);
        cmdIDMap.put(cmd.getId(), cmd);
        sampleClient.publish(cmd.getTopic(), message);
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("{isConnected:" + sampleClient.isConnected() + "}");
        while (!sampleClient.isConnected()) {
            boolean flag = true;
            try {
                System.out.println("正在重新连接...");
                connect();
                System.out.println("重连成功...");
                flag = false;
            } catch (MqttException e) {
                e.printStackTrace();
            }
            if (flag) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        byte[] b = message.getPayload();
//        String result = new String(mm.getPayload());
        //$SYS/broker/connection/D4EE0721D6A0/state
//        System.out.println("From WIFI TOPIC : " + topic + "\tgetPayload size:" + b.length + "\t AND content:" + result);
        if (topic.startsWith("chaohui")) {
            final JSONObject response = JSONObject.fromObject(new String(message.getPayload()));
            if (response.containsKey("id")) {
                final String id = response.getString("id");
                if (cmdIDMap.containsKey(id)) {
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callbackMap.get(cmdIDMap.get(id)).process(cmdIDMap.get(id), response);
                            } catch (Throwable ex) {
//                                logger.warn("threadPool:", ex);
                            } finally {
                                callbackMap.remove(cmdIDMap.get(id));
                                cmdIDMap.remove(id);
                                System.out.println("finally size:" + cmdIDMap.size());
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("finished!!");
    }
}
