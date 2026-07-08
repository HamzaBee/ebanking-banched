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
    @Mapping(target = "type", constant = "CurrentAccount")
    CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount);


    // SavingAccount mappings
    @Mapping(source = "customer", target = "customerDto")
    @Mapping(target = "type", constant = "SavingAccount")
    SavingBankAccountDto fromSavingAccount(SavingAccount savingAccount);





}
