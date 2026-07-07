package ma.enset.ebankingbanched.repositories;

import ma.enset.ebankingbanched.entities.AccountOperation;
import ma.enset.ebankingbanched.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
