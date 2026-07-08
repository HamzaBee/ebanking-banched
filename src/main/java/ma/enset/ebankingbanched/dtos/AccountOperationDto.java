package ma.enset.ebankingbanched.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ma.enset.ebankingbanched.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountOperationDto {
    private Long id;
    private Date operationDate;

    @NotNull(message = "Operation amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Operation amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Operation type is required")
    private OperationType type;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
}
