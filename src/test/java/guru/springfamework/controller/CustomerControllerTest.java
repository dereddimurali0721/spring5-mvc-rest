package guru.springfamework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import guru.springfamework.ExceptionHandler.ResourceNotFound;
import guru.springfamework.advice.RestResponseEntityExceptionHandler;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.service.CustomerService;
import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerControllerTest extends TestCase {

    @Mock
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(RestResponseEntityExceptionHandler.class).build();
    }

    @Test
    public void testCustomers() throws Exception {
        CustomerDTO customer= new CustomerDTO();
        customer.setFirstname("D");

        CustomerDTO customer1= new CustomerDTO();
        customer1.setFirstname("M");
        List<CustomerDTO> customerDTOS= Arrays.asList(customer,customer1);
        when(customerService.getCustomers()).thenReturn(customerDTOS);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", IsCollectionWithSize.hasSize(2)))
        ;
    }

    @Test
    public void testGetCustomers() throws Exception{

        CustomerDTO customer= new CustomerDTO();
        customer.setFirstname("D");

        when(customerService.findByLastname(ArgumentMatchers.anyString())).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/D")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", CoreMatchers.equalTo("D")));

    }

    @Test
    public void testSaveCustomer() throws Exception {
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setFirstname("D");
        customerDTO.setLastname("M");

        Customer customer= new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());

        when(customerService.saveCustomer(customerDTO)).thenReturn(customerDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/").contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.jsonConverter(customerDTO))).
                andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname",CoreMatchers.equalTo(customer.getFirstname())));


    }
    @Test
    public void testUpdateSaveCustomer() throws Exception {
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setFirstname("D");
        customerDTO.setLastname("M");
Long value=1L;


        when(customerService.saveCustomer(value,customerDTO)).thenReturn(customerDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                        .content(AbstractRestControllerTest.jsonConverter(customerDTO))).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname",CoreMatchers.equalTo("D")));


    }


    @Test
    public void testpatchCustomer() throws Exception {
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setFirstname("D");
        customerDTO.setLastname("M");
        Long value=1L;


        when(customerService.patchCustomer(value,customerDTO)).thenReturn(customerDTO);
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                        .content(AbstractRestControllerTest.jsonConverter(customerDTO))).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname",CoreMatchers.equalTo("D")));


    }

    @Test
    public void testDeleteCustomer() throws Exception {

        Long value=1L;



        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                        ).
                andExpect(MockMvcResultMatchers.status().isOk());



    }

    @Test
    public void testGetCustomer()throws Exception{

        when(customerService.findByLastname(anyString())).thenThrow(ResourceNotFound.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/000/")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
