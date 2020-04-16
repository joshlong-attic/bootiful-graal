package app.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication(proxyBeanMethods = false)
public class SampleApplication {

	@Bean
	public CommandLineRunner runner(ReservationRepository repository) {
		return args -> {
			Stream
				.of("Andy", "Sebastien", "Josh")
				.map(r -> new Reservation(null, r))
				.forEach(repository::save);
			repository.findAll().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

}

