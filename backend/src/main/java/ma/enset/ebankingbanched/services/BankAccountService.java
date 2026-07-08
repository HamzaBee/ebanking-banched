package ma.enset.ebankingbanched.services;

import ma.enset.ebankingbanched.dtos.BankAccountDto;
import ma.enset.ebankingbanched.dtos.CurrentBankAccountDto;
import ma.enset.ebankingbanched.dtos.SavingBankAccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {
    CurrentBankAccountDto saveCurrentBankAccount(BigDecimal initialBalance, BigDecimal overDraft, Long customerId);
    SavingBankAccountDto saveSavingAccount(BigDecimal initialBalance, BigDecimal interestRate, Long customerId);
    BankAccountDto getBankAccount(String accountId);
    List<BankAccountDto> bankAccountsList();
}
