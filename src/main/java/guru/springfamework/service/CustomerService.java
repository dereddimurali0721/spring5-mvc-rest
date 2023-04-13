package guru.springfamework.service;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.domain.Customer;

import java.util.List;

public interface CustomerService {

    public List<CustomerDTO> getCustomers();
    public CustomerDTO findByLastname(String name);

    public CustomerDTO saveCustomer(CustomerDTO customer);

    public CustomerDTO saveCustomer(Long id,CustomerDTO customerDTO);

    public CustomerDTO patchCustomer(Long id,CustomerDTO customerDTO);

    public void deleteCustomer(Long id);
}
