package edu.viterbi.staybooking.repository;

import edu.viterbi.staybooking.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.viterbi.staybooking.model.User;
import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    // common methods like save, deleteById, findById are defined in the JpaRepositry

    List<Stay> findByHost(User user);

    Stay findByIdAndHost(Long id, User host);

    List<Stay> findByIdInAndGuestNumberGreaterThanEqual(List<Long> ids, int guestNumber);

}
