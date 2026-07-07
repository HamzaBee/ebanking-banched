package ma.enset.ebankingbanched.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbanched.dtos.BankAccountDto;
import ma.enset.ebankingbanched.dtos.CurrentBankAccountDto;
import ma.enset.ebankingbanched.dtos.SavingBankAccountDto;
import ma.enset.ebankingbanched.entities.*;
import ma.enset.ebankingbanched.exceptions.BankAccountNotFoundException;
import ma.enset.ebankingbanched.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbanched.mappers.BankAccountMapper;
import ma.enset.ebankingbanched.repositories.BankAccountRepository;
import ma.enset.ebankingbanched.repositories.CustomerRepository;
import ma.enset.ebankingbanched.services.BankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overdraft, Long customerId) {
        log.info("Saving current bank account with customer id: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + customerId + " not found"));
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setOverDraft(overdraft);
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        CurrentAccount savedAccount = bankAccountRepository.save(currentAccount);
        return bankAccountMapper.fromCurrentAccount(savedAccount);
    }

    @Override
    public SavingBankAccountDto saveSavingAccount(double initialBalance, double interestRate, Long customerId) {
        log.info("Saving bank account with customer id: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + customerId + " not found"));
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setInterestRate(interestRate);
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        SavingAccount savedAccount = bankAccountRepository.save(savingAccount);
        return bankAccountMapper.fromSavingAccount(savedAccount);
    }

    @Override
    public BankAccountDto getBankAccount(String accountId) {
        log.info("Fetching bank account with id: {}", accountId);
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account with id: " + accountId + " not found"));
        if (bankAccount instanceof SavingAccount) {
            return bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
        } else {
            return bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
        }
    }

    @Override
    public List<BankAccountDto> bankAccountsList() {
        log.info("Listing all bank accounts");
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount) {
                return bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
            } else {
                return bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
            }
        }).toList();

    }
}
