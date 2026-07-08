package ma.enset.ebankingbanched.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount extends BankAccount{
    @Column(precision = 19, scale = 4)
    private BigDecimal interestRate;
}
