package tool.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Main2 {

    private final static String QUEUE_NAME = "wifitest";
    private static final String EXCHANGE_NAME ="connectionevent";

    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.20.78");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//        String message = "{\"date\":\"2016 Sep 02 09:01:00\",\"routerip\":\"192.168.96.201\",\"routermac\":\"D4EE074802A6\",\"channelpath\":\"1_POA_W4W\",\"program\":\"kt-macscan\",\"unixtime\":\"1472714987\",\"msg_kunteng\":{\"evttype\":\"connection\",\"mac\":\"C81EE7C9C7FC\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.40\",\"iptype\":\"dhcp\"},\"evttype\":\"connection\",\"mac\":\"C81EE7C9C7FC\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.40\",\"iptype\":\"dhcp\"}";
        String message = "{\"date\":\"2016 Sep 02 10:00:00\",\"routerip\":\"192.168.96.201\",\"routermac\":\"D4EE074802A6\",\"channelpath\":\"1_POA_W4W\",\"program\":\"kt-macscan\",\"unixtime\":\"1472714987\",\"msg_kunteng\":{\"evttype\":\"connection\",\"mac\":\"C81EE7C9C7FC\",\"evt\":\"down\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.40\",\"iptype\":\"dhcp\"},\"evttype\":\"connection\",\"mac\":\"C81EE7C9C7FC\",\"evt\":\"down\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.40\",\"iptype\":\"dhcp\"}";
//        String message = "{\"date\":\"2016 Sep 02 09:01:00\",\"routerip\":\"192.168.96.201\",\"routermac\":\"D4EE074802A6\",\"channelpath\":\"1_POA_W4W\",\"program\":\"kt-macscan\",\"unixtime\":\"1472714987\",\"msg_kunteng\":{\"evttype\":\"connection\",\"mac\":\"6C72E742B2BB\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.41\",\"iptype\":\"dhcp\"},\"evttype\":\"connection\",\"mac\":\"6C72E742B2BB\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.41\",\"iptype\":\"dhcp\"}";
//        String message = "{\"date\":\"2016 Sep 02 00:01:00\",\"routerip\":\"192.168.96.201\",\"routermac\":\"D4EE074802A6\",\"channelpath\":\"1_POA_W4W\",\"program\":\"kt-macscan\",\"unixtime\":\"1472714987\",\"msg_kunteng\":{\"evttype\":\"connection\",\"mac\":\"F431C3DA6C44\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.42\",\"iptype\":\"dhcp\"},\"evttype\":\"connection\",\"mac\":\"F431C3DA6C44\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.43\",\"iptype\":\"dhcp\"}";
//        String message = "{\"date\":\"2016 Sep 02 00:01:00\",\"routerip\":\"192.168.96.201\",\"routermac\":\"D4EE074802A6\",\"channelpath\":\"1_POA_W4W\",\"program\":\"kt-macscan\",\"unixtime\":\"1472714987\",\"msg_kunteng\":{\"evttype\":\"connection\",\"mac\":\"c825e15730e0\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.43\",\"iptype\":\"dhcp\"},\"evttype\":\"connection\",\"mac\":\"c825e15730e0\",\"evt\":\"up\",\"type\":\"wifi\",\"name\":\"iPhone\",\"rpt\":\"false\",\"type_wifi\":\"5G\",\"signal\":\"100\",\"ip\":\"10.1.1.43\",\"iptype\":\"dhcp\"}";
        channel.basicPublish(EXCHANGE_NAME, "ktmsg.core.connection.enterprise.POA.W4W", null, message.getBytes());


        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }



}


