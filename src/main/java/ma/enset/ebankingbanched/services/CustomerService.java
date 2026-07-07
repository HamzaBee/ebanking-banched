package ma.enset.ebankingbanched.services;

import ma.enset.ebankingbanched.dtos.CustomerDto;
import ma.enset.ebankingbanched.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);
    List<CustomerDto> listCustomers();
    CustomerDto getCustomer(Long customerId);
}
