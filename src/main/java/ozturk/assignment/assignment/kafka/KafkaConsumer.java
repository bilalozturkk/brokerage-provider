package ozturk.assignment.assignment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(String data){
        System.out.println("Listener received message: " + data);
    }
}
