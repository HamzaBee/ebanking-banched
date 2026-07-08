package ma.enset.ebankingbanched.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransferRequestDto(
    @NotBlank(message = "Source account ID is required")
    String accountIdSource,

    @NotBlank(message = "Destination account ID is required")
    String accountIdDestination,

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    BigDecimal amount,

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String description
) {}

