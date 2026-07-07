package ma.enset.ebankingbanched.services;

import ma.enset.ebankingbanched.dtos.BankAccountDto;
import ma.enset.ebankingbanched.dtos.CurrentBankAccountDto;
import ma.enset.ebankingbanched.dtos.SavingBankAccountDto;
import ma.enset.ebankingbanched.exceptions.BankAccountNotFoundException;
import ma.enset.ebankingbanched.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingBankAccountDto saveSavingAccount(double initialBalance, double interestRate, Long customerId);
    BankAccountDto getBankAccount(String accountId);
    List<BankAccountDto> bankAccountsList();
}
