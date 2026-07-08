package ma.enset.ebankingbanched.mappers;

import ma.enset.ebankingbanched.dtos.*;
import ma.enset.ebankingbanched.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    // Customer mappings
    CustomerDto fromCustomer(Customer customer);

    Customer fromCustomerDto(CustomerDto customerDto);
    @Mapping(target = "id", ignore = true)
    void updateCustomerFromDto(CustomerDto dto, @MappingTarget Customer customer);

    // CurrentAccount mappings
    @Mapping(source = "customer", target = "customerDto")
    CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount);

//    @Mapping(source = "customerDto", target = "customer")
//    CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto);

    // SavingAccount mappings
    @Mapping(source = "customer", target = "customerDto")
    SavingBankAccountDto fromSavingAccount(SavingAccount savingAccount);

//    @Mapping(source = "customerDto", target = "customer")
//    SavingAccount fromSavingBankAccountDto(SavingBankAccountDto savingBankAccountDto);

    AccountOperationDto fromAccountOperation(AccountOperation accountOperation);



}
