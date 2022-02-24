package org.example.customer;


import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("api/v1/customers")
@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) throws IllegalAccessException {
        log.info("new customer : {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }

}
