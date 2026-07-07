package ma.enset.ebankingbanched.services;

import ma.enset.ebankingbanched.entities.BankAccount;
import ma.enset.ebankingbanched.entities.CurrentAccount;
import ma.enset.ebankingbanched.entities.Customer;
import ma.enset.ebankingbanched.entities.SavingAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId);
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    List<BankAccount> bankAccountsList();
    void transfer(String accountIdSource, String accountIdDestination, double amount, String description);

}
