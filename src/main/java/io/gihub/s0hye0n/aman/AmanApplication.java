package io.gihub.s0hye0n.aman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmanApplication.class, args);
	}

}
