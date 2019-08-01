package guru.springframework.sfgfruitshoprestreactive.controllers;

import guru.springframework.sfgfruitshoprestreactive.domain.Customer;
import guru.springframework.sfgfruitshoprestreactive.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.sfgfruitshoprestreactive.controllers.CustomerController.CUSTOMERS_BASE_URL;
import static org.mockito.BDDMockito.given;

public class CustomerControllerTest {

    public static final String ID = "id";
    CustomerRepository customerRepository;
    CustomerController customerController;
    WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerController = new CustomerController(customerRepository);
        webTestClient = WebTestClient.bindToController(customerController).build();
    }

    @Test
    public void getAllCustomers() {
        given(customerRepository.findAll())
                .willReturn(Flux.just(new Customer(), new Customer()));

        webTestClient.get()
                .uri(CUSTOMERS_BASE_URL)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class)
                .hasSize(2);
    }

    @Test
    public void getCustomerById() {
        given(customerRepository.findById(ID))
                .willReturn(Mono.just(new Customer()));

        webTestClient.get()
                .uri(CUSTOMERS_BASE_URL + "/" + ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class);
    }
}