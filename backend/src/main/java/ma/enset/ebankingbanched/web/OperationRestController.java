package ma.enset.ebankingbanched.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.ebankingbanched.dtos.*;
import ma.enset.ebankingbanched.services.OperationService;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class OperationRestController {
    private final OperationService operationService;

    @PostMapping("/accounts/debit")
    public DebitRequestDto debit(@Valid @RequestBody DebitRequestDto debitRequestDto) {
        operationService.debit(
            debitRequestDto.accountId(),
            debitRequestDto.amount(),
            debitRequestDto.description()
        );
        return debitRequestDto;
    }

    @PostMapping("/accounts/credit")
    public CreditRequestDto credit(@Valid @RequestBody CreditRequestDto creditRequestDto) {
        operationService.credit(
            creditRequestDto.accountId(),
            creditRequestDto.amount(),
            creditRequestDto.description()
        );
        return creditRequestDto;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@Valid @RequestBody TransferRequestDto transferRequestDto) {
        operationService.transfer(
            transferRequestDto.accountIdSource(),
            transferRequestDto.accountIdDestination(),
            transferRequestDto.amount(),
            transferRequestDto.description()
        );
    }

    @GetMapping({"/accounts/{accountId}/pageOperations", "/accounts/{accountId}/operations"})
    public AccountHistoryDTO accountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return operationService.accountHistory(accountId, page, size);
    }
}
