package ma.enset.ebankingbanched.dtos;

import lombok.Data;
import ma.enset.ebankingbanched.enums.AccountStatus;
import java.util.Date;

@Data
public class BankAccountDto {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customerDto;
    private String type;
}
