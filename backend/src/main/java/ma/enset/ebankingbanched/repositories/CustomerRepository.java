package ma.enset.ebankingbanched.repositories;

import ma.enset.ebankingbanched.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
