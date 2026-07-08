package ma.enset.ebankingbanched.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbanched.dtos.AccountOperationDto;
import ma.enset.ebankingbanched.entities.AccountOperation;
import ma.enset.ebankingbanched.entities.BankAccount;
import ma.enset.ebankingbanched.enums.OperationType;
import ma.enset.ebankingbanched.exceptions.BalanceNotSufficentException;
import ma.enset.ebankingbanched.exceptions.BankAccountNotFoundException;
import ma.enset.ebankingbanched.mappers.BankAccountMapper;
import ma.enset.ebankingbanched.repositories.AccountOperationRepository;
import ma.enset.ebankingbanched.repositories.BankAccountRepository;
import ma.enset.ebankingbanched.services.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class OperationServiceImpl implements OperationService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountOperationRepository accountOperationRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public void debit(String accountId, BigDecimal amount, String description) {
        log.info("Debiting {} from account {}", amount, accountId);
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account with id: " + accountId + " not found"));
        if (bankAccount.getBalance().compareTo(amount) < 0) {
            throw new BalanceNotSufficentException("Insufficient balance for account with id: " + accountId);
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, BigDecimal amount, String description) {
        log.info("Crediting {} to account {}", amount, accountId);
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account with id: " + accountId + " not found"));
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, BigDecimal amount, String description) {
        log.info("Transferring {} from account {} to account {}", amount, accountIdSource, accountIdDestination);
        debit(accountIdSource, amount, description);
        credit(accountIdDestination, amount, description);
    }

    @Override
    public List<AccountOperationDto> accountHistory(String accountId) {
       List<AccountOperation> historyByAccId = accountOperationRepository.findByBankAccountId(accountId);
       return historyByAccId.stream()
                .map(bankAccountMapper::fromAccountOperation).toList();
    }
}
