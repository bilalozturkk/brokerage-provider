package ozturk.assignment.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ozturk.assignment.assignment.kafka.KafkaProducer;

@RestController
@RequestMapping(value = "/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducerService;

    @Value("{ozturk.topic.name}")
    private String topicName;

    @PostMapping("/produce")
    public ResponseEntity<String> sendMessage(@RequestBody String message)
    {
        for(int i=0;i<100;i++) {
            kafkaProducerService.sendMessageToKafka(
                    topicName,
                    message + " " + i
            );
        }
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}