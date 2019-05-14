package pbkk.spring.tcdelivery.repository;

import pbkk.spring.tcdelivery.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
