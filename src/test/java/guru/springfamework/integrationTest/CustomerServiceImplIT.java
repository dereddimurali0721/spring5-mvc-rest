package guru.springfamework.integrationTest;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.CustomerBootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.service.CustomerService;
import guru.springfamework.service.CustomerServiceImpl;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {



    @Autowired
    CustomerRepository customerRepository;

    CustomerService customerService;
    @Before
    public void setup() throws Exception {
        CustomerBootstrap customerBootstrap= new CustomerBootstrap(customerRepository);
        customerBootstrap.run();
        customerService= new CustomerServiceImpl(customerRepository);
    }
    @Test
    public void testpatchcustomer(){
        Long id=getCustomer();
        Customer originalCustomer=customerRepository.getOne(id);
        String originalFirstName=originalCustomer.getFirstname();
        String originalLastName=originalCustomer.getLastname();

        String updatedName="UpdatedName";
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        Assert.assertNotNull(originalCustomer);

        customerService.patchCustomer(id,customerDTO);

        Customer savecCustomer=customerRepository.findById(id).get();
        Assert.assertEquals(savecCustomer.getFirstname(),updatedName);


    }

    public Long getCustomer(){
        return customerRepository.findAll().get(0).getId();
    }
}
