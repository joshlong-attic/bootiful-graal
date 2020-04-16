package com.example.traditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.DatabaseClient;
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
	ApplicationRunner runner(DatabaseClient dbc, ReservationRepository reservationRepository) {
		return args -> {

			dbc.execute("create table reservation\n" +
				"(\n" +
				"    id   serial primary key,\n" +
				"    name varchar(255) not null\n" +
				")").fetch()
				.rowsUpdated().subscribe();


			reservationRepository.save(new Reservation(null ,"Andy")).subscribe();
			reservationRepository.save(new Reservation(null ,"Sebastien")).subscribe();

			reservationRepository.findAll().subscribe(System.out::println);;
		};
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

