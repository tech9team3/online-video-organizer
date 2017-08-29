package rs.levi9.tech9.team3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
public class OnlineVideoOrganizerApplication {
	
	public static void main(String[] args) {
		 SpringApplication.run(OnlineVideoOrganizerApplication.class, args);
	}

}
