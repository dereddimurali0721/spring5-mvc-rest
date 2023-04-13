package guru.springfamework.service;

import guru.springfamework.ExceptionHandler.ResourceNotFound;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.config.CustomerMapper;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    CustomerMapper customerMapper=CustomerMapper.CUSTOMER_MAPPER;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public List<CustomerDTO> getCustomers() {

        return customerRepository.findAll().stream().
                map(customer ->{
                    CustomerDTO customerDTO=customerMapper.convertCustomertoCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/customers/"+customer.getId());return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findByLastname(String name) {
        if(customerRepository.findBylastname(name)==null){
            throw new ResourceNotFound();
        }
        return customerMapper.convertCustomertoCustomerDTO(customerRepository.findBylastname(name));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerdto) {
        return customerMapper.convertCustomertoCustomerDTO(customerRepository.save(customerMapper.convertCustomerDtoToCustomer(customerdto)));
    }

    @Override
    public CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO) {

        Customer customer=customerMapper.convertCustomerDtoToCustomer(customerDTO);
        customer.setId(id);
        Customer customer1=customerRepository.save(customer);
        return customerMapper.convertCustomertoCustomerDTO(customer1);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        Customer customer=customerRepository.findById(id).get();

        if (customerDTO.getFirstname()!=null)
        {
            customer.setFirstname(customerDTO.getFirstname());
        }
        if (customerDTO.getLastname()!=null)
        {
            customer.setLastname(customerDTO.getLastname());
        }

        return CustomerMapper.CUSTOMER_MAPPER.convertCustomertoCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


}
