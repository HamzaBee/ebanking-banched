package ma.enset.ebankingbanched.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ma.enset.ebankingbanched.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BankAccountDto {
    private String id;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.00", message = "Balance must be zero or positive")
    private BigDecimal balance;

    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customerDto;

    public String getType() {
        return this.getClass().getSimpleName().replace("Dto", "").replace("Bank", "");
    }
}
