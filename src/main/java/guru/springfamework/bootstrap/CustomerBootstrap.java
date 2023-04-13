package guru.springfamework.bootstrap;

import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerBootstrap implements CommandLineRunner {

    CustomerRepository customerRepository;

    public CustomerBootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer1= new Customer();
        customer1.setLastname("Murali");
        customer1.setFirstname("Dereddy");

        Customer customer2= new Customer();
        customer2.setLastname("Bavisha");
        customer2.setFirstname("Dereddy");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

    }
}
