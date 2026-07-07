package ma.enset.ebankingbanched.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbanched.dtos.CustomerDto;
import ma.enset.ebankingbanched.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDto> customerList(){
        return customerService.listCustomers();
    }
    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }
    @GetMapping("customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long id){
        return customerService.getCustomer(id);
    }
}
