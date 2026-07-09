package ma.enset.ebankingbanched.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbanched.dtos.CustomerDto;
import ma.enset.ebankingbanched.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDto> customerList(){
        return customerService.listCustomers();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long id){
        return customerService.getCustomer(id);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDto updateCustomer(@PathVariable Long customerId,
                                      @Valid @RequestBody CustomerDto customerDto){
        return customerService.updateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/customers/search")
    public List<CustomerDto> searchCustomer(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerService.searchCustomer("%"+ keyword +"%");
    }
}
