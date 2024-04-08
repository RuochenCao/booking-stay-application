package edu.viterbi.staybooking.repository;

import edu.viterbi.staybooking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.viterbi.staybooking.model.Stay;
import edu.viterbi.staybooking.model.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGuest(User guest);

    List<Reservation> findByStay(Stay stay);

    Reservation findByIdAndGuest(Long id, User guest); // for deletion

    List<Reservation> findByStayAndCheckoutDateAfter(Stay stay, LocalDate date);

}

