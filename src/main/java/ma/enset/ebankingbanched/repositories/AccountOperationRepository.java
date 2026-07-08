package ma.enset.ebankingbanched.repositories;

import ma.enset.ebankingbanched.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
