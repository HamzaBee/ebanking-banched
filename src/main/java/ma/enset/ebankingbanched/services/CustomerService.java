package ma.enset.ebankingbanched.services;

import ma.enset.ebankingbanched.dtos.CustomerDto;


import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);
    List<CustomerDto> listCustomers();
    CustomerDto getCustomer(Long customerId);

    CustomerDto updateCustomer(Long customerId,CustomerDto customerDto);

    void deleteCustomer(Long customerId);
}
