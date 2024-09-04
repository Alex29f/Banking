package prime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prime.models.BankAccount;
import prime.models.User;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByUser(User user);
}