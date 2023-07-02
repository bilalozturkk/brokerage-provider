package ozturk.assignment.assignment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ozturk.assignment.assignment.model.Message;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(Message data){
        System.out.println("Listener received message: " + data);
    }
}
