package app.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
class ReservationRestController {

	private final ReservationRepository reservationRepository;

	ReservationRestController(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@GetMapping("/reservations")
	Collection<Reservation> get() {
		return this.reservationRepository.findAll();
	}
}
