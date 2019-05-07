package pbkk.spring.tcdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan("pbkk.spring")
@SpringBootApplication
@EnableJpaAuditing
public class TcDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcDeliveryApplication.class, args);
	}

}
