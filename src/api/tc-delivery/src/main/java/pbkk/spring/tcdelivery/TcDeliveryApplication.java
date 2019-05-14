package pbkk.spring.tcdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("pbkk.spring.tcdelivery.repository")
@SpringBootApplication
@EnableJpaAuditing
public class TcDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcDeliveryApplication.class, args);
	}

}
