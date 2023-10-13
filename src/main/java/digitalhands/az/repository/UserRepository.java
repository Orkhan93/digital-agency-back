package digitalhands.az.repository;

import digitalhands.az.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> getUserByEmail(String email);

}