/*
 * 模拟路由器返回消息
 *
 */
package com.mqtt.curl;

import com.mqtt.curl.mqtt.util.Base64Util;
import com.mqtt.curl.mqtt.util.CMD;
import com.mqtt.curl.mqtt.util.ICMDCallback;
import net.sf.json.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Administrator
 * 模拟路由器返回消息
 */
public class MQTTChaoHuiRouter implements MqttCallback {

    static Logger logger = LoggerFactory.getLogger(MQTTChaoHuiRouter.class);
    private static MQTTChaoHuiRouter instance = null;
//  String broker = "tcp://192.168.20.83:1883";
    String broker = "tcp://mqtt.kunteng.org:1883";
    String clientId = "YunACClient";
    MemoryPersistence persistence = null;
    MqttClient sampleClient = null;
    ExecutorService threadPool = Executors.newFixedThreadPool(1000);
    private final Map<CMD, ICMDCallback> callbackMap = new HashMap<>();
    private final Map<String, CMD> cmdIDMap = new HashMap<>();

    public static MQTTChaoHuiRouter getInstance() {
        if (instance == null) {
            try {
                instance = new MQTTChaoHuiRouter();
            } catch (MqttException ex) {
                logger.error("{MQTTServer for MQTTClient DOWN!!!RESTART}");
                ex.printStackTrace();
            }
        }
        return instance;
    }

    public MQTTChaoHuiRouter() throws MqttException {
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

        subscribe("ShiYun/+/+/");
    }

    public void subscribe(String topic) throws MqttException {
        System.out.println("订阅：" + topic);
        sampleClient.subscribe(topic, 0);
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

    @Override
    public void connectionLost(Throwable cause) {
        cause.printStackTrace();
        System.out.println("{isConnected:" + sampleClient.isConnected() + "}");
        logger.info("{isConnected:" + sampleClient.isConnected() + "}");
        while (!sampleClient.isConnected()) {
            boolean flag = true;
            try {
                System.out.println("正在重新连接...");
                connect();
                System.out.println("连接成功...");
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
        logger.info("{isConnected:" + sampleClient.isConnected() + "}");
    }

//   接收客户端发送的消息，并作出相应的返回
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        byte[] b = message.getPayload();
        System.out.println("From WIFI TOPIC : " + topic + "\tgetPayload size:" + b.length);
        if (topic.startsWith("ShiYun")) {

            final JSONObject response = JSONObject.fromObject(new String(message.getPayload()));
            if (response.containsKey("id")) {
                final String id = response.getString("id");
                String content = response.getString("content");
                final String jiexi = new String(Base64Util.decode(content));
                System.out.println("cont:" + jiexi);
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (jiexi.contains("SID=1")) {//开发票
                                send("chaohui/ShiYun/CMD_EXE/", getBill(id));
                            }
                            if (jiexi.contains("SID=2")) {//打印发票
                                send("chaohui/ShiYun/CMD_EXE/", getPrint(id));
                            }
                        } catch (Exception e) {
                        }
                    }
                });
            }

        }
    }

    public String getBill(String id) {
        String str = "{\n"
                + "  \"id\":\"" + id + "\",\n"
                + "  \"item\":\"script\",\n"
                + "  \"log_stdout\":\"%7B%22ENCMSG%22%3A%7B%22SID%22%3A%221%22%2C%22infoDate%22%3A%222016-11-16%2019%3A14%3A04%22%2C%22retcode%22%3A%220%22%2C%22infoTaxAmount%22%3A%225.66%22%2C%22retmsg%22%3A%22%E5%BC%80%E5%85%B7%E6%88%90%E5%8A%9F%EF%BC%81%22%2C%22invInfo%22%3A%7B%22kpfwqh%22%3A-1%2C%22invMcType%22%3A0%2C%22machineNo%22%3A0%2C%22taxCode%22%3A%2291420100587995077E%22%2C%22taxClock%22%3A%222016-11-16%22%2C%22checkCode%22%3A%22%22%2C%22isRepReached%22%3A0%2C%22invKind%22%3A2%2C%22fphm%22%3A%2208916716%22%2C%22fpdm%22%3A%224200154320%22%2C%22isLockReached%22%3A0%2C%22invStock%22%3A24%7D%2C%22fphm%22%3A%2208916715%22%2C%22fpdm%22%3A%224200154320%22%2C%22infoAmount%22%3A%2294.34%22%2C%22goodsListFlag%22%3A0%7D%7D      \",\n"
                + "  \"log_stderr\":\"\",\n"
                + "  \"state\":\"0\"\n"
                + "}";
        return str;
    }

    public String getPrint(String id) {
        String str = "{\n"
                + "  \"id\":\"" + id + "\",\n"
                + "  \"item\":\"script\",\n"
                + "  \"log_stdout\":\"%7B%22ENCMSG%22%3A%7B%22SID%22%3A%222%22%2C%22retcode%22%3A%220%22%2C%22retmsg%22%3A%22%E6%89%93%E5%8D%B0%E6%88%90%E5%8A%9F%EF%BC%81%22%7D%7D\",\n"
                + "  \"log_stderr\":\"\",\n"
                + "  \"state\":\"0\"\n"
                + "}";
        return str;
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("finished!!");
    }

    public static void main(String[] args) {

        MQTTChaoHuiRouter.getInstance();

    }
}
