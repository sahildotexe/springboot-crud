package me.sahil.contentcalender;

import me.sahil.contentcalender.model.Content;
import me.sahil.contentcalender.model.Status;
import me.sahil.contentcalender.model.Type;
import me.sahil.contentcalender.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class , args);
	}
    @Bean
	CommandLineRunner commandLineRunner(ContentRepository repository) {
		return args -> {
			Content content = new Content(null,
					"first post",
					"yo! my first blog post",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					""
			);

			repository.save(content);
		};
	}
}
