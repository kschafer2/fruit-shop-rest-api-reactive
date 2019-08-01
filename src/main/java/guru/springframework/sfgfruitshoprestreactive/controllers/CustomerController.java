package guru.springframework.sfgfruitshoprestreactive.controllers;

import guru.springframework.sfgfruitshoprestreactive.domain.Customer;
import guru.springframework.sfgfruitshoprestreactive.repositories.CustomerRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.sfgfruitshoprestreactive.controllers.CustomerController.CUSTOMERS_BASE_URL;

@RestController
@RequestMapping(CUSTOMERS_BASE_URL)
public class CustomerController {

    public static final String CUSTOMERS_BASE_URL = "/api/v1/customers";
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createNewCustomer(@RequestBody Publisher<Customer> customerPublisher) {
        return customerRepository.saveAll(customerPublisher).then();
    }

}
