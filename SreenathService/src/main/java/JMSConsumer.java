import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {
  
  public static void main(String[] args) {
    
      String url = "tcp://localhost:61616";
      ConnectionFactory factory = new ActiveMQConnectionFactory(url);
      try {
          Connection connection = factory.createConnection();
           Session session = connection.createSession(false,
               Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("VirtualTopic.HotelStayControlEvent_v1");
            MessageConsumer consumer = session.createConsumer(topic);
            JMSMessageListener listener = new JMSMessageListener();
            consumer.setMessageListener(listener);
            connection.start();
      }
      catch(JMSException exp) {
      }
  }
}

//JMS Message Listener
class JMSMessageListener implements MessageListener {
@Override
  public void onMessage(javax.jms.Message msg) {
      System.out.println(msg.toString());
  }
}
