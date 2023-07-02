package ozturk.assignment.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ozturk.assignment.assignment.model.EnumRole;
import ozturk.assignment.assignment.model.Role;
import ozturk.assignment.assignment.repository.RoleRepository;

@SpringBootApplication
public class AssignmentApplication {

	@Autowired
	RoleRepository roleRepository;

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
		};
	}

}
