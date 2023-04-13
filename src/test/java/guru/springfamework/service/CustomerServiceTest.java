package guru.springfamework.service;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest extends TestCase {


    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        customerService= new CustomerServiceImpl(customerRepository);
    }
    @Test
    public void testGetCustomers() {
        Customer customer= new Customer();
        customer.setFirstname("D");

        Customer customer1= new Customer();
        customer1.setFirstname("M");

        List<Customer> customers= Arrays.asList(customer,customer1);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(2,customerService.getCustomers().size());
    }

    @Test
    public void testFindByLastname() {

        Customer customer= new Customer();
        customer.setFirstname("D");
        Mockito.when(customerRepository.findBylastname(ArgumentMatchers.anyString())).thenReturn(customer);
        assertEquals("D",customerService.findByLastname("F").getFirstname());
    }

    @Test
    public void testSaveCustomer(){
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setFirstname("D");
        customerDTO.setLastname("M");

        Customer customer= new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO returnedCustomer = customerService.saveCustomer(customerDTO);
        assertEquals(customerDTO.getFirstname(),returnedCustomer.getFirstname());
    }
    @Test
    public void deleteCustomer(){
        Long id=1l;
        customerRepository.deleteById(id);
        verify(customerRepository,times(1)).deleteById(anyLong());
    }
}
