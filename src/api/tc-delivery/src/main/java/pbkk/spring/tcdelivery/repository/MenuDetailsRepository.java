package pbkk.spring.tcdelivery.repository;
import pbkk.spring.tcdelivery.model.MenuDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MenuDetailsRepository extends JpaRepository<MenuDetails, Long> {
}
