package ma.enset.ebankingbanched.repositories;

import ma.enset.ebankingbanched.entities.BankAccount;
import ma.enset.ebankingbanched.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
