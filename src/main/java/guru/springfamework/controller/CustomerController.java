package guru.springfamework.controller;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Customer-Controller",description = "This is my Controller")
@RestController
@RequestMapping("/api/customers/")
public class CustomerController {

    CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This give list of customer",description = "Test the tag!!")
    public CustomerListDTO customers(){
        return new CustomerListDTO(customerService.getCustomers());
    }

    @GetMapping("{lastname}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomers(@PathVariable String lastname){
        return customerService.findByLastname(lastname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO)
    {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public  CustomerDTO saveCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO)
    {
        return customerService.saveCustomer(id,customerDTO);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO)
    {
        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id,customerDTO),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id)
    {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
