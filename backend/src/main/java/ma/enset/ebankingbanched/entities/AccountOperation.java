package ma.enset.ebankingbanched.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.ebankingbanched.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    @Column(precision = 19, scale = 4)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;


}
