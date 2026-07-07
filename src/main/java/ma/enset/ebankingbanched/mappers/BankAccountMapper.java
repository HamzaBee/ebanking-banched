package ma.enset.ebankingbanched.mappers;

import ma.enset.ebankingbanched.dtos.*;
import ma.enset.ebankingbanched.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    // Customer mappings
    CustomerDto fromCustomer(Customer customer);

    Customer fromCustomerDto(CustomerDto customerDto);

    // CurrentAccount mappings
    @Mapping(source = "customer", target = "customerDto")
    @Mapping(target = "type", constant = "CurrentAccount")
    CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount);

    @Mapping(source = "customerDto", target = "customer")
    CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto);

    // SavingAccount mappings
    @Mapping(source = "customer", target = "customerDto")
    @Mapping(target = "type", constant = "SavingAccount")
    SavingBankAccountDto fromSavingAccount(SavingAccount savingAccount);

    @Mapping(source = "customerDto", target = "customer")
    SavingAccount fromSavingBankAccountDto(SavingBankAccountDto savingBankAccountDto);

    // AccountOperation mapping
    AccountOperationDto fromAccountOperation(AccountOperation accountOperation);
}
