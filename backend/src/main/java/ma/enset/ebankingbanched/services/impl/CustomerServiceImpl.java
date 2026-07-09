package ma.enset.ebankingbanched.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbanched.dtos.CustomerDto;
import ma.enset.ebankingbanched.entities.Customer;
import ma.enset.ebankingbanched.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbanched.mappers.BankAccountMapper;
import ma.enset.ebankingbanched.repositories.CustomerRepository;
import ma.enset.ebankingbanched.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("Saving customer");
        Customer customer = bankAccountMapper.fromCustomerDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer saved successfully with id: {}", savedCustomer.getId());
        return bankAccountMapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        log.info("Listing all customers");
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(bankAccountMapper::fromCustomer)
                .toList();
    }

    @Override
    public CustomerDto getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return bankAccountMapper.fromCustomer(customer);
    }
    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        log.info("Updating customer with id: {}", customerId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer"));

        bankAccountMapper.updateCustomerFromDto(customerDto, customer);

        return bankAccountMapper.fromCustomer(customer);
    }
    @Override
    public void deleteCustomer(Long customerId) {
        log.info("Deleting customer with id: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer"));
        customerRepository.delete(customer);
        log.info("Customer deleted successfully with id: {}", customerId);
    }

    @Override
    public List<CustomerDto> searchCustomer(String keyword) {
        log.info("Searching for customers with keyword: {}", keyword);
        List<Customer> search = customerRepository.searchCustomer(keyword);
        return search.stream()
                .map(bankAccountMapper::fromCustomer)
                .toList();

    }



}
