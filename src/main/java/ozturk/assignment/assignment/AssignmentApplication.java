package ozturk.assignment.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import ozturk.assignment.assignment.model.EnumRole;
import ozturk.assignment.assignment.model.Role;
import ozturk.assignment.assignment.model.User;
import ozturk.assignment.assignment.repository.RoleRepository;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class AssignmentApplication {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${ozturk.topic.name}")
	private String topicName;



	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	CommandLineRunner createDefaultRecords (){
		return args -> {
			if(!roleRepository.existsByName(EnumRole.ROLE_USER))
				roleRepository.save(new Role(EnumRole.ROLE_USER));
			if(!roleRepository.existsByName(EnumRole.ROLE_ADMIN))
				roleRepository.save(new Role(EnumRole.ROLE_ADMIN));

			for(int i=0;i<100;i++) {
				String message = "Hello Kafka - " + i;
				CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
				future.whenComplete((result, ex) -> {
					if (ex == null) {
						System.out.println("Sent message=[" + message +
								"] with offset=[" + result.getRecordMetadata().offset() + "]");
					} else {
						System.out.println("Unable to send message=[" +
								message + "] due to : " + ex.getMessage());
					}
				});
			}
		};
	}

}
