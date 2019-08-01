package guru.springframework.sfgfruitshoprestreactive.bootstrap;

import guru.springframework.sfgfruitshoprestreactive.domain.Category;
import guru.springframework.sfgfruitshoprestreactive.domain.Customer;
import guru.springframework.sfgfruitshoprestreactive.repositories.CategoryRepository;
import guru.springframework.sfgfruitshoprestreactive.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository) {

        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count().block() == 0) {
            loadCategories();
        }

        if(customerRepository.count().block() == 0) {
            loadCustomers();
        }
    }

    private void loadCategories() {
        System.out.println("### LOADING CATEGORIES ON BOOTSTRAP ###");

        categoryRepository.save(Category.builder()
                .name("Bananas").build()).block();

        categoryRepository.save(Category.builder()
                .name("Oranges").build()).block();

        categoryRepository.save(Category.builder()
                .name("Apples").build()).block();

        categoryRepository.save(Category.builder()
                .name("Mangoes").build()).block();

        categoryRepository.save(Category.builder()
                .name("Kiwis").build()).block();

        System.out.println("Loaded Categories Count: " +
                            categoryRepository.count().block());
    }

    private void loadCustomers() {
        System.out.println("### LOADING DATA ON BOOTSTRAP ###");

        customerRepository.save(Customer.builder()
                .firstName("Frank")
                .lastName("Ocean").build()).block();

        customerRepository.save(Customer.builder()
                .firstName("Albert")
                .lastName("Einstein").build()).block();

        customerRepository.save(Customer.builder()
                .firstName("George")
                .lastName("Orwell").build()).block();

        customerRepository.save(Customer.builder()
                .firstName("Natalie")
                .lastName("Portman").build()).block();

        customerRepository.save(Customer.builder()
                .firstName("Mr.")
                .lastName("Rogers").build()).block();

        System.out.println("Loaded Customers Count: " +
                customerRepository.count().block());
    }
}
