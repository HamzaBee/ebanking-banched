package ma.enset.ebankingbanched;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbanched.dtos.CustomerDto;
import ma.enset.ebankingbanched.entities.AccountOperation;
import ma.enset.ebankingbanched.enums.AccountStatus;
import ma.enset.ebankingbanched.entities.CurrentAccount;
import ma.enset.ebankingbanched.entities.Customer;
import ma.enset.ebankingbanched.entities.SavingAccount;
import ma.enset.ebankingbanched.enums.OperationType;
import ma.enset.ebankingbanched.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbanched.repositories.AccountOperationRepository;
import ma.enset.ebankingbanched.repositories.BankAccountRepository;
import ma.enset.ebankingbanched.repositories.CustomerRepository;
import ma.enset.ebankingbanched.services.BankAccountService;
import ma.enset.ebankingbanched.services.CustomerService;
import ma.enset.ebankingbanched.services.OperationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class EbankingBanchedApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBanchedApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService,
                                        BankAccountService bankAccountService,
                                        OperationService operationService) {
        return args -> {
            Stream.of("Hassan", "Imane", "Mohamed").forEach(name -> {
                CustomerDto customer = new CustomerDto(null, name, name + "@gmail.com");
                customerService.saveCustomer(customer);
            });
            customerService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(BigDecimal.valueOf(Math.random() * 90000), BigDecimal.valueOf(9000), customer.id());
                    bankAccountService.saveSavingAccount(BigDecimal.valueOf(Math.random() * 120000), BigDecimal.valueOf(5.5), customer.id());
                } catch (CustomerNotFoundException e) {
                    log.error("Failed to create accounts for customer {}: {}", customer.id(), e.getMessage());
                }
            });
            bankAccountService.bankAccountsList().forEach(bankAccount -> {
                for(int i=0; i < 10; i++){
                    operationService.credit(bankAccount.getId(), BigDecimal.valueOf(10000 + Math.random() * 12000), "credit");
                    operationService.debit(bankAccount.getId(), BigDecimal.valueOf(1000 + Math.random() * 9000), "Debit");
                }
            });
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Hamza", "Mehdi", "Younes").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(BigDecimal.valueOf(Math.random() * 90000));
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(BigDecimal.valueOf(9000));
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(BigDecimal.valueOf(Math.random() * 80000));
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(BigDecimal.valueOf(5.5));
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(BigDecimal.valueOf(Math.random() * 12000));
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(account);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}
