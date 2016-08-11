package com.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MqttClient {

    public void client(){

        String[] topic = {"1232123000/YunAC/+"};
        int[] qos = {2};
        String broker = "tcp://192.168.1.60:1883";
        String clientId = "JavaSample2";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            org.eclipse.paho.client.mqttv3.MqttClient sampleClient = new org.eclipse.paho.client.mqttv3.MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();
            sampleClient.setCallback(simpleCallbackHandler);
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            sampleClient.subscribe(topic, qos);
//            sampleClient.disconnect();
//            System.out.println("Disconnected");
//            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

}
