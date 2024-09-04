package prime.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import prime.models.User;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User ,Long> {
    Optional<User> findByEmail(String email);
}
