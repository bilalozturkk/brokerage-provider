package ozturk.assignment.assignment.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ozturk.assignment.assignment.model.Message;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessageToKafka(String topicName, Message message){
        CompletableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
