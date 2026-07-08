package ma.enset.ebankingbanched.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentBankAccountDto extends BankAccountDto {

    @NotNull(message = "Overdraft limit is required")
    @DecimalMin(value = "0.00", message = "Overdraft limit must be zero or a positive value")
    private BigDecimal overDraft;
}
