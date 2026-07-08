package ma.enset.ebankingbanched.services;


import ma.enset.ebankingbanched.dtos.AccountHistoryDTO;
import ma.enset.ebankingbanched.dtos.AccountOperationDto;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {
    void debit(String accountId, BigDecimal amount, String description);
    void credit(String accountId, BigDecimal amount, String description);
    void transfer(String accountIdSource, String accountIdDestination, BigDecimal amount, String description);

    AccountHistoryDTO accountHistory(String accountId, int page, int size);
}
