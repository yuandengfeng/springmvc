package tool.rabbitMQ;

import com.rabbitmq.client.*;
import tool.mail.Mail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Reqv {

//    private final static String QUEUE_NAME = "hello";
      private final static String QUEUE_NAME = "TT";
      private static final String EXCHANGE_NAME = "channel_path";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.20.84");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//     ===================================================
//        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "fanout");
//       ============================================
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        List<InternetAddress> inter = new ArrayList<InternetAddress>();
        try {
            inter.add(new InternetAddress("yuandengfeng@kunteng.org"));
            inter.add(new InternetAddress("dongqiang@kunteng.org"));
            inter.add(new InternetAddress("shiyun@kunteng.org"));
        } catch (AddressException e) {
            e.printStackTrace();
        }
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            if(!message.isEmpty())
            {
                Mail.sendEmailTo(inter, "channel_path is null",message);
                System.out.println(" [x] Received '" + message + "'");
            }

        }
    }

}
