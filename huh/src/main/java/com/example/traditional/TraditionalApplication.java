package com.example.traditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication(proxyBeanMethods = false)
public class TraditionalApplication {

	@Bean
	RouterFunction<ServerResponse> routes(ReservationRepository rr) {
		return route()
			.GET("/reservations", r -> ok().body(rr.findAll(), Reservation.class))
			.build();
	}

	@Bean
	ApplicationRunner runner(ReservationRepository reservationRepository) {
		return args -> reservationRepository.findAll().subscribe(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(TraditionalApplication.class, args);
	}
}

interface ReservationRepository extends ReactiveCrudRepository<Reservation, Integer> {

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Reservation {

	@Id
	private Integer id;
	private String name;
}

