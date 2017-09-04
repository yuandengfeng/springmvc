package tool.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Main1 {

//    private final static String QUEUE_NAME = "wifitest";
      private final static String QUEUE_NAME = "yuantest";

//    private static final String EXCHANGE_NAME = "kt.macscan";
     private static final String EXCHANGE_NAME ="connectionevent";
    public static void main(String[] args) throws IOException, InterruptedException {



        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.20.78");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null); //申明一个新的queue

//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "fanout");

//        String QUEUE_NAME = channel.queueDeclare().getQueue();
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "ktmsg.core.connection.enterprise.POA.W4W");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }

    }

}
